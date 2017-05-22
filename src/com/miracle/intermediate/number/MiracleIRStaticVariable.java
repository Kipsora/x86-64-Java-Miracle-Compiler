package com.miracle.intermediate.number;

import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;

public class MiracleIRStaticVariable extends MiracleIRVirtualRegister {
    public final int size;
    public final MiracleASTreeVariableDeclaration declaration;

    public MiracleIRStaticVariable(MiracleASTreeVariableDeclaration declaration) {
        super(declaration.identifier);
        this.size = declaration.typenode.getType().getMemorySize();
        this.declaration = declaration;
    }
}
