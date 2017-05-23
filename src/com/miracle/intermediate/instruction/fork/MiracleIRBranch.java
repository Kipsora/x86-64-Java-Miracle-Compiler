package com.miracle.intermediate.instruction.fork;

import com.miracle.intermediate.number.MiracleIRDirectRegister;
import com.miracle.intermediate.structure.MiracleIRBasicBlock;
import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRBranch extends MiracleIRFork {
    public final MiracleIRDirectRegister expression;
    public final MiracleIRBasicBlock branchTrue;
    public final MiracleIRBasicBlock branchFalse;


    public MiracleIRBranch(MiracleIRDirectRegister expression,
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
