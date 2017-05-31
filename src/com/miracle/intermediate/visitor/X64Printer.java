package com.miracle.intermediate.visitor;

import com.miracle.MiracleOption;
import com.miracle.intermediate.Root;
import com.miracle.intermediate.instruction.*;
import com.miracle.intermediate.instruction.arithmetic.BinaryArithmetic;
import com.miracle.intermediate.instruction.arithmetic.UnaryArithmetic;
import com.miracle.intermediate.instruction.fork.BinaryBranch;
import com.miracle.intermediate.instruction.fork.Jump;
import com.miracle.intermediate.instruction.fork.Return;
import com.miracle.intermediate.instruction.fork.UnaryBranch;
import com.miracle.intermediate.number.*;
import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;
import com.miracle.intermediate.visitor.printer.IRPrinter;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.util.*;

import static com.miracle.MiracleOption.CallingConvention;
import static com.miracle.intermediate.number.PhysicalRegister.*;

public class X64Printer implements IRPrinter {
    private StringBuilder builder;
    private Set<BasicBlock> blockProcessed;
    private String builtinPath;
    private Function curFunction;

    public X64Printer(String builtinPath) {
        this.builtinPath = builtinPath;
    }

    @Override
    public String getOutput() throws IOException {
        builder.append(FileUtils.readFileToString(
                new java.io.File(builtinPath),
                "utf-8"
        ));
        return builder.toString();
    }

    private List<String> divideStringIntoByte(String value) {
        List<String> list = new ArrayList<>();
        value = value.substring(1, value.length() - 1);
        for (int i = 0, length = value.length(); i < length; i++) {
            if (i + 1 < value.length() && value.charAt(i) == '\\' && value.charAt(i + 1) == 'n') {
                list.add(String.valueOf(10));
                i++;
            } else {
                list.add(String.valueOf((int) value.charAt(i)));
            }
        }
        return list;
    }

    @Override
    public void visit(Root ir) {
        blockProcessed = new HashSet<>();
        builder = new StringBuilder();
        builder.append("; Code generated by Kipsora").append('\n');
        builder.append('\n').append("default rel").append('\n');
        builder.append('\n').append("extern").append(' ').append("malloc");
        builder.append('\n').append("extern").append(' ').append("scanf").append('\n');
        builder.append('\n').append("global").append(' ').append("main").append('\n');
        builder.append('\n').append("section .bss").append('\n');
        ir.globalVariable.forEach((key, value) ->
                builder.append(key).append(':').append('\t')
                        .append("resb").append(' ').append(value.size).append('\n')
        );
        builder.append("int$buf").append(':').append('\t')
                .append("resw").append(' ').append('1').append('\n');
        builder.append('\n').append("section .data").append('\n');
        ir.globalString.forEach((key, value) -> {
            List<String> bytes = divideStringIntoByte(value.value);
            builder.append('\t').append("dq").append(' ').append(bytes.size()).append('\n');
            builder.append(value.name).append(':').append('\t');
            if (!bytes.isEmpty()) {
                builder.append("db").append(' ').append(String.join(", ", bytes));
            }
            builder.append('\n');
        });
        builder.append("int$fmt").append(':').append('\t')
                .append("db").append(' ').append("25H").append(", ")
                .append("64H").append(", ").append("00H").append('\n');
        builder.append("str$fmt").append(':').append('\t')
                .append("db").append(' ').append("25H").append(", ")
                .append("73H").append(", ").append("00H").append('\n');
        builder.append("fln$fmt").append(':').append('\t')
                .append("db").append(' ').append("10").append('\n');
        builder.append('\n').append("section .text").append('\n');
        ir.globalFunction.forEach((key, value) -> {
            value.accept(this);
            builder.append('\n');
        });
    }

