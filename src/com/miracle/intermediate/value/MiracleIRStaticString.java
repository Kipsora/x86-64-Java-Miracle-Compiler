package com.miracle.intermediate.value;

import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRStaticString extends MiracleIRMemory {
    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }
}
