package com.miracle.astree.base;

import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.symbol.MiracleSymbolTable;

public abstract class MiracleASTreeNode {
    private MiracleSymbolTable scope;

    public abstract void accept(MiracleASTreeVisitor visitor);

    public MiracleSymbolTable getScope() {
        assert scope != null;
        return scope;
    }

    public void setScope(MiracleSymbolTable scope) {
        assert this.scope == null;
        this.scope = scope;
    }
}
