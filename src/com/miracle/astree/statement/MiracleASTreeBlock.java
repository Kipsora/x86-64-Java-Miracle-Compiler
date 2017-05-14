package com.miracle.astree.statement;

import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;
import com.miracle.symbol.MiracleSymbolTable;

import java.util.Collections;
import java.util.List;

public class MiracleASTreeBlock extends MiracleASTreeStatement {
    public final List<MiracleASTreeStatement> statements;
    private MiracleSymbolTable scope;

    public MiracleASTreeBlock(List<MiracleASTreeStatement> statements,
                              MiracleSourcePosition startPosition) {
        super(startPosition);
        this.statements = Collections.unmodifiableList(statements);
    }

    public MiracleSymbolTable getScope() {
        return scope;
    }

    public void setScope(MiracleSymbolTable scope) {
        this.scope = scope;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
