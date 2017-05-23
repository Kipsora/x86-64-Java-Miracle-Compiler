package com.miracle.intermediate.instruction.fork;

import com.miracle.intermediate.number.MiracleIRNumber;
import com.miracle.intermediate.number.MiracleIRRegister;
import com.miracle.intermediate.structure.MiracleIRFunction;
import com.miracle.intermediate.visitor.MiracleIRVisitor;

import java.util.List;

public class MiracleIRCall extends MiracleIRFork {
    public final MiracleIRFunction function;
    public final List<MiracleIRNumber> parameters;
    public final MiracleIRRegister returnRegister;
    public final MiracleIRRegister selfRegister;

    public MiracleIRCall(MiracleIRFunction function,
                         List<MiracleIRNumber> parameters,
                         MiracleIRRegister returnRegister,
                         MiracleIRRegister selfRegister) {
        this.function = function;
        this.parameters = parameters;
        this.returnRegister = returnRegister;
        this.selfRegister = selfRegister;
    }

    @Override
    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }
}
