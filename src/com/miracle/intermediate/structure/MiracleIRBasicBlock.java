package com.miracle.intermediate.structure;

import com.miracle.intermediate.instruction.MiracleIRInstruction;
import com.miracle.intermediate.instruction.fork.MiracleIRFork;
import com.miracle.intermediate.visitor.MiracleIRVisitor;

import java.util.HashSet;
import java.util.Set;

public class MiracleIRBasicBlock {
    public final String name;

    public final Node tail;
    public final MiracleIRFunction blockFrom;
    public final boolean isFunctionEntryBlock;
    public final boolean isFunctionExitBlock;
    private final Node head;
    private boolean isForked;
    private Set<MiracleIRBasicBlock> prevBasicBlock = new HashSet<>();
    private Set<MiracleIRBasicBlock> succBasicBlock = new HashSet<>();

    public MiracleIRBasicBlock(String name,
                               MiracleIRFunction blockFrom,
                               boolean isFunctionEntryBlock,
                               boolean isFunctionExitBlock) {
        this.name = name;
        this.blockFrom = blockFrom;
        this.isFunctionEntryBlock = isFunctionEntryBlock;
        this.isFunctionExitBlock = isFunctionExitBlock;
        this.head = new Node(null);
        this.tail = new Node(null);
        this.head.succ = this.tail;
        this.tail.prev = this.head;
    }

    public boolean isForked() {
        return isForked;
    }

    public Set<MiracleIRBasicBlock> getPrevBasicBlock() {
        return prevBasicBlock;
    }

    public Set<MiracleIRBasicBlock> getSuccBasicBlock() {
        return succBasicBlock;
    }

    public void setFork(MiracleIRFork fork) {
        if (isForked) {
            throw new RuntimeException("already forked");
        }
        tail.prepend(fork);
        isForked = true;
    }

    public void addSuccBasicBlock(MiracleIRBasicBlock block) {
        succBasicBlock.add(block);
        block.prevBasicBlock.add(this);
    }

    public void addPredBasicBlock(MiracleIRBasicBlock block) {
        prevBasicBlock.add(block);
        block.succBasicBlock.add(this);
    }

    public Node getHead() {
        return head.succ;
    }

    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }

    public void clearSuccBlock() {
        succBasicBlock.forEach(element -> element.prevBasicBlock.remove(this));
        succBasicBlock.clear();
    }

    public class Node {
        public final MiracleIRInstruction instruction;
        private Node prev;
        private Node succ;

        public Node(MiracleIRInstruction instruction) {
            this.instruction = instruction;
        }

        public Node getSucc() {
            return succ;
        }

        public Node getPrev() {
            return prev;
        }

        public void append(MiracleIRInstruction instruction) {
            Node node = new Node(instruction);
            node.prev = this;
            node.succ = this.succ;
            if (succ != null) succ.prev = node;
            this.succ = node;
        }

        public void prepend(MiracleIRInstruction instruction) {
            Node node = new Node(instruction);
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
