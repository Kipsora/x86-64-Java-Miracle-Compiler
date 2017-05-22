package com.miracle.intermediate.visitor;

import com.miracle.intermediate.MiracleIR;
import com.miracle.intermediate.instruction.MiracleIRBinary;
import com.miracle.intermediate.instruction.MiracleIRMove;
import com.miracle.intermediate.structure.MiracleIRBasicBlock;
import com.miracle.intermediate.structure.MiracleIRFunction;
import com.miracle.intermediate.value.MiracleIRStaticString;
import com.miracle.intermediate.value.MiracleIRStaticVariable;

public class MiracleIRBaseVisitor implements MiracleIRVisitor {
    @Override
    public void visit(MiracleIR ir) {

    }

    @Override
    public void visit(MiracleIRBinary binary) {

    }

    @Override
    public void visit(MiracleIRStaticString staticString) {

    }

    @Override
    public void visit(MiracleIRStaticVariable staticVariable) {

    }

    @Override
    public void visit(MiracleIRFunction function) {

    }

    @Override
    public void visit(MiracleIRBasicBlock block) {

    }

    @Override
    public void visit(MiracleIRMove move) {

    }
}
