package com.miracle.intermediate.instruction.fork;

import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.visitor.IRVisitor;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class Jump extends Fork {
    public final BasicBlock block;

    public Jump(BasicBlock block) {
        this.block = block;
    }

    @Override
    public void accept(IRVisitor IRVisitor) {
        IRVisitor.visit(this);
    }

    @Override
    public void rename(Map<Number, Register> map) {

    }

    @Override
    public Set<Number> getUseNumbers() {
        return Collections.emptySet();
    }

    @Override
    public Set<Number> getDefNumbers() {
        return Collections.emptySet();
    }
}
