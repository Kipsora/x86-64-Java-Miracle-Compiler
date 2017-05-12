package com.miracle.astree.statement;

import com.miracle.astree.MiracleASTreeNode;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

import java.util.Collections;
import java.util.List;

public class MiracleASTreeBlock extends MiracleASTreeStatement {
    public final List<MiracleASTreeStatement> statements;

    public MiracleASTreeBlock(List<MiracleASTreeStatement> statements) {
        this.statements = Collections.unmodifiableList(statements);
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
