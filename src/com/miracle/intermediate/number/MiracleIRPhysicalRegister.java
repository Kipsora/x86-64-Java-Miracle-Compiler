package com.miracle.intermediate.number;

import java.util.Arrays;
import java.util.List;

public class MiracleIRPhysicalRegister extends MiracleIRDirectRegister {
    public static MiracleIRPhysicalRegister RAX = new MiracleIRPhysicalRegister("rax", 8, true);
    public static MiracleIRPhysicalRegister RBX = new MiracleIRPhysicalRegister("rbx", 8, false);
    public static MiracleIRPhysicalRegister RCX = new MiracleIRPhysicalRegister("rcx", 8, true);
    public static MiracleIRPhysicalRegister RDX = new MiracleIRPhysicalRegister("rdx", 8, true);
    public static MiracleIRPhysicalRegister RSP = new MiracleIRPhysicalRegister("rsp", 8, true);
    public static MiracleIRPhysicalRegister RBP = new MiracleIRPhysicalRegister("rbp", 8, true);
    public static MiracleIRPhysicalRegister RSI = new MiracleIRPhysicalRegister("rsi", 8, true);
    public static MiracleIRPhysicalRegister RDI = new MiracleIRPhysicalRegister("rdi", 8, true);
    public static MiracleIRPhysicalRegister R8 = new MiracleIRPhysicalRegister("r8", 8, true);
    public static MiracleIRPhysicalRegister R9 = new MiracleIRPhysicalRegister("r9", 8, true);
    public static MiracleIRPhysicalRegister R10 = new MiracleIRPhysicalRegister("r10", 8, true);
    public static MiracleIRPhysicalRegister R11 = new MiracleIRPhysicalRegister("r11", 8, true);
    public static MiracleIRPhysicalRegister R12 = new MiracleIRPhysicalRegister("r12", 8, false);
    public static MiracleIRPhysicalRegister R13 = new MiracleIRPhysicalRegister("r13", 8, false);
    public static MiracleIRPhysicalRegister R14 = new MiracleIRPhysicalRegister("r14", 8, false);
    public static MiracleIRPhysicalRegister R15 = new MiracleIRPhysicalRegister("r15", 8, false);
    public static List<MiracleIRPhysicalRegister> GeneralPurposeRegister = Arrays.asList(
            RAX, RBX, RCX, RDX, RSI, RDI, R8, R9, R10, R11, R12, R13, R14, R15
    );
    public final boolean isCallerSave;

    private MiracleIRPhysicalRegister(String name, int size, boolean isCallerSave) {
        super(name, size);
        this.isCallerSave = isCallerSave;
    }

    @Override
    public String toString() {
        return name;
    }
}
