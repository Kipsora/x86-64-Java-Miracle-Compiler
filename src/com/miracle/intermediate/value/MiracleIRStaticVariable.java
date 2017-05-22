package com.miracle.intermediate.value;

import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRStaticVariable {
    public final String identifier;
    public final int size;

    public MiracleIRStaticVariable(String identifier, int size) {
        this.identifier = identifier;
        this.size = size;
    }

    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }
}
