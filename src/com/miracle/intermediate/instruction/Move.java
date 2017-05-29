package com.miracle.intermediate.instruction;

import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.OffsetRegister;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.number.VirtualRegister;
import com.miracle.intermediate.visitor.IRVisitor;

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
    public void set(Map<Number, Register> map) {
        target = map.getOrDefault(target, target);
        if (target instanceof OffsetRegister) {
            ((OffsetRegister) target).set(map);
        }
        if (map.containsKey(source)) source = map.get(source);
        if (source instanceof OffsetRegister) {
            ((OffsetRegister) source).set(map);
        }
    }

    @Override
    public void rename(Map<VirtualRegister, VirtualRegister> map) {
        if (target instanceof VirtualRegister) {
            target = map.get(target);
        } else if (target instanceof OffsetRegister) {
            ((OffsetRegister) target).rename(map);
        }
        if (source instanceof VirtualRegister) {
            source = map.get(source);
        } else if (source instanceof OffsetRegister) {
            ((OffsetRegister) source).rename(map);
        }
    }

    @Override
    public Set<Number> getUseNumbers() {
        Set<Number> set = new HashSet<>();
        addToSet(source, set);
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

    public Number getSource() {
        return source;
    }
}
