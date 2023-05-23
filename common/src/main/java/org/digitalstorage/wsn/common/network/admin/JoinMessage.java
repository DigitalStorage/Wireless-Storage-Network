package org.digitalstorage.wsn.common.network.admin;

import net.minecraft.network.chat.Component;

public class JoinMessage {
    private final Component component;
    private final JoinResponse response;

    public JoinMessage(Component component, JoinResponse response) {
        this.component = component;
        this.response = response;
    }

    public Component getComponent() {
        return component;
    }

    public JoinResponse getResponse() {
        return response;
    }
}
