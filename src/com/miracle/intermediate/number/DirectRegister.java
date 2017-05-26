package com.miracle.intermediate.number;

import com.miracle.astree.statement.declaration.VariableDeclaration;

public abstract class DirectRegister extends Register {
    public final String name;

    public DirectRegister(String name, int size) {
        super(size);
        this.name = name;
    }

    public DirectRegister(VariableDeclaration declaration) {
        super(declaration.typenode.getType().getRegisterSize());
        this.name = declaration.identifier;
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
