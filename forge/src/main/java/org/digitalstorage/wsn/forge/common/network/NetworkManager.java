package org.digitalstorage.wsn.forge.common.network;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.LazyOptional;
import org.digitalstorage.wsn.forge.common.core.Capabilities;
import org.digitalstorage.wsn.forge.common.network.admin.Settings;
import org.digitalstorage.wsn.forge.common.network.nodes.Node;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static org.digitalstorage.wsn.core.CommonConstants.MODID;

public class NetworkManager implements INetworkManager {

    public static class Provider implements ICapabilityProvider {
        public static final ResourceLocation KEY = new ResourceLocation(MODID, "manager");
        public static final Provider INSTANCE = new Provider();
        private final LazyOptional manager = LazyOptional.of(() -> new NetworkManager());
        private Provider() {}

        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
            if (capability == Capabilities.NETWORK_MANAGER)
                return manager.cast();

            return LazyOptional.empty();
        }
    }
    public static INetworkManager getNetworkManager(ServerLevel level) {
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

    public void createNetwork(UUID ID, Settings settings) {
        networks.put(ID, new Network(ID, settings));
    }

}
