package com.miracle.astree.node.expression.unary.prefix;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.expression.unary.MiracleASTreeUnaryExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionSpecialExpression;

public class MiracleASTreeNegate extends MiracleASTreeUnaryExpression {
    public MiracleASTreeNegate(MiracleASTreeExpression node) {
        super("!", node);
        if (!node.getType().equals("boolean")) {
            throw new MiracleExceptionSpecialExpression("prefix !",
                    "boolean", node.getType());
        }
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
