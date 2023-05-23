package org.digitalstorage.wsn.forge.common.core.compat;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityToken;

import static net.minecraftforge.common.capabilities.CapabilityManager.get;

public class Mekanism {
    public static final Capability<Object> GAS = get(new CapabilityToken<>(){});
    public static <T> boolean isGas(T object) {
        //if (object instanceof Gas)
            //return true;
        return false;
    }
}
