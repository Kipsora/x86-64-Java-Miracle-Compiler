package com.miracle.astree.node.expression.binary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionSpecialExpression;

import static com.miracle.scanner.listener.MiracleRuntimeMaintainer.MiracleASTreeINT;
import static com.miracle.scanner.listener.MiracleRuntimeMaintainer.MiracleASTreeSTRING;

public class MiracleASTreeStringConcat extends MiracleASTreeArithmetic {
    public MiracleASTreeStringConcat(MiracleASTreeExpression left, MiracleASTreeExpression right) {
        super(MiracleASTreeSTRING, left, "+", right);
        if (!left.getType().equals(MiracleASTreeSTRING)) {
            throw new MiracleExceptionSpecialExpression("string concat", "string",
                    left.getType().toString());
        }
        if (!right.getType().equals(MiracleASTreeSTRING)) {
            throw new MiracleExceptionSpecialExpression("string concat", "string",
                    right.getType().toString());
        }
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
