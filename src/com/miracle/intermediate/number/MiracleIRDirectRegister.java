package com.miracle.intermediate.number;

import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;

public abstract class MiracleIRDirectRegister extends MiracleIRRegister {
    public final String name;
    public final int size;

    public MiracleIRDirectRegister(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public MiracleIRDirectRegister(MiracleASTreeVariableDeclaration declaration) {
        this.name = declaration.identifier;
        this.size = declaration.typenode.getType().getRegisterSize();
    }

    @Override
    public String toString() {
        return "$" + name;
    }

    @Override
    public int getNumberSize() {
        return size;
    }
}
