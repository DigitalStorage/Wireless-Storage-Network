package org.digitalstorage.wsn.forge.common.core;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class Constants {

    public static final CreativeModeTab TAB = new CreativeModeTab("wsn") {
        @Override
        public ItemStack makeIcon() {
            return WSNItems.CONTROLLER_ITEM.get().getDefaultInstance();
        }
    };
}
