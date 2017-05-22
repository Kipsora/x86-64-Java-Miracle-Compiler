package com.miracle.intermediate;

import com.miracle.intermediate.visitor.MiracleIRVisitor;

public abstract class MiracleIRNode {
    public abstract void accept(MiracleIRVisitor visitor);

}
