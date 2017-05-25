package com.miracle.intermediate.visitor;

import com.miracle.intermediate.Root;
import com.miracle.intermediate.RegisterBuffer;
import com.miracle.intermediate.instruction.Call;
import com.miracle.intermediate.instruction.Compare;
import com.miracle.intermediate.instruction.HeapAllocate;
import com.miracle.intermediate.instruction.Move;
import com.miracle.intermediate.instruction.arithmetic.BinaryArithmetic;
import com.miracle.intermediate.instruction.arithmetic.UnaryArithmetic;
import com.miracle.intermediate.instruction.fork.Branch;
import com.miracle.intermediate.instruction.fork.Jump;
import com.miracle.intermediate.instruction.fork.Return;
import com.miracle.intermediate.number.DirectRegister;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;

import java.util.HashSet;
import java.util.Set;

public class RegisterCollector implements Visitor {
    private Set<BasicBlock> blockProcessed;
    private Function curFunction;

    @Override
    public void visit(Root ir) {
        blockProcessed = new HashSet<>();
        ir.globalFunction.forEach((key, value) -> value.accept(this));
    }

    @Override
    public void visit(BinaryArithmetic binaryArithmetic) {
        if (binaryArithmetic.getTarget() instanceof DirectRegister) {
            curFunction.getBuffer().enroll((DirectRegister) binaryArithmetic.getTarget());
        }
    }

    @Override
    public void visit(Move move) {
        if (move.getTarget() instanceof DirectRegister) {
            curFunction.getBuffer().enroll((DirectRegister) move.getTarget());
        }
    }

    @Override
    public void visit(Function function) {
        curFunction = function;
        curFunction.setBuffer(new RegisterBuffer());
        function.getEntryBasicBlock().accept(this);
        curFunction = null;
    }

    @Override
    public void visit(BasicBlock block) {
        if (blockProcessed.contains(block)) return;
        blockProcessed.add(block);
        for (BasicBlock.Node it = block.getHead(); it != block.tail; it = it.getSucc()) {
            it.instruction.accept(this);
        }
        block.getSuccBasicBlock().forEach(element -> element.accept(this));
    }

    @Override
    public void visit(Call call) {
        if (call.getReturnRegister() instanceof DirectRegister) {
            curFunction.getBuffer().enroll((DirectRegister) call.getReturnRegister());
        }
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
        if (allocate.getRegister() instanceof DirectRegister) {
            curFunction.getBuffer().enroll((DirectRegister) allocate.getRegister());
        }
    }
}
