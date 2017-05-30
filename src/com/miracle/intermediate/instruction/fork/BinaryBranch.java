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

public class BinaryBranch extends Branch {
    private Types operator;
    private Number expressionA;
    private Number expressionB;

    public BinaryBranch(Number expressionA,
                        BinaryExpression.OPERATOR operator,
                        Number expressionB,
                        BasicBlock branchTrue,
                        BasicBlock branchFalse) {
        super(branchTrue, branchFalse);
        this.expressionA = expressionA;
        this.operator = fromASTreeTypes(operator);
        this.expressionB = expressionB;
    }

    public BinaryBranch(Number expressionA,
                        Types operator,
                        Number expressionB,
                        BasicBlock branchTrue,
                        BasicBlock branchFalse) {
        super(branchTrue, branchFalse);
        this.expressionA = expressionA;
        this.operator = operator;
        this.expressionB = expressionB;
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

    public Types getOperator() {
        return operator;
    }

    public void swapExpressions() {
        Number t = expressionA;
        expressionA = expressionB;
        expressionB = t;
        operator = operator.getReverse();
    }

    @Override
    public void accept(IRVisitor IRVisitor) {
        IRVisitor.visit(this);
    }

    @Override
    public void set(Map<Number, Register> map) {
        if (map.containsKey(expressionB)) expressionB = map.get(expressionB);
        if (expressionB instanceof OffsetRegister) {
            ((OffsetRegister) expressionB).set(map);
        }
        if (map.containsKey(expressionA)) expressionA = map.get(expressionA);
        if (expressionA instanceof OffsetRegister) {
            ((OffsetRegister) expressionA).set(map);
        }
    }

    @Override
    public void rename(Map<VirtualRegister, VirtualRegister> map) {
        if (expressionA instanceof VirtualRegister) {
            expressionA = map.get(expressionA);
        } else if (expressionA instanceof OffsetRegister) {
            ((OffsetRegister) expressionA).rename(map);
        }
        if (expressionB instanceof VirtualRegister) {
            expressionB = map.get(expressionB);
        } else if (expressionB instanceof OffsetRegister) {
            ((OffsetRegister) expressionB).rename(map);
        }
    }

    @Override
    public Set<Number> getUseNumbers() {
        Set<Number> set = new HashSet<>();
        addToSet(expressionB, set, false);
        addToSet(expressionA, set, false);
        return set;
    }

    @Override
    public Set<Number> getDefNumbers() {
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
                return "jl";
            } else if (this.equals(RT)) {
                return "jg";
            } else if (this.equals(LEQ)) {
                return "jle";
            } else {
                return "jge";
            }
        }

        Types getReverse() {
            if (this.equals(EQL)) {
                return EQL;
            } else if (this.equals(NEQ)) {
                return NEQ;
            } else if (this.equals(LT)) {
                return REQ;
            } else if (this.equals(RT)) {
                return LEQ;
            } else if (this.equals(LEQ)) {
                return RT;
            } else {
                return LT;
            }
        }
    }
}
