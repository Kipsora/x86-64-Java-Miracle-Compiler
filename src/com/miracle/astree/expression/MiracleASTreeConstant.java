package com.miracle.astree.expression;

import com.miracle.astree.MiracleASTreeNode;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public abstract class MiracleASTreeConstant extends MiracleASTreeExpression {
    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public abstract String getValue();
}
