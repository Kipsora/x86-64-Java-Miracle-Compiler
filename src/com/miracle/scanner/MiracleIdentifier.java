package com.miracle.scanner;

import java.util.LinkedList;

public abstract class MiracleIdentifier {
}

class MiracleClassIdentifier extends MiracleIdentifier {
}

class MiracleVariableIdentifier extends MiracleIdentifier {
    private String type;
    private String value;

    public String getType() { return type; }
    public String getValue() { return value; }

    public MiracleVariableIdentifier(String type) {
        this.type = type;
    }

    public MiracleVariableIdentifier(String type, String value) {
        this.type = type;
        this.value = value;
    }
}

class MiracleFunctionIdentifier extends MiracleVariableIdentifier {
    private LinkedList<MiracleVariableIdentifier> argumentList;

    public MiracleFunctionIdentifier(String type) {
        super( type);
    }
    public MiracleFunctionIdentifier(String type, String value) {
        super(type, value);
    }
    public void appendArgument(MiracleVariableIdentifier parameter) { argumentList.addLast(parameter); }
}
