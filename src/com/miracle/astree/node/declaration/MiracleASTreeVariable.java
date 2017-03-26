package com.miracle.astree.node.declaration;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeVariable extends MiracleASTreeDeclaration {
    private final String type;
    private final MiracleASTreeExpression expression;

    public MiracleASTreeVariable(String identifier, String type) {
        super(identifier);
        this.type = type;
        this.expression = null;
    }

    public MiracleASTreeVariable(String identifier, String type, MiracleASTreeExpression expression) {
        super(identifier);
        this.type = type;
        this.expression = expression;
    }

    public String getType() {
        return type;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
