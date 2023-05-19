package org.digitalstorage.wsn.forge.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.digitalstorage.wsn.forge.common.blocktiles.TerminalTile;
import org.jetbrains.annotations.Nullable;

public class TerminalBlock extends NetworkBlock {
    public TerminalBlock(Properties arg) {
        super(arg);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TerminalTile(blockPos, blockState);
    }
}
