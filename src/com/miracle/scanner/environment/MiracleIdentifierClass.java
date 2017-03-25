package com.miracle.scanner.environment;

public class MiracleIdentifierClass extends MiracleIdentifier {
    private final String extendClass;

    public MiracleIdentifierClass(String extendClass) {
        this.extendClass = extendClass;
    }

    public final String getExtendClass() {
        return extendClass;
    }

    @Override
    public String getIdentifierType() {
        return "class";
    }
}
