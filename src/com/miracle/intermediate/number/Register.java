package com.miracle.intermediate.number;

public abstract class Register extends Number {
    public final int size;

    Register(int size) {
        this.size = size;
    }

    @Override
    public int getNumberSize() {
        return size;
    }

    public abstract boolean isIndirect();

    String getSizeDescriptor() {
        if (size == 1) {
            return "byte";
        } else if (size == 2) {
            return "word";
        } else if (size == 4) {
            return "dword";
        } else if (size == 8) {
            return "qword";
        } else {
            throw new RuntimeException("unsupported size");
        }
    }
}
