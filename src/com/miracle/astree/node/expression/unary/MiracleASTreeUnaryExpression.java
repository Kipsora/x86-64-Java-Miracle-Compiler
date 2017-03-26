package com.miracle.astree.node.expression.unary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;

public abstract class MiracleASTreeUnaryExpression extends MiracleASTreeExpression {
    protected MiracleASTreeUnaryExpression(MiracleASTreeExpression node, String type) {
        super(type, node.getMutable());
    }
}
