package com.miracle.astree.node.statement.iteration;

import com.miracle.astree.node.MiracleASTreeTypename;
import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.statement.MiracleASTreeStatement;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionJudgeExpression;

public class MiracleASTreeWhile extends MiracleASTreeStatement {
    private final MiracleASTreeExpression expression;
    private final MiracleASTreeStatement statement;

    public MiracleASTreeWhile(MiracleASTreeExpression expression, MiracleASTreeStatement statement) {
        this.expression = expression;
        this.statement = statement;
        if (expression.getType().equals(new MiracleASTreeTypename("boolean"))) {
            throw new MiracleExceptionJudgeExpression(expression.getType().toString());
        }
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }
}
