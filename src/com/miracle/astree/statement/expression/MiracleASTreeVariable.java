package com.miracle.astree.statement.expression;

import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;

public class MiracleASTreeVariable extends MiracleASTreeExpression {
    public final String identifier;

    public MiracleASTreeVariable(String identifier, MiracleSourcePosition position) {
        super(position);
        this.identifier = identifier;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toPrintableString() {
        return identifier;
    }
}
