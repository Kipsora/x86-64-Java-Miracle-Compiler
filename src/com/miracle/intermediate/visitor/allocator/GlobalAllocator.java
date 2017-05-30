package com.miracle.intermediate.visitor.allocator;

import com.miracle.MiracleOption;
import com.miracle.intermediate.Root;
import com.miracle.intermediate.instruction.Instruction;
import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.number.StackRegister;
import com.miracle.intermediate.number.VirtualRegister;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;
import com.miracle.intermediate.visitor.BaseIRVisitor;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GlobalAllocator extends BaseIRVisitor {
    private int get16Multiplier(int totalSize) {
        return (totalSize + 15) / 16 * 16;
    }

    @Override
    public void visit(Root ir) {
        ir.globalFunction.forEach((key, value) -> value.accept(this));
    }

    @Override
    public void visit(Function function) {
        InterferenceGraph graph = new InterferenceGraph();
        for (BasicBlock block : function.getPostOrder()) {
            if (block.isFunctionEntryBlock()) {
                function.parameters.forEach(element -> graph.addVertex((VirtualRegister) element));
            }
            for (BasicBlock.Node it = block.getHead(); it != block.tail; it = it.getSucc()) {
                it.instruction.getDefNumbers().forEach(element -> {
                    if (element instanceof VirtualRegister) {
                        graph.addVertex((VirtualRegister) element);
                    }
                });
                it.instruction.getUseNumbers().forEach(element -> {
                    if (element instanceof VirtualRegister) {
                        graph.addVertex((VirtualRegister) element);
                    }
                });
            }
        }
        for (BasicBlock block : function.getPostOrder()) {
            Set<VirtualRegister> live = new HashSet<VirtualRegister>() {{
                block.liveliness.liveOut.forEach(this::add);
            }};
            for (BasicBlock.Node it = block.tail.getPrev(); it != block.getHead().getPrev(); it = it.getPrev()) {
                Instruction instruction = it.instruction;
                for (Number register : instruction.getDefNumbers()) {
                    if (register instanceof VirtualRegister) {
                        live.forEach(element -> graph.setForbidden((VirtualRegister) register, element));
                    }
                }
                instruction.getDefNumbers().forEach(element -> {
                    if (element instanceof VirtualRegister) {
                        live.remove(element);
                    }
                });
                instruction.getUseNumbers().forEach(element -> {
                    if (element instanceof VirtualRegister) {
                        live.add((VirtualRegister) element);
                    }
                });
            }
            if (block.isFunctionEntryBlock()) {
                int size = 0;
                List<Register> parameters = function.parameters;
                for (int i = MiracleOption.CallingConvention.size(), length = parameters.size(); i < length; i++) {
                    Register register = parameters.get(i);
                    size += register.size;
                }
                int oldSize = get16Multiplier(size);
                size = 0;
                for (int i = 0, length = parameters.size(); i < length; i++) {
                    Register register = parameters.get(i);
                    if (i < MiracleOption.CallingConvention.size()) {

                    } else {
                        size += register.size;
                        StackRegister stackRegister = new StackRegister(register.size);
                        stackRegister.setOffset(-(oldSize - size + 16));
                        graph.addPreColor((VirtualRegister) function.parameters.get(i), stackRegister);
                    }
                }

                function.parameters.forEach(arg -> live.forEach(
                        element -> graph.setForbidden((VirtualRegister) arg, element)
                ));
            }
        }
        new SimpleDyer().color(graph).forEach((key, value) ->
                ((VirtualRegister) key).setRealName(value)
        );
    }
}
