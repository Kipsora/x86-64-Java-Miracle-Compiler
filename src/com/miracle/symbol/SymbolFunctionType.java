package com.miracle.symbol;

import com.miracle.intermediate.structure.Function;

import java.util.LinkedList;
import java.util.List;

public class SymbolFunctionType extends SymbolType {
    private SymbolVariableType returnType;
    private List<SymbolVariableType> argType = new LinkedList<>();
    private List<String> argName = new LinkedList<>();
    private SymbolVariableType memberFrom;
    private Function address;

    public SymbolFunctionType(SymbolVariableType returnType,
                              SymbolVariableType memberFrom) {
        super(Category.METHOD, 0);
        this.returnType = returnType;
        this.memberFrom = memberFrom;
    }

    public SymbolFunctionType(SymbolVariableType returnType) {
        super(Category.METHOD, 0);
        this.returnType = returnType;
    }

    public SymbolFunctionType() {
        super(Category.METHOD, 0);
    }

    public SymbolVariableType getMemberFrom() {
        return memberFrom;
    }

    public void setMemberFrom(SymbolVariableType memberFrom) {
        this.memberFrom = memberFrom;
    }

    public SymbolVariableType getReturnType() {
        return returnType;
    }

    public void setReturnType(SymbolVariableType returnType) {
        if (this.returnType != null) {
            throw new RuntimeException("ret type settled");
        }
        this.returnType = returnType;
    }

    @Override
    public boolean isSameType(SymbolType type) {
        return this == type;
    }

    @Override
    public String toPrintableString() {
        StringBuilder builder = new StringBuilder();
        builder.append(returnType.toPrintableString());
        argType.forEach(value -> builder.append(value.toPrintableString()));
        return builder.toString();
    }

    public void addParameter(String name, SymbolVariableType type) {
        argType.add(type);
        argName.add(name);
    }

    public List<SymbolVariableType> getArgType() {
        return argType;
    }

    public Function getAddress() {
        return address;
    }

    public void setAddress(String identifier) {
        if (this.address != null) {
            throw new RuntimeException("check failed");
        }
        this.address = new Function(identifier, this);
    }

    public List<String> getArgName() {
        return argName;
    }
}
