package com.miracle.astree.expression;

import com.miracle.astree.visitor.MiracleASTreeVisitor;

import java.util.Collections;
import java.util.List;

public class MiracleASTreeCall extends MiracleASTreeExpression {
    public final MiracleASTreeExpression function;
    public final List<MiracleASTreeExpression> parameters;

    public MiracleASTreeCall(MiracleASTreeExpression function,
                             List<MiracleASTreeExpression> parameters) {
        super(left, right);
        this.function = function;
        this.parameters = Collections.unmodifiableList(parameters);
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
