package com.miracle.astree.node.expression;

import com.miracle.astree.node.MiracleASTreeNode;
import com.miracle.astree.node.MiracleASTreeTypename;

public abstract class MiracleASTreeExpression extends MiracleASTreeNode {
    private final boolean mutable;
    private final String operator;
    private final MiracleASTreeTypename type;

    protected MiracleASTreeExpression(MiracleASTreeTypename type, String operator, boolean mutable) {
        this.mutable = mutable;
        this.operator = operator;
        this.type = type;
        assert type != null;
    }

    public MiracleASTreeTypename getType() {
        return type;
    }

    public boolean getMutable() {
        return mutable;
    }

    public String getOperator() {
        return operator;
    }
}
