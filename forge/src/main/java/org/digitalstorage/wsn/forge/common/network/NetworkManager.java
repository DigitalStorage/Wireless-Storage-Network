package org.digitalstorage.wsn.forge.common.network;

import net.minecraft.server.level.ServerLevel;
import org.digitalstorage.wsn.forge.common.core.Capabilities;
import org.digitalstorage.wsn.forge.common.network.admin.Settings;
import org.digitalstorage.wsn.forge.common.network.nodes.Node;

import java.util.*;

public class NetworkManager implements INetworkManager {
    public static Optional<INetworkManager> getNetworkManager(ServerLevel level) {
        if (level.getCapability(Capabilities.NETWORK_MANAGER).isPresent())
            return level.getCapability(Capabilities.NETWORK_MANAGER).resolve();

        return Optional.empty();
    }

    private final HashMap<UUID, INetwork> networks = new HashMap<>();


    public Optional<INetwork> getNetwork(UUID networkID) {
        return networks.containsKey(networkID) ? Optional.of(networks.get(networkID)) : Optional.empty();
    }

    @Override
    public boolean ping(UUID networkID, Node node) {
        Optional<INetwork> network = getNetwork(networkID);
        network.ifPresent(net -> net.ping(node));
        return network.isPresent();
    }


    public UUID createNetworkID() {
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
