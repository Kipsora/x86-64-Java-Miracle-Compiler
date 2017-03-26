package com.miracle.astree.node.expression;

import com.miracle.astree.node.MiracleASTreeNode;

public abstract class MiracleASTreeExpression extends MiracleASTreeNode {
    private final String type;
    private final boolean mutable;
    private final String operator;

    protected MiracleASTreeExpression(String operator, String type, boolean mutable) {
        this.mutable = mutable;
        this.type = type;
        this.operator = operator;
    }

    public final String getType() {
        return type;
    }

    public final boolean getMutable() {
        return mutable;
    }

    public String getOperator() {
        return operator;
    }
}
