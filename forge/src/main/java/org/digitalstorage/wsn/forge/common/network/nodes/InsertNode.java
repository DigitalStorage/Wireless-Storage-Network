package org.digitalstorage.wsn.forge.common.network.nodes;

// Take from System and put into inventory

// GAS, ITEMS, FLUIDS
public interface InsertNode extends Node {
    <T> boolean insert(T stack, boolean simulate);
}
