package com.miracle.astree.expression;

import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeVariable extends MiracleASTreeExpression {
    public final String identifier;

    public MiracleASTreeVariable(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
