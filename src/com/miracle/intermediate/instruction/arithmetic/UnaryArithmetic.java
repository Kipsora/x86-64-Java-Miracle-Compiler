package com.miracle.intermediate.instruction.arithmetic;

import com.miracle.intermediate.instruction.Instruction;
import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.OffsetRegister;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.visitor.IRVisitor;

import java.util.HashSet;
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
    public void rename(Map<Number, Register> map) {
        target = map.getOrDefault(target, target);
        if (target instanceof OffsetRegister) {
            ((OffsetRegister) target).map(map);
        }
    }

    @Override
    public Set<Number> getUseNumbers() {
        Set<Number> set = new HashSet<>();
        addToSet(target, set);
        return set;
    }

    @Override
    public Set<Number> getDefNumbers() {
        Set<Number> set = new HashSet<>();
        addToSet(target, set);
        return set;
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
