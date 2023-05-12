package org.digitalstorage.wsn.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import org.digitalstorage.wsn.WirelessStorageNetwork;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.digitalstorage.wsn.forge.core.WSNBlockTiles;
import org.digitalstorage.wsn.forge.core.WSNBlocks;
import org.digitalstorage.wsn.forge.core.WSNItems;

import java.util.function.Consumer;

import static org.digitalstorage.wsn.core.CommonConstants.MODID;

@Mod(MODID)
public class WirelessStorageNetworkForge {
    public WirelessStorageNetworkForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(MODID, FMLJavaModLoadingContext.get().getModEventBus());
        WirelessStorageNetwork.init();

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        Consumer<DeferredRegister> REGISTER_CONSUMER = (register -> register.register(bus));

        WSNBlocks.init(REGISTER_CONSUMER);
        WSNBlockTiles.init(REGISTER_CONSUMER);
        WSNItems.init(REGISTER_CONSUMER);
    }
}