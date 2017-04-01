package com.miracle.astree.node.expression.binary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionBinaryExpression;

public abstract class MiracleASTreeArithmetic extends MiracleASTreeBinaryExpression {
    MiracleASTreeArithmetic(MiracleASTreeTypename type, MiracleASTreeExpression left, String operator, MiracleASTreeExpression right, boolean mutable) {
        super(type, left, operator, right, mutable);
        if (!left.getType().equals(right.getType())) {
            throw new MiracleExceptionBinaryExpression();
        }
    }

    MiracleASTreeArithmetic(MiracleASTreeTypename type, MiracleASTreeExpression left, String operator, MiracleASTreeExpression right) {
        super(type, left, operator, right);
        if (!left.getType().equals(right.getType())) {
            throw new MiracleExceptionBinaryExpression();
        }
    }
}
