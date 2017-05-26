package com.miracle.intermediate.number;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Formatter;

public class OffsetRegister extends IndirectRegister { // memory in stack/heap
    public final DirectRegister base;
    public final Integer offsetA;
    public final ImmutablePair<DirectRegister, Integer> offsetB;

    public OffsetRegister(DirectRegister base,
                          Integer offsetA,
                          ImmutablePair<DirectRegister, Integer> offsetB,
                          int size) {
        super(size);
        this.base = base;
        this.offsetA = offsetA;
        this.offsetB = offsetB;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getSizeDescriptor()).append(" [").append(base);
        if (offsetB != null) {
            builder.append('+').append(offsetB.getLeft()).append('*')
                    .append(offsetB.getRight());
        }
        if (offsetA != null && offsetA != 0) {
            builder.append(new Formatter().format("%+d", offsetA));
        }
        builder.append(']');
        return builder.toString();
    }

    @Override
    public int getNumberSize() {
        return size;
    }
}
