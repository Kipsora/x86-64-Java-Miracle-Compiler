package com.miracle.intermediate.visitor.printer;

import com.miracle.intermediate.Root;
import com.miracle.intermediate.instruction.*;
import com.miracle.intermediate.instruction.arithmetic.BinaryArithmetic;
import com.miracle.intermediate.instruction.arithmetic.UnaryArithmetic;
import com.miracle.intermediate.instruction.fork.BinaryBranch;
import com.miracle.intermediate.instruction.fork.Jump;
import com.miracle.intermediate.instruction.fork.Return;
import com.miracle.intermediate.instruction.fork.UnaryBranch;
import com.miracle.intermediate.number.VirtualRegister;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LMHIRPrinter implements IRPrinter {
    private StringBuilder builder;
    private Set<BasicBlock> blockProcessed;

    @Override
    public String getOutput() {
        return builder.toString();
    }

    @Override
    public void visit(Root ir) {
        builder = new StringBuilder();
        blockProcessed = new HashSet<>();
        builder.append("; Code generated by Kipsora").append('\n').append('\n');
        builder.append("section .bss:").append('\n');
        ir.globalVariable.forEach((key, value) ->
                builder.append(key).append(':').append('\t')
                        .append("resb").append(' ').append(value.size).append('\n')
        );
        builder.append('\n');
        builder.append("section .data:").append('\n');
        ir.globalString.forEach((key, value) ->
                builder.append(value.name).append(':').append('\t')
                        .append("db").append(' ').append(value.value.length() - 1)
                        .append(' ').append(value.value).append(", ").append('0')
                        .append('\n')
        );
        builder.append('\n');
        builder.append("section .text:").append('\n');
        ir.globalFunction.forEach((key, value) -> {
            value.accept(this);
            builder.append('\n');
        });
    }

    @Override
    public void visit(BinaryArithmetic binaryArithmetic) {
        builder.append('\t').append(binaryArithmetic.operator).append(' ')
                .append(binaryArithmetic.getTarget()).append(", ")
                .append(binaryArithmetic.getSource()).append('\n');
    }

    @Override
    public void visit(Move move) {
        builder.append('\t').append("mov").append(' ').append(move.getTarget())
                .append(", ").append(move.getSource()).append('\n');
    }

    @Override
    public void visit(Function function) {
        builder.append("func ").append(function.identifier);
        function.getParameters().forEach(element -> builder.append(' ').append(element));
        builder.append(' ').append('{').append('\n');
        builder.append("Callee Saved Registers: ");
        function.buffer.getCalleeSaveRegisters().forEach(element -> builder.append(element).append(' '));
        builder.append('\n');
        function.getEntryBasicBlock().accept(this);
        builder.append('}').append('\n');
    }

    @Override
    public void visit(BasicBlock block) {
        if (blockProcessed.contains(block)) return;
        blockProcessed.add(block);
        builder.append(block.name).append(':').append('\n');
        block.phis.forEach((key, value) -> value.accept(this));
        for (BasicBlock.Node it = block.getHead(); it != block.tail; it = it.getSucc()) {
            it.instruction.accept(this);
        }
        block.getSuccBasicBlock().forEach(element -> element.accept(this));
    }

    @Override
    public void visit(Call call) {
        builder.append('\t').append("call").append(' ').append("ret=")
                .append(call.getTarget()).append(' ')
                .append("self=").append(call.getSelfRegister())
                .append(' ').append(call.function.identifier);
        call.parameters.forEach(element -> builder.append(' ').append(element));
        builder.append(" ").append("Caller Save Registers:");
        call.callerSave.forEach(element -> builder.append(' ').append(element));
        builder.append('\n');
    }

    @Override
    public void visit(UnaryArithmetic prefixArithmetic) {
        builder.append('\t').append(prefixArithmetic.operator).append(' ')
                .append(prefixArithmetic.getTarget()).append('\n');
    }

    @Override
    public void visit(UnaryBranch unaryBranch) {
        builder.append('\t').append("jnz").append(' ').append(unaryBranch.getExpression())
                .append(", ").append(unaryBranch.getBranchTrue().name)
                .append(", ").append(unaryBranch.getBranchFalse().name)
                .append('\n');
    }

    @Override
    public void visit(Return irReturn) {
        builder.append('\t').append("ret").append(' ').append(irReturn.getValue()).append('\n');
    }

    @Override
    public void visit(Jump jump) {
        builder.append('\t').append("jmp").append(' ')
                .append(jump.block.name).append('\n');
    }

    @Override
    public void visit(Compare compare) {
        builder.append('\t').append("cmp").append(' ')
                .append(compare.getSourceA()).append(", ")
                .append(compare.getSourceB()).append('\n');
        builder.append('\t').append(compare.getOperator()).append(' ')
                .append(compare.getTarget()).append('\n');
    }

    @Override
    public void visit(HeapAllocate allocate) {
        builder.append('\t').append("hac").append(' ')
                .append(allocate.getTarget()).append(", ")
                .append(allocate.getNumber()).append(", ")
                .append(allocate.getSize()).append('\n');
    }

    @Override
    public void visit(BinaryBranch binaryBranch) {
        builder.append('\t').append(binaryBranch.getOperator())
                .append(' ').append(binaryBranch.getExpressionA())
                .append(' ').append(binaryBranch.getExpressionB())
                .append(", ").append(binaryBranch.getBranchTrue().name)
                .append(", ").append(binaryBranch.getBranchFalse().name)
                .append('\n');
    }

    @Override
    public void visit(PhiInstruction phiInstruction) {
        builder.append('\t').append(phiInstruction.getRegister()).append("=")
                .append("phi").append('(');
        boolean first = true;
        for (Map.Entry<BasicBlock, VirtualRegister> entry : phiInstruction.args.entrySet()) {
            if (!first) {
                builder.append(", ");
            }
            first = false;
            builder.append(entry.getKey().name).append("=>").append(entry.getValue());
        }
        builder.append(')').append('\n');
    }
}
