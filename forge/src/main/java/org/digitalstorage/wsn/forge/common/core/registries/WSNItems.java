package org.digitalstorage.wsn.forge.common.core.registries;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.digitalstorage.wsn.forge.common.items.TestItem;
import org.digitalstorage.wsn.forge.common.items.debug.CreateNetworkItem;
import org.digitalstorage.wsn.forge.common.items.debug.JoinNetworkItem;

import java.util.function.Supplier;

import static org.digitalstorage.wsn.common.core.CommonConstants.MODID;
import static org.digitalstorage.wsn.forge.common.core.Constants.TAB;

public class WSNItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final Supplier<Item.Properties> PROPERTIES = () -> new Item.Properties().tab(TAB).stacksTo(1);

    // Items
    public static final RegistryObject<TestItem> TEST_ITEM = ITEMS.register("test", () -> new TestItem(PROPERTIES.get()));


    // Block Items
    public static final RegistryObject<BlockItem> CONTROLLER_ITEM = registerBlockItem(WSNBlocks.CONTROLLER_BLOCK);

    public static final RegistryObject<BlockItem> DRIVE_BAY_ITEM = registerBlockItem(WSNBlocks.DRIVE_BAY_BLOCK);

    public static final RegistryObject<BlockItem> TERMINAL_ITEM = registerBlockItem(WSNBlocks.TERMINAL_BLOCK);


    // DEBUG
    public static final RegistryObject<CreateNetworkItem> DEBUG_A = ITEMS.register("create", () -> new CreateNetworkItem(PROPERTIES.get()));
    public static final RegistryObject<JoinNetworkItem> DEBUG_B = ITEMS.register("join", () -> new JoinNetworkItem(PROPERTIES.get()));



    private static RegistryObject<BlockItem> registerBlockItem(RegistryObject<? extends Block> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), PROPERTIES.get()));
    }

    public static void init(IEventBus bus) {
        ITEMS.register(bus);
    }
}
