package com.miracle.intermediate.number;

import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;

public class MiracleIRStaticVariable extends MiracleIRDirectRegister {
    public final MiracleASTreeVariableDeclaration declaration;

    public MiracleIRStaticVariable(MiracleASTreeVariableDeclaration declaration) {
        super(declaration);
        this.declaration = declaration;
    }
}
