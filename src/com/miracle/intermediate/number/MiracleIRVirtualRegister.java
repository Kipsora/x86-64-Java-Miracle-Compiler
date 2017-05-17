package com.miracle.intermediate.number;

public class MiracleIRVirtualRegister extends MiracleIRNumber {
    public MiracleIRVirtualRegister(int registerId) {
        super(registerId);
    }

    public int getRegisterId() {
        return value;
    }
}
