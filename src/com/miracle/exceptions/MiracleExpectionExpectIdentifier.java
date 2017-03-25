package com.miracle.exceptions;

public class MiracleExpectionExpectIdentifier extends MiracleException{
    public MiracleExpectionExpectIdentifier(int line, int column) {
        super(line, column);
    }

    @Override
    public String toString() {
        return "an identifier expected here.";
    }
}
