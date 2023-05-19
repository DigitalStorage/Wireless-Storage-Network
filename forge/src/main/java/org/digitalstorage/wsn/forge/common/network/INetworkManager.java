package org.digitalstorage.wsn.forge.common.network;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.event.TickEvent;
import org.digitalstorage.wsn.forge.common.network.admin.Settings;
import java.util.UUID;

@AutoRegisterCapability
public interface INetworkManager extends INBTSerializable<CompoundTag> {
    INetwork getNetwork(UUID networkID);
    void createNetwork(UUID ID, Settings settings);
    void createNetwork(Settings settings);
    void registerTicker();
    void unregisterTicker();
    void tick(TickEvent.LevelTickEvent event);
}
