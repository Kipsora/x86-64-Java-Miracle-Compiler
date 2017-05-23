package com.miracle.intermediate.instruction.fork;

import com.miracle.intermediate.structure.MiracleIRBasicBlock;
import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRJump extends MiracleIRFork {
    public final MiracleIRBasicBlock block;

    public MiracleIRJump(MiracleIRBasicBlock block) {
        this.block = block;
    }

    @Override
    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }
}
