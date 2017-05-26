package com.miracle.intermediate.instruction;

import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.visitor.IRVisitor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Compare extends Instruction {
    public final Types operator;
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

    @Override
    public void accept(IRVisitor IRVisitor) {
        IRVisitor.visit(this);
    }

    @Override
    public void rename(Map map) {
        if (sourceA instanceof Register) sourceA = (Number) map.getOrDefault(sourceA, sourceA);
        if (sourceB instanceof Register) sourceB = (Number) map.getOrDefault(sourceB, sourceB);
        target = (Register) map.getOrDefault(target, target);
    }

    @Override
    public Set<Register> getUsedRegisters() {
        return new HashSet<Register>() {{
            add(target);
            if (sourceA instanceof Register) add((Register) sourceA);
            if (sourceB instanceof Register) add((Register) sourceB);
        }};
    }

    @Override
    public Set<String> getDeprecatedRegisters() {
        return Collections.emptySet();
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
