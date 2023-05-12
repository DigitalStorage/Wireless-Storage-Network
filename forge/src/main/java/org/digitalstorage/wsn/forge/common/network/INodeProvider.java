package org.digitalstorage.wsn.forge.common.network;

public interface INodeProvider<T extends Node> {
    T getNode();
}