    @Override
    public void visit(BinaryArithmetic binaryArithmetic) {
        if (binaryArithmetic.operator.equals(BinaryArithmetic.Types.SHL) ||
                binaryArithmetic.operator.equals(BinaryArithmetic.Types.SHR)) {
            builder.append('\t').append("mov").append(' ')
                    .append(PhysicalRegister.getBy16BITName("RCX", binaryArithmetic.getSource().getNumberSize()))
                    .append(", ").append(binaryArithmetic.getSource()).append('\n');
            builder.append('\t').append("shl").append(' ')
                    .append(binaryArithmetic.getTarget()).append(", ")
                    .append(PhysicalRegister.getBy16BITName("RCX", 1)).append('\n');
        } else if (binaryArithmetic.operator.equals(BinaryArithmetic.Types.DIV)) {
            /*
             *  Div Instruction:  DIV RAX, src (src != RDX, RAX, imm) RAX /= src
             *    - src cannot be immediate number   -> processed in MLIRTransformer
             *    - src cannot be RDX and RAX        -> TODO: in Register Allocator
             *    - tar must be RAX                  -> TODO: in Register Allocator
             *  cdq
             *  idiv src
             */
            builder.append('\t').append("mov").append(' ')
                    .append(PhysicalRegister.getBy16BITName("RAX", binaryArithmetic.getSource().getNumberSize()))
                    .append(", ").append(binaryArithmetic.getTarget()).append('\n');
            builder.append('\t').append("cdq").append('\n');
            builder.append('\t').append("idiv").append(' ').append(binaryArithmetic.getSource()).append('\n');
            builder.append('\t').append("mov")
                    .append(' ').append(binaryArithmetic.getTarget())
                    .append(", ").append(getBy16BITName("RAX", binaryArithmetic.getTarget().size)).append('\n');
        } else if (binaryArithmetic.operator.equals(BinaryArithmetic.Types.MOD)) {
            /*
             *  Div Instruction:  MOD RAX, src RAX /= src
             *    - src cannot be immediate number   -> processed in MLIRTransformer
             *    - src cannot be RDX and RAX        -> TODO: in Register Allocator
             *    - tar must be RAX                  -> TODO: in Register Allocator
             *  cdq
             *  idiv src
             *  mov RAX, RDX
             */
            builder.append('\t').append("mov").append(' ')
                    .append(PhysicalRegister.getBy16BITName("RAX", binaryArithmetic.getSource().getNumberSize()))
                    .append(", ").append(binaryArithmetic.getTarget()).append('\n');
            builder.append('\t').append("cdq").append('\n');
            builder.append('\t').append("idiv").append(' ').append(binaryArithmetic.getSource()).append('\n');
            builder.append('\t').append("mov")
                    .append(' ').append(binaryArithmetic.getTarget())
                    .append(", ").append(getBy16BITName("RDX", binaryArithmetic.getTarget().size)).append('\n');
        } else {
            /*
             *  Other Arithmetic Instruction:  OP tar, src (tar != src) tar OP= src
             *    - tar and src cannot be both indirect registers   -> TODO: in Register Allocator
             *  EXCEPTION:
             *     >>, <<: target must be physical registers        -> TODO: in Register Allocator
             *     *: source cannot be indirect registers           -> TODO: in Register Allocator
             */
            if (binaryArithmetic.operator.equals(BinaryArithmetic.Types.MUL)
                    && binaryArithmetic.getTarget().isIndirect()) {
                builder.append('\t').append("mov").append(' ')
                        .append(PhysicalRegister.getBy16BITName("RAX", binaryArithmetic.getTarget().size))
                        .append(", ").append(binaryArithmetic.getTarget()).append("\n");
                builder.append('\t').append(binaryArithmetic.operator)
                        .append(' ').append(PhysicalRegister.getBy16BITName("RAX", binaryArithmetic.getTarget().size))
                        .append(", ").append(binaryArithmetic.getSource())
                        .append('\n');
                builder.append('\t').append("mov").append(' ').append(binaryArithmetic.getTarget())
                        .append(", ")
                        .append(PhysicalRegister.getBy16BITName("RAX", binaryArithmetic.getTarget().size))
                        .append("\n");
            } else {
                if (binaryArithmetic.getSource() instanceof Register
                        && ((Register) binaryArithmetic.getSource()).isIndirect()
                        && binaryArithmetic.getTarget().isIndirect()) {
                    builder.append('\t').append("mov").append(' ')
                            .append(PhysicalRegister.getBy16BITName("RAX", binaryArithmetic.getTarget().size))
                            .append(", ").append(binaryArithmetic.getTarget()).append("\n");
                    builder.append('\t').append(binaryArithmetic.operator)
                            .append(' ').append(PhysicalRegister.getBy16BITName("RAX", binaryArithmetic.getTarget().size))
                            .append(", ").append(binaryArithmetic.getSource())
                            .append('\n');
                    builder.append('\t').append("mov").append(' ').append(binaryArithmetic.getTarget())
                            .append(", ")
                            .append(PhysicalRegister.getBy16BITName("RAX", binaryArithmetic.getTarget().size))
                            .append("\n");
                } else {
                    builder.append('\t').append(binaryArithmetic.operator)
                            .append(' ').append(binaryArithmetic.getTarget())
                            .append(", ").append(binaryArithmetic.getSource())
                            .append('\n');
                }
            }
        }
    }

