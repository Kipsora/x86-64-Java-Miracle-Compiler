package com.miracle.intermediate.instruction;

import com.miracle.intermediate.number.MiracleIRDirectRegister;
import com.miracle.intermediate.number.MiracleIRNumber;
import com.miracle.intermediate.number.MiracleIRRegister;
import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRHeapAllocate extends MiracleIRInstruction {
    public final MiracleIRRegister register;
    public final int size;
    public final MiracleIRNumber number;

    public MiracleIRHeapAllocate(MiracleIRDirectRegister register,
                                 int size,
                                 MiracleIRNumber number) {
        this.register = register;
        this.size = size;
        this.number = number;
    }

    @Override
    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }
}
