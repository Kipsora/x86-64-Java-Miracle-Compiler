package com.miracle.intermediate.structure;

import com.miracle.intermediate.MiracleIRBase;
import com.miracle.intermediate.MiracleIRBasicBlock;
import com.miracle.symbol.type.MiracleVariableType;

public class MiracleIRFunction extends MiracleIRBase {
    private final MiracleVariableType returnType;
    private MiracleIRBasicBlock entryBasicBlock;

    public MiracleIRFunction(MiracleVariableType returnType,
                             MiracleIRBasicBlock block) {
        this.returnType = returnType;
        this.entryBasicBlock = block;
    }

    public MiracleIRFunction(MiracleVariableType returnType) {
        this.returnType = returnType;
    }

    public MiracleIRBasicBlock getEntryBasicBlock() {
        return entryBasicBlock;
    }

    public void setEntryBasicBlock(MiracleIRBasicBlock entryBasicBlock) {
        this.entryBasicBlock = entryBasicBlock;
    }
}
