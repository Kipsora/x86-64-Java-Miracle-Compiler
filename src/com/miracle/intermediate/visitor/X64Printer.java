package com.miracle.intermediate.visitor;

import com.miracle.intermediate.Root;
import com.miracle.intermediate.instruction.Call;
import com.miracle.intermediate.instruction.Compare;
import com.miracle.intermediate.instruction.HeapAllocate;
import com.miracle.intermediate.instruction.Move;
import com.miracle.intermediate.instruction.arithmetic.BinaryArithmetic;
import com.miracle.intermediate.instruction.arithmetic.UnaryArithmetic;
import com.miracle.intermediate.instruction.fork.BinaryBranch;
import com.miracle.intermediate.instruction.fork.Jump;
import com.miracle.intermediate.instruction.fork.Return;
import com.miracle.intermediate.instruction.fork.UnaryBranch;
import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.PhysicalRegister;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.util.*;

import static com.miracle.MiracleOption.CallingConvention;
import static com.miracle.intermediate.number.PhysicalRegister.*;

public class X64Printer implements IRVisitor {
    private StringBuilder builder;
    private Set<BasicBlock> blockProcessed;
    private String builtinPath;
    private Function curFunction;

    public X64Printer(String builtinPath) {
        this.builtinPath = builtinPath;
    }

    public String getOutput() throws IOException {
        builder.append(FileUtils.readFileToString(
                new java.io.File(builtinPath),
                "utf-8"
        ));
        return builder.toString();
    }

    private void printNumberWith256Base(int number, int padding) {
        List<Integer> array = new LinkedList<>();
        while (number != 0 || array.size() < padding) {
            array.add(number % 256);
            number /= 256;
        }
        Collections.reverse(array);
        array.forEach(element -> builder.append(element).append(", "));
    }

    @Override
    public void visit(Root ir) {
        blockProcessed = new HashSet<>();
        builder = new StringBuilder();
        builder.append("default rel").append('\n');
        builder.append('\n');
        builder.append("extern").append(' ').append("puts").append('\n');
        builder.append("extern").append(' ').append("malloc").append('\n');
        builder.append("extern").append(' ').append("scanf").append('\n');
        builder.append('\n');
        builder.append("global").append(' ').append("main").append('\n');
        builder.append('\n').append("section .bss").append('\n');
        ir.globalVariable.forEach((key, value) ->
                builder.append(key).append(':').append('\t')
                        .append("resb").append(' ').append(value.size).append('\n')
        );
        builder.append("int$buf").append(':').append('\t')
                .append("resw").append(' ').append('1').append('\n');
        builder.append('\n').append("section .data").append('\n');
        ir.globalString.forEach((key, value) -> {
            builder.append(value.name).append(':').append('\t')
                    .append("db").append(' ');
            printNumberWith256Base(value.value.length() - 1, 4);
            builder.append(value.value).append(", ").append('0')
                    .append('\n');
        });
        builder.append("int$fmt").append(':').append('\t')
                .append("db").append(' ').append("25H").append(", ")
                .append("64H").append(", ").append("00H").append('\n');
        builder.append('\n').append("section .text").append('\n');
        ir.globalFunction.forEach((key, value) -> {
            value.accept(this);
            builder.append('\n');
        });
    }

    @Override
    public void visit(BinaryArithmetic binaryArithmetic) {
        if (binaryArithmetic.operator.equals(BinaryArithmetic.Types.DIV)) {
        } else {
            builder.append('\t').append(binaryArithmetic.operator)
                    .append(' ').append(binaryArithmetic.getTarget())
                    .append(", ").append(binaryArithmetic.getSource())
                    .append('\n');
        }
    }

    @Override
    public void visit(Move move) {
        builder.append('\t').append("mov").append(' ').append(move.getTarget())
                .append(", ").append(move.getSource()).append('\n');
    }

    @Override
    public void visit(Function function) {
        curFunction = function;
        builder.append(function.identifier).append(':').append('\n');
        function.getEntryBasicBlock().accept(this);
        curFunction = null;
    }

    private int get16Multiplier(int totalSize) {
        return (totalSize + 15) / 16 * 16;
    }

