package org.digitalstorage.wsn.forge.common.network;

import org.digitalstorage.wsn.forge.common.network.admin.Settings;
import org.digitalstorage.wsn.forge.common.network.nodes.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Network implements INetwork {
    private final List<Node> nodes = new ArrayList<>();
    private final UUID ID;
    private final Settings settings;


    public Network(UUID ID, Settings settings) {
        this.ID = ID;
        this.settings = settings;
    }

    @Override
    public void ping(Node node) {
        if (!nodes.contains(node))
            nodes.add(node);
    }

    @Override
    public void disconnectNode(Node node) {
        if (nodes.contains(node))
            nodes.remove(node);
    }


    @Override
    public void tick() {

    }
}
