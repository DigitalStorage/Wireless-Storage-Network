package org.digitalstorage.wsn.forge.common.blocktiles;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.digitalstorage.wsn.common.network.INetwork;
import org.digitalstorage.wsn.common.network.INetworkManager;
import org.digitalstorage.wsn.common.network.admin.JoinMessage;
import org.digitalstorage.wsn.common.network.nodes.INode;
import org.digitalstorage.wsn.forge.common.network.ForgeNetwork;
import org.digitalstorage.wsn.forge.common.network.ForgeNetworkManager;

import java.util.UUID;

public abstract class NetworkTile extends BlockEntity implements INode {
    private UUID connectionID;
    private ForgeNetwork network;
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
    public <T extends INetwork> T getConnectedNetwork() {
        return (T) network;
    }

    @Override
    public GlobalPos getDimPos() {
        return GlobalPos.of(level.dimension(), getBlockPos());
    }

    @Override
    public <T extends INetwork> void connect(T network, UUID connectionID, UUID playerID) {
        this.network = (ForgeNetwork) network;
        this.networkID = network.getID();
        this.connectionID = connectionID;
        this.playerID = playerID;
    }

    @Override
    public JoinMessage joinNetwork(MinecraftServer server, UUID networkID, Player player, String password) {
        INetworkManager manager = ForgeNetworkManager.getNetworkManager(server);
        if (manager != null && manager.getNetwork(networkID) != null) {
            return manager.getNetwork(networkID).joinNetwork(this, player, password);
        }

        return null;
    }
}
