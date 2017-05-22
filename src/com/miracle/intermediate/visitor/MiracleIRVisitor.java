package com.miracle.intermediate.visitor;

import com.miracle.intermediate.MiracleIR;
import com.miracle.intermediate.instruction.MiracleIRBinary;
import com.miracle.intermediate.instruction.MiracleIRMove;
import com.miracle.intermediate.structure.MiracleIRBasicBlock;
import com.miracle.intermediate.structure.MiracleIRFunction;
import com.miracle.intermediate.value.MiracleIRStaticString;
import com.miracle.intermediate.value.MiracleIRStaticVariable;

public interface MiracleIRVisitor {
    void visit(MiracleIR ir);

    void visit(MiracleIRBinary binary);

    void visit(MiracleIRStaticString staticString);

    void visit(MiracleIRStaticVariable staticVariable);

    void visit(MiracleIRFunction function);

    void visit(MiracleIRBasicBlock block);

    void visit(MiracleIRMove move);
}
