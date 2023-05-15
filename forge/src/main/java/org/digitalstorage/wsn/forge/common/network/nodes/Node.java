package org.digitalstorage.wsn.forge.common.network.nodes;


import net.minecraft.core.GlobalPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import org.digitalstorage.wsn.forge.common.network.INetworkManager;
import org.digitalstorage.wsn.forge.common.network.Network;
import org.digitalstorage.wsn.forge.common.network.NetworkManager;
import org.digitalstorage.wsn.forge.common.network.admin.JoinMessage;

import java.util.UUID;
import java.util.logging.Level;

// Handles Network based things
public interface Node {
    GlobalPos getDimPos();
    int getEnergyUsage();
    UUID getConnectedNetworkUUID();
    UUID getConnectionID();
    UUID getPlayerID();
    Network getConnectedNetwork();
    void connect(Network network, UUID connectionID, UUID playerID);

    default JoinMessage joinNetwork(ServerLevel level, UUID networkID, Player player, String password) {
        INetworkManager manager = NetworkManager.getNetworkManager(level);
        if (manager != null && manager.getNetwork(networkID) != null) {
            return manager.getNetwork(networkID).joinNetwork(this, player, password);
        }
        return null;
    };
}
