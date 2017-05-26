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
import com.miracle.intermediate.number.*;
import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;

import java.util.HashSet;
import java.util.Set;

public class LLTransformer implements IRVisitor {
    private Set<BasicBlock> blockProcessed;
    private Function curFunction;
    private int countTmpVars;

    @Override
    public void visit(Root ir) {
        countTmpVars = 0;
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
        for (BasicBlock.Node it = block.getHead(); it != block.tail; it = it.getSucc()) {
            if (it.instruction instanceof HeapAllocate) { // malloc <size> is supported only
                it.remove();
                Number number;
                if (((HeapAllocate) it.instruction).getNumber() instanceof Immediate) {
                    int totalMemory = ((HeapAllocate) it.instruction).size *
                            ((Immediate) ((HeapAllocate) it.instruction).getNumber()).value;
                    number = new Immediate(
                            totalMemory,
                            ((HeapAllocate) it.instruction).getNumber().getNumberSize()
                    );
                } else {
                    number = new VirtualRegister(
                            ".aux" + String.valueOf(countTmpVars++),
                            ((HeapAllocate) it.instruction).getNumber().getNumberSize()
                    );
                    it.getSucc().prepend(new Move(
                            (Register) number,
                            ((HeapAllocate) it.instruction).getNumber()
                    ));
                    it.getSucc().prepend(new BinaryArithmetic(
                            BinaryArithmetic.Types.MUL, (Register) number,
                            new Immediate(
                                    ((HeapAllocate) it.instruction).size,
                                    ((HeapAllocate) it.instruction).getNumber().getNumberSize()
                            )
                    ));
                }
                it.getSucc().prepend(new HeapAllocate(((HeapAllocate) it.instruction).getRegister(), 1, number));
            } else if (it.instruction instanceof UnaryBranch) {
                it.append(new Jump(((UnaryBranch) it.instruction).branchFalse));
            } else if (it.instruction instanceof Compare) {
                if ((((Compare) it.instruction).getSourceA() instanceof IndirectRegister &&
                        ((Compare) it.instruction).getSourceB() instanceof IndirectRegister)) {
                    VirtualRegister register = new VirtualRegister(
                            ".aux" + String.valueOf(countTmpVars++),
                            ((IndirectRegister) ((Compare) it.instruction).getSourceA()).size
                    );
                    it.prepend(new Move(register, ((Compare) it.instruction).getSourceA()));
                    it.remove();
                    it.getPrev().append(new Compare(
                            ((Compare) it.instruction).operator,
                            register,
                            ((Compare) it.instruction).getSourceB(),
                            ((Compare) it.instruction).getTarget()
                    ));
                }
                if ((((Compare) it.instruction).getSourceA() instanceof Immediate &&
                        ((Compare) it.instruction).getSourceB() instanceof Immediate)) {
                    VirtualRegister register = new VirtualRegister(
                            ".aux" + String.valueOf(countTmpVars++),
                            ((Immediate) ((Compare) it.instruction).getSourceA()).size
                    );
                    it.prepend(new Move(register, ((Compare) it.instruction).getSourceA()));
                    it.remove();
                    it.getPrev().append(new Compare(
                            ((Compare) it.instruction).operator,
                            register,
                            ((Compare) it.instruction).getSourceB(),
                            ((Compare) it.instruction).getTarget()
                    ));
                }
            } else if (it.instruction instanceof BinaryBranch) {
                it.append(new Jump(((BinaryBranch) it.instruction).branchFalse));
            }
        }
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
