package com.miracle.astree.statement.declaration;

import com.miracle.astree.expression.MiracleASTreeExpression;
import com.miracle.astree.type.MiracleASTreeVariableType;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.astree.type.MiracleASTreeType;

public class MiracleASTreeVariableDeclaration extends MiracleASTreeMemberDeclaration {
    public final MiracleASTreeExpression expression;
    public final MiracleASTreeVariableType type;

    public MiracleASTreeVariableDeclaration(String identifier,
                                            MiracleASTreeVariableType type,
                                            MiracleASTreeExpression expression) {
        super(identifier);
        this.type = type;
        this.expression = expression;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public MiracleASTreeType getType() {
        return type;
    }
}
