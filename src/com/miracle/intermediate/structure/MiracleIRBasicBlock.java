package com.miracle.intermediate.structure;

import com.miracle.intermediate.instruction.MiracleIRInstruction;
import com.miracle.intermediate.visitor.MiracleIRVisitor;

import java.util.LinkedList;
import java.util.List;

public class MiracleIRBasicBlock {
    public final Node head;
    public final Node tail;

    private List<MiracleIRBasicBlock> predBB = new LinkedList<>();
    private List<MiracleIRBasicBlock> succBB = new LinkedList<>();

    public MiracleIRBasicBlock() {
        this.head = new Node(null, null, null);
        this.tail = new Node(null, null, null);

        this.head.setSucc(this.tail);
        this.tail.setPrev(this.head);
    }

    public void addPredBB(MiracleIRBasicBlock block) {
        predBB.add(block);
    }

    public void addSuccBB(MiracleIRBasicBlock block) {
        succBB.add(block);
    }

    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }

    public void append(MiracleIRInstruction data) {
        Node node = new Node(tail.prev, tail, data);
        tail.prev.setSucc(node);
        tail.setPrev(node);
    }

    public static class Node {
        public final MiracleIRInstruction instruction;
        private Node prev;
        private Node succ;

        private Node(Node prev, Node succ, MiracleIRInstruction instruction) {
            this.prev = prev;
            this.succ = succ;
            this.instruction = instruction;
        }

        public Node getSucc() {
            return succ;
        }

        private Node setSucc(Node succ) {
            this.succ = succ;
            return this;
        }

        public Node getPrev() {
            return prev;
        }

        private Node setPrev(Node prev) {
            this.prev = prev;
            return this;
        }
    }
}
