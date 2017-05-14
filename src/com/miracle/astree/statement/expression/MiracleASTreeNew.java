package com.miracle.astree.statement.expression;

import com.miracle.astree.base.MiracleASTreeTypeNode;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;

import java.util.Collections;
import java.util.List;

public class MiracleASTreeNew extends MiracleASTreeExpression {
    public final MiracleASTreeTypeNode variableType;
    public final List<MiracleASTreeExpression> expressions;

    public MiracleASTreeNew(MiracleASTreeTypeNode variableType,
                            List<MiracleASTreeExpression> expressions,
                            MiracleSourcePosition startPosition) {
        super(startPosition);
        this.variableType = variableType;
        this.expressions = Collections.unmodifiableList(expressions);
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toPrintableString() {
        StringBuilder builder = new StringBuilder();
        builder.append("new ").append(variableType.type.toPrintableString()).append(" (");
        expressions.forEach(element -> builder.append('[').append(element.toPrintableString()).append(']'));
        builder.append(")");
        return builder.toString();
    }
}
