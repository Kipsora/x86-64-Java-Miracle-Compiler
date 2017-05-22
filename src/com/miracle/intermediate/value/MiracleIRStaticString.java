package com.miracle.intermediate.value;

import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRStaticString {
    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }
}
