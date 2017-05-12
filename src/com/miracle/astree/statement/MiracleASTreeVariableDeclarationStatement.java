package com.miracle.astree.statement;

import com.miracle.astree.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeVariableDeclarationStatement extends MiracleASTreeStatement {
    MiracleASTreeVariableDeclaration declaration;

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        declaration.accept(visitor);
    }
}
