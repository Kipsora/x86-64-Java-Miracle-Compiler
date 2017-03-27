package com.miracle.astree.node.expression.unary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;

public abstract class MiracleASTreeUnaryExpression extends MiracleASTreeExpression {
    final MiracleASTreeExpression node;
    final public String type;

    protected MiracleASTreeUnaryExpression(String operator, MiracleASTreeExpression node) {
        super(operator, false);
        this.type = node.getType();
        this.node = node;
    }

    public String getType() {
        return type;
    }
}
