package org.digitalstorage.wsn.forge.common.network.user;

import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class User {
    private final UUID userID;
    private String username;

    public User(Player player) {
        this.userID = player.getUUID();
        this.username = player.getName().getString();
    }

    public String getUsername() {
        return username;
    }

    public UUID getUserID() {
        return userID;
    }
}
