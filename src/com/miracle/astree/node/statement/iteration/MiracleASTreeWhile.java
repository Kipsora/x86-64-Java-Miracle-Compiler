package com.miracle.astree.node.statement.iteration;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.statement.MiracleASTreeStatement;
import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionJudgeExpression;

import static com.miracle.scanner.listener.MiracleRuntimeMaintainer.MiracleASTreeBOOLEAN;

public class MiracleASTreeWhile extends MiracleASTreeIteration {
    private MiracleASTreeExpression expression;
    private MiracleASTreeStatement statement;

    public void setExpression(MiracleASTreeExpression expression) {
        this.expression = expression;
        if (!expression.getType().equals(MiracleASTreeBOOLEAN)) {
            throw new MiracleExceptionJudgeExpression(expression.getType().toString());
        }
    }

    public void setStatement(MiracleASTreeStatement statement) {
        this.statement = statement;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }

    public MiracleASTreeExpression getExpression() {
        return expression;
    }

    public MiracleASTreeStatement getStatement() {
        return statement;
    }
}
