package org.digitalstorage.wsn.forge;

import dev.architectury.platform.forge.EventBuses;
import org.digitalstorage.wsn.WirelessStorageNetwork;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(WirelessStorageNetwork.MOD_ID)
public class WirelessStorageNetworkForge {
    public WirelessStorageNetworkForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(WirelessStorageNetwork.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        WirelessStorageNetwork.init();
    }
}