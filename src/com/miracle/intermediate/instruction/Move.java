package com.miracle.intermediate.instruction;

import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.visitor.Visitor;

import java.util.Map;

public class Move extends Instruction {
    private Register target;
    private Number source;

    public Move(Register target, Number source) {
        this.target = target;
        this.source = source;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void map(Map<Register, Register> map) {
        target = map.get(target);
        if (source instanceof Register) source = map.get(source);
    }

    public Register getTarget() {
        return target;
    }

    public Number getSource() {
        return source;
    }
}
