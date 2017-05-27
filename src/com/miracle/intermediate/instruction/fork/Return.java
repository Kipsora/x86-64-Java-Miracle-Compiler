package com.miracle.intermediate.instruction.fork;

import com.miracle.intermediate.number.Immediate;
import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.visitor.IRVisitor;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class Return extends Fork {
    private Number value;

    public Return(Number value) {
        this.value = value;
    }

    public Number getValue() {
        return value;
    }

    @Override
    public void accept(IRVisitor IRVisitor) {
        IRVisitor.visit(this);
    }

    @Override
    public void rename(Map<Register, Register> map) {
        if (value instanceof Register) {
            value = map.get(value);
        }
    }

    @Override
    public Set<Register> getUseRegisters() {
        return value instanceof Immediate
                ? Collections.emptySet()
                : Collections.singleton((Register) value);
    }

    @Override
    public Set<Register> getDefRegisters() {
        return Collections.emptySet();
    }
}
