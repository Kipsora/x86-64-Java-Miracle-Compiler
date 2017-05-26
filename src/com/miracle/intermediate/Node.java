package com.miracle.intermediate;

import com.miracle.intermediate.visitor.IRVisitor;

public abstract class Node {
    public abstract void accept(IRVisitor IRVisitor);
}
