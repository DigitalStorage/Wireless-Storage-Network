package org.digitalstorage.wsn.forge.common.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.LazyOptional;
import org.checkerframework.checker.units.qual.C;
import org.digitalstorage.wsn.forge.common.core.Capabilities;
import org.digitalstorage.wsn.forge.common.network.admin.Settings;
import org.digitalstorage.wsn.forge.common.network.nodes.Node;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static org.digitalstorage.wsn.core.CommonConstants.MODID;

public class NetworkManager implements INetworkManager {
    public static class Provider implements ICapabilitySerializable<CompoundTag> {
        public static final ResourceLocation KEY = new ResourceLocation(MODID, "manager");
        public static final Provider INSTANCE = new Provider();

        private final INetworkManager manager = new NetworkManager();
        private final LazyOptional<INetworkManager> managerLazy = LazyOptional.of(() -> manager);

        private Provider() {}

        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
            if (capability == Capabilities.NETWORK_MANAGER)
                return managerLazy.cast();

            return LazyOptional.empty();
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag DATA = new CompoundTag();
            DATA.put("networks", manager.serializeNBT());
            return DATA;
        }

        @Override
        public void deserializeNBT(CompoundTag managerTag) {
            manager.deserializeNBT(managerTag);
        }
    }

    public static INetworkManager getNetworkManager(MinecraftServer server) {
        ServerLevel level = server.getLevel(Level.OVERWORLD);

        if (level.getCapability(Capabilities.NETWORK_MANAGER).isPresent())
            return level.getCapability(Capabilities.NETWORK_MANAGER).resolve().get();

        return null;
    }

    private final HashMap<UUID, INetwork> networks = new HashMap<>();

    @Override
    public INetwork getNetwork(UUID networkID) {
        return networks.containsKey(networkID) ? networks.get(networkID) : null;
    }

    private UUID createNetworkID() {
        UUID ID = UUID.randomUUID();
        int attempts = 0;
        while (networks.containsKey(ID) || attempts > 20) {
            ID = UUID.randomUUID();
            attempts++;
        }

        if (attempts > 20)
            throw new IllegalStateException("Unable to create Network ID, couldnt find an unused UUID to use");

        return ID;
    }

    public void createNetwork(Settings settings) {
        createNetwork(createNetworkID(), settings);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag networksTag = new CompoundTag();
        networks.forEach((id, iNetwork) -> {
            networksTag.put(id.toString(), iNetwork.serializeNBT());
        });
        return networksTag;
    }

    @Override
    public void deserializeNBT(CompoundTag manager) {
        CompoundTag networks = manager.getCompound("networks");
        networks.getAllKeys().forEach(networkString -> {
            UUID id = UUID.fromString(networkString);
            CompoundTag networkTag = networks.getCompound(networkString);

            Network network = new Network(id, networkTag);
            this.networks.put(id, network);
        });
    }

    public void createNetwork(UUID ID, Settings settings) {
        networks.put(ID, new Network(ID, settings));
    }

}
