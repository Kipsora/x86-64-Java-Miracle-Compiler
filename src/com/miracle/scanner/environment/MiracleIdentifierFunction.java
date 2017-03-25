package com.miracle.scanner.environment;

import java.util.LinkedList;

public class MiracleIdentifierFunction extends MiracleIdentifier {
    private final String type;
    private final LinkedList<MiracleIdentifierVariable> arguments;

    public MiracleIdentifierFunction(String type, LinkedList<MiracleIdentifierVariable> arguments) {
        this.type = type;
        this.arguments = arguments;
    }

    @Override
    public String getIdentifierType() {
        return "function";
    }
}
