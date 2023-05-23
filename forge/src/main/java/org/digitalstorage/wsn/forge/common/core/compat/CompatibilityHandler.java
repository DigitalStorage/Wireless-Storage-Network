package org.digitalstorage.wsn.forge.common.core.compat;

import net.minecraftforge.fml.ModList;

public class CompatibilityHandler {
    public static boolean isMekanismLoaded() {
        return ModList.get().isLoaded("mekanism");
    }
}
