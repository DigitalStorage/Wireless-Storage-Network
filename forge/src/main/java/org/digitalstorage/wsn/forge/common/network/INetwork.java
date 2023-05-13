package org.digitalstorage.wsn.forge.common.network;

import org.digitalstorage.wsn.forge.common.network.nodes.Node;

public interface INetwork {
    void ping(Node node);

    void disconnectNode(Node node);

    void tick();
}
