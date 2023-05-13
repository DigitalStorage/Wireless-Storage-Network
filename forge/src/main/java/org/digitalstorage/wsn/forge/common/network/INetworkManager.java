package org.digitalstorage.wsn.forge.common.network;

import org.digitalstorage.wsn.forge.common.network.nodes.Node;

import java.util.UUID;

public interface INetworkManager {
    boolean ping(UUID network, Node node);
}
