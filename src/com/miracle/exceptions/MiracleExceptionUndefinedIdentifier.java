package com.miracle.exceptions;

class MiracleExceptionUndefinedIdentifier extends MiracleException {
    private String identifier;
    public MiracleExceptionUndefinedIdentifier(int line, int column, String id) {
        super(line, column);
        this.identifier = id;
    }
    @Override
    public String toString() {
        return "identifier " + identifier + " was not in scope.";
    }

}

class MiracleExceptionDuplicatesIdentifier extends MiracleException {
    private String identifier;
    public MiracleExceptionDuplicatesIdentifier(int line, int column, String id) {
        super(line, column);
        this.identifier = id;
    }
    @Override
    public String toString() {
        return "duplicated identifier " + identifier + " was found.";
    }
}