    @Override
    public void visit(Move move) {
        /*
         *  Move Instruction:
         *  tar and src cannot be both indirect registers       -> TODO: in Register Allocator
         */
        if (move.getSource().toString().equals(move.getTarget().toString())) return;
        if (move.getSource() instanceof Register && ((Register) move.getSource()).isIndirect() && move.getTarget().isIndirect()) {
            builder.append('\t').append("mov").append(' ').append(PhysicalRegister.getBy16BITName("R15", move.getSource().getNumberSize()))
                    .append(", ").append(move.getSource()).append('\n');
            builder.append('\t').append("mov").append(' ').append(move.getTarget())
                    .append(", ").append(PhysicalRegister.getBy16BITName("R15", move.getSource().getNumberSize())).append('\n');
        } else {
            builder.append('\t').append("mov").append(' ').append(move.getTarget())
                    .append(", ").append(move.getSource()).append('\n');
        }
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
        if (block.isFunctionEntryBlock()) {
            if (curFunction.buffer.getSpillSize() > 0 || !curFunction.buffer.getCalleeSaveRegisters().isEmpty()) {
                builder.append('\t').append("push").append(' ').append(RBP.getELF64Name()).append('\n');
                builder.append('\t').append("mov").append(' ').append(RBP).append(", ")
                        .append(RSP).append('\n');
                if (curFunction.buffer.getSpillSize() > 0) {
                    builder.append('\t').append("sub").append(' ').append(RSP).append(", ")
                            .append(get16Multiplier(block.blockFrom.buffer.getSpillSize()))
                            .append('\n');
                }
                curFunction.buffer.getCalleeSaveRegisters().forEach(element -> {
                    builder.append('\t').append("push").append(' ')
                            .append(element.getELF64Name()).append('\n');
                });
                for (int i = 0, size = curFunction.parameters.size(); i < size && i < MiracleOption.CallingConvention.size(); i++) {
                    if (!(curFunction.parameters.get(i) instanceof PhysicalRegister) ||
                            !((PhysicalRegister) curFunction.parameters.get(i)).indexName.equals(MiracleOption.CallingConvention.get(i))) {
                        builder.append('\t').append("mov").append(' ').append(curFunction.parameters.get(i)).append(", ")
                                .append(PhysicalRegister.getBy16BITName(
                                        MiracleOption.CallingConvention.get(i),
                                        curFunction.parameters.get(i).size
                                )).append('\n');
                    }
                }
            }
        }
        for (BasicBlock.Node it = block.getHead(); it != block.tail; it = it.getSucc()) {
            it.instruction.accept(this);
        }
        block.getSuccBasicBlock().forEach(element -> element.accept(this));
    }

