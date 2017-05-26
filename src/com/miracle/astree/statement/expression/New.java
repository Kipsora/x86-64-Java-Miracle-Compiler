package com.miracle.astree.statement.expression;

import com.miracle.astree.base.TypeNode;
import com.miracle.astree.visitor.ASTreeVisitor;
import com.miracle.cstree.SourcePosition;

import java.util.Collections;
import java.util.List;

public class New extends Expression {
    public final TypeNode variableType;
    public final List<Expression> expressions;

    public New(TypeNode variableType,
               List<Expression> expressions,
               SourcePosition startPosition) {
        super(startPosition);
        this.variableType = variableType;
        this.expressions = Collections.unmodifiableList(expressions);
    }

    @Override
    public void accept(ASTreeVisitor ASTreeVisitor) {
        ASTreeVisitor.visit(this);
    }

    @Override
    public String toPrintableString() {
        StringBuilder builder = new StringBuilder();
        builder.append("new ").append(variableType.typename).append(" (");
        expressions.forEach(element -> {
            if (element != null) {
                builder.append('[').append(element.toPrintableString()).append(']');
            } else {
                builder.append("[]");
            }
        });
        builder.append(")");
        return builder.toString();
    }
}
