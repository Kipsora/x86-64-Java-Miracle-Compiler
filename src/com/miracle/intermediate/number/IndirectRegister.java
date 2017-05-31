package com.miracle.intermediate.number;

public abstract class IndirectRegister extends Register {
    IndirectRegister(int size) {
        super(size);
    }

    @Override
    public boolean isIndirect() {
        return true;
    }
}
