package com.miracle.astree.statement.expression;

import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;

public class MiracleASTreeSubscript extends MiracleASTreeExpression {
    public final MiracleASTreeExpression base;
    public final MiracleASTreeExpression coordinate;

    public MiracleASTreeSubscript(MiracleASTreeExpression base,
                                  MiracleASTreeExpression coordinate,
                                  MiracleSourcePosition startPosition) {
        super(startPosition);
        this.base = base;
        this.coordinate = coordinate;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toPrintableString() {
        return base.toPrintableString() + "[" + coordinate.toPrintableString() + "]";
    }
}
