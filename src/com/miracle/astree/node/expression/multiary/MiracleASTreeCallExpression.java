package com.miracle.astree.node.expression.multiary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

import java.util.List;

public class MiracleASTreeCallExpression extends MiracleASTreeExpression {
    private final MiracleASTreeExpression function;
    private final List<MiracleASTreeExpression> arguments;

    public MiracleASTreeCallExpression(MiracleASTreeExpression function,
                                       List<MiracleASTreeExpression> arguments) {
        super(new MiracleASTreeTypename(function.getType().getBasetype(), function.getType().getDimension()),
                "function call", false);
        this.function = function;
        this.arguments = arguments;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public MiracleASTreeExpression getFunction() {
        return function;
    }

    public List<MiracleASTreeExpression> getArguments() {
        return arguments;
    }
}
