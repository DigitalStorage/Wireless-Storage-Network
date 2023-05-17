package org.digitalstorage.wsn.forge.common.network.admin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class User implements INBTSerializable<CompoundTag> {
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

    public User(UUID ID, CompoundTag tag) {
        this.userID = ID;
        deserializeNBT(tag);
    }

    public String getUsername() {
        return username;
    }

    public UUID getUserID() {
        return userID;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag userTag = new CompoundTag();
        CompoundTag permissionsTag = new CompoundTag();
        userTag.putString("username", this.username);
        permissions.forEach(perm -> {
            permissionsTag.putString(perm.toString(), perm.toString());
        });
        userTag.put("perms", permissionsTag);
        return userTag;
    }

    @Override
    public void deserializeNBT(CompoundTag userTag) {
        this.username = userTag.getString("username");
        userTag.getCompound("perms").getAllKeys().forEach(key -> permissions.add(UserPermissions.valueOf(key)));
    }
}
