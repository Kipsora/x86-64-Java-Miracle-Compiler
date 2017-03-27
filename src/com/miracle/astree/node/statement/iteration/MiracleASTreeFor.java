package com.miracle.astree.node.statement.iteration;

import com.miracle.astree.node.MiracleASTreeTypename;
import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.statement.MiracleASTreeStatement;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionJudgeExpression;

public class MiracleASTreeFor extends MiracleASTreeStatement {
    private final MiracleASTreeExpression leftExpression;
    private final MiracleASTreeExpression middleExpression;
    private final MiracleASTreeExpression rightExpression;
    private final MiracleASTreeStatement statement;

    public MiracleASTreeFor(MiracleASTreeExpression leftExpression,
                            MiracleASTreeExpression middleExpression,
                            MiracleASTreeExpression rightExpression,
                            MiracleASTreeStatement statement) {
        this.leftExpression = leftExpression;
        this.middleExpression = middleExpression;
        this.rightExpression = rightExpression;
        this.statement = statement;
        if (!middleExpression.getType().equals(new MiracleASTreeTypename("boolean"))) {
            throw new MiracleExceptionJudgeExpression(middleExpression.getType().toString());
        }
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }

    public MiracleASTreeExpression getLeftExpression() {
        return leftExpression;
    }

    public MiracleASTreeExpression getMiddleExpression() {
        return middleExpression;
    }

    public MiracleASTreeExpression getRightExpression() {
        return rightExpression;
    }
}
