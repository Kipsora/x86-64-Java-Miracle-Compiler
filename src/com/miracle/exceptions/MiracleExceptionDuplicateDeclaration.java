package com.miracle.exceptions;

public class MiracleExceptionDuplicateDeclaration extends MiracleException {
    private final String typeA, typeB;
    private final String identifier;

    public MiracleExceptionDuplicateDeclaration(String typeA, String typeB, String identifier) {
        this.typeA = typeA;
        this.typeB = typeB;
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        if (typeA.equals(typeB)) {
            return "duplicated declarations of identifier \"" + identifier
                    + "\" as \"" + typeA + "\" were found.";
        } else {
            return "duplicated declarations of identifier \"" + identifier
                    + "\" as \"" + typeA + "\" and \"" + typeB + "\" were found.";
        }
    }
}
