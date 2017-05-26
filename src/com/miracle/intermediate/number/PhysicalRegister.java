package com.miracle.intermediate.number;

import java.util.*;

public class PhysicalRegister extends DirectRegister {// name array: 1 bit, 4 bit, 8 bit
    public static PhysicalRegister AL = new PhysicalRegister("al", "RAX", 1, true);
    public static PhysicalRegister BL = new PhysicalRegister("bl", "RBX", 1, false);
    public static PhysicalRegister CL = new PhysicalRegister("cl", "RCX", 1, true);
    public static PhysicalRegister DL = new PhysicalRegister("dl", "RDX", 1, true);
    public static PhysicalRegister SIL = new PhysicalRegister("sil", "RSI", 1, true);
    public static PhysicalRegister DIL = new PhysicalRegister("dil", "RDI", 1, true);
    public static PhysicalRegister BPL = new PhysicalRegister("bpl", "RBP", 1, true);
    public static PhysicalRegister SPL = new PhysicalRegister("spl", "RSP", 1, true);
    public static PhysicalRegister R8B = new PhysicalRegister("r8b", "R8", 1, true);
    public static PhysicalRegister R9B = new PhysicalRegister("r9b", "R9", 1, true);
    public static PhysicalRegister R10B = new PhysicalRegister("r10b", "R10", 1, true);
    public static PhysicalRegister R11B = new PhysicalRegister("r11b", "R11", 1, true);
    public static PhysicalRegister R12B = new PhysicalRegister("r12b", "R12", 1, false);
    public static PhysicalRegister R13B = new PhysicalRegister("r13b", "R13", 1, false);
    public static PhysicalRegister R14B = new PhysicalRegister("r14b", "R14", 1, false);
    public static PhysicalRegister R15B = new PhysicalRegister("r15b", "R15", 1, false);

    public static PhysicalRegister AX = new PhysicalRegister("ax", "RAX", 2, true);
    public static PhysicalRegister BX = new PhysicalRegister("bx", "RBX", 2, false);
    public static PhysicalRegister CX = new PhysicalRegister("cx", "RCX", 2, true);
    public static PhysicalRegister DX = new PhysicalRegister("dx", "RDX", 2, true);
    public static PhysicalRegister SI = new PhysicalRegister("si", "RSI", 2, true);
    public static PhysicalRegister DI = new PhysicalRegister("di", "RDI", 2, true);
    public static PhysicalRegister BP = new PhysicalRegister("bp", "RBP", 2, true);
    public static PhysicalRegister SP = new PhysicalRegister("sp", "RSP", 2, true);
    public static PhysicalRegister R8W = new PhysicalRegister("r8w", "R8", 2, true);
    public static PhysicalRegister R9W = new PhysicalRegister("r9w", "R9", 2, true);
    public static PhysicalRegister R10W = new PhysicalRegister("r10w", "R10", 2, true);
    public static PhysicalRegister R11W = new PhysicalRegister("r11w", "R11", 2, true);
    public static PhysicalRegister R12W = new PhysicalRegister("r12w", "R12", 2, false);
    public static PhysicalRegister R13W = new PhysicalRegister("r13w", "R13", 2, false);
    public static PhysicalRegister R14W = new PhysicalRegister("r14w", "R14", 2, false);
    public static PhysicalRegister R15W = new PhysicalRegister("r15w", "R15", 2, false);

    public static PhysicalRegister EAX = new PhysicalRegister("eax", "RAX", 4, true);
    public static PhysicalRegister EBX = new PhysicalRegister("ebx", "RBX", 4, false);
    public static PhysicalRegister ECX = new PhysicalRegister("ecx", "RCX", 4, true);
    public static PhysicalRegister EDX = new PhysicalRegister("edx", "RDX", 4, true);
    public static PhysicalRegister ESI = new PhysicalRegister("esi", "RSI", 4, true);
    public static PhysicalRegister EDI = new PhysicalRegister("edi", "RDI", 4, true);
    public static PhysicalRegister ESP = new PhysicalRegister("esp", "RSP", 4, true);
    public static PhysicalRegister EBP = new PhysicalRegister("ebp", "RBP", 4, true);
    public static PhysicalRegister R8D = new PhysicalRegister("r8d", "R8", 4, true);
    public static PhysicalRegister R9D = new PhysicalRegister("r9d", "R9", 4, true);
    public static PhysicalRegister R10D = new PhysicalRegister("r10d", "R10", 4, true);
    public static PhysicalRegister R11D = new PhysicalRegister("r11d", "R11", 4, true);
    public static PhysicalRegister R12D = new PhysicalRegister("r12d", "R12", 4, false);
    public static PhysicalRegister R13D = new PhysicalRegister("r13d", "R13", 4, false);
    public static PhysicalRegister R14D = new PhysicalRegister("r14d", "R14", 4, false);
    public static PhysicalRegister R15D = new PhysicalRegister("r15d", "R15", 4, false);

