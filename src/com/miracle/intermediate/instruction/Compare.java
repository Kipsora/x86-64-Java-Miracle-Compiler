package com.miracle.intermediate.instruction;

import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.visitor.Visitor;

import java.util.Map;

public class Compare extends Instruction {
    private Number sourceA;
    private Number sourceB;
    private Register target;
    public final Types operator;

    public Compare(Types operator,
                   Number sourceA,
                   Number sourceB,
                   Register target) {
        this.sourceA = sourceA;
        this.sourceB = sourceB;
        this.target = target;
        this.operator = operator;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void map(Map<Register, Register> map) {
        if (sourceA instanceof Register) sourceA = map.get(sourceA);
        if (sourceB instanceof Register) sourceB = map.get(sourceB);
        target = map.get(target);
    }

    public Number getSourceA() {
        return sourceA;
    }

    public Number getSourceB() {
        return sourceB;
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
    }
}
