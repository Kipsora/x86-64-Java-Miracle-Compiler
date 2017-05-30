package com.miracle.intermediate.visitor;

import com.miracle.intermediate.Root;
import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.VirtualRegister;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;

import java.util.*;

public class LivelinessAnalyser extends BaseIRVisitor {
    @Override
    public void visit(Root ir) {
        ir.globalFunction.forEach((key, value) -> value.accept(this));
    }

    @Override
    public void visit(Function function) {
        List<BasicBlock> blocks = new LinkedList<>(function.getPostOrder());
        for (BasicBlock block : blocks) {
            block.liveliness.used = new ArrayList<>();
            block.liveliness.defined = new ArrayList<>();
            if (block.isFunctionEntryBlock()) {
                function.parameters.forEach(element -> {
                    block.liveliness.defined.add((VirtualRegister) element);
                });
            }
            for (BasicBlock.Node it = block.getHead(); it != block.tail; it = it.getSucc()) {
                for (Number register : it.instruction.getUseNumbers()) {
                    if (register instanceof VirtualRegister && !block.liveliness.defined.contains(register)) {
                        block.liveliness.used.add((VirtualRegister) register);
                    }
                }
                for (Number register : it.instruction.getDefNumbers()) {
                    if (register instanceof VirtualRegister) {
                        block.liveliness.defined.add((VirtualRegister) register);
                    }
                }
            }
        }
        for (BasicBlock block : blocks) {
            block.liveliness.liveIn = new HashSet<>();
            block.liveliness.liveOut = new HashSet<>();
        }
        while (true) {
            for (BasicBlock block : blocks) {
                block.liveliness.liveIn = new HashSet<VirtualRegister>() {{
                    block.liveliness.liveOut.forEach(this::add);
                    block.liveliness.defined.forEach(this::remove);
                    block.liveliness.used.forEach(this::add);
                }};
            }
            boolean modified = false;
            for (BasicBlock block : blocks) {
                Set<VirtualRegister> origin = block.liveliness.liveOut;
                block.liveliness.liveOut = new HashSet<VirtualRegister>() {{
                    for (BasicBlock successor : block.getSuccBasicBlock()) {
                        addAll(successor.liveliness.liveIn);
                    }
                }};
                if (!block.liveliness.liveOut.equals(origin)) {
                    modified = true;
                }
            }
            if (!modified) {
                break;
            }
        }
    }
}
