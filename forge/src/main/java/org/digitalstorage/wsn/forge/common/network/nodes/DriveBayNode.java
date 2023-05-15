package org.digitalstorage.wsn.forge.common.network.nodes;

import net.minecraft.core.GlobalPos;
import net.minecraft.world.entity.player.Player;

public interface DriveBayNode extends Node {
    void open(Player player);
}
