package org.digitalstorage.wsn.forge.common.blocktiles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ControllerTile extends BlockEntity {
    public ControllerTile(BlockPos blockPos, BlockState blockState) {
        super(null, blockPos, blockState);
    }
}
