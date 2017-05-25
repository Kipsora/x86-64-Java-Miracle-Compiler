package com.miracle.astree.statement.expression;

import com.miracle.astree.visitor.Visitor;
import com.miracle.cstree.MiracleSourcePosition;

public class Subscript extends Expression {
    public final Expression base;
    public final Expression coordinate;

    public Subscript(Expression base,
                     Expression coordinate,
                     MiracleSourcePosition startPosition) {
        super(startPosition);
        this.base = base;
        this.coordinate = coordinate;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toPrintableString() {
        return base.toPrintableString() + "[" + coordinate.toPrintableString() + "]";
    }
}
