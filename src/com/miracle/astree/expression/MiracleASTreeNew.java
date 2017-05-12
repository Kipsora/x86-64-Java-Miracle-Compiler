package com.miracle.astree.expression;

import com.miracle.astree.type.MiracleASTreeBaseType;
import com.miracle.astree.type.MiracleASTreeVariableType;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

import java.util.Collections;
import java.util.List;

public class MiracleASTreeNew extends MiracleASTreeExpression {
    public final MiracleASTreeVariableType type;
    public final List<MiracleASTreeExpression> expressions;

    public MiracleASTreeNew(MiracleASTreeVariableType type,
                            List<MiracleASTreeExpression> expressions) {
        this.type = type;
        this.expressions = Collections.unmodifiableList(expressions);
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
