package com.miracle.intermediate.number;

import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;

public class MiracleIRVirtualRegister extends MiracleIRDirectRegister {
    public MiracleIRVirtualRegister(String name, int size) {
        super(name, size);
    }

    public MiracleIRVirtualRegister(MiracleASTreeVariableDeclaration declaration) {
        super(declaration);
    }
}
