package com.miracle.intermediate.value;

import org.apache.commons.lang3.tuple.ImmutablePair;

public class MiracleIRDynamic extends MiracleIRMemory {
    public final MiracleIRRegister base;
    public final MiracleIRImmediate offsetA;
    public final ImmutablePair<MiracleIRRegister, MiracleIRImmediate> offsetB;

    public MiracleIRDynamic(MiracleIRRegister base) {
        this.base = base;
        this.offsetA = null;
        this.offsetB = null;
    }

    public MiracleIRDynamic(MiracleIRRegister base, MiracleIRImmediate offsetA) {
        this.base = base;
        this.offsetA = offsetA;
        this.offsetB = null;
    }

    public MiracleIRDynamic(MiracleIRRegister base,
                            MiracleIRImmediate offsetA,
                            MiracleIRRegister subscript,
                            MiracleIRImmediate size) { // For array
        this.base = base;
        this.offsetA = offsetA;
        this.offsetB = ImmutablePair.of(subscript, size);
    }
}
