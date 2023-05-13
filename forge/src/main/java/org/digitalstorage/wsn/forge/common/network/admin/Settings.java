package org.digitalstorage.wsn.forge.common.network.admin;

import net.minecraft.world.entity.player.Player;
import org.digitalstorage.wsn.forge.common.network.user.User;

import java.util.HashMap;
import java.util.UUID;

public class Settings {
    private String name = "%p's Storage Network";
    private String password = null; // encrypted
    private SecurityLevel security = SecurityLevel.PUBLIC;
    private User owner = null;
    private final HashMap<UUID, User> users = new HashMap<>();

    public Settings(String name, SecurityLevel security) {
        this.name = name;
        this.security = security;
    }

    public void setPassword(String unEncryptedPassword) {
        this.password = unEncryptedPassword; // TODO: Encrypt
    }

    public JoinMessage join(Player player, String unEncryptedPassword) {
        return JoinMessage.DENIED_ACCESS;
    }
}
