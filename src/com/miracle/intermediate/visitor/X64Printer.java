package com.miracle.intermediate.visitor;

import com.miracle.intermediate.Root;
import com.miracle.intermediate.instruction.Call;
import com.miracle.intermediate.instruction.Compare;
import com.miracle.intermediate.instruction.HeapAllocate;
import com.miracle.intermediate.instruction.Move;
import com.miracle.intermediate.instruction.arithmetic.BinaryArithmetic;
import com.miracle.intermediate.instruction.arithmetic.UnaryArithmetic;
import com.miracle.intermediate.instruction.fork.Branch;
import com.miracle.intermediate.instruction.fork.Jump;
import com.miracle.intermediate.instruction.fork.Return;
import com.miracle.intermediate.number.PhysicalRegister;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;

import static com.miracle.intermediate.number.PhysicalRegister.RSP;

public class X64Printer implements Visitor {
    private StringBuilder builder;

    public String getOutput() {
        return builder.toString();
    }

    @Override
    public void visit(Root ir) {
        builder = new StringBuilder();
        builder.append("default rel").append('\n');
        builder.append('\n').append("section .bss").append('\n');
        ir.globalVariable.forEach((key, value) ->
                builder.append(key).append(':').append('\t')
                        .append("resb").append(' ').append(value.size).append('\n')
        );
        builder.append('\n').append("section .data").append('\n');
        ir.globalString.forEach((key, value) ->
                builder.append(value.name).append(':').append('\t')
                        .append("db").append(' ').append(value.value.length() - 1)
                        .append(' ').append(value.value).append(", ").append('0')
                        .append('\n')
        );
        builder.append('\n').append("section .text").append('\n');
        ir.globalFunction.forEach((key, value) -> {
            value.accept(this);
            builder.append('\n');
        });
    }

    @Override
    public void visit(BinaryArithmetic binaryArithmetic) {
        builder.append('\t').append(binaryArithmetic.operator)
                .append(' ').append(binaryArithmetic.getTarget())
                .append(", ").append(binaryArithmetic.getSource())
                .append('\n');
    }

    @Override
    public void visit(Move move) {
        builder.append('\t').append("mov").append(' ').append(move.getTarget())
                .append(", ").append(move.getSource()).append('\n');
    }

    @Override
    public void visit(Function function) {
        builder.append(function.identifier).append(':').append('\n');
        builder.append('\t').append("jmp").append(' ').append(function.getEntryBasicBlock().name).append('\n');
        function.getEntryBasicBlock().accept(this);
    }

    private int get16Multiplier(int totalSize) {
        return (totalSize + 15) / 16 * 16;
    }

    @Override
    public void visit(BasicBlock block) {
        builder.append(block.name).append(':').append('\n');
        if (block.isFunctionEntryBlock) {
            builder.append('\t').append("enter").append('\n');
            builder.append('\t').append("sub").append(' ').append(RSP).append(", ")
                    .append(get16Multiplier(block.blockFrom.getBuffer().getSpillSize()))
                    .append('\n');
        }
        if (block.isFunctionExitBlock) {
            builder.append('\t').append("leave").append('\n');
        }
        for (BasicBlock.Node it = block.getHead(); it != block.tail; it = it.getSucc()) {
            it.instruction.accept(this);
        }
        block.getSuccBasicBlock().forEach(element -> element.accept(this));
    }

    @Override
    public void visit(Call call) {
        call.function.getBuffer().getRegisters().forEach((element, value) -> {
            if (element instanceof PhysicalRegister &&
                    ((PhysicalRegister) element).isCallerSave) {
                builder.append('\t').append("push").append(' ').append(element).append('\n');
            }
        });
        call.getReverseParameters().forEach(element -> builder.append('\t')
                .append("push").append(' ').append(element).append('\n')
        );
        builder.append('\t').append("call").append(' ')
                .append(call.function.identifier).append('\n');
        if (call.function.getReturnRegister() != null) {
            builder.append('\t').append("mov").append(' ')
                    .append(call.getReturnRegister()).append(", ")
                    .append(call.function.getReturnRegister()).append('\n');
        }
        call.function.getBuffer().getRegisters().forEach((element, value) -> {
            if (element instanceof PhysicalRegister &&
                    ((PhysicalRegister) element).isCallerSave) {
                builder.append('\t').append("pop").append(' ').append(element).append('\n');
            }
        });
    }

    @Override
    public void visit(UnaryArithmetic prefixArithmetic) {
        builder.append(prefixArithmetic.operator).append(' ')
                .append(prefixArithmetic.getTarget()).append('\n');
    }

    @Override
    public void visit(Branch branch) {
        builder.append('\t').append("jnz").append(' ').append(branch.getExpression())
                .append(", ").append(branch.branchTrue.name).append('\n');
    }

    @Override
    public void visit(Return irReturn) {
        builder.append('\t').append("ret").append('\n');
    }

    @Override
    public void visit(Jump jump) {
        builder.append('\t').append("jmp").append(' ')
                .append(jump.block.name).append('\n');
    }

    @Override
    public void visit(Compare compare) {
        builder.append('\t').append("cmp").append(' ').append(compare.getSourceA())
                .append(", ").append(compare.getSourceB()).append('\n');
        builder.append('\t').append(compare.operator).append(' ')
                .append(compare.getTarget()).append('\n');
    }

    @Override
    public void visit(HeapAllocate allocate) {
        throw new RuntimeException("unsupported method");
    }
}
