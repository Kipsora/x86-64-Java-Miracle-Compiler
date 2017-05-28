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
import com.miracle.intermediate.number.*;
import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;

import java.util.*;

public class SimpleAllocator implements IRVisitor {
    private Set<BasicBlock> blockProcessed;
    private Map<Number, Register> globalRenameMap;
    private Map<Number, Register> localRenameMap;
    private BasicBlock.Node node;
    private Function curFunction;


    @Override
    public void visit(Root ir) {
        blockProcessed = new HashSet<>();
        ir.globalFunction.forEach((key, value) -> value.accept(this));
    }

    @Override
    public void visit(BinaryArithmetic binaryArithmetic) {
        PhysicalRegister register;
        if (binaryArithmetic.operator.equals(BinaryArithmetic.Types.DIV)) {
            register = PhysicalRegister.getBy16BITName("RAX", binaryArithmetic.getTarget().getNumberSize());
            node.append(new Move(binaryArithmetic.getTarget(), register));
            localRenameMap.put(binaryArithmetic.getTarget(), register);
        } else if (binaryArithmetic.operator.equals(BinaryArithmetic.Types.MOD)) {
            register = PhysicalRegister.getBy16BITName("RAX", binaryArithmetic.getTarget().getNumberSize());
            node.append(new Move(binaryArithmetic.getTarget(), register));
            localRenameMap.put(binaryArithmetic.getTarget(), register);
        } else if (binaryArithmetic.operator.equals(BinaryArithmetic.Types.MUL)) {
            register = PhysicalRegister.getBy16BITName("RAX", binaryArithmetic.getTarget().getNumberSize());
            node.prepend(new Move(register, binaryArithmetic.getTarget()));
            localRenameMap.put(binaryArithmetic.getTarget(), register);
        } else if (binaryArithmetic.operator.equals(BinaryArithmetic.Types.SHL) ||
                binaryArithmetic.operator.equals(BinaryArithmetic.Types.SHR)) {
            throw new RuntimeException("uncompleted case");
        } else {
            if (binaryArithmetic.getSource() instanceof IndirectRegister &&
                    binaryArithmetic.getTarget() instanceof IndirectRegister) {
                register = PhysicalRegister.getBy16BITName("RAX", binaryArithmetic.getSource().getNumberSize());
                node.prepend(new Move(register, binaryArithmetic.getSource()));
                localRenameMap.put(binaryArithmetic.getSource(), register);
            }
        }
    }

    @Override
    public void visit(Move move) {
        PhysicalRegister register;
        if (move.getSource() instanceof IndirectRegister &&
                move.getTarget() instanceof IndirectRegister) {
            register = PhysicalRegister.getBy16BITName("RAX", move.getSource().getNumberSize());
            node.prepend(new Move(register, move.getSource()));
            localRenameMap.put(move.getSource(), register);
        }
    }

    @Override
    public void visit(Function function) {
        globalRenameMap = new HashMap<>();
        curFunction = function;
        function.parameters.forEach(element -> {
            if (!globalRenameMap.containsKey(element)) {
                globalRenameMap.put(element, new StackRegister(element.size));
            }
        });
        function.map(globalRenameMap);
        function.getEntryBasicBlock().accept(this);
    }

    private void reAllocateOffsetRegister(Number number) {
        if (!(number instanceof OffsetRegister)) return;
        OffsetRegister register = (OffsetRegister) number;
        if (register.getRawBase() instanceof IndirectRegister) {
            PhysicalRegister r14 = PhysicalRegister.getBy16BITName("R14", register.getRawBase().size);
            node.prepend(new Move(r14, register.getRawBase()));
            localRenameMap.put(register.getRawBase(), r14);
        }
        if (register.getRawOffsetB() instanceof IndirectRegister) {
            PhysicalRegister r15 = PhysicalRegister.getBy16BITName("R15", register.getRawOffsetB().size);
            node.prepend(new Move(r15, register.getRawOffsetB()));
            localRenameMap.put(register.getRawOffsetB(), r15);
        }
    }

