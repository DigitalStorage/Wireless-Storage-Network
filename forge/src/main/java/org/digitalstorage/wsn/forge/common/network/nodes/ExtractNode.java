package org.digitalstorage.wsn.forge.common.network.nodes;

import net.minecraft.core.BlockPos;


// Take items from inventory and insert into system
public interface ExtractNode extends INode {
    <T> T extract(BlockPos pos);
}
