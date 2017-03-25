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
            if (typeA.equals("class")) {
                return "duplicated declarations of " + typeA + "es \"" + identifier + "\" were found.";
            } else {
                return "duplicated declarations of " + typeA + "s \"" + identifier + "\" were found.";
            }
        } else {
            return "duplicated declarations of " + typeA + " \"" + identifier + "\" and " + typeB + " \"" + identifier + "\" were found.";
        }
    }
}
