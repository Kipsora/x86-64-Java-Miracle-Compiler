package com.miracle.astree.node.expression.unary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;

public abstract class MiracleASTreeUnaryIntegral extends MiracleASTreeUnaryExpression {
    protected MiracleASTreeUnaryIntegral(MiracleASTreeExpression node) {
        super(node, "int");
    }
}
