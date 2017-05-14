package com.miracle.symbol.type;

import java.util.Collections;
import java.util.List;

public class MiracleFunctionType extends MiracleType {
    public final MiracleVariableType returnType;
    public final List<MiracleVariableType> parameters;

    public MiracleFunctionType(MiracleVariableType returnType,
                               List<MiracleVariableType> parameters) {
        this.returnType = returnType;
        this.parameters = Collections.unmodifiableList(parameters);
    }

    boolean isSameType(MiracleFunctionType miracleFunctionType) {
        if (!miracleFunctionType.returnType.isSameType(returnType)) {
            return false;
        }
        if (parameters.size() != miracleFunctionType.parameters.size()) {
            return false;
        }
        for (int i = 0; i < parameters.size(); i++) {
            if (!parameters.get(i).isSameType(miracleFunctionType.returnType)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toClassTypeString() {
        return "$function";
    }

    @Override
    public String toPrintableString() {
        StringBuilder builder = new StringBuilder();
        builder.append(returnType.toPrintableString());
        builder.append("(");
        for (int i = 0; i < parameters.size(); i++) {
            if (i != 0) {
                builder.append(",");
            }
            builder.append(parameters.get(i).toPrintableString());
        }
        builder.append(")");
        return builder.toString();
    }

    @Override
    public String toPrintableString(String identifier) {
        StringBuilder builder = new StringBuilder();
        builder.append(returnType.toPrintableString());
        builder.append(identifier);
        builder.append("(");
        for (int i = 0; i < parameters.size(); i++) {
            if (i != 0) {
                builder.append(",");
            }
            builder.append(parameters.get(i).toPrintableString());
        }
        builder.append(")");
        return builder.toString();
    }
}
