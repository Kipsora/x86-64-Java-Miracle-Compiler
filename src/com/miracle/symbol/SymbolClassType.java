package com.miracle.symbol;

import com.miracle.MiracleOption;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.HashMap;
import java.util.Map;

public class SymbolClassType extends SymbolBaseType {
    public String identifier;
    private int totalSize;
    private Map<String, ImmutablePair<Integer, SymbolVariableType>> variables = new HashMap<>();

    public SymbolClassType(String identifier) {
        super(Category.CUSTOM, MiracleOption.POINTER_SIZE);
        this.identifier = identifier;
    }

    @Override
    public int getMemorySize() {
        return totalSize;
    }

    public void addVariable(String name, SymbolVariableType type) {
        variables.put(name, ImmutablePair.of(totalSize, type));
        totalSize += type.getRegisterSize();
    }

    public SymbolVariableType getVariable(String name) {
        ImmutablePair<Integer, SymbolVariableType> result = variables.getOrDefault(name, null);
        if (result != null) {
            return result.getRight();
        }
        return null;
    }

    public int getVariableOffset(String name) {
        ImmutablePair<Integer, SymbolVariableType> result = variables.getOrDefault(name, null);
        if (result != null) {
            return result.getLeft();
        }
        throw new RuntimeException("cannot find variable");
    }


    @Override
    public boolean isSameType(SymbolType type) {
        return type.category == Category.NULL || this == type;
    }

    @Override
    public String toPrintableString() {
        return identifier;
    }

    @Override
    public String getName() {
        return identifier;
    }
}
