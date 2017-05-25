package com.miracle.intermediate.instruction.fork;

import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.visitor.Visitor;

import java.util.Map;

public class Jump extends Fork {
    public final BasicBlock block;

    public Jump(BasicBlock block) {
        this.block = block;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void map(Map<Register, Register> map) {

    }
}
