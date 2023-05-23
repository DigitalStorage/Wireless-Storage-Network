package org.digitalstorage.wsn.forge.common.items.drives;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemDrive extends AbstractDriveItem {
    private final Integer DEFAULT_SIZE;

    public ItemDrive(Properties arg, Integer size) {
        super(arg);
        this.DEFAULT_SIZE = size;
    }

}
