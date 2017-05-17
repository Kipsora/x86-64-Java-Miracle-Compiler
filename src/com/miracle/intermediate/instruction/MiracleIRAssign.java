package com.miracle.intermediate.instruction;

import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRAssign extends MiracleIRInstruction {
    @Override
    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }
}
