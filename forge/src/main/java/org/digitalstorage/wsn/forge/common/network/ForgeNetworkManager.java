package org.digitalstorage.wsn.forge.common.network;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.Bindings;
import net.minecraftforge.fml.ModList;
import org.digitalstorage.wsn.common.network.INetwork;
import org.digitalstorage.wsn.common.network.INetworkManager;
import org.digitalstorage.wsn.common.network.admin.ISettings;
import org.digitalstorage.wsn.forge.common.core.Capabilities;
import org.digitalstorage.wsn.forge.common.core.compat.Mekanism;
import org.digitalstorage.wsn.forge.common.network.admin.Settings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.digitalstorage.wsn.common.core.CommonConstants.MODID;

public class ForgeNetworkManager implements INetworkManager, ICapabilitySerializable<CompoundTag> {
    private static Optional<INetworkManager> currentManager = Optional.empty();
    public static final ResourceLocation KEY = new ResourceLocation(MODID, "manager");
    public static final ArrayList<Capability<?>> VALID_CAPABILITIES = new ArrayList<>();

    static {
        registerCapability(ForgeCapabilities.FLUID_HANDLER_ITEM);
        registerCapability(ForgeCapabilities.ITEM_HANDLER);
        if (ModList.get().isLoaded("mekanism")) {
            registerCapability(Mekanism.GAS);
        }
    }

    private static void registerCapability(Capability<?> capability) {
        if (VALID_CAPABILITIES.contains(capability)) return;
        VALID_CAPABILITIES.add(capability);
    }

    public static INetworkManager getNetworkManager(MinecraftServer server) {
        ServerLevel level = server.getLevel(Level.OVERWORLD);

        if (level.getCapability(Capabilities.NETWORK_MANAGER).isPresent())
            return level.getCapability(Capabilities.NETWORK_MANAGER).resolve().get();

        return null;
    }

    public static ICapabilityProvider getCapabilityProvider() {
        currentManager.ifPresent(iNetworkManager -> iNetworkManager.unregisterTicker());
        currentManager = null;
        ForgeNetworkManager manager = new ForgeNetworkManager();
        currentManager = Optional.of(manager);
        currentManager.orElseThrow().registerTicker();
        return manager;
    }

    private final HashMap<UUID, ForgeNetwork> networks = new HashMap<>();
    private final LazyOptional<INetworkManager> manager = LazyOptional.of(() -> this);
    private AtomicBoolean registeredTicker = new AtomicBoolean(false);

    private ForgeNetworkManager() {
        if (currentManager != null)
            throw new IllegalStateException("Cannot have more then one ForgeNetworkManager loaded at any point!");
        ForgeNetworkManager.currentManager = Optional.of(this);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
        if (capability == Capabilities.NETWORK_MANAGER)
            return manager.cast();

        return LazyOptional.empty();
    }

    @Override
    public INetwork getNetwork(UUID networkID) {
        return networks.containsKey(networkID) ? (INetwork) networks.get(networkID) : null;
    }

    @Override
    public <T extends ISettings> void createNetwork(UUID ID, T settings) {
        networks.put(ID, new ForgeNetwork(ID, (Settings) settings));
    }

    @Override
    public <T extends ISettings> void createNetwork(T settings) {
        createNetwork(createNetworkID(), settings);
    }

    private UUID createNetworkID() {
        UUID ID = UUID.randomUUID();
        int attempts = 0;
        while (networks.containsKey(ID) || attempts > 20) {
            ID = UUID.randomUUID();
            attempts++;
        }

        if (attempts > 20)
            throw new IllegalStateException("Unable to create ForgeNetwork ID, couldnt find an unused UUID to use");

        return ID;
    }

    @Override
    public void registerTicker() {
        if (registeredTicker.get()) return;

        registeredTicker.set(true);
    }

    @Override
    public void unregisterTicker() {
        if (!registeredTicker.get()) return;

        Bindings.getForgeBus().get().unregister(this);
        registeredTicker.set(false);
    }

    @Override
    public void tick() {

    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag managerTag = new CompoundTag();
        CompoundTag networksTag = new CompoundTag();
        networks.forEach((id, iNetwork) -> {
            networksTag.put(id.toString(), iNetwork.serializeNBT());
        });
        managerTag.put("networks", networksTag);

        return managerTag;
    }

    @Override
    public void deserializeNBT(CompoundTag manager) {
        CompoundTag networks = manager.getCompound("networks");
        networks.getAllKeys().forEach(networkString -> {
            UUID id = UUID.fromString(networkString);
            CompoundTag networkTag = networks.getCompound(networkString);

            ForgeNetwork network = new ForgeNetwork(id, networkTag);
            this.networks.put(id, network);
        });
    }

}
