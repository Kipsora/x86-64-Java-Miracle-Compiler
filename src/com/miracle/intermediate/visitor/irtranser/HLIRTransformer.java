package com.miracle.intermediate.visitor.irtranser;

import com.miracle.intermediate.Root;
import com.miracle.intermediate.instruction.Move;
import com.miracle.intermediate.instruction.fork.BinaryBranch;
import com.miracle.intermediate.instruction.fork.Jump;
import com.miracle.intermediate.instruction.fork.Return;
import com.miracle.intermediate.instruction.fork.UnaryBranch;
import com.miracle.intermediate.number.PhysicalRegister;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;
import com.miracle.intermediate.visitor.BaseIRVisitor;

import java.util.HashSet;
import java.util.Set;

public class HLIRTransformer extends BaseIRVisitor {
    private Set<BasicBlock> blockProcessed;

    @Override
    public void visit(Root ir) {
        blockProcessed = new HashSet<>();
        ir.globalFunction.forEach((key, value) -> value.accept(this));
    }

    @Override
    public void visit(Function function) {
        int size = function.getReturns().size();
        if (size == 0) {
            throw new RuntimeException("no return");
        }
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
                    block.addSuccBasicBlock(((UnaryBranch) it.instruction).getBranchTrue());
                    block.addSuccBasicBlock(((UnaryBranch) it.instruction).getBranchFalse());
                } else if (it.instruction instanceof BinaryBranch) {
                    flag = true;
                    block.addSuccBasicBlock(((BinaryBranch) it.instruction).getBranchFalse());
                    block.addSuccBasicBlock(((BinaryBranch) it.instruction).getBranchTrue());
                }
            } else {
                it.remove();
            }
        }
        if (!flag) {
            throw new RuntimeException("NO FORK STATEMENT");
        }
        block.getSuccBasicBlock().forEach(element -> element.accept(this));
    }
}
