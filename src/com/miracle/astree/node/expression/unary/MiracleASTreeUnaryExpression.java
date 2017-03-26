package com.miracle.astree.node.expression.unary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeUnaryExpression extends MiracleASTreeExpression {


    protected MiracleASTreeUnaryExpression(String type) {
        super(type);
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
