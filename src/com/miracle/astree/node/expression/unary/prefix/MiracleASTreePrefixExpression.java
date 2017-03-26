package com.miracle.astree.node.expression.unary.prefix;

import com.miracle.astree.node.expression.MiracleASTreeExpression;

abstract class MiracleASTreePrefixExpression extends MiracleASTreeExpression {
    final MiracleASTreeExpression node;

    MiracleASTreePrefixExpression(String operator, MiracleASTreeExpression node) {
        super(operator, node.getType(), node.getMutable());
        this.node = node;
    }
}
