package com.miracle.intermediate.instruction;

import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.visitor.IRVisitor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HeapAllocate extends Instruction {
    public final int size;
    private Register register;
    private Number number;

    public HeapAllocate(Register register,
                        int size,
                        Number number) {
        this.register = register;
        this.size = size;
        this.number = number;
    }

    @Override
    public void accept(IRVisitor IRVisitor) {
        IRVisitor.visit(this);
    }

    @Override
    public void rename(Map map) {
        register = (Register) map.getOrDefault(register, register);
        if (number instanceof Register) number = (Number) map.getOrDefault(number, number);
    }

    @Override
    public Set<Register> getUsedRegisters() {
        return new HashSet<Register>() {{
            add(register);
            if (number instanceof Register) {
                add((Register) number);
            }
        }};
    }

    @Override
    public Set<String> getDeprecatedRegisters() {
        return Collections.emptySet();
    }

    public Register getRegister() {
        return register;
    }

    public Number getNumber() {
        return number;
    }
}
