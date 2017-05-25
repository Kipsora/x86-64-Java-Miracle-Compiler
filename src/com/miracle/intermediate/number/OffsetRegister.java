package com.miracle.intermediate.number;

import org.apache.commons.lang3.tuple.ImmutablePair;

public class OffsetRegister extends Register { // memory in stack/heap
    public final DirectRegister base;
    public final Immediate offsetA;
    public final ImmutablePair<DirectRegister, Immediate> offsetB;
    public final int size;

    public OffsetRegister(DirectRegister base,
                          Immediate offsetA,
                          ImmutablePair<DirectRegister, Immediate> offsetB,
                          int size) {
        this.base = base;
        this.offsetA = offsetA;
        this.offsetB = offsetB;
        this.size = size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (size == 1) {
            builder.append("byte");
        } else if (size == 2) {
            builder.append("word");
        } else if (size == 4) {
            builder.append("dword");
        } else if (size == 8) {
            builder.append("qword");
        }
        builder.append(" [").append(base);
        if (offsetB != null) {
            builder.append('+').append(offsetB.getLeft()).append('*')
                    .append(offsetB.getRight());
        }
        if (offsetA != null) {
            if (offsetA.value < 0) {
                builder.append(offsetA);
            } else {
                builder.append('+').append(offsetA);
            }
        }
        builder.append(']');
        return builder.toString();
    }

    @Override
    public int getNumberSize() {
        return size;
    }
}
