package com.miracle.intermediate.visitor;

import com.miracle.intermediate.Root;
import com.miracle.intermediate.instruction.Call;
import com.miracle.intermediate.instruction.Compare;
import com.miracle.intermediate.instruction.HeapAllocate;
import com.miracle.intermediate.instruction.Move;
import com.miracle.intermediate.instruction.arithmetic.BinaryArithmetic;
import com.miracle.intermediate.instruction.arithmetic.UnaryArithmetic;
import com.miracle.intermediate.instruction.fork.BinaryBranch;
import com.miracle.intermediate.instruction.fork.Jump;
import com.miracle.intermediate.instruction.fork.Return;
import com.miracle.intermediate.instruction.fork.UnaryBranch;
import com.miracle.intermediate.number.PhysicalRegister;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;

import java.util.HashSet;
import java.util.Set;

public class HLIRTransformer implements IRVisitor {
    private Set<BasicBlock> blockProcessed;
    private Function curFunction;

    @Override
    public void visit(Root ir) {
        blockProcessed = new HashSet<>();
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
        curFunction = function;
        int size = function.getReturns().size();
        if (size == 0) throw new RuntimeException("no return");
        if (size == 1) {
            function.getReturns().iterator().next().block.setFunctionExitBlock();
        } else {
            function.getReturns().forEach(element -> {
                element.append(new Jump(function.getExitBasicBlock()));
                if (((Return) element.instruction).getValue() != null) {
                    element.prepend(new Move(
                            PhysicalRegister.getBy16BITName("RAX", ((Return) element.instruction).getValue().getNumberSize()),
                            ((Return) element.instruction).getValue()
                    ));
                }
                element.remove();
            });
            function.getExitBasicBlock().tail.prepend(new Return(null));
        }
        function.getEntryBasicBlock().accept(this);
        curFunction = null;
    }

    @Override
    public void visit(BasicBlock block) {
        if (blockProcessed.contains(block)) return;
        blockProcessed.add(block);
        boolean flag = false;
        for (BasicBlock.Node it = block.getHead(); it != block.tail; it = it.getSucc()) {
            if (!flag) {
                if (it.instruction instanceof Jump) {
                    flag = true;
                    block.addSuccBasicBlock(((Jump) it.instruction).block);
                } else if (it.instruction instanceof Return) {
                    flag = true;
                } else if (it.instruction instanceof UnaryBranch) {
                    flag = true;
                    block.addSuccBasicBlock(((UnaryBranch) it.instruction).branchTrue);
                    block.addSuccBasicBlock(((UnaryBranch) it.instruction).branchFalse);
                } else if (it.instruction instanceof BinaryBranch) {
                    flag = true;
                    block.addSuccBasicBlock(((BinaryBranch) it.instruction).branchFalse);
                    block.addSuccBasicBlock(((BinaryBranch) it.instruction).branchTrue);
                }
            } else {
                it.remove();
            }
        }
        /*if (!flag) {
            throw new RuntimeException("NO FORK STATEMENT");
        }*/
        block.getSuccBasicBlock().forEach(element -> element.accept(this));
    }

    @Override
    public void visit(Call call) {

    }

    @Override
    public void visit(UnaryArithmetic prefixArithmetic) {

    }

    @Override
    public void visit(UnaryBranch unaryBranch) {

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

    @Override
    public void visit(BinaryBranch binaryBranch) {

    }
}
