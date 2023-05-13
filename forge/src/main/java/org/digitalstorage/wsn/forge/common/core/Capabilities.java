package org.digitalstorage.wsn.forge.common.core;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityToken;
import org.digitalstorage.wsn.forge.common.network.INetworkManager;

import static net.minecraftforge.common.capabilities.CapabilityManager.get;

public class Capabilities {
    public static final Capability<INetworkManager> NETWORK_MANAGER = get(new CapabilityToken<>(){});
}
