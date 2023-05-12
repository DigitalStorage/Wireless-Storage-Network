package org.digitalstorage.wsn.forge.common.items;

import net.minecraft.world.item.Item;

public class ItemDrive extends Drive<Item> {
    private final Integer DEFAULT_SIZE;

    public ItemDrive(Properties arg, Integer size) {
        super(arg);
        this.DEFAULT_SIZE = size;
    }
}
