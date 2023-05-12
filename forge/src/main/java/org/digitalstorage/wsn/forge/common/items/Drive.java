package org.digitalstorage.wsn.forge.common.items;


import net.minecraft.world.item.Item;

// TYPE being Item/Fluid/Gas
public abstract class Drive<TYPE> extends Item {
    public Drive(Properties arg) {
        super(arg);
    }
}
