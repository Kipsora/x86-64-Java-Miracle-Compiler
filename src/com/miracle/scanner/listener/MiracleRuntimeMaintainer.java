package com.miracle.scanner.listener;

import com.miracle.cstree.MiracleBaseListener;
import com.miracle.cstree.MiracleParser;
import com.miracle.scanner.MiracleEnvironmentManager;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class MiracleRuntimeMaintainer extends MiracleBaseListener {
    private static int row;
    private static int column;

    public static int getRow() {
        return row;
    }

    static void setRow(int row) {
        MiracleRuntimeMaintainer.row = row;
    }

    public static int getColumn() {
        return column + 1;
    }

    static void setColumn(int column) {
        MiracleRuntimeMaintainer.column = column;
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        row = ctx.getStart().getLine();
        column = ctx.getStart().getCharPositionInLine();
    }

    @Override
    public void enterMiracle(MiracleParser.MiracleContext ctx) {
        MiracleEnvironmentManager.init();
        MiracleEnvironmentManager.newscope(false);
    }

    @Override
    public void exitMiracle(MiracleParser.MiracleContext ctx) {
        MiracleEnvironmentManager.exitscope();
    }

    @Override
    public void enterClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        MiracleEnvironmentManager.newscope(true);
    }

    @Override
    public void exitClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        MiracleEnvironmentManager.exitscope();
    }

    @Override
    public void enterBlockStatement(MiracleParser.BlockStatementContext ctx) {
        MiracleEnvironmentManager.newscope(false);
    }

    @Override
    public void exitBlockStatement(MiracleParser.BlockStatementContext ctx) {
        MiracleEnvironmentManager.exitscope();
    }

    @Override
    public void enterFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx) {
        MiracleEnvironmentManager.newscope(false);
    }

    @Override
    public void exitFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx) {
        MiracleEnvironmentManager.exitscope();
    }

    @Override
    public void enterForStatement(MiracleParser.ForStatementContext ctx) {
        MiracleEnvironmentManager.newscope(false);
    }

    @Override
    public void exitForStatement(MiracleParser.ForStatementContext ctx) {
        MiracleEnvironmentManager.exitscope();
    }

    @Override
    public void enterWhileStatement(MiracleParser.WhileStatementContext ctx) {
        MiracleEnvironmentManager.newscope(false);
    }

    @Override
    public void exitWhileStatement(MiracleParser.WhileStatementContext ctx) {
        MiracleEnvironmentManager.exitscope();
    }

    @Override
    public void enterSelectionStatement(MiracleParser.SelectionStatementContext ctx) {
        MiracleEnvironmentManager.newscope(false);
    }

    @Override
    public void exitSelectionStatement(MiracleParser.SelectionStatementContext ctx) {
        MiracleEnvironmentManager.exitscope();
    }
}
