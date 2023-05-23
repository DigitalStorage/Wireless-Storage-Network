package org.digitalstorage.wsn.forge.common.blocktiles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ExportTile extends NetworkTile {
    public ExportTile(BlockPos pos, BlockState state) {
        super(null, pos, state);
    }
}
