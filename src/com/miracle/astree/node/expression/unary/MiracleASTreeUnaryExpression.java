package com.miracle.astree.node.expression.unary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;

public abstract class MiracleASTreeUnaryExpression extends MiracleASTreeExpression {
    final MiracleASTreeExpression node;

    protected MiracleASTreeUnaryExpression(String operator, MiracleASTreeExpression node) {
        super(operator, node.getType(), false);
        this.node = node;
    }
}
