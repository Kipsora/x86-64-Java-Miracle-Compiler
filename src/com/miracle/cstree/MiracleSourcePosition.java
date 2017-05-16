package com.miracle.cstree;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class MiracleSourcePosition {
    public final int row;
    public final int columnStart;
    public final int columnEnd;

    public MiracleSourcePosition(Token ctx) {
        this.row = ctx.getLine();
        this.columnStart = ctx.getCharPositionInLine();
        this.columnEnd = this.columnStart + ctx.getText().length();
    }

    public MiracleSourcePosition(ParserRuleContext ctx) {
        this(ctx.getStart());
    }

    public MiracleSourcePosition(int row, int columnStart, int columnEnd) {
        this.row = row;
        this.columnStart = columnStart;
        this.columnEnd = columnEnd;
    }

    public String toPrintableString() {
        return String.valueOf(row) + ":" + String.valueOf(columnStart + 1);
    }

    public int getPosition() {
        return columnStart + columnEnd >> 1;
    }
}
