package com.miracle.intermediate.instruction.branch;

import com.miracle.intermediate.base.MiracleIRBasicBlock;
import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRJump extends MiracleIRBranch {
    public MiracleIRJump(MiracleIRBasicBlock branchTrue, MiracleIRBasicBlock branchFalse, MiracleIRBasicBlock branchAfter) {
        super(branchTrue, branchFalse, branchAfter);
    }

    @Override
    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }
}
