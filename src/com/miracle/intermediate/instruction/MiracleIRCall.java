package com.miracle.intermediate.instruction;

import com.miracle.intermediate.number.MiracleIRRegister;
import com.miracle.intermediate.number.MiracleIRVirtualRegister;
import com.miracle.intermediate.structure.MiracleIRFunction;
import com.miracle.intermediate.visitor.MiracleIRVisitor;

import java.util.List;

public class MiracleIRCall extends MiracleIRInstruction {
    public final MiracleIRFunction function;
    public final List<MiracleIRRegister> parameters;
    public final MiracleIRVirtualRegister returnRegister;

    public MiracleIRCall(MiracleIRFunction function,
                         List<MiracleIRRegister> parameters,
                         MiracleIRVirtualRegister returnRegister) {
        this.function = function;
        this.parameters = parameters;
        this.returnRegister = returnRegister;
    }

    @Override
    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }
}
