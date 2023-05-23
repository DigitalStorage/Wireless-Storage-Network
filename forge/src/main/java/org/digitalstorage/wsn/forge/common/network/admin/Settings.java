package org.digitalstorage.wsn.forge.common.network.admin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;
import org.digitalstorage.wsn.common.network.admin.ISettings;
import org.digitalstorage.wsn.common.network.admin.JoinMessage;
import org.digitalstorage.wsn.common.network.admin.JoinResponse;
import org.digitalstorage.wsn.common.network.admin.SecurityLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Settings implements ISettings, INBTSerializable<CompoundTag> {
    private static final String defaultName = "%p's Storage ForgeNetwork";

    private String name;
    private String password = ""; // encrypted
    private SecurityLevel security;
    private User owner = null;
    private final HashMap<UUID, User> users = new HashMap<>();
    private final List<UUID> blocked = new ArrayList<>();

    public Settings(Player player, String name, SecurityLevel security) {
        this.owner = new User(player);
        this.name = name == null ? defaultName.replaceAll("%p", player.getName().getString()) : name;
        this.security = security;
    }

    public Settings(CompoundTag tag) {
        deserializeNBT(tag);
    }

    public boolean isUserLoggedIn(UUID playerID) {
        return users.containsKey(playerID);
    }

    public JoinMessage loginUser(Player player, String password) {
        if (users.containsKey(player.getUUID()))
            return new JoinMessage(Component.empty(), JoinResponse.ALREADY_JOINED);
        if (blocked.contains(player.getUUID()))
            return new JoinMessage(Component.empty(), JoinResponse.DENIED);

        if (security == SecurityLevel.PUBLIC || this.password == password) {
            users.put(player.getUUID(), new User(player));
            return new JoinMessage(Component.empty(), JoinResponse.SUCESS);
        } else {
            return new JoinMessage(Component.empty(), JoinResponse.DENIED);
        }
    }

    public void setPassword(String unEncryptedPassword) {
        this.password = unEncryptedPassword; // TODO: Encrypt
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag settingsTag = new CompoundTag();
        CompoundTag userDataTag = new CompoundTag();
        CompoundTag blockedTag = new CompoundTag();

        users.forEach((id, user) -> {
            userDataTag.put(id.toString(), user.serializeNBT());
        });

        blocked.forEach(id -> {
            blockedTag.putUUID(id.toString(), id);
        });


        settingsTag.putString("name", this.name);
        settingsTag.putString("password", this.password);
        settingsTag.putString("security", this.security.toString());
        settingsTag.putUUID("ownerid", this.owner.getUserID());
        settingsTag.put("owner", this.owner.serializeNBT());
        settingsTag.put("users", userDataTag);
        settingsTag.put("blocked", blockedTag);

        return settingsTag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        CompoundTag userDataTag = tag.getCompound("users");
        CompoundTag blockedTag = tag.getCompound("blocked");

        this.name = tag.getString("name");
        this.password = tag.getString("password");
        this.security = SecurityLevel.valueOf(tag.getString("security"));
        this.owner = new User(tag.getUUID("ownerid"), tag.getCompound("owner"));

        userDataTag.getAllKeys().forEach((key) -> {
            UUID id = UUID.fromString(key);
            users.put(id, new User(id, userDataTag.getCompound(key)));
        });

        blockedTag.getAllKeys().forEach(key -> blocked.add(UUID.fromString(key)));
    }
}
