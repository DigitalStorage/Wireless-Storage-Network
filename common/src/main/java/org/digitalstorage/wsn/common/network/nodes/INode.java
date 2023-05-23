package org.digitalstorage.wsn.common.network.nodes;


import net.minecraft.core.GlobalPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import org.digitalstorage.wsn.common.network.INetwork;
import org.digitalstorage.wsn.common.network.admin.JoinMessage;

import java.util.UUID;

// Handles ForgeNetwork based things
public interface INode {
    GlobalPos getDimPos();
    default int getEnergyUsage() {
            return 0;
    }
    UUID getConnectedNetworkUUID();
    UUID getConnectionID();
    UUID getPlayerID();
    <T extends INetwork> T getConnectedNetwork();
    <T extends INetwork> void connect(T network, UUID connectionID, UUID playerID);
    JoinMessage joinNetwork(MinecraftServer server, UUID networkID, Player player, String password);
}
