package com.miracle.astree.statement;

import com.miracle.astree.expression.MiracleASTreeExpression;
import com.miracle.astree.MiracleASTreeNode;
import com.miracle.astree.statement.MiracleASTreeStatement;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeIteration extends MiracleASTreeStatement{
    public final MiracleASTreeExpression initializeExpression;
    public final MiracleASTreeExpression conditionExpression;
    public final MiracleASTreeExpression incrementExpression;
    public final MiracleASTreeStatement body;

    public MiracleASTreeIteration(MiracleASTreeExpression initializeExpression,
                                  MiracleASTreeExpression conditionExpression,
                                  MiracleASTreeExpression incrementExpression,
                                  MiracleASTreeStatement body) {
        this.initializeExpression = initializeExpression;
        this.conditionExpression = conditionExpression;
        this.incrementExpression = incrementExpression;
        this.body = body;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
