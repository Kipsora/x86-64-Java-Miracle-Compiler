package com.miracle.intermediate.number;

import com.miracle.astree.statement.declaration.VariableDeclaration;

public abstract class DirectRegister extends Register {
    public final String name;
    public final int size;

    public DirectRegister(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public DirectRegister(VariableDeclaration declaration) {
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