    @Override
    public void visit(BasicBlock block) {
        if (blockProcessed.contains(block)) return;
        blockProcessed.add(block);

        /* Count how many possible virtual registers */
        Set<Object> totalRegisters = new HashSet<>();
        for (node = block.getHead(); node != block.tail; node = node.getSucc()) {
            node.instruction.getUseNumbers().stream()
                    .filter(element -> element instanceof VirtualRegister)
                    .forEach(totalRegisters::add);
            node.instruction.getDefNumbers().stream()
                    .filter(element -> element instanceof VirtualRegister)
                    .forEach(totalRegisters::add);
        }
        totalRegisters.addAll(PhysicalRegister.GeneralPurposeRegister);
        /* Scheme: allocate them on stack */
        totalRegisters.forEach(element -> {
            if (element instanceof VirtualRegister) {
                if (!globalRenameMap.containsKey(element)) {
                    globalRenameMap.put(
                            (VirtualRegister) element,
                            new StackRegister(((VirtualRegister) element).size)
                    );
                }
            }
        });
        for (node = block.getHead(); node != block.tail; node = node.getSucc()) {
            node.instruction.rename(globalRenameMap);
        }
        /*
         * Due to all of them are actually on stack,
         * thus all registers can be used without hesitation
         */
        /* Adjust invalid operands */
        for (node = block.getHead(); node != block.tail; node = node.getSucc()) {
            localRenameMap = new HashMap<>();
            node.instruction.accept(this);
            node.instruction.rename(localRenameMap);
        }
        for (node = block.getHead(); node != block.tail; node = node.getSucc()) {
            localRenameMap = new HashMap<>();
            node.instruction.getUseNumbers().stream()
                    .filter(element -> element instanceof OffsetRegister)
                    .forEach(this::reAllocateOffsetRegister);
            node.instruction.getDefNumbers().stream()
                    .filter(element -> element instanceof OffsetRegister)
                    .forEach(this::reAllocateOffsetRegister);
            node.instruction.rename(localRenameMap);
        }
        localRenameMap = null;
        block.getSuccBasicBlock().forEach(this::visit);
    }

    @Override
    public void visit(Call call) {
        PhysicalRegister register;
        if (call.getReturnRegister() != null) {
            register = PhysicalRegister.getBy16BITName("RAX", call.getReturnRegister().size);
            node.append(new Move(call.getReturnRegister(), register));
            localRenameMap.put(call.getReturnRegister(), register);
        }
        List<Number> parameters = call.getReverseParameters();
        for (int i = 0; i < parameters.size() && i < MiracleOption.CallingConvention.size(); i++) {
            if (i < MiracleOption.CallingConvention.size() && parameters.get(i) instanceof IndirectRegister) {
                register = PhysicalRegister.getBy16BITName(
                        MiracleOption.CallingConvention.get(i),
                        parameters.get(i).getNumberSize()
                );
                node.prepend(new Move(register, parameters.get(i)));
                localRenameMap.put(parameters.get(i), register);
            }
        }
    }

    @Override
    public void visit(UnaryArithmetic prefixArithmetic) {

    }

    @Override
    public void visit(UnaryBranch unaryBranch) {

    }

    @Override
    public void visit(Return irReturn) {
        if (irReturn.getValue() != null) {
            PhysicalRegister register = PhysicalRegister.getBy16BITName("RAX", irReturn.getValue().getNumberSize());
            node.prepend(new Move(register, irReturn.getValue()));
            localRenameMap.put(irReturn.getValue(), register);
        }
    }

    @Override
    public void visit(Jump jump) {

    }

    @Override
    public void visit(Compare compare) {
        if (compare.getSourceA() instanceof IndirectRegister &&
                compare.getSourceB() instanceof IndirectRegister) {
            PhysicalRegister register = PhysicalRegister.getBy16BITName("RAX", compare.getSourceA().getNumberSize());
            node.prepend(new Move(register, compare.getSourceA()));
            localRenameMap.put(compare.getSourceA(), register);
        }
    }

    @Override
    public void visit(HeapAllocate allocate) {
        PhysicalRegister register;
        register = PhysicalRegister.getBy16BITName("RDI", allocate.getNumber().getNumberSize());
        node.prepend(new Move(register, allocate.getNumber()));
        localRenameMap.put(allocate.getNumber(), register);

        register = PhysicalRegister.getBy16BITName("RAX", allocate.getNumber().getNumberSize());
        node.append(new Move(allocate.getTarget(), register));
        localRenameMap.put(allocate.getTarget(), register);
    }

    @Override
    public void visit(BinaryBranch binaryBranch) {
        PhysicalRegister register;
        if (binaryBranch.getExpressionA() instanceof IndirectRegister &&
                binaryBranch.getExpressionB() instanceof IndirectRegister) {
            register = PhysicalRegister.getBy16BITName("RAX", binaryBranch.getExpressionA().getNumberSize());
            node.append(new Move(register, binaryBranch.getExpressionA()));
            localRenameMap.put(binaryBranch.getExpressionA(), register);
        }
    }
}
