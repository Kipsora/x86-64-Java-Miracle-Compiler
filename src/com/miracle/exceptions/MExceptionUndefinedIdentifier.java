package com.miracle.exceptions;

public class MExceptionUndefinedIdentifier extends MException {
    private String identifier;
    public MExceptionUndefinedIdentifier(int line, int column, String id) {
        super(line, column);
        this.identifier = id;
    }
    @Override
    public String toString() {
        return "identifier " + identifier + " was not in scope.";
    }

}

class MExceptionDuplicatesIdentifier extends MException {
    private String identifier;
    public MExceptionDuplicatesIdentifier(int line, int column, String id) {
        super(line, column);
        this.identifier = id;
    }
    @Override
    public String toString() {
        return "duplicated identifier " + identifier + " was found.";
    }
}