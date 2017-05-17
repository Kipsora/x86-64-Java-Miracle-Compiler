package com.miracle.intermediate.instruction.branch;

import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRReturn extends MiracleIRBranch {
    @Override
    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }
}
