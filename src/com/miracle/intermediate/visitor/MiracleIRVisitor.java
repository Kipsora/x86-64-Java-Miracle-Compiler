package com.miracle.intermediate.visitor;

import com.miracle.intermediate.instruction.MiracleIRAllocate;
import com.miracle.intermediate.instruction.MiracleIRAssign;
import com.miracle.intermediate.instruction.branch.MiracleIRJump;

public interface MiracleIRVisitor {
    void visit(MiracleIRAllocate allocate);

    void visit(MiracleIRAssign assign);

    void visit(MiracleIRJump jump);
}
