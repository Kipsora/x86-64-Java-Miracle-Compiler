package com.miracle.astree.node.expression.value;

import com.miracle.astree.node.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeVariable extends MiracleASTreeValue {
    private final MiracleASTreeVariableDeclaration declaration;

    public MiracleASTreeVariable(MiracleASTreeVariableDeclaration declaration) {
        super(declaration.getType(), true);
        this.declaration = declaration;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }

    public String getIdentifier() {
        return declaration.getIdentifier();
    }

    public MiracleASTreeVariableDeclaration getDeclaration() {
        return declaration;
    }
}
