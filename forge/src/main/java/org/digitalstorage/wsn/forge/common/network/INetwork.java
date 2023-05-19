package org.digitalstorage.wsn.forge.common.network;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;
import org.digitalstorage.wsn.forge.common.network.admin.JoinMessage;
import org.digitalstorage.wsn.forge.common.network.nodes.INode;

import java.util.UUID;

public interface INetwork extends INBTSerializable<CompoundTag> {
    UUID getID();
    JoinMessage joinNetwork(INode node, Player player, String password);
    boolean joinNetwork(INode node, UUID playerID);
    void disconnectNode(INode node);
    boolean isPublic();
    void tick();
}
