package com.miracle.intermediate.number;

import com.miracle.astree.statement.declaration.VariableDeclaration;

public class VirtualRegister extends DirectRegister {
    private final VirtualRegister oldName;

    public VirtualRegister newName(String name) {
        return new VirtualRegister(this.name + name, this);
    }

    public VirtualRegister getOldName() {
        if (oldName == null) {
            throw new RuntimeException("oldest version");
        }
        return oldName;
    }

    private VirtualRegister(String name, VirtualRegister oldName) {
        super(name, oldName.size);
        this.oldName = oldName;
    }

    public VirtualRegister(String name, int size) {
        super(name, size);
        this.oldName = null;
    }

    public VirtualRegister(VariableDeclaration declaration) {
        super(declaration);
        this.oldName = null;
    }

    @Override
    public String toString() {
        return getSizeDescriptor() + " $" + name;
    }
}
