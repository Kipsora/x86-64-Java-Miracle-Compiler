package com.miracle.astree.node.statement.control;

import com.miracle.astree.node.statement.iteration.MiracleASTreeIteration;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeBreak extends MiracleASTreeControl {
    private MiracleASTreeIteration iteration;

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }

    public void link(MiracleASTreeIteration iteration) {
        this.iteration = iteration;
    }

    public MiracleASTreeIteration getIteration() {
        return iteration;
    }
}
