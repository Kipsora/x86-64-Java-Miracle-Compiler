package com.miracle.intermediate.instruction.arithmetic;

import com.miracle.intermediate.instruction.Instruction;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.visitor.IRVisitor;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class UnaryArithmetic extends Instruction {
    public final Types operator;
    private Register target;

    public UnaryArithmetic(Register target, Types operator) {
        this.target = target;
        this.operator = operator;
    }

    @Override
    public void accept(IRVisitor IRVisitor) {
        IRVisitor.visit(this);
    }

    @Override
    public void rename(Map map) {
        target = (Register) map.getOrDefault(target, target);
    }

    @Override
    public Set<Register> getUsedRegisters() {
        return Collections.singleton(target);
    }

    @Override
    public Set<String> getDeprecatedRegisters() {
        return Collections.emptySet();
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
