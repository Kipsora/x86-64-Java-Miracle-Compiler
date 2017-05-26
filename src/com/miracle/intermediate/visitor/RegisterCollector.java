package com.miracle.intermediate.visitor;

import com.miracle.intermediate.Root;
import com.miracle.intermediate.number.PhysicalRegister;
import com.miracle.intermediate.number.StackRegister;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;

import java.util.HashSet;
import java.util.Set;

public class RegisterCollector extends BaseIRVisitor {
    private Set<BasicBlock> blockProcessed;
    private Function curFunction;

    @Override
    public void visit(Root ir) {
        blockProcessed = new HashSet<>();
        ir.globalFunction.forEach((key, value) -> value.accept(this));
    }

    @Override
    public void visit(Function function) {
        curFunction = function;
        function.parameters.forEach(element -> {
            if (element instanceof StackRegister) {
                curFunction.buffer.enroll((StackRegister) element);
            }
        });
        function.getEntryBasicBlock().accept(this);
        curFunction = null;
    }

    @Override
    public void visit(BasicBlock block) {
        if (blockProcessed.contains(block)) return;
        blockProcessed.add(block);
        for (BasicBlock.Node it = block.getHead(); it != block.tail; it = it.getSucc()) {
            it.instruction.accept(this);
            it.instruction.getUsedRegisters().forEach(element -> {
                if (element instanceof PhysicalRegister) {
                    curFunction.buffer.enroll((PhysicalRegister) element);
                } else if (element instanceof StackRegister) {
                    curFunction.buffer.enroll((StackRegister) element);
                }
            });
        }
        block.getSuccBasicBlock().forEach(element -> element.accept(this));
    }
}
