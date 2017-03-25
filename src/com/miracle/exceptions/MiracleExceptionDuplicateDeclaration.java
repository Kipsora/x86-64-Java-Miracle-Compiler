package com.miracle.exceptions;

public class MiracleExceptionDuplicateDeclaration extends MiracleException {
    private String typeA, typeB;
    private String identifier;

    public MiracleExceptionDuplicateDeclaration(String typeA, String typeB, String identifier) {
        this.typeA = typeA;
        this.typeB = typeB;
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        if (typeA.equals(typeB)) {
            if (typeA.equals("class")) {
                return "duplicated declaration of " + typeA + "es " + identifier + " were found.";
            } else {
                return "duplicated declaration of " + typeA + "s " + identifier + " were found.";
            }
        } else {
            return "duplicated declaration of " + typeA + " " + identifier + " and " + typeB + " " + identifier + "were found.";
        }
    }
}
