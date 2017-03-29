package com.miracle.astree.node.expression.multiary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.expression.value.MiracleASTreeFunction;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

import java.util.List;

public class MiracleASTreeCallExpression extends MiracleASTreeExpression {
    private final MiracleASTreeFunction function;
    private final List<MiracleASTreeExpression> arguments;

    public MiracleASTreeCallExpression(MiracleASTreeFunction function,
                                       List<MiracleASTreeExpression> arguments) {
        super(function.getDeclaration().getType(), "function call", false);
        this.function = function;
        this.arguments = arguments;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }

    public MiracleASTreeFunction getFunction() {
        return function;
    }

    public List<MiracleASTreeExpression> getArguments() {
        return arguments;
    }
}
