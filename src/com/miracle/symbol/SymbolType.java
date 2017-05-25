package com.miracle.symbol;

import java.util.HashMap;
import java.util.Map;

public abstract class SymbolType implements Symbol {
    public final Category category;
    public final int size;
    private Map<String, SymbolFunctionType> functions = new HashMap<>();

    public SymbolType(Category category, int size) {
        this.category = category;
        this.size = size;
    }

    public int getRegisterSize() {
        return size;
    }

    public abstract boolean isSameType(SymbolType type);

    public abstract String toPrintableString();

    public void addMethod(String identifier, SymbolFunctionType method) {
        functions.put(identifier, method);
    }

    public SymbolFunctionType getMethod(String name) {
        return functions.getOrDefault(name, null);
    }

    public enum Category {
        METHOD, BUILTIN, ARRAY, CUSTOM, NULL
    }
}
