package org.digitalstorage.wsn.forge.common.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import org.digitalstorage.wsn.forge.common.network.nodes.Node;

public abstract class NetworkBlock extends Block {
    public NetworkBlock(Properties properties) {
        super(properties);
    }

}
