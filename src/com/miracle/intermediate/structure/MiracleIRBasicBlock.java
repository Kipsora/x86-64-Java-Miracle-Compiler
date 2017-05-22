package com.miracle.intermediate.structure;

import com.miracle.intermediate.instruction.MiracleIRInstruction;
import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRBasicBlock {
    public final String name;

    public final Node tail;
    private final Node head;

    public MiracleIRBasicBlock(String name) {
        this.name = name;
        this.head = new Node(null);
        this.tail = new Node(null);
        this.head.succ = this.tail;
        this.tail.prev = this.head;
    }

    public Node getHead() {
        return head.succ;
    }

    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }

    public static class Node {
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
