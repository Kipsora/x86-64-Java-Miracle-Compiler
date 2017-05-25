package com.miracle.intermediate;

import com.miracle.intermediate.visitor.Visitor;

public abstract class Node {
    public abstract void accept(Visitor visitor);
}
