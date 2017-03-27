package com.miracle.astree.node.statement.control;

import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeBreak extends MiracleASTreeControl {
    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }
}