    public static PhysicalRegister RAX = new PhysicalRegister("rax", "RAX", 8, true);
    public static PhysicalRegister RBX = new PhysicalRegister("rbx", "RBX", 8, false);
    public static PhysicalRegister RCX = new PhysicalRegister("rcx", "RCX", 8, true);
    public static PhysicalRegister RDX = new PhysicalRegister("rdx", "RDX", 8, true);
    public static PhysicalRegister RSP = new PhysicalRegister("rsp", "RSP", 8, true);
    public static PhysicalRegister RBP = new PhysicalRegister("rbp", "RBP", 8, true);
    public static PhysicalRegister RSI = new PhysicalRegister("rsi", "RSI", 8, true);
    public static PhysicalRegister RDI = new PhysicalRegister("rdi", "RDI", 8, true);
    public static PhysicalRegister R8 = new PhysicalRegister("r8", "R8", 8, true);
    public static PhysicalRegister R9 = new PhysicalRegister("r9", "R9", 8, true);
    public static PhysicalRegister R10 = new PhysicalRegister("r10", "R10", 8, true);
    public static PhysicalRegister R11 = new PhysicalRegister("r11", "R11", 8, true);
    public static PhysicalRegister R12 = new PhysicalRegister("r12", "R12", 8, false);
    public static PhysicalRegister R13 = new PhysicalRegister("r13", "R13", 8, false);
    public static PhysicalRegister R14 = new PhysicalRegister("r14", "R14", 8, false);
    public static PhysicalRegister R15 = new PhysicalRegister("r15", "R15", 8, false);

    private static Map<String, List<PhysicalRegister>> GeneralRegisterMap = new HashMap<String, List<PhysicalRegister>>() {{
        put("RAX", Arrays.asList(AL, AX, EAX, RAX));
        put("RBX", Arrays.asList(BL, BX, EBX, RBX));
        put("RCX", Arrays.asList(CL, CX, ECX, RCX));
        put("RDX", Arrays.asList(DL, DX, EDX, RDX));
        put("RSI", Arrays.asList(SIL, SI, ESI, RSI));
        put("RDI", Arrays.asList(DIL, DI, EDI, RDI));
        put("R8", Arrays.asList(R8B, R8W, R8D, R8));
        put("R9", Arrays.asList(R9B, R9W, R9D, R9));
        put("R10", Arrays.asList(R10B, R10W, R10D, R10));
        put("R11", Arrays.asList(R11B, R11W, R11D, R11));
        put("R12", Arrays.asList(R12B, R12W, R12D, R12));
        put("R13", Arrays.asList(R13B, R13W, R13D, R13));
        put("R14", Arrays.asList(R14B, R14W, R14D, R14));
        put("R15", Arrays.asList(R15B, R15W, R15D, R15));
    }};

    public static Set<String> GeneralPurposeRegister = Collections.unmodifiableSet(new HashSet<String>() {{
        GeneralRegisterMap.forEach((key, value) -> add(key));
    }});

    public final boolean isCallerSave;
    public final String indexName;

    private PhysicalRegister(String name, String indexName, int size, boolean isCallerSave) {
        super(name, size);
        this.indexName = indexName;
        this.isCallerSave = isCallerSave;
    }

    public static PhysicalRegister getBy16BITName(String name, int size) {
        if (size == 1) size = 0;
        else if (size == 2) size = 1;
        else if (size == 4) size = 2;
        else if (size == 8) size = 3;
        else {
            throw new RuntimeException("unsupported size");
        }
        if (!GeneralRegisterMap.containsKey(name)) {
            throw new RuntimeException("invalid physical register name");
        }
        return GeneralRegisterMap.get(name).get(size);
    }

    public static List<PhysicalRegister> getAllBy16BITName(String name) {
        if (!GeneralRegisterMap.containsKey(name)) {
            throw new RuntimeException("invalid physical register name");
        }
        return GeneralRegisterMap.get(name);
    }

    @Override
    public String toString() {
        return name;
    }

    public String getELF64Name() {
        return indexName.toLowerCase();
    }
}