package com.miracle.intermediate.structure;

import com.miracle.intermediate.RegisterBuffer;
import com.miracle.intermediate.number.DirectRegister;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.number.VirtualRegister;
import com.miracle.intermediate.visitor.Visitor;
import com.miracle.symbol.SymbolFunctionType;
import com.miracle.symbol.SymbolVariableType;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.miracle.symbol.SymbolTable.__builtin_void;

public class Function {
    public final String identifier;
    public final List<Register> parameters;
    private DirectRegister returnRegister;
    private DirectRegister selfRegister;

    private RegisterBuffer buffer;

    private BasicBlock entryBasicBlock;
    private BasicBlock exitBasicBlock;

    public List<Register> getReverseParameters() {
        List<Register> reverse = new LinkedList<>(parameters);
        Collections.reverse(reverse);
        return reverse;
    }

    public Function(String identifier,
                    SymbolFunctionType type) {
        this.identifier = identifier;
        if (type.getReturnType() != null && !type.getReturnType().isSameType(__builtin_void)) {
            this.returnRegister = new VirtualRegister(".retval", type.getReturnType().getRegisterSize());
        } else {
            this.returnRegister = null;
        }
        this.parameters = new LinkedList<>();
        List<String> argName = type.getArgName();
        List<SymbolVariableType> argType = type.getArgType();
        for (int i = 0, size = argName.size(); i < size; i++) {
            if (argType.get(i) == null) break;
            this.parameters.add(new VirtualRegister(
                    argName.get(i),
                    argType.get(i).getRegisterSize()
            ));
        }
        if (type.getMemberFrom() != null) {
            this.selfRegister = new VirtualRegister("this", type.getMemberFrom().getRegisterSize());
        } else {
            this.selfRegister = null;
        }
        this.entryBasicBlock = new BasicBlock("__" + identifier + ".entry", this, true, false);
        this.exitBasicBlock = new BasicBlock("__" + identifier + ".exit", this, false, true);
    }

    public BasicBlock getEntryBasicBlock() {
        return entryBasicBlock;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public BasicBlock getExitBasicBlock() {
        return exitBasicBlock;
    }

    public RegisterBuffer getBuffer() {
        return buffer;
    }

    public void setBuffer(RegisterBuffer buffer) {
        this.buffer = buffer;
    }

    public Register getReturnRegister() {
        return returnRegister;
    }

    public DirectRegister getSelfRegister() {
        return selfRegister;
    }

    public void map(Map<Register, Register> map) {
        if (returnRegister != null) {
            returnRegister = (DirectRegister) map.get(returnRegister);
        }
        if (selfRegister != null) {
            selfRegister = (DirectRegister) map.get(selfRegister);
        }
        for (int i = 0, size = map.size(); i < size; i++) {
            parameters.set(i, map.get(parameters.get(i)));
        }
    }
}