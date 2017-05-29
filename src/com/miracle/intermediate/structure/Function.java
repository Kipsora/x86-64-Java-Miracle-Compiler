package com.miracle.intermediate.structure;

import com.miracle.intermediate.RegisterBuffer;
import com.miracle.intermediate.number.DirectRegister;
import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.number.VirtualRegister;
import com.miracle.intermediate.visitor.IRVisitor;
import com.miracle.symbol.SymbolFunctionType;

import java.util.*;

import static com.miracle.symbol.SymbolTable.__builtin_void;

public class Function {
    public final String identifier;
    public final RegisterBuffer buffer;
    public final List<Register> parameters;

    private DirectRegister selfRegister;

    private BasicBlock entryBasicBlock;
    private BasicBlock exitBasicBlock;

    private Set<BasicBlock.Node> returns;
    private Register returnRegister;

    public Function(String identifier,
                    SymbolFunctionType type) {
        this.identifier = identifier;
        if (type.getMemberFrom() != null) {
            this.selfRegister = new VirtualRegister("this", type.getMemberFrom().getRegisterSize());
        } else {
            this.selfRegister = null;
        }
        if (type.getReturnType() != null && !type.getReturnType().isSameType(__builtin_void)) {
            returnRegister = new VirtualRegister("retval", type.getReturnType().getRegisterSize());
        }
        this.parameters = new LinkedList<>();
        this.entryBasicBlock = new BasicBlock("__" + identifier + ".entry", this, true, false);
        this.exitBasicBlock = new BasicBlock("__" + identifier + ".exit", this, false, true);
        this.buffer = new RegisterBuffer();
        this.returns = new HashSet<>();
    }

    public void addReturn(BasicBlock.Node node) {
        returns.add(node);
    }

    public Set<BasicBlock.Node> getReturns() {
        return returns;
    }

    public BasicBlock getEntryBasicBlock() {
        return entryBasicBlock;
    }

    public void accept(IRVisitor IRVisitor) {
        IRVisitor.visit(this);
    }

    public BasicBlock getExitBasicBlock() {
        return exitBasicBlock;
    }

    public DirectRegister getSelfRegister() {
        return selfRegister;
    }

    public void setSelfRegister(DirectRegister selfRegister) {
        this.selfRegister = selfRegister;
    }

    public void map(Map<Number, Register> map) {
        if (returnRegister != null) {
            returnRegister = map.getOrDefault(returnRegister, returnRegister);
        }
        if (selfRegister != null) {
            selfRegister = (DirectRegister) map.getOrDefault(selfRegister, selfRegister);
        }
        for (int i = 0, size = parameters.size(); i < size; i++) {
            parameters.set(i, map.getOrDefault(parameters.get(i), parameters.get(i)));
        }
    }

    public List<Register> getParameters() {
        return parameters;
    }

    public void addParameter(Register parameter) {
        this.parameters.add(parameter);
    }

    public Register getReturnRegister() {
        return returnRegister;
    }
}
