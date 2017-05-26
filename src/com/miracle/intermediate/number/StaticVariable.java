package com.miracle.intermediate.number;

import com.miracle.astree.statement.declaration.VariableDeclaration;

public class StaticVariable extends IndirectRegister {
    public final String name;

    public StaticVariable(VariableDeclaration declaration) {
        super(declaration.typenode.getType().getRegisterSize());
        this.name = declaration.identifier;
    }

    @Override
    public String toString() {
        return getSizeDescriptor() + " [rel " + name + "]";
    }
}
