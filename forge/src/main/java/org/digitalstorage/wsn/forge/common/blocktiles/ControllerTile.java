package org.digitalstorage.wsn.forge.common.blocktiles;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import org.digitalstorage.wsn.forge.common.core.registries.WSNBlockTiles;
import org.digitalstorage.wsn.forge.common.network.Network;
import org.digitalstorage.wsn.forge.common.network.nodes.ControllerNode;

import java.util.UUID;

public class ControllerTile extends NetworkTile implements ControllerNode {

    private UUID connectionID;
    private Network network;
    private UUID networkID;
    private UUID playerID;


    public ControllerTile(BlockPos blockPos, BlockState blockState) {
        super(WSNBlockTiles.CONTROLLER_TILE.get(), blockPos, blockState);
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
        return networkID;
    }

    @Override
    public UUID getConnectionID() {
        return connectionID;
    }

    @Override
    public UUID getPlayerID() {
        return playerID;
    }

    @Override
    public Network getConnectedNetwork() {
        return network;
    }

    @Override
    public void connect(Network network, UUID connectionID, UUID playerID) {
        this.network = network;
        this.networkID = network.getID();
        this.connectionID = connectionID;
        this.playerID = playerID;
    }


    @Override
    public void open(Player player) {

    }
}
