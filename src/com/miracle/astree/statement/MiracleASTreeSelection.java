package com.miracle.astree.statement;

import com.miracle.astree.statement.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;

public class MiracleASTreeSelection extends MiracleASTreeStatement {
    public final MiracleASTreeExpression expression;
    public final MiracleASTreeStatement branchTrue;
    public final MiracleASTreeStatement branchFalse;

    public MiracleASTreeSelection(MiracleASTreeExpression expression,
                                  MiracleASTreeStatement branchTrue,
                                  MiracleASTreeStatement branchFalse,
                                  MiracleSourcePosition startPosition) {
        super(startPosition);
        this.expression = expression;
        this.branchTrue = branchTrue;
        this.branchFalse = branchFalse;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
