package org.digitalstorage.wsn.forge.common.network;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import org.digitalstorage.wsn.forge.common.network.admin.Settings;
import java.util.UUID;

@AutoRegisterCapability
public interface INetworkManager {
    INetwork getNetwork(UUID networkID);
    void createNetwork(UUID ID, Settings settings);
    void createNetwork(Settings settings);
}
