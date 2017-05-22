package com.miracle.intermediate.structure;

import com.miracle.intermediate.number.MiracleIRVirtualRegister;
import com.miracle.intermediate.visitor.MiracleIRVisitor;

import java.util.List;

public class MiracleIRFunction {
    public final String identifier;
    public final List<MiracleIRVirtualRegister> parameters;

    private MiracleIRBasicBlock entryBasicBlock;

    public MiracleIRFunction(String identifier,
                             List<MiracleIRVirtualRegister> parameters) {
        this.identifier = identifier;
        this.parameters = parameters;
        this.entryBasicBlock = new MiracleIRBasicBlock("__" + identifier + ".entry");
    }

    public MiracleIRBasicBlock getEntryBasicBlock() {
        return entryBasicBlock;
    }

    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }
}
