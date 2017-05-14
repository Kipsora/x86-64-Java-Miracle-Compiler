package com.miracle.astree.statement.expression;

import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;

public class MiracleASTreeThis extends MiracleASTreeExpression {
    public MiracleASTreeThis(MiracleSourcePosition startPosition) {
        super(startPosition);
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toPrintableString() {
        return "this";
    }
}
