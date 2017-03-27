package com.miracle.astree.node.expression.binary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.exceptions.MiracleExceptionBinaryExpression;

public abstract class MiracleASTreeBinaryExpression extends MiracleASTreeExpression {
    final MiracleASTreeExpression left;
    final MiracleASTreeExpression right;
    final String operator;

    MiracleASTreeBinaryExpression(String type, MiracleASTreeExpression left, String operator, MiracleASTreeExpression right) {
        super(operator, type, false);
        if (!left.getType().equals(right.getType())) {
            throw new MiracleExceptionBinaryExpression();
        }
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public MiracleASTreeExpression getLeft() {
        return left;
    }

    public MiracleASTreeExpression getRight() {
        return right;
    }

    public String getOperator() {
        return operator;
    }
}
