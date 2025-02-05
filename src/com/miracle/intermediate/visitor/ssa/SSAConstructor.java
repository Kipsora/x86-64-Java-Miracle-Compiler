package com.miracle.intermediate.visitor.ssa;

import com.miracle.intermediate.Root;
import com.miracle.intermediate.instruction.PhiInstruction;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.number.VirtualRegister;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;
import com.miracle.intermediate.visitor.BaseIRVisitor;

import java.util.*;

public class SSAConstructor extends BaseIRVisitor {
    private static class SSAId{
        final Stack<VirtualRegister> stack;
        int counter;

        private SSAId() {
            this.stack = new Stack<>();
        }
    }

    private List<BasicBlock> reversePostOrder;
    private Set<VirtualRegister> globals;
    private Map<VirtualRegister, SSAId> mapRegToSSAId;
    private Map<VirtualRegister, Set<BasicBlock>> blockContain;
    private Function function;

    @Override
    public void visit(Root ir) {
        ir.globalFunction.forEach((key, value) -> value.accept(this));
    }

    private void getReversePostOrder() {
        reversePostOrder = new LinkedList<>(function.getPostOrder());
        Collections.reverse(reversePostOrder);
    }

    /*
     * Keith D. Cooper et al. A Simple, Fast Dominance Algorithm
     * Figure 3. The Engineered Algorithm
     */
    private BasicBlock getIntersect(BasicBlock finger1, BasicBlock finger2) {
        while (finger1 != finger2) {
            while (finger1.getDfsOrder() < finger2.getDfsOrder()) {
                finger1 = finger1.getIdom();
            }
            while (finger2.getDfsOrder() < finger1.getDfsOrder()) {
                finger2 = finger2.getIdom();
            }
        }
        return finger1;
    }

    private void calcDominance() {
        BasicBlock startNode = function.getEntryBasicBlock();
        startNode.setIdom(startNode);
        boolean changed = true;
        while (changed) {
            changed = false;
            for (BasicBlock block : reversePostOrder) {
                if (block == startNode) continue;
                BasicBlock newIdom = null;
                for (BasicBlock predecessor : block.getPredBasicBlock()) {
                    if (predecessor.getIdom() != null) {
                        newIdom = predecessor;
                        break;
                    }
                }
                if (newIdom == null) {
                    throw new RuntimeException("Failed to find new IDOM");
                }
                BasicBlock oldPredecessor = newIdom;
                for (BasicBlock predecessor : block.getPredBasicBlock()) {
                    if (predecessor != oldPredecessor && predecessor.getIdom() != null) {
                        newIdom = getIntersect(predecessor, newIdom);
                    }
                }
                if (block.getIdom() != newIdom) {
                    block.setIdom(newIdom);
                    changed = true;
                }
            }
        }
        reversePostOrder.stream().filter(e -> e != startNode)
                .forEach(startNode.getIdom().DTChildren::add);
    }

    /*
     * Keith D. Cooper et al. A Simple, Fast Dominance Algorithm
     * Figure 5: The Dominance-Frontier Algorithm
     */
    private void calcDominanceFrontier() {
        List<BasicBlock> blocks = function.getPostOrder();
        for (BasicBlock block : blocks) {
            if (block.getPredBasicBlock().size() > 1) {
                for (BasicBlock predecessor : block.getPredBasicBlock()) {
                    BasicBlock runner = predecessor;
                    while (runner != block.getIdom()) {
                        runner.addToDFSet(block);
                        runner = runner.getIdom();
                    }
                }
            }
        }
    }

