package com.miracle.scanner.scope;

import java.util.LinkedList;

public abstract class MiracleIdentifier {
}

class MiracleClassIdentifier extends MiracleIdentifier {
    private boolean declared = false;
    public boolean declare() {
        if (this.declared) {
            return false;
        }
        this.declared = true;
        return true;
    }
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
    private boolean declared = false;

    private LinkedList<MiracleVariableIdentifier> argumentList;

    public MiracleFunctionIdentifier(String type) { super(type); }
    public MiracleFunctionIdentifier(String type, String value) { super(type, value); }
    public boolean declare() {
        if (this.declared) {
            return false;
        }
        this.declared = true;
        return true;
    }
    public void appendArgument(MiracleVariableIdentifier parameter) { argumentList.addLast(parameter); }
}
