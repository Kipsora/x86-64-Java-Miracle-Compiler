package com.miracle.intermediate;

import com.miracle.intermediate.number.DirectRegister;

import java.util.HashMap;
import java.util.Map;

public class RegisterBuffer {
    private final Map<DirectRegister, Integer> set = new HashMap<>();
    private int totalSize;
    private int spillSize;

    public Map<DirectRegister, Integer> getRegisters() {
        return set;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void enroll(DirectRegister register) {
        set.put(register, totalSize);
        totalSize += register.getNumberSize();
    }

    public int getSpillSize() {
        return spillSize;
    }
}
