package com.miracle.intermediate.value;

public abstract class MiracleIRRegister extends MiracleIRDynamic {
    public final String name;

    public MiracleIRRegister(String name) {
        this.name = "vreg" + name;
    }

    @Override
    public String toString() {
        return name;
    }
}
