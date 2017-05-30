package com.miracle.intermediate.visitor.irtranser;

import com.miracle.MiracleOption;
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
import com.miracle.intermediate.number.*;
import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;
import com.miracle.intermediate.visitor.BaseIRVisitor;

import java.util.HashSet;
import java.util.Set;

public class MLIRTransformer extends BaseIRVisitor {
    private Set<BasicBlock> blockProcessed;
    private int countTmpVars;

    @Override
    public void visit(Root ir) {
        countTmpVars = 0;
        blockProcessed = new HashSet<>();
        ir.globalFunction.forEach((key, value) -> value.accept(this));
    }

    @Override
    public void visit(Function function) {
        if (function.getSelfRegister() != null) {
            function.parameters.add(0, function.getSelfRegister());
            function.setSelfRegister(null);
        }
        function.getEntryBasicBlock().accept(this);
    }

    @Override
    public void visit(BasicBlock block) {
        if (blockProcessed.contains(block)) return;
        blockProcessed.add(block);
        for (BasicBlock.Node it = block.getHead(); it != block.tail; it = it.getSucc()) {
            if (it.instruction instanceof HeapAllocate) { // malloc <size> is supported only
                Number number;
                // hal imm1, imm2 will be translated into hal imm1 * imm2, 1
                if (((HeapAllocate) it.instruction).getNumber() instanceof Immediate) {
                    number = new Immediate(
                            ((HeapAllocate) it.instruction).getSize() *
                                    ((Immediate) ((HeapAllocate) it.instruction).getNumber()).value + MiracleOption.INT_SIZE,
                            ((HeapAllocate) it.instruction).getNumber().getNumberSize()
                    );
                } else {
                    number = new VirtualRegister(
                            ".aux" + String.valueOf(countTmpVars++),
                            ((HeapAllocate) it.instruction).getNumber().getNumberSize()
                    );
                    it.prepend(new Move(
                            (Register) number,
                            ((HeapAllocate) it.instruction).getNumber()
                    ));
                    it.prepend(new BinaryArithmetic(
                            BinaryArithmetic.Types.MUL, (Register) number,
                            new Immediate(
                                    ((HeapAllocate) it.instruction).getSize(),
                                    ((HeapAllocate) it.instruction).getNumber().getNumberSize()
                            )
                    ));
                    it.prepend(new BinaryArithmetic(
                            BinaryArithmetic.Types.ADD, (Register) number,
                            new Immediate(
                                    MiracleOption.INT_SIZE,
                                    ((HeapAllocate) it.instruction).getNumber().getNumberSize()
                            )
                    ));
                }
                ((HeapAllocate) it.instruction).setSize(1);
                ((HeapAllocate) it.instruction).setNumber(number);
            } else if (it.instruction instanceof UnaryBranch) {
                if (((UnaryBranch) it.instruction).getExpression() instanceof Immediate) {
                    VirtualRegister register = new VirtualRegister(
                            ".aux" + String.valueOf(countTmpVars++),
                            ((UnaryBranch) it.instruction).getExpression().getNumberSize()
                    );
                    it.prepend(new Move(register, ((UnaryBranch) (it.instruction)).getExpression()));
                    ((UnaryBranch) it.instruction).setExpression(register);
                }
                it.append(new Jump(((UnaryBranch) it.instruction).getBranchFalse()));
            } else if (it.instruction instanceof BinaryBranch) {
                if ((((BinaryBranch) it.instruction).getExpressionA() instanceof IndirectRegister &&
                        ((BinaryBranch) it.instruction).getExpressionB() instanceof IndirectRegister)) {
                    VirtualRegister register = new VirtualRegister(
                            ".aux" + String.valueOf(countTmpVars++),
                            ((IndirectRegister) ((BinaryBranch) it.instruction).getExpressionA()).size
                    );
                    it.prepend(new Move(register, ((BinaryBranch) it.instruction).getExpressionA()));
                    ((BinaryBranch) it.instruction).setExpressionA(register);
                }
                if ((((BinaryBranch) it.instruction).getExpressionA() instanceof Immediate &&
                        ((BinaryBranch) it.instruction).getExpressionB() instanceof Immediate)) {
                    VirtualRegister register = new VirtualRegister(
                            ".aux" + String.valueOf(countTmpVars++),
                            ((IndirectRegister) ((BinaryBranch) it.instruction).getExpressionA()).size
                    );
                    it.prepend(new Move(register, ((BinaryBranch) it.instruction).getExpressionA()));
                    ((BinaryBranch) it.instruction).setExpressionA(register);
                }
                if (((BinaryBranch) it.instruction).getExpressionA() instanceof Immediate) {
                    ((BinaryBranch) it.instruction).swapExpressions();
                }
                it.append(new Jump(((BinaryBranch) it.instruction).getBranchFalse()));
            } else if (it.instruction instanceof Compare) {
                if ((((Compare) it.instruction).getSourceA() instanceof IndirectRegister &&
                        ((Compare) it.instruction).getSourceB() instanceof IndirectRegister)) {
                    VirtualRegister register = new VirtualRegister(
                            ".aux" + String.valueOf(countTmpVars++),
                            ((IndirectRegister) ((Compare) it.instruction).getSourceA()).size
                    );
                    it.prepend(new Move(register, ((Compare) it.instruction).getSourceA()));
                    ((Compare) it.instruction).setSourceA(register);
                }
                if ((((Compare) it.instruction).getSourceA() instanceof Immediate &&
                        ((Compare) it.instruction).getSourceB() instanceof Immediate)) {
                    VirtualRegister register = new VirtualRegister(
                            ".aux" + String.valueOf(countTmpVars++),
                            ((Immediate) ((Compare) it.instruction).getSourceA()).size
                    );
                    it.prepend(new Move(register, ((Compare) it.instruction).getSourceA()));
                    ((Compare) it.instruction).setSourceA(register);
                }
                if ((((Compare) it.instruction).getSourceA() instanceof Immediate)) {
                    ((Compare) it.instruction).swapSources();
                }
            } else if (it.instruction instanceof BinaryArithmetic) {
                if ((((BinaryArithmetic) it.instruction).operator.equals(BinaryArithmetic.Types.DIV) ||
                        ((BinaryArithmetic) it.instruction).operator.equals(BinaryArithmetic.Types.MOD)) &&
                        ((BinaryArithmetic) it.instruction).getSource() instanceof Immediate) {
                    VirtualRegister register = new VirtualRegister(
                            ".aux" + String.valueOf(countTmpVars++),
                            ((Immediate) ((BinaryArithmetic) it.instruction).getSource()).size
                    );
                    it.prepend(new Move(register, ((BinaryArithmetic) it.instruction).getSource()));
                    ((BinaryArithmetic) it.instruction).setSource(register);
                }
            } else if (it.instruction instanceof Call) {
                if (((Call) it.instruction).getSelfRegister() != null) {
                    ((Call) it.instruction).parameters.add(0, ((Call) it.instruction).getSelfRegister());
                    ((Call) it.instruction).setSelfRegister(null);
                }
            } else if (it.instruction instanceof Move) {
                if (((Move) it.instruction).getSource() instanceof OffsetRegister &&
                        ((Move) it.instruction).getSource() instanceof OffsetRegister) {
                    VirtualRegister register = new VirtualRegister(
                            ".aux" + String.valueOf(countTmpVars++),
                            ((Move) it.instruction).getSource().getNumberSize()
                    );
                    it.prepend(new Move(
                            register,
                            ((Move) it.instruction).getSource()
                    ));
                    it.prepend(new Move(
                            ((Move) it.instruction).getTarget(),
                            register
                    ));
                    it.remove();
                }
            }
        }
        block.getSuccBasicBlock().forEach(element -> element.accept(this));
    }
}
