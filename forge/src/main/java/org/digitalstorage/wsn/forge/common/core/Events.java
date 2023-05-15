package org.digitalstorage.wsn.forge.common.core;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.digitalstorage.wsn.forge.common.network.NetworkManager;

import static org.digitalstorage.wsn.core.CommonConstants.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class Events {
    @SubscribeEvent
    public static void onLoad(AttachCapabilitiesEvent<Level> event) {
        event.addCapability(NetworkManager.Provider.KEY, NetworkManager.Provider.INSTANCE);
    }
}
