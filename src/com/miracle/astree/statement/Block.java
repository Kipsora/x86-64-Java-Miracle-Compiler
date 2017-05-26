package com.miracle.astree.statement;

import com.miracle.astree.visitor.ASTreeVisitor;
import com.miracle.cstree.SourcePosition;

import java.util.Collections;
import java.util.List;

public class Block extends Statement {
    public final List<Statement> statements;

    public Block(List<Statement> statements,
                 SourcePosition startPosition) {
        super(startPosition);
        this.statements = Collections.unmodifiableList(statements);
    }

    @Override
    public void accept(ASTreeVisitor ASTreeVisitor) {
        ASTreeVisitor.visit(this);
    }
}
