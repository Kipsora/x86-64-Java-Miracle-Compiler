package com.miracle.intermediate.structure;

import com.miracle.intermediate.RegisterBuffer;
import com.miracle.intermediate.number.DirectRegister;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.number.VirtualRegister;
import com.miracle.intermediate.visitor.IRVisitor;
import com.miracle.symbol.SymbolFunctionType;

import java.util.*;

public class Function {
    public final String identifier;
    public final RegisterBuffer buffer;
    public final List<Register> parameters;

    private DirectRegister selfRegister;

    private BasicBlock entryBasicBlock;
    private BasicBlock exitBasicBlock;

    private Set<BasicBlock.Node> returns;

    public Function(String identifier,
                    SymbolFunctionType type) {
        this.identifier = identifier;
        if (type.getMemberFrom() != null) {
            this.selfRegister = new VirtualRegister("this", type.getMemberFrom().getRegisterSize());
        } else {
            this.selfRegister = null;
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

    public List<Register> getReverseParameters() {
        List<Register> reverse = new LinkedList<>(parameters);
        Collections.reverse(reverse);
        return reverse;
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

    public void map(Map<Register, Register> map) {
        if (selfRegister != null) {
            selfRegister = (DirectRegister) map.get(selfRegister);
        }
        for (int i = 0, size = map.size(); i < size; i++) {
            parameters.set(i, map.get(parameters.get(i)));
        }
    }

    public List<Register> getParameters() {
        return parameters;
    }

    public void addParameter(Register parameter) {
        this.parameters.add(parameter);
    }
}
