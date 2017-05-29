package com.miracle.intermediate.visitor;

import com.miracle.MiracleOption;
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
import com.miracle.intermediate.number.Immediate;
import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.PhysicalRegister;
import com.miracle.intermediate.number.StackRegister;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;

import java.util.HashSet;
import java.util.Set;

public class LLIRTransformer implements IRVisitor {
    private Set<BasicBlock> blockProcessed;
    private Function curFunction;
    private BasicBlock.Node node;

    private void enroll(Number register) {
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
        curFunction.getEntryBasicBlock().accept(this);
        curFunction = null;
    }

    @Override
    public void visit(BasicBlock block) {
        if (blockProcessed.contains(block)) return;
        blockProcessed.add(block);

        if (block.isFunctionEntryBlock()) {
            for (int i = 0; i < curFunction.parameters.size() && i < MiracleOption.CallingConvention.size(); i++) {
                if (!(curFunction.parameters.get(i) instanceof PhysicalRegister) ||
                        !((PhysicalRegister) curFunction.parameters.get(i)).indexName.equals(MiracleOption.CallingConvention.get(i))) {
                    block.getHead().prepend(new Move(
                            curFunction.parameters.get(i),
                            PhysicalRegister.getBy16BITName(MiracleOption.CallingConvention.get(i), curFunction.parameters.get(i).size)
                    ));
                }
            }
        }

        for (node = block.getHead(); node != block.tail; node = node.getSucc()) {
            node.instruction.getUseNumbers().forEach(this::enroll);
            node.instruction.getDefNumbers().forEach(this::enroll);
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
        if (irReturn.getValue() instanceof Immediate) {
            node.prepend(new Move(
                    PhysicalRegister.getBy16BITName("RAX", irReturn.getValue().getNumberSize()),
                    irReturn.getValue()
            ));
        }
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
