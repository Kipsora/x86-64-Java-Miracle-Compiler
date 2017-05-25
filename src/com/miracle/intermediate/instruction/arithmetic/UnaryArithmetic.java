package com.miracle.intermediate.instruction.arithmetic;

import com.miracle.intermediate.instruction.Instruction;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.visitor.Visitor;

import java.util.Map;

public class UnaryArithmetic extends Instruction {
    private Register target;
    public final Types operator;

    public UnaryArithmetic(Register target, Types operator) {
        this.target = target;
        this.operator = operator;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void map(Map<Register, Register> map) {
        target = map.get(target);
    }

    public Register getTarget() {
        return target;
    }

    public enum Types {
        ADD, SUB, REV, MINUS;

        @Override
        public String toString() {
            if (this.equals(ADD)) {
                return "inc";
            } else if (this.equals(SUB)) {
                return "dec";
            } else if (this.equals(REV)) {
                return "not";
            } else {
                return "neg";
            }
        }
    }
}
