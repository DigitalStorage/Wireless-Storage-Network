package org.digitalstorage.wsn.forge.common.network.admin;

import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class User {
    private final UUID userID;
    private String username;
    private final List<UserPermissions> permissions = new ArrayList<>();


    public User(Player player, UserPermissions... perms) {
        this.userID = player.getUUID();
        this.username = player.getName().getString();
        this.permissions.addAll(Arrays.stream(perms).toList());
    }

    public User(UUID playerID, String username, List<UserPermissions> permissions) {
        this.userID = playerID;
        this.username = username;
        this.permissions.addAll(permissions);
    }

    public String getUsername() {
        return username;
    }

    public UUID getUserID() {
        return userID;
    }
}
