package org.digitalstorage.wsn.forge.common.network.admin;

import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Settings {
    private static final String defaultName = "%p's Storage Network";

    private String name;
    private String password = null; // encrypted
    private SecurityLevel security;
    private User owner = null;
    private final HashMap<UUID, User> users = new HashMap<>();
    private final List<UUID> blocked = new ArrayList<>();

    public Settings(Player player, String name, SecurityLevel security) {
        this.owner = new User(player);
        this.name = name == null ? defaultName.replaceAll("%p", player.getName().getString()) : name;
        this.security = security;
    }

    public boolean isUserLoggedIn(UUID playerID) {
        return users.containsKey(playerID);
    }

    public JoinMessage loginUser(Player player, String password) {
        if (users.containsKey(player.getUUID()))
            return JoinMessage.ALREADY_JOINED;
        if (blocked.contains(player.getUUID()))
            return JoinMessage.DENIED_ACCESS;

        if (security == SecurityLevel.PUBLIC || this.password == password) {
            users.put(player.getUUID(), new User(player));
            return JoinMessage.SUCCESS;
        } else {
            return JoinMessage.WRONG_PASSWORD;
        }
    }

    public void setPassword(String unEncryptedPassword) {
        this.password = unEncryptedPassword; // TODO: Encrypt
    }
}
