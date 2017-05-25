package com.miracle.astree.base;

import com.miracle.astree.visitor.Visitor;
import com.miracle.cstree.MiracleSourcePosition;
import com.miracle.symbol.SymbolVariableType;

public class TypeNode extends Node {
    public final String typename;
    public final int dimension;
    public final MiracleSourcePosition startPosition;
    private SymbolVariableType type;

    public TypeNode(String typename,
                    int dimension,
                    MiracleSourcePosition startPosition) {
        this.typename = typename;
        this.dimension = dimension;
        this.startPosition = startPosition;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public SymbolVariableType getType() {
        assert type != null;
        return type;
    }

    public void setType(SymbolVariableType type) {
        if (this.type != null) {
            throw new RuntimeException("settled type");
        }
        this.type = type;
    }
}
