package org.digitalstorage.wsn.forge.common.core.registries;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.digitalstorage.wsn.forge.common.blocks.ControllerBlock;
import org.digitalstorage.wsn.forge.common.blocks.DriveBayBlock;
import org.digitalstorage.wsn.forge.common.blocks.TerminalBlock;

import java.util.function.Supplier;

import static org.digitalstorage.wsn.core.CommonConstants.MODID;

public class WSNBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    private static final Supplier<BlockBehaviour.Properties> PROPERTIES = () -> BlockBehaviour.Properties.of(Material.STONE);


    public static final RegistryObject<Block> BLOCK_EXAMPLE = BLOCKS.register("example",
            () -> new Block(PROPERTIES.get())
    );

    public static final RegistryObject<ControllerBlock> CONTROLLER_BLOCK = BLOCKS.register("controller",
            () -> new ControllerBlock(PROPERTIES.get())
    );

    public static final RegistryObject<DriveBayBlock> DRIVE_BAY_BLOCK = BLOCKS.register("drive",
            () -> new DriveBayBlock(PROPERTIES.get())
    );

    public static final RegistryObject<TerminalBlock> TERMINAL_BLOCK = BLOCKS.register("terminal",
            () -> new TerminalBlock(PROPERTIES.get())
    );

    public static void init(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
