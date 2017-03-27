package com.miracle.astree.node.statement;

import com.miracle.astree.visitor.MiracleASTreeVisitor;

import java.util.List;

public class MiracleASTreeBlock extends MiracleASTreeStatement {
    private final List<MiracleASTreeStatement> statement;

    public MiracleASTreeBlock(List<MiracleASTreeStatement> statement) {
        this.statement = statement;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public List<MiracleASTreeStatement> getStatement() {
        return statement;
    }
}
