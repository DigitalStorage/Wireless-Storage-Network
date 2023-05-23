package org.digitalstorage.wsn.common.network.nodes;

// Take from System and put into inventory

// GAS, ITEMS, FLUIDS
public interface InsertNode extends INode {
    <T> boolean insert(T stack, boolean simulate);
}
