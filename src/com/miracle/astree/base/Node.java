package com.miracle.astree.base;

import com.miracle.astree.visitor.ASTreeVisitor;
import com.miracle.symbol.SymbolTable;

public abstract class Node {
    private SymbolTable scope;

    public abstract void accept(ASTreeVisitor ASTreeVisitor);

    public SymbolTable getScope() {
        assert scope != null;
        return scope;
    }

    public void setScope(SymbolTable scope) {
        assert this.scope == null;
        this.scope = scope;
    }
}
