package com.miracle.astree.statement;

import com.miracle.astree.base.Node;
import com.miracle.cstree.SourcePosition;

public abstract class Statement extends Node {
    public final SourcePosition position;

    public Statement(SourcePosition position) {
        this.position = position;
    }
}
