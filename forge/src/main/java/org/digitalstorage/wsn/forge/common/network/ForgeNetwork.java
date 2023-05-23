package org.digitalstorage.wsn.forge.common.network;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;
import org.digitalstorage.wsn.common.network.INetwork;
import org.digitalstorage.wsn.common.network.INetworkStorage;
import org.digitalstorage.wsn.common.network.admin.JoinMessage;
import org.digitalstorage.wsn.common.network.admin.JoinResponse;
import org.digitalstorage.wsn.common.network.nodes.INode;
import org.digitalstorage.wsn.forge.common.network.admin.Settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * Make it so when a player is logged into a network, we allow the node they are connecting to connect.
 *
 * If not logged in, make the player log in and then do the above.
 */
public class ForgeNetwork implements INetwork, INBTSerializable<CompoundTag> {
    private final ArrayList<INode> nodes = new ArrayList<>();
    private final NetworkStorage storage;

    // connectionID, playerUUID
    private final HashMap<UUID, UUID> connectionIDs = new HashMap<>();
    private final UUID ID;
    private Settings settings;


    public ForgeNetwork(UUID ID, Settings settings) {
        this.ID = ID;
        this.settings = settings;
        this.storage = new NetworkStorage(this);
    }

    public ForgeNetwork(UUID ID, CompoundTag tag) {
        this.ID = ID;
        deserializeNBT(tag);
        this.storage = new NetworkStorage(this);
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
    public JoinMessage joinNetwork(INode node, Player player, String password) {
        if (nodes.contains(node))
            return new JoinMessage(Component.empty(), JoinResponse.ALREADY_JOINED);

        JoinMessage message = settings.loginUser(player, password);

        if (message.getResponse() == JoinResponse.SUCESS) {
            UUID connectionID = UUID.randomUUID();
            connectionIDs.put(connectionID, player.getUUID());
            nodes.add(node);
            node.connect(this, connectionID, player.getUUID());
        }
        return message;
    }

    @Override
    public boolean joinNetwork(INode node, UUID playerID) {
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
    public void disconnectNode(INode node) {
        if (nodes.contains(node)) {
            nodes.remove(node);
            connectionIDs.remove(node.getConnectionID());
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public Stream<INode> getNodes() {
        return nodes.stream();
    }

    @Override
    public INetworkStorage getStorageContents() {
        return storage;
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
