package com.miracle.astree.node.statement.iteration;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.statement.MiracleASTreeStatement;
import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionJudgeExpression;

import static com.miracle.scanner.listener.MiracleRuntimeMaintainer.MiracleASTreeBOOLEAN;

public class MiracleASTreeFor extends MiracleASTreeIteration {
    private MiracleASTreeExpression leftExpression;
    private MiracleASTreeExpression middleExpression;
    private MiracleASTreeExpression rightExpression;
    private MiracleASTreeStatement statement;

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }

    public void setLeftExpression(MiracleASTreeExpression leftExpression) {
        this.leftExpression = leftExpression;
    }

    public void setMiddleExpression(MiracleASTreeExpression middleExpression) {
        this.middleExpression = middleExpression;
        if (middleExpression != null) {
            if (!middleExpression.getType().equals(MiracleASTreeBOOLEAN)) {
                throw new MiracleExceptionJudgeExpression(middleExpression.getType().toString());
            }
        }
    }

    public void setRightExpression(MiracleASTreeExpression rightExpression) {
        this.rightExpression = rightExpression;
    }

    public void setStatement(MiracleASTreeStatement statement) {
        this.statement = statement;
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

    public MiracleASTreeStatement getStatement() {
        return statement;
    }
}
