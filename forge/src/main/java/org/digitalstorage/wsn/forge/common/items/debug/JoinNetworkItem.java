package org.digitalstorage.wsn.forge.common.items.debug;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import org.digitalstorage.wsn.forge.common.blocktiles.NetworkTile;
import org.digitalstorage.wsn.forge.common.network.INetwork;
import org.digitalstorage.wsn.forge.common.network.Network;
import org.digitalstorage.wsn.forge.common.network.NetworkManager;
import org.digitalstorage.wsn.forge.common.network.nodes.Node;

public class JoinNetworkItem extends Item {
    public JoinNetworkItem(Properties arg) {
        super(arg);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getLevel().isClientSide)
            return super.useOn(context);

        if (context.getLevel() instanceof ServerLevel serverLevel && serverLevel.getBlockEntity(context.getClickedPos()) != null && serverLevel.getBlockEntity(context.getClickedPos()) instanceof Node node) {
            INetwork network = NetworkManager.getNetworkManager(serverLevel).getNetwork(CreateNetworkItem.debugNetworkID);
            if (network != null) {
                node.joinNetwork(serverLevel, CreateNetworkItem.debugNetworkID, context.getPlayer(), null);
            }
        }

        return super.useOn(context);
    }
}
