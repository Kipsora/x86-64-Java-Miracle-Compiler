package com.miracle.intermediate.number;

import java.util.Formatter;
import java.util.Map;

public class OffsetRegister extends IndirectRegister { // memory in stack/heap
    private Register base;
    private Integer offsetA;
    private Register offsetB;
    private Integer multiplier;

    public OffsetRegister(DirectRegister base,
                          Integer offsetA,
                          DirectRegister offsetB,
                          Integer multiplier,
                          int size) {
        super(size);
        this.base = base;
        this.offsetA = offsetA;
        this.offsetB = offsetB;
        this.multiplier = multiplier;
    }

    public Register getRawBase() {
        return base;
    }

    public Register getRawOffsetB() {
        return offsetB;
    }

    public DirectRegister getBase() {
        return (DirectRegister) base;
    }

    public Integer getOffsetA() {
        return offsetA;
    }

    public DirectRegister getOffsetB() {
        return (DirectRegister) offsetB;
    }

    public void set(Map<Number, Register> map) {
        base = map.getOrDefault(base, base);
        offsetB = map.getOrDefault(offsetB, offsetB);
    }

    public void rename(Map<VirtualRegister, VirtualRegister> map) {
        base = map.get(base);
        offsetB = map.get(offsetB);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getSizeDescriptor()).append(" [").append(base);
        if (offsetB != null) {
            builder.append('+').append(offsetB).append('*')
                    .append(multiplier);
        }
        if (offsetA != null && offsetA != 0) {
            builder.append(new Formatter().format("%+d", offsetA));
        }
        builder.append(']');
        return builder.toString();
    }

    @Override
    public int getNumberSize() {
        return size;
    }

    public void setOffsetB(PhysicalRegister offsetB) {
        this.offsetB = offsetB;
    }

    public void setBase(PhysicalRegister base) {
        this.base = base;
    }
}
