package com.miracle.symbol;

import com.miracle.MiracleOption;

import java.util.HashMap;
import java.util.Map;

public class MiracleSymbolClassType extends MiracleSymbolBaseType {
    public String identifier;
    private Map<String, MiracleSymbolVariableType> variables = new HashMap<>();

    public MiracleSymbolClassType(String identifier) {
        super(Category.CUSTOM, MiracleOption.POINTER_SIZE);
        this.identifier = identifier;
    }

    public void addVariable(String name, MiracleSymbolVariableType type) {
        variables.put(name, type);
    }

    public MiracleSymbolVariableType getVariable(String name) {
        return variables.getOrDefault(name, null);
    }

    @Override
    public boolean isSameType(MiracleSymbolType type) {
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
