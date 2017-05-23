package com.miracle.intermediate.instruction.fork;

import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRReturn extends MiracleIRFork {
    @Override
    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }
}
