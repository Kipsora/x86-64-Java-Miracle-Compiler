package com.miracle.astree.node.statement.control;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeReturn extends MiracleASTreeControl {
    private final MiracleASTreeExpression expression;

    public MiracleASTreeReturn(MiracleASTreeExpression expression) {
        this.expression = expression;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
