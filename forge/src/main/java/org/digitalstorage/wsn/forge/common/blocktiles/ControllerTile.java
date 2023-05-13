package org.digitalstorage.wsn.forge.common.blocktiles;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.digitalstorage.wsn.forge.common.blocks.ControllerBlock;
import org.digitalstorage.wsn.forge.common.network.Network;
import org.digitalstorage.wsn.forge.common.network.nodes.ControllerNode;

import java.util.UUID;

public class ControllerTile extends NetworkTile implements ControllerNode {


    public ControllerTile(BlockPos blockPos, BlockState blockState) {
        super(null, blockPos, blockState);
    }


    @Override
    public GlobalPos getDimPos() {
        return null;
    }

    @Override
    public int getEnergyUsage() {
        return 0;
    }

    @Override
    public UUID getConnectedNetworkUUID() {
        return null;
    }

    @Override
    public Network getConnectedNetwork() {
        return null;
    }

    @Override
    public void joinNetwork(Network network) {

    }
}
