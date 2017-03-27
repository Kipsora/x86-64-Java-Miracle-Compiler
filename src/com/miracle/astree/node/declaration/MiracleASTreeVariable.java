package com.miracle.astree.node.declaration;

import com.miracle.astree.node.MiracleASTreeTypename;
import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeVariable extends MiracleASTreeDeclaration {
    private final MiracleASTreeTypename type;
    private final MiracleASTreeExpression value;

    public MiracleASTreeVariable(String identifier, MiracleASTreeTypename type) {
        super(identifier);
        this.type = type;
        this.value = null;
    }

    public MiracleASTreeVariable(String identifier, MiracleASTreeTypename type, MiracleASTreeExpression value) {
        super(identifier);
        this.type = type;
        this.value = value;
    }

    public MiracleASTreeTypename getType() {
        return type;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
