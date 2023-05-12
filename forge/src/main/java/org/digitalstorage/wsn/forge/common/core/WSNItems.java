package org.digitalstorage.wsn.forge.common.core;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

import static org.digitalstorage.wsn.core.CommonConstants.MODID;

public class WSNItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);


    public static void init(Consumer<DeferredRegister> registerConsumer) {
        registerConsumer.accept(ITEMS);
    }
}
