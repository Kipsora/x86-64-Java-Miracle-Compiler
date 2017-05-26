package com.miracle.intermediate.instruction.fork;

import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.visitor.IRVisitor;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class Return extends Fork {
    @Override
    public void accept(IRVisitor IRVisitor) {
        IRVisitor.visit(this);
    }

    @Override
    public void rename(Map map) {

    }

    @Override
    public Set<Register> getUsedRegisters() {
        return Collections.emptySet();
    }

    @Override
    public Set<String> getDeprecatedRegisters() {
        return Collections.emptySet();
    }
}
