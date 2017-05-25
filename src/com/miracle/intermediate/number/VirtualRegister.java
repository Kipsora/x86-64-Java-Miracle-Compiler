package com.miracle.intermediate.number;

import com.miracle.astree.statement.declaration.VariableDeclaration;

public class VirtualRegister extends DirectRegister {
    private PhysicalRegister forcedPhysical;

    public VirtualRegister(String name, int size) {
        super(name, size);
    }

    public VirtualRegister(VariableDeclaration declaration) {
        super(declaration);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (size == 1) {
            builder.append("byte");
        } else if (size == 2) {
            builder.append("word");
        } else if (size == 4) {
            builder.append("dword");
        } else if (size == 8) {
            builder.append("qword");
        }
        builder.append(" $").append(name);
        return builder.toString();
    }

    public PhysicalRegister getForcedPhysical() {
        return forcedPhysical;
    }

    public void setForcedPhysical(PhysicalRegister physical) {
        this.forcedPhysical = physical;
    }
}
