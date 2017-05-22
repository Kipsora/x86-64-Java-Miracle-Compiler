package com.miracle.intermediate.visitor;

import com.miracle.intermediate.MiracleIR;
import com.miracle.intermediate.instruction.MiracleIRBinary;
import com.miracle.intermediate.instruction.MiracleIRMove;
import com.miracle.intermediate.structure.MiracleIRBasicBlock;
import com.miracle.intermediate.structure.MiracleIRFunction;
import com.miracle.intermediate.value.MiracleIRStaticString;
import com.miracle.intermediate.value.MiracleIRStaticVariable;

public class MiracleAssemblyGenerator implements MiracleIRVisitor {
    private StringBuilder buffer = new StringBuilder();

    public String getOutput() {
        return buffer.toString();
    }

    @Override
    public void visit(MiracleIR ir) {
        buffer.append("default rel").append('\n');
        ir.globalFunctions.forEach((key, value) ->
                buffer.append("global").append(' ')
                        .append(value.identifier).append('\n')
        );
        buffer.append('\n').append("SECTION .text").append('\n');
        ir.globalFunctions.forEach((key, value) -> value.accept(this));
        buffer.append('\n').append("SECTION .data").append('\n');
        ir.globalString.forEach((key, value) -> buffer.append(value));
        buffer.append('\n').append("SECTION .bss").append('\n');
        ir.globalVariable.forEach((key, value) ->
                buffer.append(value.identifier)
                        .append(':').append('\t').append("resb")
                        .append(' ').append(value.size).append('\n')
        );
    }

    @Override
    public void visit(MiracleIRBinary binary) {
        buffer.append('\t').append(binary.operator.toString())
                .append(' ').append(binary.target).append(' ')
                .append(binary.sourceA).append(' ')
                .append(binary.sourceB).append('\n');
    }

    @Override
    public void visit(MiracleIRStaticString staticString) {

    }

    @Override
    public void visit(MiracleIRStaticVariable staticVariable) {

    }

    @Override
    public void visit(MiracleIRFunction function) {
        buffer.append(function.identifier).append(':').append('\n');
        function.entryBB.accept(this);
    }

    @Override
    public void visit(MiracleIRBasicBlock block) {
        for (MiracleIRBasicBlock.Node it = block.head.getSucc(); it != block.tail; it = it.getSucc()) {
            it.instruction.accept(this);
        }
    }

    @Override
    public void visit(MiracleIRMove move) {
        buffer.append('\t').append("mov").append(' ').append(move.source)
                .append(' ').append(move.target).append('\n');
    }
}
