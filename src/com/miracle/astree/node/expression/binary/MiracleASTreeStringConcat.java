package com.miracle.astree.node.expression.binary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionSpecialExpression;

public class MiracleASTreeStringConcat extends MiracleASTreeBinaryExpression {
    public MiracleASTreeStringConcat(MiracleASTreeExpression left, MiracleASTreeExpression right) {
        super("string", left, "+", right);
        if (!left.getType().equals("string")) {
            throw new MiracleExceptionSpecialExpression("string concat", "string", left.getType());
        }
        if (!right.getType().equals("string")) {
            throw new MiracleExceptionSpecialExpression("string concat", "string", right.getType());
        }
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
