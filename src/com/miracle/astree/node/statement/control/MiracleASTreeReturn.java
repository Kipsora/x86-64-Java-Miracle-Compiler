package com.miracle.astree.node.statement.control;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionReturn;

public class MiracleASTreeReturn extends MiracleASTreeControl {
    private final MiracleASTreeExpression expression;
    private final MiracleASTreeFunctionDeclaration function;

    public MiracleASTreeReturn(MiracleASTreeExpression expression, MiracleASTreeFunctionDeclaration function) {
        this.expression = expression;
        this.function = function;
        if (!function.getRetType().equals(expression.getType())) {
            throw new MiracleExceptionReturn(function.getRetType().toString(),
                    expression.getType().toString());
        }
    }

    public MiracleASTreeReturn(MiracleASTreeFunctionDeclaration function) {
        this.expression = null;
        this.function = function;
        if (!function.getRetType().equals(new MiracleASTreeTypename("void"))) {
            throw new MiracleExceptionReturn(function.getRetType().toString(), "void");
        }
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
}
