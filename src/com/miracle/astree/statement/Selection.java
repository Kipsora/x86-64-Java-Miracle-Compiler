package com.miracle.astree.statement;

import com.miracle.astree.statement.expression.Expression;
import com.miracle.astree.visitor.ASTreeVisitor;
import com.miracle.cstree.SourcePosition;

public class Selection extends Statement {
    public final Expression expression;
    public final Statement branchTrue;
    public final Statement branchFalse;

    public Selection(Expression expression,
                     Statement branchTrue,
                     Statement branchFalse,
                     SourcePosition startPosition) {
        super(startPosition);
        this.expression = expression;
        this.branchTrue = branchTrue;
        this.branchFalse = branchFalse;
    }

    @Override
    public void accept(ASTreeVisitor ASTreeVisitor) {
        ASTreeVisitor.visit(this);
    }
}