    @Override
    public void visit(BasicBlock block) {
        if (blockProcessed.contains(block)) return;
        blockProcessed.add(block);
        builder.append(block.name).append(':').append('\n');
        if (block.isFunctionEntryBlock) {
            builder.append('\t').append("push").append(' ').append(RBP.getELF64Name()).append('\n');
            builder.append('\t').append("mov").append(' ').append(RBP).append(", ")
                    .append(RSP).append('\n');
            if (block.blockFrom.buffer.getSpillSize() > 0) {
                builder.append('\t').append("sub").append(' ').append(RSP).append(", ")
                        .append(get16Multiplier(block.blockFrom.buffer.getSpillSize()))
                        .append('\n');
            }

            List<Register> parameters = curFunction.getReverseParameters();
            int size = parameters.size();
            for (int i = 0; i < size; i++) {
                if (i < CallingConvention.size()) {
                    builder.append('\t').append("mov").append(' ')
                            .append(parameters.get(i)).append(", ")
                            .append(PhysicalRegister.getBy16BITName(CallingConvention.get(i), parameters.get(i).getNumberSize())).append('\n');
                }
            }
            size -= CallingConvention.size();
            for (int i = 0; i < size; i++) {
                builder.append('\t').append("pop").append(' ')
                        .append(curFunction.getParameters().get(i)).append('\n');
            }
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
        List<PhysicalRegister> saveRegister = new LinkedList<>(call.function.buffer.getPhysicalRegisters());
        saveRegister.forEach(element -> {
            if (element.isCallerSave) {
                builder.append('\t').append("push").append(' ').append(element.getELF64Name()).append('\n');
            }
        });
        List<Number> parameters = call.getReverseParameters();
        for (int i = 0, size = parameters.size(); i < size; i++) {
            if (i < CallingConvention.size()) {
                builder.append('\t').append("mov").append(' ')
                        .append(PhysicalRegister.getBy16BITName(CallingConvention.get(i), parameters.get(i).getNumberSize()))
                        .append(", ").append(parameters.get(i))
                        .append('\n');
            } else {
                if (parameters.get(i) instanceof PhysicalRegister) {
                    builder.append('\t').append("push").append(' ')
                            .append(((PhysicalRegister) parameters.get(i)).getELF64Name())
                            .append('\n');
                } else {
                    builder.append('\t').append("push").append(' ')
                            .append(parameters.get(i)).append('\n');
                }
            }
        }
        builder.append('\t').append("call").append(' ')
                .append(call.function.identifier).append('\n');
        if (call.getReturnRegister() != null &&
                call.function.getReturnRegister() != null) {
            builder.append('\t').append("mov").append(' ')
                    .append(call.getReturnRegister()).append(", ")
                    .append(call.function.getReturnRegister()).append('\n');
        }
        Collections.reverse(saveRegister);
        saveRegister.forEach(element -> {
            if (element.isCallerSave) {
                builder.append('\t').append("pop").append(' ').append(element.getELF64Name()).append('\n');
            }
        });
    }

    @Override
    public void visit(UnaryArithmetic prefixArithmetic) {
        builder.append('\t').append(prefixArithmetic.operator).append(' ')
                .append(prefixArithmetic.getTarget()).append('\n');
    }

    @Override
    public void visit(UnaryBranch unaryBranch) {
        builder.append('\t').append("cmp").append(' ').append(unaryBranch.getExpression())
                .append(", ").append(0).append('\n');
        builder.append('\t').append("jnz").append(' ')
                .append(unaryBranch.branchTrue.name).append('\n');
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
        builder.append('\t').append("push").append(' ').append(RAX).append('\n');
        builder.append('\t').append("push").append(' ').append(RCX).append('\n');
        builder.append('\t').append("push").append(' ').append(RDX).append('\n');
        builder.append('\t').append("push").append(' ').append(RSI).append('\n');
        builder.append('\t').append("push").append(' ').append(R8).append('\n');
        builder.append('\t').append("push").append(' ').append(R9).append('\n');
        builder.append('\t').append("push").append(' ').append(R10).append('\n');
        builder.append('\t').append("push").append(' ').append(R11).append('\n');
        builder.append('\t').append("mov").append(' ').append(EDI).append(", ")
                .append(allocate.getNumber()).append('\n');
        builder.append('\t').append("call").append(' ').append("malloc");
        builder.append('\t').append("mov").append(' ').append(EAX)
                .append(allocate.getRegister()).append('\n');
        builder.append('\t').append("pop").append(' ').append(RAX).append('\n');
        builder.append('\t').append("pop").append(' ').append(RCX).append('\n');
        builder.append('\t').append("pop").append(' ').append(RSI).append('\n');
        builder.append('\t').append("pop").append(' ').append(RDX).append('\n');
        builder.append('\t').append("pop").append(' ').append(R8).append('\n');
        builder.append('\t').append("pop").append(' ').append(R9).append('\n');
        builder.append('\t').append("pop").append(' ').append(R10).append('\n');
        builder.append('\t').append("pop").append(' ').append(R11).append('\n');
    }

    @Override
    public void visit(BinaryBranch binaryBranch) {
        builder.append('\t').append("cmp")
                .append(' ').append(binaryBranch.getExpressionA())
                .append(", ").append(binaryBranch.getExpressionB())
                .append('\n');
        builder.append('\t').append(binaryBranch.operator)
                .append(' ').append(binaryBranch.branchTrue.name).append('\n');
    }
}
