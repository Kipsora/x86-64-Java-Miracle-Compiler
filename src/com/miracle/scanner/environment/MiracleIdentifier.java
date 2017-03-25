package com.miracle.scanner.environment;

import java.util.LinkedList;

abstract class MiracleIdentifier {
}

class MiracleIdentifierClass extends MiracleIdentifier {
    private final String extendClass;

    MiracleIdentifierClass(String extendClass) {
        this.extendClass = extendClass;
    }

    public final String getExtendClass() {
        return extendClass;
    }
}

class MiracleIdentifierVariable extends MiracleIdentifier {
    private final String type;
    private final String value;

    MiracleIdentifierVariable(String type) {
        this.type = type;
        this.value = null;
    }

    MiracleIdentifierVariable(String type, String value) {
        this.type = type;
        this.value = value;
    }
}

class MiracleIdentifierFunction extends MiracleIdentifier {
    private final String type;
    private final LinkedList<MiracleIdentifierVariable> arguments;

    MiracleIdentifierFunction(String type, LinkedList<MiracleIdentifierVariable> arguments) {
        this.type = type;
        this.arguments = arguments;
    }
}

