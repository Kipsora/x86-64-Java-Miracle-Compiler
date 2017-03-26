package com.miracle.astree.node.expression.binary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;

public abstract class MiracleASTreeBinaryExpression extends MiracleASTreeExpression {
    final MiracleASTreeExpression left;
    final MiracleASTreeExpression right;

    MiracleASTreeBinaryExpression(MiracleASTreeExpression left, MiracleASTreeExpression right) {
        super(left.getType());
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
