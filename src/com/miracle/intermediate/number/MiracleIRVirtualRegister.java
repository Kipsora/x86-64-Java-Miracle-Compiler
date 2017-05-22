package com.miracle.intermediate.number;

import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;

public class MiracleIRVirtualRegister extends MiracleIRRegister {
    public final String name;

    public MiracleIRVirtualRegister(String name) {
        this.name = name;
    }

    public MiracleIRVirtualRegister(MiracleASTreeVariableDeclaration declaration) {
        this.name = declaration.identifier;
    }

    @Override
    public String toString() {
        return "$" + name;
    }
}
