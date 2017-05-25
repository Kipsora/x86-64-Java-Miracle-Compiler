package com.miracle.intermediate.number;

import com.miracle.astree.statement.declaration.VariableDeclaration;

public class StaticVariable extends DirectRegister {
    public final VariableDeclaration declaration;

    public StaticVariable(VariableDeclaration declaration) {
        super(declaration);
        this.declaration = declaration;
    }
}
