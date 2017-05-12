package com.miracle.astree.expression;

import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeSubscript extends MiracleASTreeExpression {
    public final MiracleASTreeExpression base;
    public final MiracleASTreeExpression coordinate;

    public MiracleASTreeSubscript(MiracleASTreeExpression base, MiracleASTreeExpression coordinate) {
        this.base = base;
        this.coordinate = coordinate;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
