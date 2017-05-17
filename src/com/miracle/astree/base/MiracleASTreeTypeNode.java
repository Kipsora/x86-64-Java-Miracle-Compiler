package com.miracle.astree.base;

import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;
import com.miracle.symbol.MiracleSymbolVariableType;

public class MiracleASTreeTypeNode extends MiracleASTreeNode {
    public final String typename;
    public final int dimension;
    public final MiracleSourcePosition startPosition;
    private MiracleSymbolVariableType type;

    public MiracleASTreeTypeNode(String typename,
                                 int dimension,
                                 MiracleSourcePosition startPosition) {
        this.typename = typename;
        this.dimension = dimension;
        this.startPosition = startPosition;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public MiracleSymbolVariableType getType() {
        assert type != null;
        return type;
    }

    public void setType(MiracleSymbolVariableType type) {
        this.type = type;
    }
}
