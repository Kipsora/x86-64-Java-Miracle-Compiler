package com.miracle.intermediate.visitor;

import com.miracle.intermediate.Root;
import com.miracle.intermediate.instruction.Call;
import com.miracle.intermediate.instruction.Compare;
import com.miracle.intermediate.instruction.HeapAllocate;
import com.miracle.intermediate.instruction.Move;
import com.miracle.intermediate.instruction.arithmetic.BinaryArithmetic;
import com.miracle.intermediate.instruction.arithmetic.UnaryArithmetic;
import com.miracle.intermediate.instruction.fork.Jump;
import com.miracle.intermediate.instruction.fork.Return;
import com.miracle.intermediate.instruction.fork.UnaryBranch;
import com.miracle.intermediate.number.*;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SimpleAllocator extends BaseIRVisitor {
    private Set<BasicBlock> blockProcessed;
    private BasicBlock.Node curInstruction;
    private Function curFunction;
    private Set<String> freePhysicalRegisters;
    private Map<VirtualRegister, StackRegister> mapToStack;
    private Set<IndirectRegister> moveOnRelease;
    private Map<IndirectRegister, PhysicalRegister> quickAccessToIndirect;
    private Map<PhysicalRegister, IndirectRegister> busyPhysicalRegisters;
    private int spillSize;

    @Override
    public void visit(Root ir) {
        blockProcessed = new HashSet<>();
        ir.globalFunction.forEach((key, value) -> value.accept(this));
    }

    @Override
    public void visit(Function function) {
        curFunction = function;
        mapToStack = new HashMap<>();
        spillSize = 0;
        freePhysicalRegisters = new HashSet<>(PhysicalRegister.GeneralPurposeRegister);
        busyPhysicalRegisters = new HashMap<>();
        quickAccessToIndirect = new HashMap<>();
        moveOnRelease = new HashSet<>();
        function.parameters.forEach(this::realize);
        for (int i = 0; i < function.parameters.size(); i++) {
            if (function.parameters.get(i) instanceof VirtualRegister) {
                function.parameters.set(i, mapToStack.get(function.parameters.get(i)));
            }
        }
        function.getEntryBasicBlock().accept(this);
        curFunction = null;
    }

    private void release(PhysicalRegister physicalRegister) {
        IndirectRegister indirectRegister = busyPhysicalRegisters.get(physicalRegister);
        if (moveOnRelease.contains(indirectRegister)) {
            curInstruction.prepend(new Move(indirectRegister, physicalRegister));
            moveOnRelease.remove(indirectRegister);
        }
        busyPhysicalRegisters.remove(physicalRegister);
        quickAccessToIndirect.remove(indirectRegister);
        freePhysicalRegisters.add(physicalRegister.indexName);
    }

    private void use(PhysicalRegister physicalRegister,
                     IndirectRegister indirectRegister,
                     boolean moveOnUse,
                     boolean moveOnRelease) {
        if (moveOnUse) {
            curInstruction.prepend(new Move(physicalRegister, indirectRegister));
        }
        if (moveOnRelease) {
            this.moveOnRelease.add(indirectRegister);
        }
        busyPhysicalRegisters.put(physicalRegister, indirectRegister);
        quickAccessToIndirect.put(indirectRegister, physicalRegister);
        freePhysicalRegisters.remove(physicalRegister.indexName);
    }

    private void realize(Register register) {
        if (!(register instanceof VirtualRegister)) return;
        StackRegister newRegister = mapToStack.getOrDefault(register, null);
        if (newRegister == null) {
            newRegister = new StackRegister(register.size);
            mapToStack.put((VirtualRegister) register, newRegister);
        }
    }

    @Override
    public void visit(BasicBlock block) {
        if (blockProcessed.contains(block)) return;
        blockProcessed.add(block);
        for (BasicBlock.Node it = block.getHead(); it != block.tail; it = it.getSucc()) {
            curInstruction = it;
            it.instruction.getUsedRegisters().forEach(this::realize);
            it.instruction.rename(mapToStack);             // realize all virtual register
            it.instruction.rename(quickAccessToIndirect);
            it.instruction.accept(this);
            it.instruction.rename(quickAccessToIndirect);
            curInstruction = null;
        }
        block.getSuccBasicBlock().forEach(element -> element.accept(this));
    }

    @Override
    public void visit(BinaryArithmetic binaryArithmetic) {
        switch (binaryArithmetic.operator) {
            case MUL:
            case ADD:
            case OR:
            case SHL:
            case SHR:
            case AND:
            case SUB:
            case XOR:
                if (binaryArithmetic.getSource() instanceof IndirectRegister
                        && binaryArithmetic.getTarget() instanceof IndirectRegister) {
                    if (freePhysicalRegisters.isEmpty()) {
                        release(busyPhysicalRegisters.entrySet().iterator().next().getKey());
                    }
                    use(
                            PhysicalRegister.getBy16BITName(
                                    freePhysicalRegisters.iterator().next(),
                                    binaryArithmetic.getTarget().size
                            ),
                            (IndirectRegister) binaryArithmetic.getTarget(),
                            true, true
                    );
                }
                break;
            case MOD:
            case DIV:
                if (!(binaryArithmetic.getSource() instanceof PhysicalRegister) ||
                        !((PhysicalRegister) binaryArithmetic.getSource()).indexName.equals("RAX")) {
                    if (!freePhysicalRegisters.contains("RAX")) {
                        PhysicalRegister.getAllBy16BITName("RAX").forEach(element -> {
                            if (busyPhysicalRegisters.containsKey(element)) {
                                release(element);
                            }
                        });
                    }
                    if (!freePhysicalRegisters.contains("RDX")) {
                        PhysicalRegister.getAllBy16BITName("RDX").forEach(element -> {
                            if (busyPhysicalRegisters.containsKey(element)) {
                                release(element);
                            }
                        });
                    }
                }
                break;
            default:
                throw new RuntimeException("unsupported operator");
        }
    }

    @Override
    public void visit(Move move) {
        if (move.getSource() instanceof IndirectRegister &&
                move.getTarget() instanceof IndirectRegister) {
            if (freePhysicalRegisters.isEmpty()) {
                release(busyPhysicalRegisters.entrySet().iterator().next().getKey());
            }
            use(
                    PhysicalRegister.getBy16BITName(
                            freePhysicalRegisters.iterator().next(),
                            move.getTarget().size
                    ),
                    (IndirectRegister) move.getTarget(),
                    false, true
            );
        }
    }

    @Override
    public void visit(Call call) {

    }

    @Override
    public void visit(UnaryArithmetic unaryArithmetic) {

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
        if (compare.getSourceA() instanceof IndirectRegister
                && compare.getSourceB() instanceof IndirectRegister) {
            if (freePhysicalRegisters.isEmpty()) {
                release(busyPhysicalRegisters.entrySet().iterator().next().getKey());
            }
            use(
                    PhysicalRegister.getBy16BITName(
                            freePhysicalRegisters.iterator().next(),
                            compare.getTarget().size
                    ),
                    (IndirectRegister) compare.getTarget(),
                    true, true
            );
        }
    }

    @Override
    public void visit(HeapAllocate allocate) {

    }
}
