package org.digitalstorage.wsn.forge.common.network.nodes;


import net.minecraft.core.GlobalPos;
import org.digitalstorage.wsn.forge.common.network.Network;

import java.util.UUID;

// Handles Network based things
public interface Node {
    GlobalPos getDimPos();

   int getEnergyUsage();

    UUID getConnectedNetworkUUID();

   Network getConnectedNetwork();

   void joinNetwork(Network network);

}
