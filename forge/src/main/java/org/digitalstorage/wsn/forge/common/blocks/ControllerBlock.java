package org.digitalstorage.wsn.forge.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.digitalstorage.wsn.forge.common.blocktiles.ControllerTile;
import org.jetbrains.annotations.Nullable;

public class ControllerBlock extends NetworkBlock {
    public ControllerBlock(Properties arg) {
        super(arg);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ControllerTile(pos, state);
    }
}
