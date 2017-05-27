package com.miracle.intermediate.visitor;

import com.miracle.MiracleOption;
import com.miracle.intermediate.Root;
import com.miracle.intermediate.instruction.Call;
import com.miracle.intermediate.instruction.Compare;
import com.miracle.intermediate.instruction.HeapAllocate;
import com.miracle.intermediate.instruction.Move;
import com.miracle.intermediate.instruction.arithmetic.BinaryArithmetic;
import com.miracle.intermediate.instruction.arithmetic.UnaryArithmetic;
import com.miracle.intermediate.instruction.fork.*;
import com.miracle.intermediate.number.*;
import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.*;

public class SimpleAllocator implements IRVisitor {
    private Set<String> freePhysicalRegisters;

    private Map<Register, Register> accessToMemStack;
    private Map<Register, Register> accessToRegister;
    private Map<Register, Register> currentRenameMap;
    private Map<String, ImmutablePair<Register, Boolean>> occupiedRegister;

    private BasicBlock.Node curInstruction;
    private Function curFunction;
    private Set<BasicBlock> blockProcessed;

    @Override
    public void visit(Root ir) {
        blockProcessed = new HashSet<>();
        ir.globalFunction.forEach((key, value) -> value.accept(this));
    }

    @Override
    public void visit(BinaryArithmetic binaryArithmetic) {
        if (binaryArithmetic.operator.equals(BinaryArithmetic.Types.DIV) ||
                binaryArithmetic.operator.equals(BinaryArithmetic.Types.MOD)) {
            release("RDX");
            release("RAX");
        } else {
            allocate(binaryArithmetic.getSource(), null, true, false);
        }
    }

    @Override
    public void visit(Move move) {
        if (move.getTarget() instanceof IndirectRegister &&
                move.getSource() instanceof IndirectRegister) {
            allocate(move.getTarget(), null, false, true);
        }
    }

    @Override
    public void visit(Function function) {
        curFunction = function;
        accessToMemStack = new HashMap<>();
        function.getEntryBasicBlock().accept(this);
        accessToMemStack = null;
        curFunction = null;
    }

    private void mapToStack(Register register) {
        if (register instanceof VirtualRegister) {
            if (accessToMemStack.containsKey(register)) return;
            accessToMemStack.put(register, new StackRegister(register.size));
        }
    }

    private void allocate(Number source, String target, boolean moveOnAlloc, boolean moveOnRelease) {
        if (source instanceof VirtualRegister) {
            throw new RuntimeException("unexpected case");
        }
        if (target == null && !(source instanceof IndirectRegister)) return;
        if (source instanceof PhysicalRegister && ((PhysicalRegister) source).indexName.equals(target)) return;
        if (freePhysicalRegisters.isEmpty() || (target != null && !freePhysicalRegisters.contains(target))) {
            if (target == null) {
                target = occupiedRegister.entrySet().iterator().next().getKey();
            }
            release(target);
            freePhysicalRegisters.add(target);
            occupiedRegister.remove(target);
        }
        if (target == null) {
            target = freePhysicalRegisters.iterator().next();
        }
        freePhysicalRegisters.remove(target);
        if (source instanceof Register) {
            occupiedRegister.put(target, ImmutablePair.of((Register) source, moveOnRelease));
            accessToRegister.put((Register) source, PhysicalRegister.getBy16BITName(target, ((Register) source).size));
            currentRenameMap.put((Register) source, PhysicalRegister.getBy16BITName(target, ((Register) source).size));
            if (moveOnAlloc) {
                curInstruction.prepend(new Move(PhysicalRegister.getBy16BITName(target, ((Register) source).size), source));
            }
        } else {
            occupiedRegister.put(target, null);
        }
    }

    private void reAllocOffsetRegister(Register register) {
        if (register instanceof OffsetRegister) {
            allocate(((OffsetRegister) register).getRawBase(), null, true, false);
            allocate(((OffsetRegister) register).getRawOffsetB(), null, true, false);
        }
    }

    @Override
    public void visit(BasicBlock block) {
        if (blockProcessed.contains(block)) return;
        blockProcessed.add(block);
        freePhysicalRegisters = new HashSet<>(PhysicalRegister.GeneralPurposeRegister);

        accessToRegister = new HashMap<>();
        occupiedRegister = new HashMap<>();

        if (block.isFunctionEntryBlock) {
            curFunction.parameters.forEach(this::mapToStack);
            List<Register> parameters = curFunction.getReverseParameters();
            for (int i = 0, size = parameters.size(); i < size && i < MiracleOption.CallingConvention.size(); i++) {
                allocate(accessToMemStack.get(parameters.get(i)), MiracleOption.CallingConvention.get(i), false, false);
            }
        }

        for (curInstruction = block.getHead(); curInstruction != block.tail; curInstruction = curInstruction.getSucc()) {
            currentRenameMap = new HashMap<>();
            curInstruction.instruction.getUseRegisters().forEach(this::mapToStack);
            curInstruction.instruction.getDefRegisters().forEach(this::mapToStack);
            curInstruction.instruction.rename(accessToMemStack);
            curInstruction.instruction.rename(accessToRegister);
            curInstruction.instruction.getUseRegisters().forEach(this::reAllocOffsetRegister);
            curInstruction.instruction.getDefRegisters().forEach(this::reAllocOffsetRegister);
            curInstruction.instruction.rename(currentRenameMap);
            curInstruction.instruction.accept(this);
            curInstruction.instruction.rename(currentRenameMap);
            if (curInstruction.instruction instanceof Fork) {
                occupiedRegister.forEach((key, value) -> force_release(key));
                break;
            }
        }
        block.getSuccBasicBlock().forEach(this::visit);
    }

    @Override
    public void visit(Call call) {
        release("RAX");
        List<Number> parameters = call.getReverseParameters();
        for (int i = 0, size = parameters.size(); i < size && i < MiracleOption.CallingConvention.size(); i++) {
            release(MiracleOption.CallingConvention.get(i));
        }
    }

    @Override
    public void visit(UnaryArithmetic prefixArithmetic) {
    }

    private void release(String target) {
        ImmutablePair<Register, Boolean> source = occupiedRegister.get(target);
        if (source == null) return;
        if (source.getRight()) {
            curInstruction.prepend(new Move(
                    source.getLeft(),
                    PhysicalRegister.getBy16BITName(target, source.getLeft().size)
            ));
        }
        accessToRegister.remove(source.getLeft());
    }

    private void force_release(String target) {
        ImmutablePair<Register, Boolean> source = occupiedRegister.get(target);
        if (source == null) return;
        curInstruction.prepend(new Move(
                source.getLeft(),
                PhysicalRegister.getBy16BITName(target, source.getLeft().size)
        ));
        accessToRegister.remove(source.getLeft());
    }

    @Override
    public void visit(UnaryBranch unaryBranch) {
    }

    @Override
    public void visit(Return irReturn) {
        if (irReturn.getValue() != null) {
            release("RAX");
        }
    }

    @Override
    public void visit(Jump jump) {
        //jump.getUseRegisters().forEach(element -> allocate(element, null));
    }

    @Override
    public void visit(Compare compare) {
        if (compare.getSourceA() instanceof IndirectRegister &&
                compare.getSourceB() instanceof IndirectRegister) {
            allocate(compare.getSourceA(), null, true, false);
        }
    }

    @Override
    public void visit(HeapAllocate allocate) {
        release("RAX");
        release("RCX");
        release("RDX");
        release("RDI");
        release("RSI");
        release("R8");
        release("R9");
        release("R10");
        release("R11");
    }

    @Override
    public void visit(BinaryBranch binaryBranch) {

    }
}
