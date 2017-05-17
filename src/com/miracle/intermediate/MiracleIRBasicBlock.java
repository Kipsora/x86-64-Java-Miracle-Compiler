package com.miracle.intermediate;

import com.miracle.intermediate.instruction.MiracleIRInstruction;

import java.util.LinkedList;
import java.util.List;

public class MiracleIRBasicBlock extends MiracleIRBase {
    public final Node head = new Node(null, null, null, this);
    public final Node tail = new Node(null, null, null, this);
    private final List<MiracleIRBasicBlock> prevBasicBlock = new LinkedList<>();
    private final List<MiracleIRBasicBlock> succBasicBlock = new LinkedList<>();

    public MiracleIRBasicBlock() {
        head.succ = tail;
        tail.prev = head;
    }

    public Node append(MiracleIRInstruction data) {
        Node node = new Node(tail.prev, tail, data, this);
        tail.prev.succ = node;
        tail.prev = node;
        return node;
    }

    public Node prepend(MiracleIRInstruction data) {
        Node node = new Node(head, head.succ, data, this);
        head.succ.prev = node;
        head.succ = node;
        return node;
    }

    public void remove(Node node) {
        if (node.currentBasicBlock == this) {
            throw new RuntimeException("Invalid operation");
        }
        node.prev.succ = node.succ;
        node.succ.prev = node.prev;
        node.prev = node.succ = null;
    }

    public void addPrevBlock(MiracleIRBasicBlock block) {
        prevBasicBlock.add(block);
        block.succBasicBlock.add(this);
    }

    public void addSuccBlock(MiracleIRBasicBlock block) {
        succBasicBlock.add(block);
        block.prevBasicBlock.add(this);
    }

    public static class Node {
        private final MiracleIRBasicBlock currentBasicBlock;
        private MiracleIRInstruction data;
        private Node prev;
        private Node succ;

        private Node(Node prev,
                     Node succ,
                     MiracleIRInstruction data, MiracleIRBasicBlock currentBasicBlock) {
            this.prev = prev;
            this.succ = succ;
            this.data = data;
            this.currentBasicBlock = currentBasicBlock;
        }

        public MiracleIRInstruction getInstruction() {
            return data;
        }

        public void setInstruction(MiracleIRInstruction data) {
            this.data = data;
        }
    }
}