    /*
     * Engineering a Compiler (2nd edition)
     * Chapter 9 Data-Flow Analysis Figure 9.9(a) Finding Global Names
     */
    public void findGlobalNames() {
        blockContain = new HashMap<>();
        globals = new HashSet<>();
        for (BasicBlock block : reversePostOrder) {
            Set<VirtualRegister> varKill = new HashSet<>();
            if (block == function.getEntryBasicBlock()) {
                for (Register arg : function.parameters) {
                    varKill.add((VirtualRegister) arg);
                    if (!blockContain.containsKey(arg)) {
                        blockContain.put((VirtualRegister) arg, new HashSet<>());
                    }
                    blockContain.get(arg).add(block);
                }
            }
            for (BasicBlock.Node it = block.getHead(); it != block.tail; it = it.getSucc()) {
                it.instruction.getUseNumbers().stream()
                        .filter(element -> element instanceof VirtualRegister && !varKill.contains(element))
                        .forEach(element -> globals.add((VirtualRegister) element));
                it.instruction.getDefNumbers().forEach(element -> {
                    if (element instanceof VirtualRegister) {
                        varKill.add((VirtualRegister) element);
                        if (!blockContain.containsKey(element)) {
                            blockContain.put((VirtualRegister) element, new HashSet<>());
                        }
                        blockContain.get(element).add(block);
                    }
                });
            }
        }
    }

    /*
     * Engineering a Compiler (2nd edition)
     * Chapter 9 Data-Flow Analysis Figure 9.9(b) Rewriting the Code/Insert phi-instruction
     */
    private void insertPHIInsturction() {
        globals.forEach(key -> {
            Queue<BasicBlock> workList = new ArrayDeque<>(blockContain.get(key));
            Set<BasicBlock> visited = new HashSet<>();
            while (!workList.isEmpty()) {
                BasicBlock element = workList.remove();
                if (visited.contains(element)) continue;
                visited.add(element);
                for (BasicBlock d : element.dfSet) {
                    if (!d.phis.containsKey(key)) {
                        d.phis.put(key, new PhiInstruction(key));
                        workList.add(d);
                    }
                }
            }
        });
    }

    /*
     * Engineering a Compiler (2nd edition)
     * Chapter 9 Data-Flow Analysis Figure 9.12 Renaming After phi-Insertion
     */
    private void putSSAName(VirtualRegister register, Map<VirtualRegister, VirtualRegister> map) {
        if (!mapRegToSSAId.containsKey(register)) {
            mapRegToSSAId.put(register, new SSAId());
        }
        VirtualRegister ssaName = register.newName("_ssa" + String.valueOf(mapRegToSSAId.get(register).counter++));
        map.put(register, ssaName);
        mapRegToSSAId.get(register).stack.push(ssaName);
    }

    private void renameRegisters(BasicBlock block) {
        Map<VirtualRegister, VirtualRegister> renameMap = new HashMap<>();
        function.parameters.forEach(element -> putSSAName((VirtualRegister) element, renameMap));
        function.rename(renameMap);
        block.phis.forEach((key, value) -> putSSAName(key, renameMap));
        block.phis.forEach((key, value) -> value.rename(renameMap));
        for (BasicBlock.Node it = block.getHead(); it != block.tail; it = it.getSucc()) {
            it.instruction.getUseNumbers().forEach(element -> {
                if (element instanceof VirtualRegister) {
                    renameMap.put((VirtualRegister) element, mapRegToSSAId.get(element).stack.peek());
                }
            });
            it.instruction.getDefNumbers().stream()
                    .filter(element -> element instanceof VirtualRegister)
                    .forEach(element -> putSSAName((VirtualRegister) element, renameMap));
            it.instruction.rename(renameMap);
        }
        for (BasicBlock successor : block.getSuccBasicBlock()) {
            successor.phis.forEach((key, value) ->
                    value.args.put(block, mapRegToSSAId.get(key).stack.peek())
            );
        }
        block.DTChildren.forEach(this::renameRegisters);
        block.phis.forEach((key, value) ->
                value.getDefNumbers().forEach(element -> {
                    if (element instanceof VirtualRegister) {
                        mapRegToSSAId.get(((VirtualRegister) element).getOldName()).stack.pop();
                    }
                })
        );
        for (BasicBlock.Node it = block.getHead(); it != block.tail; it = it.getSucc()) {
            it.instruction.getDefNumbers().forEach(element -> {
                if (element instanceof VirtualRegister) {
                    mapRegToSSAId.get(((VirtualRegister) element).getOldName()).stack.pop();
                }
            });
        }
    }

    @Override
    public void visit(Function function) {
        this.function = function;
        getReversePostOrder();
        calcDominance();
        calcDominanceFrontier();
        findGlobalNames();
        insertPHIInsturction();
        mapRegToSSAId = new HashMap<>();
        renameRegisters(function.getEntryBasicBlock());
    }
}
