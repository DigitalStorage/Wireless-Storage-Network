package org.digitalstorage.wsn.forge.common.core;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

import static org.digitalstorage.wsn.core.CommonConstants.MODID;

public class WSNBlockTiles {
    private static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

    public static void init(IEventBus bus) {
        TILES.register(bus);
    }
}
