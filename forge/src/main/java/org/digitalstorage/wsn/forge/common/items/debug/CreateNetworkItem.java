package org.digitalstorage.wsn.forge.common.items.debug;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.digitalstorage.wsn.forge.common.network.NetworkManager;
import org.digitalstorage.wsn.forge.common.network.admin.SecurityLevel;
import org.digitalstorage.wsn.forge.common.network.admin.Settings;

import java.util.UUID;

public class CreateNetworkItem extends Item {
    public static final UUID debugNetworkID = UUID.randomUUID();

    public CreateNetworkItem(Properties arg) {
        super(arg);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand arg3) {
        if (level.isClientSide)
            return super.use(level, player, arg3);
        NetworkManager.getNetworkManager(level.getServer()).createNetwork(debugNetworkID, new Settings(player, null, SecurityLevel.PUBLIC));
        return super.use(level, player, arg3);
    }
}
