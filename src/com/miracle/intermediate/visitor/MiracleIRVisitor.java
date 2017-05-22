package com.miracle.intermediate.visitor;

import com.miracle.intermediate.MiracleIR;
import com.miracle.intermediate.instruction.MiracleIRBinaryArithmetic;
import com.miracle.intermediate.instruction.MiracleIRCall;
import com.miracle.intermediate.instruction.MiracleIRMove;
import com.miracle.intermediate.instruction.MiracleIRPrefixArithmetic;
import com.miracle.intermediate.structure.MiracleIRBasicBlock;
import com.miracle.intermediate.structure.MiracleIRFunction;

public interface MiracleIRVisitor {
    void visit(MiracleIR ir);

    void visit(MiracleIRBinaryArithmetic binaryArithmetic);

    void visit(MiracleIRMove move);

    void visit(MiracleIRFunction function);

    void visit(MiracleIRBasicBlock block);

    void visit(MiracleIRCall call);

    void visit(MiracleIRPrefixArithmetic increment);
}
