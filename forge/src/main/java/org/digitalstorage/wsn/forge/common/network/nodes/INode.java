package org.digitalstorage.wsn.forge.common.network.nodes;


import net.minecraft.core.GlobalPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import org.digitalstorage.wsn.forge.common.network.INetworkManager;
import org.digitalstorage.wsn.forge.common.network.Network;
import org.digitalstorage.wsn.forge.common.network.NetworkManager;
import org.digitalstorage.wsn.forge.common.network.admin.JoinMessage;

import java.util.UUID;

// Handles Network based things
public interface INode {
    GlobalPos getDimPos();
    default int getEnergyUsage() {
            return 0;
    }
    UUID getConnectedNetworkUUID();
    UUID getConnectionID();
    UUID getPlayerID();
    Network getConnectedNetwork();
    void connect(Network network, UUID connectionID, UUID playerID);

    default JoinMessage joinNetwork(MinecraftServer server, UUID networkID, Player player, String password) {
        INetworkManager manager = NetworkManager.getNetworkManager(server);
        if (manager != null && manager.getNetwork(networkID) != null) {
            return manager.getNetwork(networkID).joinNetwork(this, player, password);
        }
        return null;
    }
}
