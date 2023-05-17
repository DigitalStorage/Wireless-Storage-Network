package org.digitalstorage.wsn.forge.common.blocktiles;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.GlobalPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import org.digitalstorage.wsn.forge.common.core.registries.WSNBlockTiles;
import org.digitalstorage.wsn.forge.common.network.Network;
import org.digitalstorage.wsn.forge.common.network.nodes.ControllerNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class ControllerTile extends NetworkTile implements ControllerNode {

    private final IEnergyStorage storage = new EnergyStorage(1000) {
        @Override
        public boolean canExtract() {
            return false;
        }

        protected int extract(int maxExtract, boolean simulate) {
            int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
            if (!simulate)
                energy -= energyExtracted;
            return energyExtracted;
        }
    };

    private final LazyOptional<IEnergyStorage> energyStorageLazy = LazyOptional.of(() -> storage);

    public ControllerTile(BlockPos blockPos, BlockState blockState) {
        super(WSNBlockTiles.CONTROLLER_TILE.get(), blockPos, blockState);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ENERGY)
            return energyStorageLazy.cast();

        return LazyOptional.empty();
    }

    @Override
    public void open(Player player) {

    }
}
