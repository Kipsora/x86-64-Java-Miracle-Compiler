package com.miracle.intermediate.structure;

import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRFunction {
    public final String identifier;
    public final MiracleIRBasicBlock entryBB;

    public MiracleIRFunction(String identifier) {
        this.identifier = identifier;
        this.entryBB = new MiracleIRBasicBlock();
    }

    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }
}
