package org.digitalstorage.wsn.fabric;

import org.digitalstorage.wsn.WirelessStorageNetwork;
import net.fabricmc.api.ModInitializer;

public class WirelessStorageNetworkFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        WirelessStorageNetwork.init();
    }
}