package com.miracle.intermediate.instruction;

import com.miracle.intermediate.number.MiracleIRNumber;
import com.miracle.intermediate.number.MiracleIRRegister;
import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRMove extends MiracleIRInstruction {
    public final MiracleIRRegister target;
    public final MiracleIRNumber source;

    public MiracleIRMove(MiracleIRRegister target, MiracleIRNumber source) {
        this.target = target;
        this.source = source;
    }

    @Override
    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }
}
