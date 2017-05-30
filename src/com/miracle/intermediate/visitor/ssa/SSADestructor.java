package com.miracle.intermediate.visitor.ssa;

import com.miracle.intermediate.Root;
import com.miracle.intermediate.instruction.Move;
import com.miracle.intermediate.instruction.fork.Branch;
import com.miracle.intermediate.number.VirtualRegister;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;
import com.miracle.intermediate.visitor.BaseIRVisitor;

import java.util.*;

public class SSADestructor extends BaseIRVisitor {
    private static class ParallelCopy {
        public final VirtualRegister target;
        public final VirtualRegister source;

        private ParallelCopy(VirtualRegister target, VirtualRegister source) {
            this.target = target;
            this.source = source;
        }
    }

    private Map<BasicBlock, List<ParallelCopy>> mapBlockPCList;
    private List<BasicBlock> reversePostOrder;
    private Function function;
    private int countBlock;

    private void getReversePostOrder() {
        reversePostOrder = new LinkedList<>(function.getPostOrder());
        Collections.reverse(reversePostOrder);
    }

    @Override
    public void visit(Root ir) {
        countBlock = 0;
        ir.globalFunction.forEach((key, value) -> value.accept(this));
    }

    /*
     * SSA Book: http://ssabook.gforge.inria.fr/latest/book.pdf
     * Algorithm 3.5: Critical Edge Splitting Algorithm for making
     * non-conventional SSA form conventional
     */
    private void makeParallelCopy() {
        for (BasicBlock block : reversePostOrder) {
            // place `new' here to avoid newly added blocks to be processed
            for (BasicBlock predecessor : new HashSet<>(block.getPredBasicBlock())) {
                Set<BasicBlock> workList = predecessor.getSuccBasicBlock();
                if (workList.size() > 1) { // has several outgoing edges
                    if (!(predecessor.tail.getPrev().instruction instanceof Branch)) {
                        throw new RuntimeException("unexpected case");
                    }
                    BasicBlock splitBlock = ((Branch) predecessor.tail.getPrev().instruction)
                            .splitBlock(predecessor, block, "bk_" + String.valueOf(countBlock++));
                    mapBlockPCList.put(splitBlock, new LinkedList<>());
                } else {
                    mapBlockPCList.put(predecessor, new LinkedList<>());
                }
            }
            block.phis.forEach((defVar, phi) -> phi.args.forEach((from, useVar) -> {
                VirtualRegister target = phi.getRegister();
                VirtualRegister source = useVar;
                if (source != target && source != null) {
                    mapBlockPCList.get(from).add(new ParallelCopy(target, source));
                }
            }));
            block.phis.clear();
        }
    }

    /*
     * SSA Book: http://ssabook.gforge.inria.fr/latest/book.pdf
     * Algorithm 22.6: Parallel copy sequentialization algorithm
     */
    private void replaceWithSEQCopies() {
        for (BasicBlock block : reversePostOrder) {
            Queue<VirtualRegister> ready = new LinkedList<>();
            Queue<VirtualRegister> todos = new LinkedList<>();
            List<ParallelCopy> copies = mapBlockPCList.get(block);
            Map<VirtualRegister, VirtualRegister> location = new HashMap<>();
            Map<VirtualRegister, VirtualRegister> predecessor = new HashMap<>();
            if (copies == null) continue;
            copies.forEach((element) -> {
                location.put(element.source, element.source);
                predecessor.put(element.target, element.source);
                todos.add(element.target);
            });
            copies.forEach((element) -> {
                if (location.containsKey(element.target)) {
                    ready.add(element.target);
                }
            });
            while (!todos.isEmpty()) {
                while (!ready.isEmpty()) {
                    VirtualRegister b = ready.remove();
                    VirtualRegister a = predecessor.get(b);
                    VirtualRegister c = location.get(a);
                    block.tail.getSucc().prepend(new Move(b, c));
                    if (a == c && predecessor.containsKey(a)) {
                        ready.add(a);
                    }
                }
                VirtualRegister b = todos.remove();
                if (b == location.get(predecessor.get(b))) {
                    VirtualRegister n = new VirtualRegister("tmp_reg", b.size);
                    block.tail.getSucc().prepend(new Move(n, b));
                    location.put(b, n);
                    ready.add(b);
                }
            }
        }
    }

    @Override
    public void visit(Function function) {
        this.function = function;
        getReversePostOrder();
        this.mapBlockPCList = new HashMap<>();
        getReversePostOrder();
        makeParallelCopy();
        replaceWithSEQCopies();
    }
}
