package com.miracle.intermediate.number;

import java.util.Formatter;

import static com.miracle.intermediate.number.PhysicalRegister.RBP;

public class StackRegister extends IndirectRegister {
    private Integer offset = null;

    public StackRegister(int size) {
        super(size);
    }

    public void setOffset(int offset) {
        if (this.offset != null) {
            throw new RuntimeException("already settled");
        }
        this.offset = offset;
    }

    public boolean isOffsetSettled() {
        return offset != null;
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
