package com.miracle.intermediate.visitor.irtranser;

import com.miracle.MiracleOption;
import com.miracle.intermediate.Root;
import com.miracle.intermediate.instruction.Call;
import com.miracle.intermediate.instruction.HeapAllocate;
import com.miracle.intermediate.instruction.Move;
import com.miracle.intermediate.instruction.arithmetic.BinaryArithmetic;
import com.miracle.intermediate.instruction.fork.Return;
import com.miracle.intermediate.number.*;
import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;
import com.miracle.intermediate.visitor.BaseIRVisitor;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static com.miracle.intermediate.number.PhysicalRegister.RAX;

public class LLIRTransformer extends BaseIRVisitor {
    private Set<BasicBlock> blockProcessed;
    private Function curFunction;
    private BasicBlock.Node node;

    private void enroll(Number register) {
        if (register == null) {
            throw new RuntimeException("GG");
        }
        if (register instanceof PhysicalRegister) {
            curFunction.buffer.enroll(((PhysicalRegister) register));
        } else if (register instanceof StackRegister) {
            curFunction.buffer.enroll((StackRegister) register);
        } else if (register instanceof VirtualRegister) {
            enroll(((VirtualRegister) register).getRealName());
        }
    }

    @Override
    public void visit(Root ir) {
        blockProcessed = new HashSet<>();
        ir.globalFunction.forEach((key, value) -> value.accept(this));
        ir.globalFunction.forEach((key, curFunction) -> {
            for (BasicBlock block : curFunction.getPostOrder()) {
                Set<PhysicalRegister> live = new HashSet<PhysicalRegister>() {{
                    block.liveliness.liveOut.forEach(element -> {
                        if (element.getRealName() instanceof PhysicalRegister) {
                            add((PhysicalRegister) element.getRealName());
                        }
                    });
                }};
                for (node = block.tail.getPrev(); node != block.getHead().getPrev(); node = node.getPrev()) {
                    if (node.instruction instanceof Call) {
                        ((Call) node.instruction).callerSave.addAll(
                                ((Call) node.instruction).function.buffer.getCallerSaveRegisters()
                        );
                        for (int i = 0, size = ((Call) node.instruction).parameters.size(); i < size && i < MiracleOption.CallingConvention.size(); i++) {
                            ((Call) node.instruction).callerSave.add(
                                    PhysicalRegister.getBy16BITName(MiracleOption.CallingConvention.get(i), ((Call) node.instruction).parameters.get(i).getNumberSize())
                            );
                        }
                        ((Call) node.instruction).callerSave.retainAll(live);
                    } else if (node.instruction instanceof HeapAllocate) {
                        ((HeapAllocate) node.instruction).callerSave.retainAll(live);
                    }
                    node.instruction.getDefNumbers().forEach(element -> {
                        if (element instanceof VirtualRegister && ((VirtualRegister) element).getRealName() instanceof PhysicalRegister) {
                            live.remove(((VirtualRegister) element).getRealName());
                        }
                    });
                    node.instruction.getUseNumbers().forEach(element -> {
                        if (element instanceof VirtualRegister && ((VirtualRegister) element).getRealName() instanceof PhysicalRegister) {
                            live.add((PhysicalRegister) ((VirtualRegister) element).getRealName());
                        }
                    });
                }
            }
        });
    }

    @Override
    public void visit(Function function) {
        curFunction = function;
        function.parameters.forEach(this::enroll);
        curFunction.getEntryBasicBlock().accept(this);
        curFunction = null;
    }

    @Override
    public void visit(BasicBlock block) {
        if (blockProcessed.contains(block)) return;
        blockProcessed.add(block);

        for (node = block.tail.getPrev(); node != block.getHead().getPrev(); node = node.getPrev()) {
            node.instruction.getUseNumbers().forEach(this::enroll);
            node.instruction.getDefNumbers().forEach(this::enroll);
            node.instruction.accept(this);
            node.instruction.getUseNumbers().forEach(element -> {
                if (element instanceof OffsetRegister) {
                    if (((OffsetRegister) element).getRawBase() instanceof IndirectRegister ||
                            (((OffsetRegister) element).getRawBase() instanceof VirtualRegister &&
                                    ((VirtualRegister) ((OffsetRegister) element).getRawBase()).getRealName() instanceof IndirectRegister)) {
                        node.prepend(new Move(
                                PhysicalRegister.getBy16BITName("RAX", ((OffsetRegister) element).getRawBase().size),
                                ((OffsetRegister) element).getRawBase()
                        ));
                        ((OffsetRegister) element).setBase(PhysicalRegister.getBy16BITName("RAX", ((OffsetRegister) element).getBase().size));
                    }
                    if (((OffsetRegister) element).getRawOffsetB() instanceof IndirectRegister ||
                            (((OffsetRegister) element).getRawOffsetB() instanceof VirtualRegister &&
                                    ((VirtualRegister) ((OffsetRegister) element).getRawOffsetB()).getRealName() instanceof IndirectRegister)) {
                        node.prepend(new Move(
                                PhysicalRegister.getBy16BITName("RDX", ((OffsetRegister) element).getRawOffsetB().size),
                                ((OffsetRegister) element).getRawOffsetB()
                        ));
                        ((OffsetRegister) element).setOffsetB(PhysicalRegister.getBy16BITName("RDX", ((OffsetRegister) element).getRawOffsetB().size));
                    }
                }
            });
            node.instruction.getDefNumbers().forEach(element -> {
                if (element instanceof OffsetRegister) {
                    if (((OffsetRegister) element).getRawBase() instanceof IndirectRegister ||
                            (((OffsetRegister) element).getRawBase() instanceof VirtualRegister &&
                                    ((VirtualRegister) ((OffsetRegister) element).getRawBase()).getRealName() instanceof IndirectRegister)) {
                        node.prepend(new Move(
                                PhysicalRegister.getBy16BITName("RAX", ((OffsetRegister) element).getRawBase().size),
                                ((OffsetRegister) element).getRawBase()
                        ));
                        ((OffsetRegister) element).setBase(PhysicalRegister.getBy16BITName("RAX", ((OffsetRegister) element).getBase().size));
                    }
                    if (((OffsetRegister) element).getRawOffsetB() instanceof IndirectRegister ||
                            (((OffsetRegister) element).getRawOffsetB() instanceof VirtualRegister &&
                                    ((VirtualRegister) ((OffsetRegister) element).getRawOffsetB()).getRealName() instanceof IndirectRegister)) {
                        node.prepend(new Move(
                                PhysicalRegister.getBy16BITName("RDX", ((OffsetRegister) element).getRawOffsetB().size),
                                ((OffsetRegister) element).getRawOffsetB()
                        ));
                        ((OffsetRegister) element).setOffsetB(PhysicalRegister.getBy16BITName("RDX", ((OffsetRegister) element).getRawOffsetB().size));
                    }
                }
            });
        }
        block.getSuccBasicBlock().forEach(this::visit);
    }

    @Override
    public void visit(Return irReturn) {
        if (irReturn.getValue() != null) {
            node.prepend(new Move(
                    PhysicalRegister.getBy16BITName("RAX", irReturn.getValue().getNumberSize()),
                    irReturn.getValue()
            ));
        }
    }

    @Override
    public void visit(Move move) {
    }
}
