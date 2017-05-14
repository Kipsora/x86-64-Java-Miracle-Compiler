package com.miracle.cstree;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class MiracleSourcePosition {
    public final int row;
    public final int column;

    public MiracleSourcePosition(Token ctx) {
        this.row = ctx.getLine();
        this.column = ctx.getCharPositionInLine();
    }

    public MiracleSourcePosition(ParserRuleContext ctx) {
        this(ctx.getStart());
    }

    public MiracleSourcePosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public String toPrintableString() {
        return "line " + String.valueOf(row) + " column " + String.valueOf(column + 1);
    }
}
