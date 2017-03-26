package com.miracle.astree.node.expression;

import com.miracle.astree.node.MiracleASTreeNode;

public abstract class MiracleASTreeExpression extends MiracleASTreeNode {
    private final String type;

    protected MiracleASTreeExpression(final String type) {
        this.type = type;
    }

    public final String getType() {
        return type;
    }
}
