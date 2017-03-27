package com.miracle.astree.node.expression.binary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.exceptions.MiracleExceptionBinaryExpression;

public abstract class MiracleASTreeBinaryExpression extends MiracleASTreeExpression {
    private final MiracleASTreeExpression left;
    private final MiracleASTreeExpression right;
    private final String operator;
    private final String type;

    MiracleASTreeBinaryExpression(String type, MiracleASTreeExpression left, String operator, MiracleASTreeExpression right) {
        super(operator, false);
        if (!left.getType().equals(right.getType())) {
            throw new MiracleExceptionBinaryExpression();
        }
        this.type = type;
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

    public String getType() {
        return type;
    }
}
