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
    private List<BasicBlock> postOrder;
    private Set<BasicBlock> visitedBlock;

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

    public void set(Map<Number, Register> map) {
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

    private void dfsPostOrder(BasicBlock node) {
        if (visitedBlock.contains(node)) return;
        visitedBlock.add(node);
        node.getSuccBasicBlock().forEach(this::dfsPostOrder);
        postOrder.add(node);
    }

    public List<BasicBlock> getPostOrder() {
        postOrder = new LinkedList<>();
        visitedBlock = new HashSet<>();
        dfsPostOrder(entryBasicBlock);
        for (int i = 0, size = postOrder.size(); i < size; i++) {
            postOrder.get(i).setDfsOrder(i);
        }
        return postOrder;
    }

    public void rename(Map<VirtualRegister, VirtualRegister> map) {
        selfRegister = map.get((VirtualRegister) selfRegister);
        for (int i = 0, size = parameters.size(); i < size; i++) {
            parameters.set(i, map.get((VirtualRegister) parameters.get(i)));
        }
    }
}
