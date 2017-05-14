package com.miracle.astree.statement;

import com.miracle.astree.base.MiracleASTreeNode;
import com.miracle.cstree.MiracleSourcePosition;

public abstract class MiracleASTreeStatement extends MiracleASTreeNode {
    public final MiracleSourcePosition startPosition;

    public MiracleASTreeStatement(MiracleSourcePosition startPosition) {
        this.startPosition = startPosition;
    }
}
