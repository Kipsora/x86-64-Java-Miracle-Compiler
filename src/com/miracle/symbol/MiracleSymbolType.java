package com.miracle.symbol;

import java.util.HashMap;
import java.util.Map;

public abstract class MiracleSymbolType implements MiracleSymbol {
    public final Category category;
    public final int size;
    private Map<String, MiracleSymbolFunctionType> functions = new HashMap<>();

    public MiracleSymbolType(Category category, int size) {
        this.category = category;
        this.size = size;
    }

    public int getRegisterSize() {
        return size;
    }

    public abstract boolean isSameType(MiracleSymbolType type);

    public abstract String toPrintableString();

    public void addMethod(String identifier, MiracleSymbolFunctionType method) {
        functions.put(identifier, method);
    }

    public MiracleSymbolFunctionType getMethod(String name) {
        return functions.getOrDefault(name, null);
    }

    public enum Category {
        METHOD, BUILTIN, ARRAY, CUSTOM, NULL
    }
}
