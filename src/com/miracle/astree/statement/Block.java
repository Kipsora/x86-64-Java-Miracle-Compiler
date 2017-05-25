package com.miracle.astree.statement;

import com.miracle.astree.visitor.Visitor;
import com.miracle.cstree.MiracleSourcePosition;

import java.util.Collections;
import java.util.List;

public class Block extends Statement {
    public final List<Statement> statements;

    public Block(List<Statement> statements,
                 MiracleSourcePosition startPosition) {
        super(startPosition);
        this.statements = Collections.unmodifiableList(statements);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
