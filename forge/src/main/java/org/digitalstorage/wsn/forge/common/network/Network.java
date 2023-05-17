package org.digitalstorage.wsn.forge.common.network;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;
import org.digitalstorage.wsn.forge.common.network.admin.JoinMessage;
import org.digitalstorage.wsn.forge.common.network.admin.Settings;
import org.digitalstorage.wsn.forge.common.network.nodes.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Network implements INetwork, INBTSerializable<CompoundTag> {
    private final ArrayList<Node> nodes = new ArrayList<>();

    // connectionID, playerUUID
    private final HashMap<UUID, UUID> connectionIDs = new HashMap<>();
    private final UUID ID;
    private Settings settings;


    public Network(UUID ID, Settings settings) {
        this.ID = ID;
        this.settings = settings;
    }

    public Network(UUID ID, CompoundTag tag) {
        this.ID = ID;
        deserializeNBT(tag);
    }

    @Override
    public boolean isPublic() {
        return false;
    }

    @Override
    public UUID getID() {
        return ID;
    }

    @Override
    public JoinMessage joinNetwork(Node node, Player player, String password) {
        if (nodes.contains(node))
            return JoinMessage.ALREADY_JOINED;

        JoinMessage message = settings.loginUser(player, password);
        if (message == JoinMessage.SUCCESS) {
            UUID connectionID = UUID.randomUUID();
            connectionIDs.put(connectionID, player.getUUID());
            nodes.add(node);
            node.connect(this, connectionID, player.getUUID());
        }
        return message;
    }

    @Override
    public boolean joinNetwork(Node node, UUID playerID) {
        if (nodes.contains(node))
            return true;

        UUID connectionID = node.getConnectionID();

        if (connectionIDs.containsValue(connectionID)) {
            if (node.getConnectionID() == connectionID) {
                boolean loggedIn = settings.isUserLoggedIn(playerID);
                if (loggedIn) {
                    node.connect(this, connectionID, playerID);
                    nodes.add(node);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void disconnectNode(Node node) {
        if (nodes.contains(node)) {
            nodes.remove(node);
            connectionIDs.remove(node.getConnectionID());
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag networkTag = new CompoundTag();

        CompoundTag connectionIDsTag = new CompoundTag();

        connectionIDs.forEach((IDA, IDB) -> {
            connectionIDsTag.putUUID(IDA.toString(), IDB);
        });

        networkTag.put("settings", settings.serializeNBT());
        networkTag.put("connections", connectionIDsTag);


        return networkTag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        CompoundTag connectionsTag = tag.getCompound("connections");
        connectionsTag.getAllKeys().forEach((key) -> {
            connectionIDs.put(UUID.fromString(key), connectionsTag.getUUID(key));
        });

        CompoundTag settingsTag = tag.getCompound("settings");
        Settings dataSettings = new Settings(settingsTag);
        this.settings = dataSettings;

    }
}
