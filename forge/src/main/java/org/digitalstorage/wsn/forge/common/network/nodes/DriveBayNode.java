package org.digitalstorage.wsn.forge.common.network.nodes;

import java.util.List;

public interface DriveBayNode extends INode {
    <T> List<T> getContents();
}
