package org.digitalstorage.wsn.forge.common.network.nodes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;


// Take items from inventory and insert into system
public interface ExtractNode extends Node {
    <T> T extract(BlockPos pos);

}
