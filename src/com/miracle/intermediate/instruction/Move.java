package com.miracle.intermediate.instruction;

import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.visitor.IRVisitor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Move extends Instruction {
    private Register target;
    private Number source;

    public Move(Register target, Number source) {
        this.target = target;
        this.source = source;
    }

    @Override
    public void accept(IRVisitor IRVisitor) {
        IRVisitor.visit(this);
    }

    @Override
    public void rename(Map map) {
        target = (Register) map.getOrDefault(target, target);
        if (source instanceof Register) {
            source = (Number) map.getOrDefault(source, source);
        }
    }

    @Override
    public Set<Register> getUsedRegisters() {
        return new HashSet<Register>() {{
            add(target);
            if (source instanceof Register) {
                add((Register) source);
            }
        }};
    }

    @Override
    public Set<String> getDeprecatedRegisters() {
        return Collections.emptySet();
    }

    public Register getTarget() {
        return target;
    }

    public Number getSource() {
        return source;
    }
}
