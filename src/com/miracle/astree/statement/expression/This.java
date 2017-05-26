package com.miracle.astree.statement.expression;

import com.miracle.astree.visitor.ASTreeVisitor;
import com.miracle.cstree.SourcePosition;

public class This extends Expression {
    public This(SourcePosition startPosition) {
        super(startPosition);
    }

    @Override
    public void accept(ASTreeVisitor ASTreeVisitor) {
        ASTreeVisitor.visit(this);
    }

    @Override
    public String toPrintableString() {
        return "this";
    }
}
