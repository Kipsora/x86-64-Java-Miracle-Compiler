package com.miracle.intermediate;

public class MiracleIRInstruction {
    private final MiracleIRInstruction prev;
    private final MiracleIRInstruction succ;
    private final MiracleIRBasicBlock currentBasicBlock;

    public MiracleIRInstruction(MiracleIRInstruction prev,
                                MiracleIRInstruction succ,
                                MiracleIRBasicBlock currentBasicBlock) {
        this.prev = prev;
        this.succ = succ;
        this.currentBasicBlock = currentBasicBlock;
    }
}
