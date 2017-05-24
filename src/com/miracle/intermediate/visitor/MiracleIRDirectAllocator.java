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

public class MiracleIRDirectAllocator implements MiracleIRVisitor {
    @Override
    public void visit(MiracleIR ir) {
        ir.globalFunction.forEach((key, value) -> value.accept(this));
    }

    @Override
    public void visit(MiracleIRBinaryArithmetic binaryArithmetic) {

    }

    @Override
    public void visit(MiracleIRMove move) {

    }

    @Override
    public void visit(MiracleIRFunction function) {
        function.getEntryBasicBlock().accept(this);
    }

    @Override
    public void visit(MiracleIRBasicBlock block) {
        for (MiracleIRBasicBlock.Node it = block.getHead(); it != block.tail; it = it.getSucc()) {
            it.instruction.accept(this);
        }
        //block.getSuccBasicBlock().forEach(element -> element.accept(this));
    }

    @Override
    public void visit(MiracleIRCall call) {

    }

    @Override
    public void visit(MiracleIRPrefixArithmetic prefixArithmetic) {

    }

    @Override
    public void visit(MiracleIRBranch branch) {

    }

    @Override
    public void visit(MiracleIRReturn irReturn) {

    }

    @Override
    public void visit(MiracleIRJump jump) {

    }

    @Override
    public void visit(MiracleIRCompare compare) {

    }

    @Override
    public void visit(MiracleIRHeapAllocate allocate) {

    }
}
