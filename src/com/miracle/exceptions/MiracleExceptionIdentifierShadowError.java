package com.miracle.exceptions;

public class MiracleExceptionIdentifierShadowError extends MiracleException {
    public final String identifier;
    public final String typeA;
    public final String typeB;

    public MiracleExceptionIdentifierShadowError(String identifier, String typeA, String typeB) {
        this.identifier = identifier;
        this.typeA = typeA;
        this.typeB = typeB;
    }

    @Override
    public String toString() {
        return "find identifier \"" + identifier + "\" of " + typeA +
                ", but duplicated with identifier of " + typeB + ", which cannot be shadowed.";
    }
}
