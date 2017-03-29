package com.miracle.astree.node.expression.binary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionSpecialExpression;

public class MiracleASTreeStringConcat extends MiracleASTreeBinaryExpression {
    public MiracleASTreeStringConcat(MiracleASTreeExpression left, MiracleASTreeExpression right) {
        super(new MiracleASTreeTypename("string"), left, "+", right);
        if (!left.getType().equals(new MiracleASTreeTypename("string"))) {
            throw new MiracleExceptionSpecialExpression("string concat", "string",
                    left.getType().toString());
        }
        if (!right.getType().equals(new MiracleASTreeTypename("string"))) {
            throw new MiracleExceptionSpecialExpression("string concat", "string",
                    right.getType().toString());
        }
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }
}
