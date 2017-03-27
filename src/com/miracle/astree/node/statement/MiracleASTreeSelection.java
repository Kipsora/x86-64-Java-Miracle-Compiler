package com.miracle.astree.node.statement;

import com.miracle.astree.node.MiracleASTreeTypename;
import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionJudgeExpression;

public class MiracleASTreeSelection extends MiracleASTreeStatement {
    private final MiracleASTreeExpression expression;
    private final MiracleASTreeStatement trueStatement;
    private final MiracleASTreeStatement falseStatement;

    public MiracleASTreeSelection(MiracleASTreeExpression expression,
                                  MiracleASTreeStatement trueStatement,
                                  MiracleASTreeStatement falseStatement) {
        this.expression = expression;
        this.trueStatement = trueStatement;
        this.falseStatement = falseStatement;
        if (!expression.getType().equals(new MiracleASTreeTypename("boolean"))) {
            throw new MiracleExceptionJudgeExpression(expression.getType().toString());
        }
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

    public MiracleASTreeStatement getTrueStatement() {
        return trueStatement;
    }

    public MiracleASTreeStatement getFalseStatement() {
        return falseStatement;
    }
}
