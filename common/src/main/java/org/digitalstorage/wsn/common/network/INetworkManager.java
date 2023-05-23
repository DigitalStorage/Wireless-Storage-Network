package org.digitalstorage.wsn.common.network;

import org.digitalstorage.wsn.common.network.admin.ISettings;

import java.util.UUID;

public interface INetworkManager {
    INetwork getNetwork(UUID networkID);
    <T extends ISettings> void createNetwork(UUID ID, T settings);
    <T extends ISettings> void createNetwork(T settings);
    void registerTicker();
    void unregisterTicker();
    void tick();
}
