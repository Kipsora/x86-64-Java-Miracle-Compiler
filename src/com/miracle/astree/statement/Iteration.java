package com.miracle.astree.statement;

import com.miracle.astree.statement.expression.Expression;
import com.miracle.astree.visitor.Visitor;
import com.miracle.cstree.SourcePosition;

public class Iteration extends Statement {
    public final Expression initializeExpression;
    public final Expression conditionExpression;
    public final Expression incrementExpression;
    public final Statement body;

    public Iteration(Expression initializeExpression,
                     Expression conditionExpression,
                     Expression incrementExpression,
                     Statement body,
                     SourcePosition startPosition) {
        super(startPosition);
        this.initializeExpression = initializeExpression;
        this.conditionExpression = conditionExpression;
        this.incrementExpression = incrementExpression;
        this.body = body;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
