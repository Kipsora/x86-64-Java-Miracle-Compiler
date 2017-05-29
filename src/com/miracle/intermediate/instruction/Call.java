package com.miracle.intermediate.instruction;

import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.OffsetRegister;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.number.VirtualRegister;
import com.miracle.intermediate.structure.Function;
import com.miracle.intermediate.visitor.IRVisitor;

import java.util.*;

public class Call extends Instruction {
    public final Function function;
    public final List<Number> parameters;
    private Register target;
    private Register selfRegister;

    public Call(Function function,
                List<Number> parameters,
                Register target,
                Register selfRegister) {
        this.function = function;
        this.parameters = parameters;
        this.target = target;
        this.selfRegister = selfRegister;
    }

    @Override
    public void accept(IRVisitor IRVisitor) {
        IRVisitor.visit(this);
    }

    @Override
    public void set(Map<Number, Register> map) {
        for (int i = 0, size = parameters.size(); i < size; i++) {
            Number arg = parameters.get(i);
            if (map.containsKey(arg)) parameters.set(i, map.get(arg));
            if (arg instanceof OffsetRegister) {
                ((OffsetRegister) arg).set(map);
            }
        }
        if (target != null) {
            target = map.getOrDefault(target, target);
            if (target instanceof OffsetRegister) {
                ((OffsetRegister) target).set(map);
            }
        }
        if (selfRegister != null) {
            selfRegister = map.getOrDefault(selfRegister, selfRegister);
            if (selfRegister instanceof OffsetRegister) {
                ((OffsetRegister) selfRegister).set(map);
            }
        }
    }

    @Override
    public void rename(Map<VirtualRegister, VirtualRegister> map) {
        if (target instanceof VirtualRegister) {
            target = map.get(target);
        } else if (target instanceof OffsetRegister) {
            ((OffsetRegister) target).rename(map);
        }
        if (selfRegister instanceof VirtualRegister) {
            selfRegister = map.get(selfRegister);
        } else if (selfRegister instanceof OffsetRegister) {
            ((OffsetRegister) selfRegister).rename(map);
        }
        for (int i = 0, size = parameters.size(); i < size; i++) {
            Number arg = parameters.get(i);
            if (arg instanceof VirtualRegister) {
                parameters.set(i, map.get(arg));
            } else if (arg instanceof OffsetRegister) {
                ((OffsetRegister) arg).rename(map);
            }
        }
    }

    @Override
    public Set<Number> getUseNumbers() {
        Set<Number> registers = new HashSet<>();
        addToSet(selfRegister, registers);
        parameters.forEach(element -> addToSet(element, registers));
        return registers;
    }

    @Override
    public Set<Number> getDefNumbers() {
        return Collections.singleton(target);
    }

    public Register getTarget() {
        return target;
    }

    public Register getSelfRegister() {
        return selfRegister;
    }

    public void setSelfRegister(Register selfRegister) {
        this.selfRegister = selfRegister;
    }
}
