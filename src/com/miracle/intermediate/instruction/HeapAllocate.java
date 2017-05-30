package com.miracle.intermediate.instruction;

import com.miracle.intermediate.RegisterBuffer;
import com.miracle.intermediate.number.*;
import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.visitor.IRVisitor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.miracle.intermediate.number.PhysicalRegister.*;

public class HeapAllocate extends Instruction {
    private int size;
    private Register target;
    private Number number;
    public final Set<PhysicalRegister> callerSave;

    public HeapAllocate(Register target,
                        int size,
                        Number number) {
        this.target = target;
        this.size = size;
        this.number = number;
        this.callerSave = new HashSet<>(Arrays.asList(RDI, RDX, RCX, R8, R9, R10, R11));
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
        addToSet(number, set, false);
        if (target instanceof OffsetRegister) {
            if (((OffsetRegister) target).getRawBase() != null) {
                set.add(((OffsetRegister) target).getRawBase());
            }
            if (((OffsetRegister) target).getRawOffsetB() != null) {
                set.add(((OffsetRegister) target).getRawOffsetB());
            }
        }
        return set;
    }

    @Override
    public Set<Number> getDefNumbers() {
        Set<Number> set = new HashSet<>();
        addToSet(target, set, true);
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
