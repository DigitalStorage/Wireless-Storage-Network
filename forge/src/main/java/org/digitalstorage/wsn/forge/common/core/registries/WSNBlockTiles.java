package org.digitalstorage.wsn.forge.common.core.registries;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.digitalstorage.wsn.forge.common.blocktiles.ControllerTile;
import static org.digitalstorage.wsn.core.CommonConstants.MODID;

public class WSNBlockTiles {
    private static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

    public static final RegistryObject<BlockEntityType<ControllerTile>> CONTROLLER_TILE =
            TILES.register("controller", () -> BlockEntityType.Builder.of(
                            ControllerTile::new,
                            WSNBlocks.CONTROLLER_BLOCK.get()
                    ).build(null)
            );


    public static void init(IEventBus bus) {
        TILES.register(bus);
    }
}
