package com.miracle.astree.statement.expression;

import com.miracle.astree.visitor.ASTreeVisitor;
import com.miracle.cstree.SourcePosition;

public class Subscript extends Expression {
    public final Expression base;
    public final Expression coordinate;

    public Subscript(Expression base,
                     Expression coordinate,
                     SourcePosition startPosition) {
        super(startPosition);
        this.base = base;
        this.coordinate = coordinate;
    }

    @Override
    public void accept(ASTreeVisitor ASTreeVisitor) {
        ASTreeVisitor.visit(this);
    }

    @Override
    public String toPrintableString() {
        return base.toPrintableString() + "[" + coordinate.toPrintableString() + "]";
    }
}
