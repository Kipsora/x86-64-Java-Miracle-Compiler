package com.miracle.intermediate.visitor;

import com.miracle.intermediate.Root;
import com.miracle.intermediate.instruction.Call;
import com.miracle.intermediate.instruction.Compare;
import com.miracle.intermediate.instruction.HeapAllocate;
import com.miracle.intermediate.instruction.Move;
import com.miracle.intermediate.instruction.arithmetic.BinaryArithmetic;
import com.miracle.intermediate.instruction.arithmetic.UnaryArithmetic;
import com.miracle.intermediate.instruction.fork.Branch;
import com.miracle.intermediate.instruction.fork.Jump;
import com.miracle.intermediate.instruction.fork.Return;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;

public class DirectAllocator implements Visitor {
    @Override
    public void visit(Root ir) {
        ir.globalFunction.forEach((key, value) -> value.accept(this));
    }

    @Override
    public void visit(BinaryArithmetic binaryArithmetic) {

    }

    @Override
    public void visit(Move move) {

    }

    @Override
    public void visit(Function function) {
        function.getEntryBasicBlock().accept(this);
    }

    @Override
    public void visit(BasicBlock block) {

    }

    @Override
    public void visit(Call call) {

    }

    @Override
    public void visit(UnaryArithmetic prefixArithmetic) {

    }

    @Override
    public void visit(Branch branch) {

    }

    @Override
    public void visit(Return irReturn) {

    }

    @Override
    public void visit(Jump jump) {

    }

    @Override
    public void visit(Compare compare) {

    }

    @Override
    public void visit(HeapAllocate allocate) {

    }
}
