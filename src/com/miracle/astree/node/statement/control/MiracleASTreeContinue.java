package com.miracle.astree.node.statement.control;

import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeContinue extends MiracleASTreeControl {
    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
