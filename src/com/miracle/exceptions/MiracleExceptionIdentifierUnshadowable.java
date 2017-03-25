package com.miracle.exceptions;

public class MiracleExceptionIdentifierUnshadowable extends MiracleException{
    public final String identifier;

    public MiracleExceptionIdentifierUnshadowable(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "find duplicated identifier " + identifier + ", but the previous cannot be shadowed.";
    }
}
