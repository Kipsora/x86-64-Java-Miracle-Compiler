package com.miracle.intermediate.structure;

import com.miracle.intermediate.instruction.Instruction;
import com.miracle.intermediate.instruction.PhiInstruction;
import com.miracle.intermediate.instruction.fork.Fork;
import com.miracle.intermediate.number.VirtualRegister;
import com.miracle.intermediate.visitor.IRVisitor;

import java.util.*;

public class BasicBlock {
    public final String name;
    public final Function blockFrom;

    public final Node tail;
    private final Node head;

    private boolean isFunctionEntryBlock;
    private boolean isFunctionExitBlock;

    private boolean isForked;

    private Set<BasicBlock> predBasicBlock = new HashSet<>();
    private Set<BasicBlock> succBasicBlock = new HashSet<>();

    public final Set<BasicBlock> dfSet;
    public final Map<VirtualRegister, PhiInstruction> phis;
    public final Set<BasicBlock> DTChildren;
    private BasicBlock idom;
    private int dfsOrder;

    public static class Liveliness {
        public Set<VirtualRegister> liveIn;
        public Set<VirtualRegister> liveOut;
        public List<VirtualRegister> used;
        public List<VirtualRegister> defined;
    }

    public final Liveliness liveliness;

    public void setDfsOrder(int dfsOrder) {
        this.dfsOrder = dfsOrder;
    }

    public int getDfsOrder() {
        return dfsOrder;
    }

    public BasicBlock getIdom() {
        return idom;
    }

    public void setIdom(BasicBlock idom) {
        this.idom = idom;
    }

    public boolean isFunctionEntryBlock() {
        return isFunctionEntryBlock;
    }

    public boolean isFunctionExitBlock() {
        return isFunctionExitBlock;
    }

    public void setFunctionExitBlock() {
        isFunctionExitBlock = true;
    }

    public void setFunctionEntryBlock() {
        isFunctionEntryBlock = true;
    }

    public BasicBlock(String name,
                      Function blockFrom,
                      boolean isFunctionEntryBlock,
                      boolean isFunctionExitBlock) {
        this.name = name;
        this.blockFrom = blockFrom;
        this.isFunctionEntryBlock = isFunctionEntryBlock;
        this.isFunctionExitBlock = isFunctionExitBlock;
        this.head = new Node(null, this);
        this.tail = new Node(null, this);
        this.head.succ = this.tail;
        this.tail.prev = this.head;
        this.dfSet = new HashSet<>();
        this.phis = new HashMap<>();
        this.DTChildren = new HashSet<>();
        this.liveliness = new Liveliness();
    }

    public boolean isForked() {
        return isForked;
    }

    public Set<BasicBlock> getPredBasicBlock() {
        return predBasicBlock;
    }

    public Set<BasicBlock> getSuccBasicBlock() {
        return succBasicBlock;
    }

    public void setFork(Fork fork) {
        if (isForked) {
            throw new RuntimeException("already forked");
        }
        tail.prepend(fork);
        isForked = true;
    }

    public void addSuccBasicBlock(BasicBlock block) {
        succBasicBlock.add(block);
        block.predBasicBlock.add(this);
    }

    public void addPredBasicBlock(BasicBlock block) {
        predBasicBlock.add(block);
        block.succBasicBlock.add(this);
    }

    public Node getHead() {
        return head.succ;
    }

    public void accept(IRVisitor IRVisitor) {
        IRVisitor.visit(this);
    }

    public void addToDFSet(BasicBlock block) {
        dfSet.add(block);
    }

    public void removeSucc(BasicBlock block) {
        this.succBasicBlock.remove(block);
        block.predBasicBlock.remove(this);
    }

    public class Node {
        public final Instruction instruction;
        public final BasicBlock block;
        private Node prev;
        private Node succ;

        private Node(Instruction instruction, BasicBlock block) {
            this.block = block;
            this.instruction = instruction;
        }

        public Node getSucc() {
            return succ;
        }

        public Node getPrev() {
            return prev;
        }

        public void append(Instruction instruction) {
            Node node = new Node(instruction, this.block);
            node.prev = this;
            node.succ = this.succ;
            if (succ != null) succ.prev = node;
            this.succ = node;
        }

        public void prepend(Instruction instruction) {
            Node node = new Node(instruction, this.block);
            node.succ = this;
            node.prev = this.prev;
            if (prev != null) prev.succ = node;
            this.prev = node;
        }

        public void remove() {
            this.prev.succ = this.succ;
            this.succ.prev = this.prev;
        }
    }
}
