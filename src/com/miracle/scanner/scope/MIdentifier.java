package com.miracle.scanner.scope;

import java.util.LinkedList;

public abstract class MIdentifier {
}

class MIDClass extends MIdentifier {
    private boolean declared = false;
    public void declare() { this.declared = true; }
}

class MIDVariable extends MIdentifier {
    private String type;
    private String value;

    public String getType() { return type; }
    public String getValue() { return value; }

    public MIDVariable(String type) {
        this.type = type;
    }

    public MIDVariable(String type, String value) {
        this.type = type;
        this.value = value;
    }
}

class MIDFunction extends MIDVariable {
    private boolean declared = false;

    private LinkedList<MIDVariable> argumentList;

    public MIDFunction(String type) { super(type); }
    public MIDFunction(String type, String value) { super(type, value); }
    public void declare() {
        if (this.declared) {

        }
        this.declared = true;
    }
    public void appendArgument(MIDVariable parameter) { argumentList.addLast(parameter); }
}
