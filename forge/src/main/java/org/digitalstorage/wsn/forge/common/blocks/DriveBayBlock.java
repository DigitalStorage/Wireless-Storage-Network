package org.digitalstorage.wsn.forge.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.digitalstorage.wsn.forge.common.blocktiles.DriveBayTile;
import org.jetbrains.annotations.Nullable;

public class DriveBayBlock extends NetworkBlock {
    public DriveBayBlock(Properties arg) {
        super(arg);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new DriveBayTile(blockPos, blockState);
    }
}
