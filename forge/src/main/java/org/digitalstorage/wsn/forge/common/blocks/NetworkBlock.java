package org.digitalstorage.wsn.forge.common.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;

public abstract class NetworkBlock extends Block implements EntityBlock {
    public NetworkBlock(Properties properties) {
        super(properties);
    }

}
