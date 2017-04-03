package com.miracle.astree.node.statement.control;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeReturn extends MiracleASTreeControl {
    private final MiracleASTreeExpression expression;
    private final MiracleASTreeFunctionDeclaration function;

    public MiracleASTreeReturn(MiracleASTreeFunctionDeclaration function, MiracleASTreeExpression expression) {
        this.expression = expression;
        this.function = function;
    }

    public MiracleASTreeReturn(MiracleASTreeFunctionDeclaration function) {
        this.expression = null;
        this.function = function;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }

    public MiracleASTreeFunctionDeclaration getFunction() {
        return function;
    }

    public MiracleASTreeExpression getExpression() {
        return expression;
    }
}
