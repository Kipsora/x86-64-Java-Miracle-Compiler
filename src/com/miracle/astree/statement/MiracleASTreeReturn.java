package com.miracle.astree.statement;

import com.miracle.astree.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.statement.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;

public class MiracleASTreeReturn extends MiracleASTreeStatement {
    public final MiracleASTreeExpression expression;
    private MiracleASTreeFunctionDeclaration function;

    public MiracleASTreeReturn(MiracleASTreeExpression expression,
                               MiracleSourcePosition startPosition) {
        super(startPosition);
        this.expression = expression;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public MiracleASTreeFunctionDeclaration getFunction() {
        return function;
    }

    public void setFunction(MiracleASTreeFunctionDeclaration function) {
        this.function = function;
    }
}
