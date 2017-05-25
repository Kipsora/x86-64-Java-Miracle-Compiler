package com.miracle.intermediate.instruction;

import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.structure.Function;
import com.miracle.intermediate.visitor.Visitor;

import java.util.*;

public class Call extends Instruction {
    public final Function function;
    public final List<Number> parameters;
    private Register returnRegister;
    private Register selfRegister;

    public List<Number> getReverseParameters() {
        List<Number> reverse = new LinkedList<>(parameters);
        Collections.reverse(reverse);
        return reverse;
    }

    public Call(Function function,
                List<Number> parameters,
                Register returnRegister,
                Register selfRegister) {
        this.function = function;
        this.parameters = parameters;
        this.returnRegister = returnRegister;
        this.selfRegister = selfRegister;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void map(Map<Register, Register> map) {
        for (int i = 0, size = parameters.size(); i < size; i++) {
            if (parameters.get(i) instanceof Register) {
                parameters.set(i, map.get(parameters.get(i)));
            }
        }
        if (returnRegister != null) {
            returnRegister = map.get(returnRegister);
        }
        if (selfRegister != null) {
            selfRegister = map.get(selfRegister);
        }
    }

    public Register getReturnRegister() {
        return returnRegister;
    }

    public Register getSelfRegister() {
        return selfRegister;
    }
}
