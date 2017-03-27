package com.miracle.astree.node.expression.unary.prefix;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeNegate extends MiracleASTreePrefixExpression{
    MiracleASTreeNegate(String operator, MiracleASTreeExpression node) {
        super(operator, node);
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
