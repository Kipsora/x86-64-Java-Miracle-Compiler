package com.miracle.intermediate.structure;

import com.miracle.intermediate.instruction.MiracleIRInstruction;
import com.miracle.intermediate.instruction.fork.MiracleIRFork;
import com.miracle.intermediate.visitor.MiracleIRVisitor;

import java.util.LinkedList;
import java.util.List;

public class MiracleIRBasicBlock {
    public final String name;

    public final Node tail;
    private final Node head;
    private boolean isForked;

    private List<MiracleIRBasicBlock> prevBasicBlock = new LinkedList<>();
    private List<MiracleIRBasicBlock> succBasicBlock = new LinkedList<>();

    public MiracleIRBasicBlock(String name) {
        this.name = name;
        this.head = new Node(null);
        this.tail = new Node(null);
        this.head.succ = this.tail;
        this.tail.prev = this.head;
    }

    public boolean isForked() {
        return isForked;
    }

    public List<MiracleIRBasicBlock> getPrevBasicBlock() {
        return prevBasicBlock;
    }

    public List<MiracleIRBasicBlock> getSuccBasicBlock() {
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
    }
}
