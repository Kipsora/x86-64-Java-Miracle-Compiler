package com.miracle.astree.statement;

import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;

public class MiracleASTreeContinue extends MiracleASTreeStatement {
    private MiracleASTreeIteration iteration;

    public MiracleASTreeContinue(MiracleSourcePosition startPosition) {
        super(startPosition);
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public MiracleASTreeIteration getIteration() {
        return iteration;
    }

    public void setIteration(MiracleASTreeIteration iteration) {
        this.iteration = iteration;
    }
}