    @Override
    public void visit(Call call) {
        /*
         * CALL instruction:
         * returnRegister must be null or RAX                    -> TODO: in Register Allocator
         * The last 6 parameters must follow calling conventions -> TODO: in Register Allocator
         */
        List<PhysicalRegister> registers = new LinkedList<>(call.callerSave);
        registers.forEach(element -> {
            if (element.isCallerSave) {
                builder.append('\t').append("push")
                        .append(' ').append(element.getELF64Name()).append('\n');
            }
        });
        List<Number> parameters = call.parameters;
        int spillSize = 0;
        for (int i = CallingConvention.size(), size = parameters.size(); i < size; i++) {
            spillSize += parameters.get(i).getNumberSize();
        }
        int fitSize = get16Multiplier(spillSize);
        if (fitSize != 0) { // if there are more than 6 args
            builder.append('\t').append("sub").append(' ').append(RSP)
                    .append(", ").append(fitSize).append('\n');
            spillSize = 0;
            for (int i = CallingConvention.size(), size = parameters.size(); i < size; i++) {
                spillSize += parameters.get(i).getNumberSize();
                if (parameters.get(i) instanceof IndirectRegister) {
                    builder.append('\t').append("mov").append(' ')
                            .append(PhysicalRegister.getBy16BITName("RDI", parameters.get(i).getNumberSize()))
                            .append(", ").append(parameters.get(i)).append('\n');
                    builder.append('\t').append("mov").append(' ')
                            .append(new OffsetRegister(
                                    RSP, fitSize - spillSize,
                                    null, null,
                                    parameters.get(i).getNumberSize()
                            ))
                            .append(", ")
                            .append(PhysicalRegister.getBy16BITName("RDI", parameters.get(i).getNumberSize()))
                            .append('\n');
                } else {
                    builder.append('\t').append("mov").append(' ')
                            .append(new OffsetRegister(
                                    RSP, fitSize - spillSize,
                                    null, null,
                                    parameters.get(i).getNumberSize()
                            ))
                            .append(", ").append(parameters.get(i)).append('\n');
                }
            }
        }
        for (int i = 0; i < parameters.size() && i < MiracleOption.CallingConvention.size(); i++) {
            if (!(parameters.get(i) instanceof PhysicalRegister) ||
                    !((PhysicalRegister) parameters.get(i)).indexName.equals(MiracleOption.CallingConvention.get(i))) {
                builder.append('\t').append("push").append(' ').append(parameters.get(i)).append('\n');
            }
        }
        for (int i = Math.min(parameters.size() - 1, MiracleOption.CallingConvention.size() - 1); i >= 0; i--) {
            if (!(parameters.get(i) instanceof PhysicalRegister) ||
                    !((PhysicalRegister) parameters.get(i)).indexName.equals(MiracleOption.CallingConvention.get(i))) {
                builder.append('\t').append("pop").append(' ').append(PhysicalRegister.getBy16BITName(MiracleOption.CallingConvention.get(i), parameters.get(i).getNumberSize())).append('\n');
            }
        }
        builder.append('\t').append("call").append(' ')
                .append(call.function.identifier).append('\n');
        if (fitSize != 0) {
            builder.append('\t').append("add").append(' ').append(RSP)
                    .append(", ").append(fitSize).append('\n');
        }
        Collections.reverse(registers);
        registers.forEach(element -> {
            if (element.isCallerSave) {
                builder.append('\t').append("pop")
                        .append(' ').append(element.getELF64Name()).append('\n');
            }
        });
        if (call.getTarget() != null) {
            builder.append('\t').append("mov").append(' ')
                    .append(call.getTarget()).append(", ")
                    .append(PhysicalRegister.getBy16BITName(
                            "RAX",
                            call.getTarget().size
                    )).append('\n');
        }
    }

    @Override
    public void visit(UnaryArithmetic prefixArithmetic) {
        /*
         *  UnaryArithmetic Instruction:
         *  NO LIMITS
         */
        builder.append('\t').append(prefixArithmetic.operator).append(' ')
                .append(prefixArithmetic.getTarget()).append('\n');
    }

    @Override
    public void visit(UnaryBranch unaryBranch) {
        /*
         * UnaryBranch Instruction:
         *   expression cannot be immediate number -> processed in MLIRTransformer
         */
        builder.append('\t').append("cmp").append(' ').append(unaryBranch.getExpression())
                .append(", ").append(0).append('\n');
        builder.append('\t').append("jnz").append(' ')
                .append(unaryBranch.getBranchTrue().name).append('\n');
    }

