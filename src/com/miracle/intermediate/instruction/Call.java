package com.miracle.intermediate.instruction;

import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.structure.Function;
import com.miracle.intermediate.visitor.IRVisitor;

import java.util.*;

public class Call extends Instruction {
    public final Function function;
    public final List<Number> parameters;
    private Register returnRegister;
    private Register selfRegister;

    public Call(Function function,
                List<Number> parameters,
                Register returnRegister,
                Register selfRegister) {
        this.function = function;
        this.parameters = parameters;
        this.returnRegister = returnRegister;
        this.selfRegister = selfRegister;
    }

    public List<Number> getReverseParameters() {
        List<Number> reverse = new LinkedList<>(parameters);
        Collections.reverse(reverse);
        return reverse;
    }

    @Override
    public void accept(IRVisitor IRVisitor) {
        IRVisitor.visit(this);
    }

    @Override
    public void rename(Map map) {
        for (int i = 0, size = parameters.size(); i < size; i++) {
            if (parameters.get(i) instanceof Register) {
                Register register = (Register) parameters.get(i);
                parameters.set(i, (Number) map.getOrDefault(register, register));
            }
        }
        if (returnRegister != null) {
            returnRegister = (Register) map.getOrDefault(returnRegister, returnRegister);
        }
        if (selfRegister != null) {
            selfRegister = (Register) map.getOrDefault(selfRegister, selfRegister);
        }
    }

    @Override
    public Set<Register> getUsedRegisters() {
        Set<Register> registers = new HashSet<>();
        if (selfRegister != null) registers.add(selfRegister);
        if (returnRegister != null) registers.add(returnRegister);
        parameters.forEach(element -> {
            if (element instanceof Register) {
                registers.add((Register) element);
            }
        });
        return registers;
    }

    @Override
    public Set<String> getDeprecatedRegisters() {
        return Collections.emptySet();
    }

    public Register getReturnRegister() {
        return returnRegister;
    }

    public Register getSelfRegister() {
        return selfRegister;
    }
}
