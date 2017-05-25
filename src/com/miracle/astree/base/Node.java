package com.miracle.astree.base;

import com.miracle.astree.visitor.Visitor;
import com.miracle.symbol.SymbolTable;

public abstract class Node {
    private SymbolTable scope;

    public abstract void accept(Visitor visitor);

    public SymbolTable getScope() {
        assert scope != null;
        return scope;
    }

    public void setScope(SymbolTable scope) {
        assert this.scope == null;
        this.scope = scope;
    }
}
