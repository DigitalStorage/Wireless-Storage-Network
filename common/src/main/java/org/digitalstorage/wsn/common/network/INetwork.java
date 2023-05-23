package org.digitalstorage.wsn.common.network;

import net.minecraft.world.entity.player.Player;
import org.digitalstorage.wsn.common.network.admin.JoinMessage;
import org.digitalstorage.wsn.common.network.nodes.INode;

import java.util.UUID;
import java.util.stream.Stream;

public interface INetwork {
    UUID getID();
    JoinMessage joinNetwork(INode node, Player player, String password);
    boolean joinNetwork(INode node, UUID playerID);
    void disconnectNode(INode node);
    boolean isPublic();
    void tick();
    Stream<INode> getNodes();
    INetworkStorage getStorageContents();
}
