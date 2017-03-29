package com.miracle.astree.node.expression.value;

import com.miracle.astree.node.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeFunction extends MiracleASTreeValue {
    private final MiracleASTreeFunctionDeclaration declaration;

    public MiracleASTreeFunction(MiracleASTreeFunctionDeclaration declaration) {
        super(declaration.getType(), false);
        this.declaration = declaration;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }

    public MiracleASTreeFunctionDeclaration getDeclaration() {
        return declaration;
    }
}
