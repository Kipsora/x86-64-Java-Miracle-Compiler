package com.miracle.astree.node.expression.unary.prefix;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.expression.unary.MiracleASTreeUnaryExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionSpecialExpression;

import static com.miracle.scanner.listener.MiracleRuntimeMaintainer.MiracleASTreeBOOLEAN;

public class MiracleASTreeNegate extends MiracleASTreeUnaryExpression {
    public MiracleASTreeNegate(MiracleASTreeExpression node) {
        super("!", node);
        if (!node.getType().equals(MiracleASTreeBOOLEAN)) {
            throw new MiracleExceptionSpecialExpression("prefix !", "boolean",
                    node.getType().toString());
        }
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
