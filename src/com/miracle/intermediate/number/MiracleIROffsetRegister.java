package com.miracle.intermediate.number;

import org.apache.commons.lang3.tuple.ImmutablePair;

public class MiracleIROffsetRegister extends MiracleIRRegister { // memory in stack/heap
    public final MiracleIRDirectRegister base;
    public final MiracleIRImmediate offsetA;
    public final ImmutablePair<MiracleIRDirectRegister, MiracleIRImmediate> offsetB;
    public final int size;

    public MiracleIROffsetRegister(MiracleIRDirectRegister base,
                                   MiracleIRImmediate offsetA,
                                   ImmutablePair<MiracleIRDirectRegister, MiracleIRImmediate> offsetB,
                                   int size) {
        this.base = base;
        this.offsetA = offsetA;
        this.offsetB = offsetB;
        this.size = size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[').append(base);
        if (offsetB != null) {
            builder.append('+').append(offsetB.getLeft()).append('*')
                    .append(offsetB.getRight());
        }
        if (offsetA != null) {
            builder.append('+').append(offsetA);
        }
        builder.append(',').append(size).append(']');
        return builder.toString();
    }

    @Override
    public int getNumberSize() {
        return size;
    }
}
