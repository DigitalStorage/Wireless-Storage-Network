package org.digitalstorage.wsn.forge.common.network;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.digitalstorage.wsn.common.network.INetwork;
import org.digitalstorage.wsn.common.network.INetworkStorage;

import java.util.ArrayList;
import java.util.List;

public class NetworkStorage implements INetworkStorage {
    private final List<?> handlers = new ArrayList<>();

    public NetworkStorage(INetwork network) {
        sync(network);
    }

    public void sync(INetwork network) {
        handlers.clear();
    }

    /**
     *
     * @param resource
     * @return
     * @param <R> Resource -> ItemStack/FluidStack
     */
    public <R> R insertResource(R resource) {
        if (resource instanceof ItemStack stack)
            return (R) InsertItemStack(stack);
        return null;
    }

    /**
     *
     * @return
     * @param <R> Resource -> ItemStack/FluidStack
     * @param <T> Type (Item, Fluid, etc) Can also be ItemStack/FluidStack/Whatever
     */
    public <R, T> R extractResource(T type, int amount) {
        if (type instanceof Item item)
            return (R) extractItem(item, amount);
        return null;
    }

    private ItemStack InsertItemStack(ItemStack stack) { // Shove into Storage
        return null;
    }

    private ItemStack extractItem(Item item, int amount) { // Take from Storage
        return null;
    }

    public List<ItemStack> getItems() {
        return List.of();
    }

}
