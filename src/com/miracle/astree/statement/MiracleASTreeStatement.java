package com.miracle.astree.statement;

import com.miracle.astree.base.MiracleASTreeNode;
import com.miracle.cstree.MiracleSourcePosition;

public abstract class MiracleASTreeStatement extends MiracleASTreeNode {
    public final MiracleSourcePosition position;

    public MiracleASTreeStatement(MiracleSourcePosition position) {
        this.position = position;
    }
}
