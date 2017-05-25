package com.miracle.intermediate.instruction.fork;

import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.visitor.Visitor;

import java.util.Map;

public class Return extends Fork {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void map(Map<Register, Register> map) {

    }
}
