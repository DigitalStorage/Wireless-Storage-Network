package org.digitalstorage.wsn.forge.common.blocktiles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.digitalstorage.wsn.forge.common.network.nodes.ControllerNode;

public class DriveBayTile extends NetworkTile implements ControllerNode {
    public DriveBayTile(BlockPos blockPos, BlockState blockState) {
        super(null, blockPos, blockState);
    }

    @Override
    public void open(Player player) {

    }
}
