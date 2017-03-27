package com.miracle.astree.node.expression;

import com.miracle.astree.node.MiracleASTreeNode;

public abstract class MiracleASTreeExpression extends MiracleASTreeNode {
    private final boolean mutable;
    private final String operator;

    protected MiracleASTreeExpression(String operator, boolean mutable) {
        this.mutable = mutable;
        this.operator = operator;
    }

    public abstract String getType();

    public boolean getMutable() {
        return mutable;
    }

    public String getOperator() {
        return operator;
    }
}
