package org.digitalstorage.wsn.forge.common.blocktiles;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.digitalstorage.wsn.forge.common.network.Network;
import org.digitalstorage.wsn.forge.common.network.nodes.Node;

import java.util.UUID;

public abstract class NetworkTile extends BlockEntity implements Node {

    private UUID connectionID;
    private Network network;
    private UUID networkID;
    private UUID playerID;

    public NetworkTile(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
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
    public GlobalPos getDimPos() {
        return GlobalPos.of(level.dimension(), getBlockPos());
    }

    @Override
    public void connect(Network network, UUID connectionID, UUID playerID) {
        this.network = network;
        this.networkID = network.getID();
        this.connectionID = connectionID;
        this.playerID = playerID;
    }
}
