package org.digitalstorage.wsn.forge.common.network;

import net.minecraft.world.entity.player.Player;
import org.digitalstorage.wsn.forge.common.network.admin.JoinMessage;
import org.digitalstorage.wsn.forge.common.network.nodes.Node;

import java.util.UUID;

public interface INetwork {

    UUID getID();
    JoinMessage joinNetwork(Node node, Player player, String password);
    boolean joinNetwork(Node node, UUID playerID);
    void disconnectNode(Node node);
    boolean isPublic();
    void tick();
}
