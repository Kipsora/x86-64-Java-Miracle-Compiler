package com.miracle.intermediate.instruction;

import com.miracle.intermediate.value.MiracleIRAddress;
import com.miracle.intermediate.value.MiracleIRMemory;
import com.miracle.intermediate.value.MiracleIRRegister;
import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRMove extends MiracleIRInstruction {
    public final MiracleIRAddress source;
    public final MiracleIRAddress target;
    public final int size;

    public MiracleIRMove(MiracleIRAddress source, MiracleIRRegister target, int size) {
        this.source = source;
        this.target = target;
        this.size = size;
    }

    public MiracleIRMove(MiracleIRRegister source, MiracleIRMemory target, int size) {
        this.source = source;
        this.target = target;
        this.size = size;
    }


    @Override
    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }
}
