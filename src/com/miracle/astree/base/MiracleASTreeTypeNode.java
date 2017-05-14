package com.miracle.astree.base;

import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;
import com.miracle.symbol.type.MiracleVariableType;

public class MiracleASTreeTypeNode extends MiracleASTreeNode {
    public final MiracleVariableType type;
    public final MiracleSourcePosition startPosition;

    public MiracleASTreeTypeNode(MiracleVariableType type,
                                 MiracleSourcePosition startPosition) {
        this.type = type;
        this.startPosition = startPosition;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
