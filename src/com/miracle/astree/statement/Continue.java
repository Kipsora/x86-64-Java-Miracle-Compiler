package com.miracle.astree.statement;

import com.miracle.astree.visitor.ASTreeVisitor;
import com.miracle.cstree.SourcePosition;

public class Continue extends Statement {
    private Iteration iteration;

    public Continue(SourcePosition startPosition) {
        super(startPosition);
    }

    @Override
    public void accept(ASTreeVisitor ASTreeVisitor) {
        ASTreeVisitor.visit(this);
    }

    public Iteration getIteration() {
        return iteration;
    }

    public void setIteration(Iteration iteration) {
        this.iteration = iteration;
    }
}
