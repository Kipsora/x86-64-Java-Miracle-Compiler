package com.miracle.intermediate.number;

import com.miracle.astree.statement.declaration.VariableDeclaration;

public class VirtualRegister extends DirectRegister {
    public VirtualRegister(String name, int size) {
        super(name, size);
    }

    public VirtualRegister(VariableDeclaration declaration) {
        super(declaration);
    }

    @Override
    public String toString() {
        return getSizeDescriptor() + " $" + name;
    }
}
