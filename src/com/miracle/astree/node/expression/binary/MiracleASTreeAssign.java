package com.miracle.astree.node.expression.binary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionLeftValue;

public class MiracleASTreeAssign extends MiracleASTreeBinaryExpression {
    public MiracleASTreeAssign(MiracleASTreeExpression left, MiracleASTreeExpression right) {
        super(left.getType(), left, "=", right);
        if (!left.getMutable()) {
            throw new MiracleExceptionLeftValue();
        }
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
