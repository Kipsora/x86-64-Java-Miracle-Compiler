package com.miracle.astree.node.statement.declaration;

import com.miracle.astree.node.MiracleASTreeTypename;
import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeVariableDeclaration extends MiracleASTreeMemberDeclaration {
    private final MiracleASTreeTypename type;
    private final MiracleASTreeExpression value;

    public MiracleASTreeVariableDeclaration(String identifier, MiracleASTreeTypename type) {
        super(identifier);
        this.type = type;
        this.value = null;
    }

    public MiracleASTreeVariableDeclaration(String identifier, MiracleASTreeTypename type, MiracleASTreeExpression value) {
        super(identifier);
        this.type = type;
        this.value = value;
    }

    public MiracleASTreeVariableDeclaration(String decorator, String identifier, MiracleASTreeTypename type) {
        super(decorator, identifier);
        this.type = type;
        this.value = null;
    }

    public MiracleASTreeVariableDeclaration(String decorator, String identifier, MiracleASTreeTypename type, MiracleASTreeExpression value) {
        super(decorator, identifier);
        this.type = type;
        this.value = value;
    }

    public MiracleASTreeTypename getType() {
        return type;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }

    public MiracleASTreeExpression getValue() {
        return value;
    }
}
