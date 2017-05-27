package com.miracle.intermediate.instruction;

import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.OffsetRegister;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.visitor.IRVisitor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Move extends Instruction {
    private Register target;
    private Number source;

    public Move(Register target, Number source) {
        this.target = target;
        this.source = source;
    }

    @Override
    public void accept(IRVisitor IRVisitor) {
        IRVisitor.visit(this);
    }

    @Override
    public void rename(Map<Register, Register> map) {
        target = map.getOrDefault(target, target);
        if (target instanceof OffsetRegister) {
            ((OffsetRegister) target).map(map);
        }
        if (source instanceof Register) {
            source = map.getOrDefault(source, (Register) source);
            if (source instanceof OffsetRegister) {
                ((OffsetRegister) source).map(map);
            }
        }
    }

    @Override
    public Set<Register> getUseRegisters() {
        Set<Register> set = new HashSet<>();
        addToSet(source, set);
        return set;
    }

    @Override
    public Set<Register> getDefRegisters() {
        Set<Register> set = new HashSet<>();
        addToSet(target, set);
        return set;
    }

    public Register getTarget() {
        return target;
    }

    public Number getSource() {
        return source;
    }
}