    @Override
    public void visit(Return irReturn) {
        /*
         * Return Instruction:
         *   - value must be null                       -> processed in LLIRTransformer
         */
        List<PhysicalRegister> registers = new LinkedList<>(curFunction.buffer.getCalleeSaveRegisters());
        Collections.reverse(registers);
        registers.forEach(element -> {
            builder.append('\t').append("pop").append(' ').append(element.getELF64Name()).append('\n');
        });
        if (curFunction.buffer.getSpillSize() > 0 || !curFunction.buffer.getCalleeSaveRegisters().isEmpty()) {
            builder.append('\t').append("leave").append('\n');
        }
        builder.append('\t').append("ret").append('\n');
    }

    @Override
    public void visit(Jump jump) {
        builder.append('\t').append("jmp").append(' ')
                .append(jump.block.name).append('\n');
    }

    @Override
    public void visit(Compare compare) {
        /*
         * Compare Instruction:
         *   - srcA, srcB cannot be both immediate number   -> processed in MLIRTransformer
         *   - srcA, srcB cannot be both indirect registers -> TODO: in Register Allocator
         */
        if (compare.getSourceA() instanceof Register &&
                ((Register) compare.getSourceA()).isIndirect()) {
            builder.append('\t').append("mov").append(' ')
                    .append(PhysicalRegister.getBy16BITName("RAX", ((Register) compare.getSourceA()).size))
                    .append(", ").append(compare.getSourceA()).append('\n');
            builder.append('\t').append("cmp").append(' ').append(PhysicalRegister.getBy16BITName("RAX", ((Register) compare.getSourceA()).size))
                    .append(", ").append(compare.getSourceB()).append('\n');
            builder.append('\t').append(compare.getOperator()).append(' ')
                    .append(compare.getTarget()).append('\n');
        } else {
            builder.append('\t').append("cmp").append(' ').append(compare.getSourceA())
                    .append(", ").append(compare.getSourceB()).append('\n');
            builder.append('\t').append(compare.getOperator()).append(' ')
                    .append(compare.getTarget()).append('\n');
        }
    }

    @Override
    public void visit(HeapAllocate allocate) {
        /*
         *  HeapAllocate Instruction:
         *    - size must be one        -> processed in MLIRTransformer
         */
        List<PhysicalRegister> list  = new LinkedList<>(allocate.callerSave);
        list.forEach(element ->
                builder.append('\t').append("push").append(' ')
                        .append(element).append('\n')
        );
        builder.append('\t').append("mov").append(' ').append("rdi").append(", ")
                .append(allocate.getNumber()).append('\n');
        builder.append('\t').append("call").append(' ').append("malloc").append('\n');
        Collections.reverse(list);
        list.forEach(element ->
                builder.append('\t').append("pop").append(' ')
                        .append(element).append('\n')
        );
        builder.append('\t').append("mov").append(' ').append(allocate.getTarget())
                .append(", ").append(PhysicalRegister.getBy16BITName("RAX", allocate.getTarget().size))
                .append("\n");
    }

    @Override
    public void visit(BinaryBranch binaryBranch) {
        /*
         * BinaryBranch expression:
         *   - expA and expB cannot be both immediate number   -> processed in MLIRTransformer
         *   - expA and expB cannot be both indirect registers -> TODO: in Register Allocator
         */

        if (binaryBranch.getExpressionA() instanceof Register &&
                ((Register) binaryBranch.getExpressionA()).isIndirect()) {
            builder.append('\t').append("mov").append(' ')
                    .append(PhysicalRegister.getBy16BITName("RAX", ((Register) binaryBranch.getExpressionA()).size))
                    .append(", ").append(binaryBranch.getExpressionA()).append('\n');
            builder.append('\t').append("cmp").append(' ').append(PhysicalRegister.getBy16BITName("RAX", ((Register) binaryBranch.getExpressionA()).size))
                    .append(", ").append(binaryBranch.getExpressionB()).append('\n');
        } else {
            builder.append('\t').append("cmp")
                    .append(' ').append(binaryBranch.getExpressionA())
                    .append(", ").append(binaryBranch.getExpressionB())
                    .append('\n');
        }
        builder.append('\t').append(binaryBranch.getOperator())
                .append(' ').append(binaryBranch.getBranchTrue().name).append('\n');
    }

    @Override
    public void visit(PhiInstruction phiInstruction) {
        throw new RuntimeException("unprocessed phis");
    }
}
