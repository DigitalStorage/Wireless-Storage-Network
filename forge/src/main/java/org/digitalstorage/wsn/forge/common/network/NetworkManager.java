package org.digitalstorage.wsn.forge.common.network;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.Bindings;
import org.digitalstorage.wsn.forge.common.core.Capabilities;
import org.digitalstorage.wsn.forge.common.network.admin.Settings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.digitalstorage.wsn.core.CommonConstants.MODID;

public class NetworkManager implements INetworkManager, ICapabilitySerializable<CompoundTag> {
    private static Optional<INetworkManager> currentManager = Optional.empty();
    public static final ResourceLocation KEY = new ResourceLocation(MODID, "manager");

    public static INetworkManager getNetworkManager(MinecraftServer server) {
        ServerLevel level = server.getLevel(Level.OVERWORLD);

        if (level.getCapability(Capabilities.NETWORK_MANAGER).isPresent())
            return level.getCapability(Capabilities.NETWORK_MANAGER).resolve().get();

        return null;
    }

    public static ICapabilityProvider getCapabilityProvider() {
        currentManager.ifPresent(iNetworkManager -> iNetworkManager.unregisterTicker());
        currentManager = null;
        NetworkManager manager = new NetworkManager();
        currentManager = Optional.of(manager);
        currentManager.orElseThrow().registerTicker();
        return manager;
    }

    private final HashMap<UUID, INetwork> networks = new HashMap<>();
    private final LazyOptional<INetworkManager> manager = LazyOptional.of(() -> this);
    private AtomicBoolean registeredTicker = new AtomicBoolean(false);

    private NetworkManager() {
        if (currentManager != null)
            throw new IllegalStateException("Cannot have more then one NetworkManager loaded at any point!");
        NetworkManager.currentManager = Optional.of(this);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
        if (capability == Capabilities.NETWORK_MANAGER)
            return manager.cast();

        return LazyOptional.empty();
    }

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
    public void registerTicker() {
        if (registeredTicker.get()) return;

        Bindings.getForgeBus().get().addListener(this::tick);
        registeredTicker.set(true);
    }

    @Override
    public void unregisterTicker() {
        if (!registeredTicker.get()) return;

        Bindings.getForgeBus().get().unregister(this);
        registeredTicker.set(false);
    }

    @Override
    public void tick(TickEvent.LevelTickEvent event) {
        if (event.level.dimension() == Level.OVERWORLD && !event.level.isClientSide)  {
            networks.forEach((id, network) -> network.tick());
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag managerTag = new CompoundTag();
        CompoundTag networksTag = new CompoundTag();
        networks.forEach((id, iNetwork) -> {
            networksTag.put(id.toString(), iNetwork.serializeNBT());
        });
        managerTag.put("networks", networksTag);

        return managerTag;
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
