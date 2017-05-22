package com.miracle.astree.statement;

import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;

import java.util.Collections;
import java.util.List;

public class MiracleASTreeBlock extends MiracleASTreeStatement {
    public final List<MiracleASTreeStatement> statements;

    public MiracleASTreeBlock(List<MiracleASTreeStatement> statements,
                              MiracleSourcePosition startPosition) {
        super(startPosition);
        this.statements = Collections.unmodifiableList(statements);
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
