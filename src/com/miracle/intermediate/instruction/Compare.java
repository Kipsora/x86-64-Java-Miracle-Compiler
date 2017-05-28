package com.miracle.intermediate.instruction;

import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.OffsetRegister;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.visitor.IRVisitor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Compare extends Instruction {
    private Types operator;
    private Number sourceA;
    private Number sourceB;
    private Register target;

    public Compare(Types operator,
                   Number sourceA,
                   Number sourceB,
                   Register target) {
        this.sourceA = sourceA;
        this.sourceB = sourceB;
        this.target = target;
        this.operator = operator;
    }

    public void swapSources() {
        Number t = sourceA;
        sourceA = sourceB;
        sourceB = t;
        operator = operator.getReverse();
    }

    public Types getOperator() {
        return operator;
    }

    @Override
    public void accept(IRVisitor IRVisitor) {
        IRVisitor.visit(this);
    }

    @Override
    public void rename(Map<Number, Register> map) {
        if (map.containsKey(sourceA)) sourceA = map.get(sourceA);
        if (sourceA instanceof OffsetRegister) {
            ((OffsetRegister) sourceA).rename(map);
        }
        if (map.containsKey(sourceB)) sourceB = map.get(sourceB);
        if (sourceB instanceof OffsetRegister) {
            ((OffsetRegister) sourceB).rename(map);
        }
        target = map.getOrDefault(target, target);
        if (target instanceof OffsetRegister) {
            ((OffsetRegister) target).rename(map);
        }
    }

    @Override
    public Set<Number> getUseNumbers() {
        Set<Number> set = new HashSet<>();
        addToSet(sourceA, set);
        addToSet(sourceB, set);
        return set;
    }

    @Override
    public Set<Number> getDefNumbers() {
        Set<Number> set = new HashSet<>();
        addToSet(target, set);
        return set;
    }

    public Number getSourceA() {
        return sourceA;
    }

    public void setSourceA(Number sourceA) {
        this.sourceA = sourceA;
    }

    public Number getSourceB() {
        return sourceB;
    }

    public void setSourceB(Number sourceB) {
        this.sourceB = sourceB;
    }

    public Register getTarget() {
        return target;
    }

    public enum Types {
        EQL, NEQ, LT, RT, LEQ, REQ;

        @Override
        public String toString() {
            if (this.equals(EQL)) {
                return "sete";
            } else if (this.equals(NEQ)) {
                return "setne";
            } else if (this.equals(LT)) {
                return "setl";
            } else if (this.equals(RT)) {
                return "setg";
            } else if (this.equals(LEQ)) {
                return "setle";
            } else {
                return "setge";
            }
        }

        Types getReverse() {
            if (this.equals(EQL)) {
                return NEQ;
            } else if (this.equals(NEQ)) {
                return EQL;
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
