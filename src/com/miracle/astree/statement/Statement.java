package com.miracle.astree.statement;

import com.miracle.astree.base.Node;
import com.miracle.cstree.MiracleSourcePosition;

public abstract class Statement extends Node {
    public final MiracleSourcePosition position;

    public Statement(MiracleSourcePosition position) {
        this.position = position;
    }
}
