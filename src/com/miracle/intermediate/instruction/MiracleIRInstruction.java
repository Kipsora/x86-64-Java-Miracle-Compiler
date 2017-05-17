package com.miracle.intermediate.instruction;

import com.miracle.intermediate.MiracleIRBase;
import com.miracle.intermediate.visitor.MiracleIRVisitor;

public abstract class MiracleIRInstruction extends MiracleIRBase {
    public abstract void accept(MiracleIRVisitor visitor);
}
