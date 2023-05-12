package org.digitalstorage.wsn.forge.common.core;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

import static org.digitalstorage.wsn.core.CommonConstants.MODID;

public class WSNBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static final RegistryObject<Block> BLOCK_EXAMPLE = BLOCKS.register("example",
            () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST))
    );



    public static void init(Consumer<DeferredRegister> registerConsumer) {
        registerConsumer.accept(BLOCKS);
    }
}
