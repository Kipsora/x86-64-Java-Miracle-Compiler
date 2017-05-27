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
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.number.StackRegister;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;

import java.util.HashSet;
import java.util.Set;

public class LLIRTransformer implements IRVisitor {
    private Set<BasicBlock> blockProcessed;
    private Function curFunction;

    private void enroll(Register register) {
        if (register instanceof PhysicalRegister) {
            curFunction.buffer.enroll(((PhysicalRegister) register).indexName);
        } else if (register instanceof StackRegister) {
            curFunction.buffer.enroll((StackRegister) register);
        }
    }

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
        function.parameters.forEach(this::enroll);
        curFunction.getEntryBasicBlock().accept(this);
        int size = curFunction.getReturns().size();
        curFunction.getReturns().forEach(element -> {
            element.append(new Jump(function.getExitBasicBlock()));
            if (((Return) element.instruction).getValue() != null) {
                element.append(new Move(
                        PhysicalRegister.getBy16BITName(
                                "RAX",
                                ((Return) element.instruction).getValue().getNumberSize()
                        ),
                        ((Return) element.instruction).getValue()
                ));
            }
            element.remove();
        });
        curFunction = null;
    }

    @Override
    public void visit(BasicBlock block) {
        if (blockProcessed.contains(block)) return;
        blockProcessed.add(block);
        for (BasicBlock.Node it = block.getHead(); it != block.tail; it = it.getSucc()) {
            it.instruction.getDefRegisters().forEach(this::enroll);
        }
        block.getSuccBasicBlock().forEach(this::visit);
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
