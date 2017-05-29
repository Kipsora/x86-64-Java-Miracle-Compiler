package com.miracle.intermediate.instruction;

import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.OffsetRegister;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.number.VirtualRegister;
import com.miracle.intermediate.visitor.IRVisitor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HeapAllocate extends Instruction {
    private int size;
    private Register target;
    private Number number;

    public HeapAllocate(Register target,
                        int size,
                        Number number) {
        this.target = target;
        this.size = size;
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
        if (map.containsKey(number)) number = map.get(number);
        if (number instanceof OffsetRegister) {
            ((OffsetRegister) number).set(map);
        }
    }

    @Override
    public void rename(Map<VirtualRegister, VirtualRegister> map) {
        if (target instanceof VirtualRegister) {
            target = map.get(target);
        } else if (target instanceof OffsetRegister) {
            ((OffsetRegister) target).rename(map);
        }
        if (number instanceof VirtualRegister) {
            number = map.get(number);
        } else if (number instanceof OffsetRegister) {
            ((OffsetRegister) number).rename(map);
        }
    }

    @Override
    public Set<Number> getUseNumbers() {
        Set<Number> set = new HashSet<>();
        addToSet(number, set);
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

    public void setTarget(Register target) {
        this.target = target;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }
}
