package com.miracle.astree.statement;

import com.miracle.astree.MiracleASTreeNode;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeBreak extends MiracleASTreeStatement{
    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
