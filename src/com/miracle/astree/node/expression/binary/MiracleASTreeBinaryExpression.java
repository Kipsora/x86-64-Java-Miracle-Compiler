package com.miracle.astree.node.expression.binary;

import com.miracle.astree.node.MiracleASTreeTypename;
import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.exceptions.MiracleExceptionBinaryExpression;

public abstract class MiracleASTreeBinaryExpression extends MiracleASTreeExpression {
    private final MiracleASTreeExpression left;
    private final MiracleASTreeExpression right;
    private final String operator;

    MiracleASTreeBinaryExpression(MiracleASTreeTypename type, MiracleASTreeExpression left, String operator, MiracleASTreeExpression right, boolean mutable) {
        super(type, operator, mutable);
        if (!left.getType().equals(right.getType())) {
            throw new MiracleExceptionBinaryExpression();
        }
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    MiracleASTreeBinaryExpression(MiracleASTreeTypename type, MiracleASTreeExpression left, String operator, MiracleASTreeExpression right) {
        super(type, operator, false);
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

    public String getSymbol() {
        return operator;
    }
}
