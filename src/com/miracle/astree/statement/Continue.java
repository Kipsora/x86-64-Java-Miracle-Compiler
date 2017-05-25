package com.miracle.astree.statement;

import com.miracle.astree.visitor.Visitor;
import com.miracle.cstree.MiracleSourcePosition;

public class Continue extends Statement {
    private Iteration iteration;

    public Continue(MiracleSourcePosition startPosition) {
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
