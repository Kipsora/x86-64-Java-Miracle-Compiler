package com.miracle.intermediate.instruction.fork;

import com.miracle.astree.statement.expression.BinaryExpression;
import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.OffsetRegister;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.number.VirtualRegister;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.visitor.IRVisitor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BinaryBranch extends Fork {
    public final Types operator;
    public final BasicBlock branchTrue;
    public final BasicBlock branchFalse;
    private Number expressionA;
    private Number expressionB;

    public BinaryBranch(Number expressionA,
                        BinaryExpression.OPERATOR operator,
                        Number expressionB,
                        BasicBlock branchTrue,
                        BasicBlock branchFalse) {
        this.expressionA = expressionA;
        this.operator = fromASTreeTypes(operator);
        this.expressionB = expressionB;
        this.branchTrue = branchTrue;
        this.branchFalse = branchFalse;
    }

    public BinaryBranch(Number expressionA,
                        Types operator,
                        Number expressionB,
                        BasicBlock branchTrue,
                        BasicBlock branchFalse) {
        this.expressionA = expressionA;
        this.operator = operator;
        this.expressionB = expressionB;
        this.branchTrue = branchTrue;
        this.branchFalse = branchFalse;
    }

    public static Types fromASTreeTypes(BinaryExpression.OPERATOR operator) {
        switch (operator) {
            case LEQ:
                return Types.LEQ;
            case REQ:
                return Types.REQ;
            case LT:
                return Types.LT;
            case RT:
                return Types.RT;
            case EQL:
                return Types.EQL;
            case NEQ:
                return Types.NEQ;
            default:
                throw new RuntimeException("unsupported operator");
        }
    }

    @Override
    public void accept(IRVisitor IRVisitor) {
        IRVisitor.visit(this);
    }

    @Override
    public void rename(Map<Register, Register> map) {
        if (expressionB instanceof Register) {
            expressionB = map.getOrDefault(expressionB, (Register) expressionB);
            if (expressionB instanceof OffsetRegister) {
                ((OffsetRegister) expressionB).map(map);
            }
        }
        if (expressionA instanceof Register) {
            expressionA = map.getOrDefault(expressionA, (Register) expressionA);
            if (expressionA instanceof OffsetRegister) {
                ((OffsetRegister) expressionA).map(map);
            }
        }
    }

    @Override
    public Set<Register> getUseRegisters() {
        Set<Register> set = new HashSet<>();
        addToSet(expressionB, set);
        addToSet(expressionA, set);
        return set;
    }

    @Override
    public Set<Register> getDefRegisters() {
        return Collections.emptySet();
    }

    public Number getExpressionA() {
        return expressionA;
    }

    public void setExpressionA(VirtualRegister expressionA) {
        this.expressionA = expressionA;
    }

    public Number getExpressionB() {
        return expressionB;
    }

    public void setExpressionB(Number expressionB) {
        this.expressionB = expressionB;
    }

    public enum Types {
        EQL, LEQ, REQ, NEQ, LT, RT;

        @Override
        public String toString() {
            if (this.equals(EQL)) {
                return "je";
            } else if (this.equals(NEQ)) {
                return "jne";
            } else if (this.equals(LT)) {
                return "jb";
            } else if (this.equals(RT)) {
                return "jg";
            } else if (this.equals(LEQ)) {
                return "jbe";
            } else {
                return "jge";
            }
        }
    }
}
