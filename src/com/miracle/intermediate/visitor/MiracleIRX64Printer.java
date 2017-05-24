package com.miracle.intermediate.visitor;

import com.miracle.intermediate.MiracleIR;
import com.miracle.intermediate.instruction.MiracleIRCall;
import com.miracle.intermediate.instruction.MiracleIRCompare;
import com.miracle.intermediate.instruction.MiracleIRHeapAllocate;
import com.miracle.intermediate.instruction.MiracleIRMove;
import com.miracle.intermediate.instruction.arithmetic.MiracleIRBinaryArithmetic;
import com.miracle.intermediate.instruction.arithmetic.MiracleIRPrefixArithmetic;
import com.miracle.intermediate.instruction.fork.MiracleIRBranch;
import com.miracle.intermediate.instruction.fork.MiracleIRJump;
import com.miracle.intermediate.instruction.fork.MiracleIRReturn;
import com.miracle.intermediate.structure.MiracleIRBasicBlock;
import com.miracle.intermediate.structure.MiracleIRFunction;

import static com.miracle.intermediate.number.MiracleIRPhysicalRegister.RBP;
import static com.miracle.intermediate.number.MiracleIRPhysicalRegister.RSP;

public class MiracleIRX64Printer implements MiracleIRVisitor {
    private StringBuilder builder;

    public String getOutput() {
        return builder.toString();
    }

    @Override
    public void visit(MiracleIR ir) {
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
    public void visit(MiracleIRBinaryArithmetic binaryArithmetic) {

    }

    @Override
    public void visit(MiracleIRMove move) {

    }

    @Override
    public void visit(MiracleIRFunction function) {
        builder.append(function.identifier).append(':').append('\n');
        function.getEntryBasicBlock().accept(this);
    }

    @Override
    public void visit(MiracleIRBasicBlock block) {
        if (block.isFunctionEntryBlock) {
            builder.append('\t').append("push").append(' ').append(RBP).append('\n');
            builder.append('\t').append("mov").append(' ').append(RBP)
                    .append(", ").append(RSP).append('\n');
            builder.append('\t').append("sub").append(' ').append(RSP).append(", ")
                    .append(block.blockFrom.getBuffer().getTotalSize())
                    .append('\n');
        }
        if (block.isFunctionExitBlock) {
            builder.append('\t').append("pop").append(' ').append(RBP);
        }
    }

    @Override
    public void visit(MiracleIRCall call) {

    }

    @Override
    public void visit(MiracleIRPrefixArithmetic prefixArithmetic) {

    }

    @Override
    public void visit(MiracleIRBranch branch) {

    }

    @Override
    public void visit(MiracleIRReturn irReturn) {

    }

    @Override
    public void visit(MiracleIRJump jump) {

    }

    @Override
    public void visit(MiracleIRCompare compare) {

    }

    @Override
    public void visit(MiracleIRHeapAllocate allocate) {

    }
}
