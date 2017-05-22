package com.miracle.intermediate.value;

import org.apache.commons.lang3.tuple.ImmutablePair;

public class MiracleIRMemory extends MiracleIRDynamic {
    public final MiracleIRRegister base;
    public final MiracleIRImmediate offsetA;
    public final ImmutablePair<MiracleIRRegister, MiracleIRImmediate> offsetB;

    public MiracleIRMemory(MiracleIRRegister base) {
        this.base = base;
        this.offsetA = null;
        this.offsetB = null;
    }

    public MiracleIRMemory(MiracleIRRegister base, MiracleIRImmediate offsetA) {
        this.base = base;
        this.offsetA = offsetA;
        this.offsetB = null;
    }

    public MiracleIRMemory(MiracleIRRegister base,
                           MiracleIRImmediate offsetA,
                           MiracleIRRegister subscript,
                           MiracleIRImmediate size) { // For array
        this.base = base;
        this.offsetA = offsetA;
        this.offsetB = ImmutablePair.of(subscript, size);
    }
}
