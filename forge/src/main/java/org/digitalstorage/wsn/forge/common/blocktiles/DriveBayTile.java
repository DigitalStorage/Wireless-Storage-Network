package org.digitalstorage.wsn.forge.common.blocktiles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import org.digitalstorage.wsn.forge.common.core.registries.WSNBlockTiles;
import org.digitalstorage.wsn.forge.common.network.nodes.DriveBayNode;

import java.util.List;

public class DriveBayTile extends NetworkTile implements DriveBayNode {
    public DriveBayTile(BlockPos blockPos, BlockState blockState) {
        super(WSNBlockTiles.DRIVE_TILE.get(), blockPos, blockState);
    }

    @Override
    public <T> List<T> getContents() {
        return (List<T>) List.of(new ItemStack(Items.STONE, 64));
    }
}
