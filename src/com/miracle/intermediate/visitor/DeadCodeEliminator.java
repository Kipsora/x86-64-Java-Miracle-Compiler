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
import com.miracle.intermediate.instruction.fork.Fork;
import com.miracle.intermediate.instruction.fork.Jump;
import com.miracle.intermediate.instruction.fork.Return;
import com.miracle.intermediate.number.Immediate;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;

import java.util.HashSet;
import java.util.Set;

public class DeadCodeEliminator implements Visitor {
    private RegisterBuffer newBuffer;
    private Function curFunction;
    private Set<BasicBlock> blockProcessed;

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
        function.getEntryBasicBlock().accept(this);
        curFunction = null;
    }

    @Override
    public void visit(BasicBlock block) {
        if (blockProcessed.contains(block)) return;
        blockProcessed.add(block);
        boolean flag = true;
        block.clearSuccBlock();
        for (BasicBlock.Node it = block.getHead(); it != block.tail; it = it.getSucc()) {
            if (flag) {
                if (it.instruction instanceof Jump) {
                    block.addSuccBasicBlock(((Jump) it.instruction).block);
                } else if (it.instruction instanceof Branch) {
                    if (((Branch) it.instruction).getExpression() instanceof Immediate) {
                        if (((Immediate) ((Branch) it.instruction).getExpression()).value == 0) {
                            it.remove();
                            it.getPrev().append(new Jump(((Branch) it.instruction).branchFalse));
                            block.addSuccBasicBlock(((Branch) it.instruction).branchFalse);
                        } else {
                            it.remove();
                            it.getPrev().append(new Jump(((Branch) it.instruction).branchTrue));
                            block.addSuccBasicBlock(((Branch) it.instruction).branchTrue);
                        }
                    } else {
                        block.addSuccBasicBlock(((Branch) it.instruction).branchTrue);
                        block.addSuccBasicBlock(((Branch) it.instruction).branchFalse);
                    }
                } else if (it.instruction instanceof Return) {
                    block.addSuccBasicBlock(curFunction.getExitBasicBlock());
                }
                it.instruction.accept(this);
            } else {
                it.remove();
            }
            if (it.instruction instanceof Fork) {
                flag = false;
            }
        }
        boolean first = true;
        Set<BasicBlock> succBasicBlocks = block.getSuccBasicBlock();
        for (BasicBlock element : succBasicBlocks) {
            BasicBlock.Node forkInstruction = block.tail.getPrev();
            if (first && forkInstruction.instruction instanceof Jump &&
                    ((Jump) forkInstruction.instruction).block == element) {
                forkInstruction.remove();
            }
            element.accept(this);
            first = false;
        }
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
