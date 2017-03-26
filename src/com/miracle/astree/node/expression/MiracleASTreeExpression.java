package com.miracle.astree.node.expression;

import com.miracle.astree.node.MiracleASTreeNode;

public abstract class MiracleASTreeExpression extends MiracleASTreeNode {
    private final String type;
    private final boolean mutable;
    private final String text;

    protected MiracleASTreeExpression(String type, String text, boolean mutable) {
        this.mutable = mutable;
        this.text = text;
        this.type = type;
    }

    public final String getType() {
        return type;
    }

    public final boolean getMutable() {
        return mutable;
    }

    public String getText() {
        return text;
    }
}
