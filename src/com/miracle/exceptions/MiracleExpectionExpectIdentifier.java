package com.miracle.exceptions;

import org.antlr.v4.runtime.Token;

public class MiracleExpectionExpectIdentifier extends MiracleException{
    public MiracleExpectionExpectIdentifier(int line, int column) {
        super(line, column);
    }
    public MiracleExpectionExpectIdentifier(Token token) {
        super(token);
    }
    @Override
    public String toString() {
        return "an identifier expected here.";
    }
}
