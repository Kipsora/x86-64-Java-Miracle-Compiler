package com.miracle.scanner.listener;

import com.miracle.cstree.MiracleBaseListener;
import com.miracle.cstree.MiracleParser;
import com.miracle.scanner.environment.MiracleEnvironmentManager;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class MiracleRuntimeMaintainer extends MiracleBaseListener {
    private static int row;
    private static int column;
    protected final MiracleEnvironmentManager environment;

    MiracleRuntimeMaintainer(MiracleEnvironmentManager environment) {
        this.environment = environment;
    }

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
        this.environment.initScope();
    }

    @Override
    public void exitMiracle(MiracleParser.MiracleContext ctx) {
        this.environment.exitScope();
    }

    @Override
    public void enterClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        environment.enterScope(MiracleEnvironmentManager.ScopeType.SCOPE_CLASS);
    }

    @Override
    public void exitClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        environment.exitScope();
    }

    @Override
    public void enterForStatement(MiracleParser.ForStatementContext ctx) {
        environment.enterScope(MiracleEnvironmentManager.ScopeType.SCOPE_ITER);
    }

    @Override
    public void exitForStatement(MiracleParser.ForStatementContext ctx) {
        environment.exitScope();
    }

    @Override
    public void enterWhileStatement(MiracleParser.WhileStatementContext ctx) {
        environment.enterScope(MiracleEnvironmentManager.ScopeType.SCOPE_ITER);
    }

    @Override
    public void exitWhileStatement(MiracleParser.WhileStatementContext ctx) {
        environment.exitScope();
    }

    @Override
    public void enterBlockStatement(MiracleParser.BlockStatementContext ctx) {
        environment.enterScope(MiracleEnvironmentManager.ScopeType.SCOPE_BLOCK);
    }

    @Override
    public void exitBlockStatement(MiracleParser.BlockStatementContext ctx) {
        environment.exitScope();
    }

    @Override
    public void enterFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx) {
        environment.enterScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC);
    }

    @Override
    public void exitFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx) {
        environment.exitScope();
    }
}
