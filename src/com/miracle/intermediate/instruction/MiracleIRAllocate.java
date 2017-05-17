package com.miracle.intermediate.instruction;

import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRAllocate extends MiracleIRInstruction {
    private int size;

    public MiracleIRAllocate(int size) {
        this.size = size;
    }

    @Override
    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }

    public void setSize(int size) {
        this.size = size;
    }
}
