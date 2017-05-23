package com.miracle.symbol;

import com.miracle.intermediate.structure.MiracleIRFunction;

import java.util.LinkedList;
import java.util.List;

public class MiracleSymbolFunctionType extends MiracleSymbolType {
    private MiracleSymbolVariableType returnType;
    private List<MiracleSymbolVariableType> argType = new LinkedList<>();
    private List<String> argName = new LinkedList<>();
    private MiracleSymbolVariableType memberFrom;
    private MiracleIRFunction address;

    public MiracleSymbolFunctionType(MiracleSymbolVariableType returnType,
                                     MiracleSymbolVariableType memberFrom) {
        super(Category.METHOD, 0);
        this.returnType = returnType;
        this.memberFrom = memberFrom;
    }

    public MiracleSymbolFunctionType(MiracleSymbolVariableType returnType) {
        super(Category.METHOD, 0);
        this.returnType = returnType;
    }

    public MiracleSymbolFunctionType() {
        super(Category.METHOD, 0);
    }

    public MiracleSymbolVariableType getMemberFrom() {
        return memberFrom;
    }

    public void setMemberFrom(MiracleSymbolVariableType memberFrom) {
        this.memberFrom = memberFrom;
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

    public MiracleIRFunction getAddress() {
        return address;
    }

    public void setAddress(String identifier) {
        if (this.address != null) {
            throw new RuntimeException("check failed");
        }
        this.address = new MiracleIRFunction(identifier, this);
    }

    public List<String> getArgName() {
        return argName;
    }
}
