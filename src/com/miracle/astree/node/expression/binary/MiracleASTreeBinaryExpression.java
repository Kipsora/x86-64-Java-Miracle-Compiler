package com.miracle.astree.node.expression.binary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.exceptions.MiracleExceptionBinaryExpression;

public abstract class MiracleASTreeBinaryExpression extends MiracleASTreeExpression {
    final MiracleASTreeExpression left;
    final MiracleASTreeExpression right;

    MiracleASTreeBinaryExpression(String type, MiracleASTreeExpression left, String operator, MiracleASTreeExpression right) {
        super(type, left.getText() + right.getText(), left.getMutable() && right.getMutable());
        if (!left.getType().equals(right.getType())) {
            throw new MiracleExceptionBinaryExpression();
        }
        this.left = left;
        this.right = right;
    }

    public MiracleASTreeExpression getLeft() {
        return left;
    }

    public MiracleASTreeExpression getRight() {
        return right;
    }
}
