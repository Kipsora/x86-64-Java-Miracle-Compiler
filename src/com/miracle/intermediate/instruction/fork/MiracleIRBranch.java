package com.miracle.intermediate.instruction.fork;

import com.miracle.intermediate.number.MiracleIRNumber;
import com.miracle.intermediate.structure.MiracleIRBasicBlock;
import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRBranch extends MiracleIRFork {
    public final MiracleIRNumber expression;
    public final MiracleIRBasicBlock branchTrue;
    public final MiracleIRBasicBlock branchFalse;

    public MiracleIRBranch(MiracleIRNumber expression,
                           MiracleIRBasicBlock branchTrue,
                           MiracleIRBasicBlock branchFalse) {
        this.expression = expression;
        this.branchTrue = branchTrue;
        this.branchFalse = branchFalse;
    }

    @Override
    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }
}
