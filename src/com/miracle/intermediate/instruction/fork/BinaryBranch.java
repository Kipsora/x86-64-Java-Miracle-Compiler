package com.miracle.intermediate.instruction.fork;

import com.miracle.astree.statement.expression.BinaryExpression;
import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.Register;
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
    public void rename(Map map) {
        if (expressionB instanceof Register) {
            expressionB = (Number) map.getOrDefault(expressionB, expressionB);
        }
        if (expressionA instanceof Register) {
            expressionA = (Number) map.getOrDefault(expressionA, expressionA);
        }
    }

    @Override
    public Set<Register> getUsedRegisters() {
        return new HashSet<Register>() {{
            if (expressionB instanceof Register) {
                add((Register) expressionB);
            }
            if (expressionA instanceof Register) {
                add((Register) expressionA);
            }
        }};
    }

    @Override
    public Set<String> getDeprecatedRegisters() {
        return Collections.emptySet();
    }

    public Number getExpressionA() {
        return expressionA;
    }

    public Number getExpressionB() {
        return expressionB;
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
