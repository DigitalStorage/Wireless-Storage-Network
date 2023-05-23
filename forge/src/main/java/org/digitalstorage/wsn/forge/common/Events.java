package org.digitalstorage.wsn.forge.common;


import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.digitalstorage.wsn.common.network.INetworkManager;
import org.digitalstorage.wsn.forge.common.network.ForgeNetworkManager;

import static org.digitalstorage.wsn.common.core.CommonConstants.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class Events {
    @SubscribeEvent
    public static void onLoad(AttachCapabilitiesEvent<Level> event) {
        Level level = event.getObject();
        if (level.isClientSide)
            return;
        if (level.dimension() != Level.OVERWORLD)
            return;

        event.addCapability(ForgeNetworkManager.KEY, ForgeNetworkManager.getCapabilityProvider());
    }

    @SubscribeEvent
    public static void onRegisterCaps(RegisterCapabilitiesEvent event) {
        event.register(INetworkManager.class);
    }
}
