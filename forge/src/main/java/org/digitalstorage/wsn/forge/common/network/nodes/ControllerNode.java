package org.digitalstorage.wsn.forge.common.network.nodes;


import net.minecraft.world.entity.player.Player;

public interface ControllerNode extends Node {
    void open(Player player);
}
