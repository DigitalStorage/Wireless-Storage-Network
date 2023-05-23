package org.digitalstorage.wsn.forge.common.blocktiles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.digitalstorage.wsn.common.network.nodes.TerminalNode;
import org.digitalstorage.wsn.forge.common.core.registries.WSNBlockTiles;

public class TerminalTile extends NetworkTile implements TerminalNode {

    public TerminalTile(BlockPos blockPos, BlockState blockState) {
        super(WSNBlockTiles.TERMINAL_TILE.get(), blockPos, blockState);
    }
}
