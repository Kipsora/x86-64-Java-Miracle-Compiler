package com.miracle.astree.node.expression.binary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;
import com.miracle.exceptions.MiracleExceptionBinaryExpression;

abstract class MiracleASTreeArithmetic extends MiracleASTreeBinaryExpression {
    MiracleASTreeArithmetic(MiracleASTreeTypename type, MiracleASTreeExpression left, String operator, MiracleASTreeExpression right, boolean mutable) {
        super(type, left, operator, right, mutable);
        if (!left.getType().equals(right.getType())) {
            throw new MiracleExceptionBinaryExpression(left.getType().toString(), right.getType().toString());
        }
    }

    MiracleASTreeArithmetic(MiracleASTreeTypename type, MiracleASTreeExpression left, String operator, MiracleASTreeExpression right) {
        super(type, left, operator, right);
        if (!left.getType().equals(right.getType())) {
            throw new MiracleExceptionBinaryExpression(left.getType().toString(), right.getType().toString());
        }
    }
}
