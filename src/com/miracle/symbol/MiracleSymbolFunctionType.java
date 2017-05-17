package com.miracle.symbol;

import java.util.LinkedList;
import java.util.List;

public class MiracleSymbolFunctionType extends MiracleSymbolType {
    private MiracleSymbolVariableType returnType;
    private List<MiracleSymbolVariableType> argType = new LinkedList<>();
    private List<String> argName = new LinkedList<>();

    public MiracleSymbolFunctionType(MiracleSymbolVariableType returnType) {
        super(Category.METHOD, 0);
        this.returnType = returnType;
    }

    public MiracleSymbolFunctionType() {
        super(Category.METHOD, 0);
    }

    public MiracleSymbolVariableType getReturnType() {
        return returnType;
    }

    public void setReturnType(MiracleSymbolVariableType returnType) {
        this.returnType = returnType;
    }

    @Override
    public boolean isSameType(MiracleSymbolType type) {
        return this == type;
    }

    @Override
    public String toPrintableString() {
        StringBuilder builder = new StringBuilder();
        builder.append(returnType.toPrintableString());
        argType.forEach(value -> builder.append(value.toPrintableString()));
        return builder.toString();
    }

    public void addParameter(String name, MiracleSymbolVariableType type) {
        argType.add(type);
        argName.add(name);
    }

    public List<MiracleSymbolVariableType> getArgType() {
        return argType;
    }
}
