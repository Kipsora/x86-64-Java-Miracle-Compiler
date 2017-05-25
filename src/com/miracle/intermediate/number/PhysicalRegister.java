package com.miracle.intermediate.number;

public class PhysicalRegister extends DirectRegister {// name array: 1 bit, 4 bit, 8 bit
    public static PhysicalRegister AL   = new PhysicalRegister("al"  , 1, true);
    public static PhysicalRegister BL   = new PhysicalRegister("bl"  , 1, false);
    public static PhysicalRegister CL   = new PhysicalRegister("cl"  , 1, true);
    public static PhysicalRegister DL   = new PhysicalRegister("dl"  , 1, true);
    public static PhysicalRegister SIL  = new PhysicalRegister("sil" , 1, true);
    public static PhysicalRegister DIL  = new PhysicalRegister("dil" , 1, true);
    public static PhysicalRegister BPL  = new PhysicalRegister("bpl" , 1, true);
    public static PhysicalRegister SPL  = new PhysicalRegister("spl" , 1, true);
    public static PhysicalRegister R8B  = new PhysicalRegister("r8b" , 1, true);
    public static PhysicalRegister R9B  = new PhysicalRegister("r9b" , 1, true);
    public static PhysicalRegister R10B = new PhysicalRegister("r10b", 1, true);
    public static PhysicalRegister R11B = new PhysicalRegister("r11b", 1, true);
    public static PhysicalRegister R12B = new PhysicalRegister("r12b", 1, false);
    public static PhysicalRegister R13B = new PhysicalRegister("r13b", 1, false);
    public static PhysicalRegister R14B = new PhysicalRegister("r14b", 1, false);
    public static PhysicalRegister R15B = new PhysicalRegister("r15b", 1, false);

    public static PhysicalRegister AX   = new PhysicalRegister("ax"  , 2, true);
    public static PhysicalRegister BX   = new PhysicalRegister("bx"  , 2, false);
    public static PhysicalRegister CX   = new PhysicalRegister("cx"  , 2, true);
    public static PhysicalRegister DX   = new PhysicalRegister("dx"  , 2, true);
    public static PhysicalRegister SI   = new PhysicalRegister("si"  , 2, true);
    public static PhysicalRegister DI   = new PhysicalRegister("di"  , 2, true);
    public static PhysicalRegister BP   = new PhysicalRegister("bp"  , 2, true);
    public static PhysicalRegister SP   = new PhysicalRegister("sp"  , 2, true);
    public static PhysicalRegister R8W  = new PhysicalRegister("r8w" , 2, true);
    public static PhysicalRegister R9W  = new PhysicalRegister("r9w" , 2, true);
    public static PhysicalRegister R10W = new PhysicalRegister("r10w", 2, true);
    public static PhysicalRegister R11W = new PhysicalRegister("r11w", 2, true);
    public static PhysicalRegister R12W = new PhysicalRegister("r12w", 2, false);
    public static PhysicalRegister R13W = new PhysicalRegister("r13w", 2, false);
    public static PhysicalRegister R14W = new PhysicalRegister("r14w", 2, false);
    public static PhysicalRegister R15W = new PhysicalRegister("r15w", 2, false);

    public static PhysicalRegister EAX  = new PhysicalRegister("eax" , 4, true);
    public static PhysicalRegister EBX  = new PhysicalRegister("ebx" , 4, false);
    public static PhysicalRegister ECX  = new PhysicalRegister("ecx" , 4, true);
    public static PhysicalRegister EDX  = new PhysicalRegister("edx" , 4, true);
    public static PhysicalRegister ESP  = new PhysicalRegister("esp" , 4, true);
    public static PhysicalRegister EBP  = new PhysicalRegister("ebp" , 4, true);
    public static PhysicalRegister ESI  = new PhysicalRegister("esi" , 4, true);
    public static PhysicalRegister EDI  = new PhysicalRegister("edi" , 4, true);
    public static PhysicalRegister R8D  = new PhysicalRegister("r8d" , 4, true);
    public static PhysicalRegister R9D  = new PhysicalRegister("r9d" , 4, true);
    public static PhysicalRegister R10D = new PhysicalRegister("r10d", 4, true);
    public static PhysicalRegister R11D = new PhysicalRegister("r11d", 4, true);
    public static PhysicalRegister R12D = new PhysicalRegister("r12d", 4, false);
    public static PhysicalRegister R13D = new PhysicalRegister("r13d", 4, false);
    public static PhysicalRegister R14D = new PhysicalRegister("r14d", 4, false);
    public static PhysicalRegister R15D = new PhysicalRegister("r15d", 4, false);

    public static PhysicalRegister RAX = new PhysicalRegister("rax", 8, true);
    public static PhysicalRegister RBX = new PhysicalRegister("rbx", 8, false);
    public static PhysicalRegister RCX = new PhysicalRegister("rcx", 8, true);
    public static PhysicalRegister RDX = new PhysicalRegister("rdx", 8, true);
    public static PhysicalRegister RSP = new PhysicalRegister("rsp", 8, true);
    public static PhysicalRegister RBP = new PhysicalRegister("rbp", 8, true);
    public static PhysicalRegister RSI = new PhysicalRegister("rsi", 8, true);
    public static PhysicalRegister RDI = new PhysicalRegister("rdi", 8, true);
    public static PhysicalRegister R8  = new PhysicalRegister("r8" , 8, true);
    public static PhysicalRegister R9  = new PhysicalRegister("r9" , 8, true);
    public static PhysicalRegister R10 = new PhysicalRegister("r10", 8, true);
    public static PhysicalRegister R11 = new PhysicalRegister("r11", 8, true);
    public static PhysicalRegister R12 = new PhysicalRegister("r12", 8, false);
    public static PhysicalRegister R13 = new PhysicalRegister("r13", 8, false);
    public static PhysicalRegister R14 = new PhysicalRegister("r14", 8, false);
    public static PhysicalRegister R15 = new PhysicalRegister("r15", 8, false);

    public static PhysicalRegister[][] GeneralPurposeRegister = {
            {AL  , AX  , EAX , RAX},
            {BL  , BX  , EBX , RBX},
            {CL  , CX  , ECX , RCX},
            {DL  , DX  , EDX , RDX},
            //{SPL , SP  , ESP , RSP},
            //{BPL , BP  , EBP , RBP},
            {SIL , SI  , ESI , RSI},
            {DIL , DI  , EDI , RDI},
            {R8B , R8W , R8D , R8 },
            {R9B , R9W , R9D , R9 },
            {R10B, R10W, R10D, R10},
            {R11B, R11W, R11D, R11},
            {R12B, R12W, R12D, R12},
            {R13B, R13W, R13D, R13},
            {R14B, R14W, R14D, R14},
            {R15B, R15W, R15D, R15},
    };

    public final boolean isCallerSave;

    private PhysicalRegister(String name, int size, boolean isCallerSave) {
        super(name, size);
        this.isCallerSave = isCallerSave;
    }

    @Override
    public String toString() {
        return name;
    }
}
