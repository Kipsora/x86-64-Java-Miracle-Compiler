package com.miracle.intermediate.instruction;

import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.OffsetRegister;
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
    public void rename(Map<Number, Register> map) {
        for (int i = 0, size = parameters.size(); i < size; i++) {
            Number arg = parameters.get(i);
            if (map.containsKey(arg)) parameters.set(i, map.get(arg));
            if (arg instanceof OffsetRegister) {
                ((OffsetRegister) arg).rename(map);
            }
        }
        if (returnRegister != null) {
            returnRegister = map.getOrDefault(returnRegister, returnRegister);
            if (returnRegister instanceof OffsetRegister) {
                ((OffsetRegister) returnRegister).rename(map);
            }
        }
        if (selfRegister != null) {
            selfRegister = map.getOrDefault(selfRegister, selfRegister);
            if (selfRegister instanceof OffsetRegister) {
                ((OffsetRegister) selfRegister).rename(map);
            }
        }
    }

    @Override
    public Set<Number> getUseNumbers() {
        Set<Number> registers = new HashSet<>();
        addToSet(selfRegister, registers);
        addToSet(returnRegister, registers);
        parameters.forEach(element -> addToSet(element, registers));
        return registers;
    }

    @Override
    public Set<Number> getDefNumbers() {
        return Collections.singleton(returnRegister);
    }

    public Register getReturnRegister() {
        return returnRegister;
    }

    public Register getSelfRegister() {
        return selfRegister;
    }

    public void setSelfRegister(Register selfRegister) {
        this.selfRegister = selfRegister;
    }
}
