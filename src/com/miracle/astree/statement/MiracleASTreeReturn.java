package com.miracle.astree.statement;

import com.miracle.astree.MiracleASTreeNode;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.astree.expression.MiracleASTreeExpression;

public class MiracleASTreeReturn extends MiracleASTreeStatement{
    public final MiracleASTreeExpression expression;

    public MiracleASTreeReturn(MiracleASTreeExpression expression) {
        this.expression = expression;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
