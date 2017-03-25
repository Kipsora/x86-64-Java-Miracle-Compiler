package com.miracle.exceptions;

public class MiracleExceptionControlStatement extends MiracleException{
    public String token;
    public String scope;

    public MiracleExceptionControlStatement(int line, int column, String token, String scope) {
        super(line, column);
        this.token = token;
        this.scope = scope;
    }
    @Override
    public String toString() {
        return "token " + token + " should be used in a " + scope + " scope.";
    }
}
