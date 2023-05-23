package org.digitalstorage.wsn.forge;

import net.minecraftforge.eventbus.api.IEventBus;
import org.digitalstorage.wsn.WirelessStorageNetwork;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.digitalstorage.wsn.forge.common.core.registries.WSNBlockTiles;
import org.digitalstorage.wsn.forge.common.core.registries.WSNBlocks;
import org.digitalstorage.wsn.forge.common.core.registries.WSNItems;

import static org.digitalstorage.wsn.common.core.CommonConstants.MODID;

@Mod(MODID)
public class WirelessStorageNetworkForge {

    public WirelessStorageNetworkForge() {
        WirelessStorageNetwork.init();

        IEventBus BUS = FMLJavaModLoadingContext.get().getModEventBus();
        WSNBlocks.init(BUS);
        WSNBlockTiles.init(BUS);
        WSNItems.init(BUS);
    }

}