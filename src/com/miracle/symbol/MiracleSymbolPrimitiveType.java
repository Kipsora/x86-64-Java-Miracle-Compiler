package com.miracle.symbol;

import com.miracle.MiracleOption;

import java.util.HashMap;
import java.util.Map;

public class MiracleSymbolPrimitiveType extends MiracleSymbolBaseType {
    private final Map<String, MiracleSymbolFunctionType> functions = new HashMap<>();
    public Types type;

    public MiracleSymbolPrimitiveType(Types type) {
        super(Category.BUILTIN, type.getSize());
        this.type = type;
    }

    @Override
    public boolean isSameType(MiracleSymbolType type) {
        return this == type;
    }

    @Override
    public String toPrintableString() {
        return type.toString();
    }

    @Override
    public String getName() {
        return type.toString();
    }

    @Override
    public int getMemorySize() {
        return 0;
    }

    @Override
    public void addMethod(String identifier, MiracleSymbolFunctionType method) {
        functions.put(identifier, method);
    }

    @Override
    public MiracleSymbolFunctionType getMethod(String name) {
        return functions.getOrDefault(name, null);
    }

    public enum Types {
        INT, BOOL, VOID, STRING;

        @Override
        public String toString() {
            if (this.equals(INT)) {
                return "int";
            } else if (this.equals(BOOL)) {
                return "bool";
            } else if (this.equals(VOID)) {
                return "void";
            } else {
                return "string";
            }
        }

        public int getSize() {
            if (this.equals(INT)) {
                return MiracleOption.INT_SIZE;
            } else if (this.equals(BOOL)) {
                return MiracleOption.BOOL_SIZE;
            } else if (this.equals(VOID)) {
                return 0;
            } else {
                return MiracleOption.POINTER_SIZE;
            }
        }
    }
}
