package com.miracle.exceptions;

public class MExceptionDuplicateDeclaration extends MException{
    private String type;
    private String identifier;
    public MExceptionDuplicateDeclaration(int line, int column, String type, String identifier) {
        super(line, column);
        this.type = type;
        this.identifier = identifier;
    }
    @Override
    public String toString() {
        return "duplicated declaration of " + type + " " + identifier + " was found.";
    }
}
