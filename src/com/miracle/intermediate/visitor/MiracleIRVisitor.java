package com.miracle.intermediate.visitor;

import com.miracle.intermediate.MiracleIR;
import com.miracle.intermediate.instruction.MiracleIRCall;
import com.miracle.intermediate.instruction.MiracleIRCompare;
import com.miracle.intermediate.instruction.MiracleIRHeapAllocate;
import com.miracle.intermediate.instruction.MiracleIRMove;
import com.miracle.intermediate.instruction.arithmetic.MiracleIRBinaryArithmetic;
import com.miracle.intermediate.instruction.arithmetic.MiracleIRPrefixArithmetic;
import com.miracle.intermediate.instruction.fork.MiracleIRBranch;
import com.miracle.intermediate.instruction.fork.MiracleIRJump;
import com.miracle.intermediate.instruction.fork.MiracleIRReturn;
import com.miracle.intermediate.structure.MiracleIRBasicBlock;
import com.miracle.intermediate.structure.MiracleIRFunction;

public interface MiracleIRVisitor {
    void visit(MiracleIR ir);

    void visit(MiracleIRBinaryArithmetic binaryArithmetic);

    void visit(MiracleIRMove move);

    void visit(MiracleIRFunction function);

    void visit(MiracleIRBasicBlock block);

    void visit(MiracleIRCall call);

    void visit(MiracleIRPrefixArithmetic prefixArithmetic);

    void visit(MiracleIRBranch branch);

    void visit(MiracleIRReturn irReturn);

    void visit(MiracleIRJump jump);

    void visit(MiracleIRCompare compare);

    void visit(MiracleIRHeapAllocate allocate);
}
