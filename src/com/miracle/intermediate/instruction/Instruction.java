package com.miracle.intermediate.instruction;

import com.miracle.intermediate.Node;
import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.OffsetRegister;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.number.VirtualRegister;

import java.util.Map;
import java.util.Set;

public abstract class Instruction extends Node {
    protected static void addToSet(Number register, Set<Number> set) {
        if (register instanceof OffsetRegister) {
            if (((OffsetRegister) register).getRawBase() != null) {
                set.add(((OffsetRegister) register).getRawBase());
            }
            if (((OffsetRegister) register).getRawOffsetB() != null) {
                set.add(((OffsetRegister) register).getRawOffsetB());
            }
        }
        set.add(register);
    }

    public abstract void set(Map<Number, Register> map);

    public abstract void rename(Map<VirtualRegister, VirtualRegister> map);

    /**
     * @return a set contain registers to be allocated which stores source data
     * in the instruction, and the second is whether it is forced to be allocated
     * to a specific register
     */
    public abstract Set<Number> getUseNumbers();

    public abstract Set<Number> getDefNumbers();
}
