package com.miracle.intermediate.number;

import java.util.Formatter;

import static com.miracle.intermediate.number.PhysicalRegister.RBP;

public class StackRegister extends IndirectRegister {
    private int offset;

    public StackRegister(int size) {
        super(size);
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getSizeDescriptor()).append(' ')
                .append('[').append(RBP);
        if (offset != 0) builder.append(new Formatter().format("%+d", -offset));
        builder.append(']');
        return builder.toString();
    }
}
