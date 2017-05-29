package com.miracle;

import java.util.LinkedList;
import java.util.List;

public class MiracleOption {
    // String and array and other class types are regarded as pointer type
    // FIXME: size cannot be changed, due to machine instruction limits
    public static final int BOOL_SIZE = 1;    // 1 Byte for bool type
    public static final int POINTER_SIZE = 8; // 8 Byte for pointer type
    public static final int INT_SIZE = 8;     // 8 Byte for int type

    public static List<String> CallingConvention = new LinkedList<String>() {{
        add("RDI");
        add("RSI");
        add("RDX");
        add("RCX");
        add("R8");
        add("R9");
    }};

    private MiracleOption() {
    }
}
