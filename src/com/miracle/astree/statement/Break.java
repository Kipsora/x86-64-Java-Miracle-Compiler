package com.miracle.astree.statement;

import com.miracle.astree.visitor.Visitor;
import com.miracle.cstree.SourcePosition;

public class Break extends Statement {
    private Iteration iteration;

    public Break(SourcePosition startPosition) {
        super(startPosition);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Iteration getIteration() {
        return iteration;
    }

    public void setIteration(Iteration iteration) {
        this.iteration = iteration;
    }
}
