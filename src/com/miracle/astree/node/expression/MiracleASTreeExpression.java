package com.miracle.astree.node.expression;

import com.miracle.astree.node.MiracleASTreeNode;
import com.miracle.astree.node.statement.MiracleASTreeStatement;
import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;

public abstract class MiracleASTreeExpression extends MiracleASTreeStatement {
    private final boolean mutable;
    private final String symbol;
    private final MiracleASTreeTypename type;

    protected MiracleASTreeExpression(MiracleASTreeTypename type, String symbol, boolean mutable) {
        this.mutable = mutable;
        this.symbol = symbol;
        this.type = type;
        assert type != null;
    }

    public MiracleASTreeTypename getType() {
        return type;
    }

    public boolean getMutable() {
        return mutable;
    }

    public String getSymbol() {
        return symbol;
    }
}
