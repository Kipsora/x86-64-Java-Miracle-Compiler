package com.miracle.exceptions;

public class MiracleExceptionIdentifierShadow extends MiracleException {
    public final String identifier;
    public final String typeA;
    public final String typeB;

    public MiracleExceptionIdentifierShadow(String identifier, String typeA, String typeB) {
        this.identifier = identifier;
        this.typeA = typeA;
        this.typeB = typeB;
    }

    @Override
    public String toString() {
        return "declaration of identifier \"" + identifier + "\" of " + typeA +
                " shadows the " + typeB + " \"" + identifier + "\".";
    }
}
