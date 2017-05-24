package com.miracle.intermediate.visitor;

import com.miracle.intermediate.MiracleIR;
import com.miracle.intermediate.MiracleIRRegisterBuffer;
import com.miracle.intermediate.instruction.MiracleIRCall;
import com.miracle.intermediate.instruction.MiracleIRCompare;
import com.miracle.intermediate.instruction.MiracleIRHeapAllocate;
import com.miracle.intermediate.instruction.MiracleIRMove;
import com.miracle.intermediate.instruction.arithmetic.MiracleIRBinaryArithmetic;
import com.miracle.intermediate.instruction.arithmetic.MiracleIRPrefixArithmetic;
import com.miracle.intermediate.instruction.fork.MiracleIRBranch;
import com.miracle.intermediate.instruction.fork.MiracleIRFork;
import com.miracle.intermediate.instruction.fork.MiracleIRJump;
import com.miracle.intermediate.instruction.fork.MiracleIRReturn;
import com.miracle.intermediate.number.MiracleIRImmediate;
import com.miracle.intermediate.structure.MiracleIRBasicBlock;
import com.miracle.intermediate.structure.MiracleIRFunction;

import java.util.HashSet;
import java.util.Set;

public class MiracleIRDeadCodeEliminator implements MiracleIRVisitor {
    private MiracleIRRegisterBuffer newBuffer;
    private MiracleIRFunction curFunction;
    private Set<MiracleIRBasicBlock> blockProcessed;

    @Override
    public void visit(MiracleIR ir) {
        blockProcessed = new HashSet<>();
        ir.globalFunction.forEach((key, value) -> value.accept(this));
    }

    @Override
    public void visit(MiracleIRBinaryArithmetic binaryArithmetic) {
        newBuffer.enroll(binaryArithmetic.target);
    }

    @Override
    public void visit(MiracleIRMove move) {
        newBuffer.enroll(move.target);
    }

    @Override
    public void visit(MiracleIRFunction function) {
        newBuffer = new MiracleIRRegisterBuffer();
        curFunction = function;
        function.getEntryBasicBlock().accept(this);
        function.setBuffer(newBuffer);
        if (function.selfRegister != null) {
            newBuffer.enroll(function.selfRegister);
        }
        if (function.retRegister != null) {
            newBuffer.enroll(function.retRegister);
        }
        curFunction = null;
    }

    @Override
    public void visit(MiracleIRBasicBlock block) {
        if (blockProcessed.contains(block)) return;
        blockProcessed.add(block);
        boolean flag = true;
        block.clearSuccBlock();
        for (MiracleIRBasicBlock.Node it = block.getHead(); it != block.tail; it = it.getSucc()) {
            if (flag) {
                if (it.instruction instanceof MiracleIRJump) {
                    block.addSuccBasicBlock(((MiracleIRJump) it.instruction).block);
                } else if (it.instruction instanceof MiracleIRBranch) {
                    if (((MiracleIRBranch) it.instruction).expression instanceof MiracleIRImmediate) {
                        if (((MiracleIRImmediate) ((MiracleIRBranch) it.instruction).expression).value == 0) {
                            it.remove();
                            it.getPrev().append(new MiracleIRJump(((MiracleIRBranch) it.instruction).branchFalse));
                            block.addSuccBasicBlock(((MiracleIRBranch) it.instruction).branchFalse);
                        } else {
                            it.remove();
                            it.getPrev().append(new MiracleIRJump(((MiracleIRBranch) it.instruction).branchTrue));
                            block.addSuccBasicBlock(((MiracleIRBranch) it.instruction).branchTrue);
                        }
                    } else {
                        block.addSuccBasicBlock(((MiracleIRBranch) it.instruction).branchTrue);
                        block.addSuccBasicBlock(((MiracleIRBranch) it.instruction).branchFalse);
                    }
                } else if (it.instruction instanceof MiracleIRReturn) {
                    block.addSuccBasicBlock(curFunction.getExitBasicBlock());
                }
                it.instruction.accept(this);
            } else {
                it.remove();
            }
            if (it.instruction instanceof MiracleIRFork) {
                flag = false;
            }
        }
        boolean first = true;
        Set<MiracleIRBasicBlock> succBasicBlocks = block.getSuccBasicBlock();
        for (MiracleIRBasicBlock element : succBasicBlocks) {
            MiracleIRBasicBlock.Node forkInstruction = block.tail.getPrev();
            if (first && forkInstruction.instruction instanceof MiracleIRJump &&
                    ((MiracleIRJump) forkInstruction.instruction).block == element) {
                forkInstruction.remove();
            }
            element.accept(this);
            first = false;
        }
    }

    @Override
    public void visit(MiracleIRCall call) {
        if (call.returnRegister != null) {
            newBuffer.enroll(call.returnRegister);
        }
    }

    @Override
    public void visit(MiracleIRPrefixArithmetic prefixArithmetic) {
        newBuffer.enroll(prefixArithmetic.target);
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
        newBuffer.enroll(compare.target);
    }

    @Override
    public void visit(MiracleIRHeapAllocate allocate) {
        newBuffer.enroll(allocate.register);
    }
}
