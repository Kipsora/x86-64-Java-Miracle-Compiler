package com.miracle.intermediate.instruction;

import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.visitor.Visitor;

import java.util.Map;

public class HeapAllocate extends Instruction {
    private Register register;
    public final int size;
    private Number number;

    public HeapAllocate(Register register,
                        int size,
                        Number number) {
        this.register = register;
        this.size = size;
        this.number = number;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void map(Map<Register, Register> map) {
        register = map.get(register);
        if (number instanceof Register) number = map.get(number);
    }

    public Register getRegister() {
        return register;
    }

    public Number getNumber() {
        return number;
    }
}
