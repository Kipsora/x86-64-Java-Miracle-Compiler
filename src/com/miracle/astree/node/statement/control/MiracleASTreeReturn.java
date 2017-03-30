package com.miracle.astree.node.statement.control;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeReturn extends MiracleASTreeControl {
    private final MiracleASTreeExpression expression;
    private MiracleASTreeFunctionDeclaration function;

    public MiracleASTreeReturn(MiracleASTreeExpression expression) {
        this.expression = expression;
    }

    public MiracleASTreeReturn() {
        this.expression = null;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }

    public void link(MiracleASTreeFunctionDeclaration declaration) {
        this.function = declaration;
    }

    public MiracleASTreeFunctionDeclaration getFunction() {
        return function;
    }

    public MiracleASTreeExpression getExpression() {
        return expression;
    }
}
