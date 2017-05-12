package com.miracle.astree.statement;

import com.miracle.astree.MiracleASTreeNode;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.astree.expression.MiracleASTreeExpression;

public class MiracleASTreeSelection extends MiracleASTreeStatement {
    public final MiracleASTreeExpression expression;
    public final MiracleASTreeStatement branchTrue;
    public final MiracleASTreeStatement branchFalse;

    public MiracleASTreeSelection(MiracleASTreeExpression expression,
                                  MiracleASTreeStatement branchTrue,
                                  MiracleASTreeStatement branchFalse) {
        this.expression = expression;
        this.branchTrue = branchTrue;
        this.branchFalse = branchFalse;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
