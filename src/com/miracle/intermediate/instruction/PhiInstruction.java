package com.miracle.intermediate.instruction;

import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.number.VirtualRegister;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.visitor.IRVisitor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PhiInstruction extends Instruction {
    private VirtualRegister register;
    public final Map<BasicBlock, VirtualRegister> args;

    public VirtualRegister getRegister() {
        return register;
    }

    public PhiInstruction(VirtualRegister register) {
        this.register = register;
        this.args = new HashMap<>();
    }

    @Override
    public void accept(IRVisitor IRVisitor) {
        IRVisitor.visit(this);
    }

    @Override
    public void set(Map<Number, Register> map) {
        throw new RuntimeException("unsupported method");
    }

    @Override
    public void rename(Map<VirtualRegister, VirtualRegister> map) {
        register = map.get(register);
    }

    @Override
    public Set<Number> getUseNumbers() {
        throw new RuntimeException("unsupported method");
    }

    @Override
    public Set<Number> getDefNumbers() {
        Set<Number> set = new HashSet<>();
        addToSet(register, set, true);
        return set;
    }
}
