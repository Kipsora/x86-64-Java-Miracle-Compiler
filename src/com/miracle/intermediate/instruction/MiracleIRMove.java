package com.miracle.intermediate.instruction;

import com.miracle.intermediate.value.MiracleIRDynamic;
import com.miracle.intermediate.value.MiracleIRMemory;
import com.miracle.intermediate.value.MiracleIRRegister;
import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRMove extends MiracleIRInstruction {
    public final MiracleIRDynamic source;
    public final MiracleIRDynamic target;

    public MiracleIRMove(MiracleIRRegister source, MiracleIRRegister target) {
        this.source = source;
        this.target = target;
    }

    public MiracleIRMove(MiracleIRRegister source, MiracleIRMemory target) {
        this.source = source;
        this.target = target;
    }

    public MiracleIRMove(MiracleIRMemory source, MiracleIRRegister target) {
        this.source = source;
        this.target = target;
    }


    @Override
    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }
}
