package com.miracle.scanner.listener;

import com.miracle.cstree.MiracleBaseListener;
import com.miracle.cstree.MiracleParser;
import com.miracle.exceptions.MiracleExceptionControlStatement;
import com.miracle.exceptions.MiracleExpectionExpectIdentifier;
import com.miracle.scanner.scope.MiracleScope;

public class MiracleScopeListener extends MiracleBaseListener {
    @Override
    public void enterBlockStatement(MiracleParser.BlockStatementContext ctx) {
        if (!MiracleScope.inScope(MiracleScope.ScopeType.SCOPE_FUNC)
                && !MiracleScope.getScopeType().equals(MiracleScope.ScopeType.SCOPE_CLASS)) {
            /**
             * TODO: Modified here to acutal rows and columns.
             */
            throw new MiracleExpectionExpectIdentifier(-1, -1);
        }
        MiracleScope.addScope(MiracleScope.ScopeType.SCOPE_BLOCK);
    }

    @Override
    public void exitBlockStatement(MiracleParser.BlockStatementContext ctx) {
        MiracleScope.exitScope();
    }

    @Override
    public void enterClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        MiracleScope.addScope(MiracleScope.ScopeType.SCOPE_CLASS);
    }

    @Override
    public void exitClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        MiracleScope.exitScope();
    }

    @Override
    public void enterForStatement(MiracleParser.ForStatementContext ctx) {
        MiracleScope.addScope(MiracleScope.ScopeType.SCOPE_LOOP);
    }

    @Override
    public void exitForStatement(MiracleParser.ForStatementContext ctx) {
        MiracleScope.exitScope();
    }

    @Override
    public void enterContinueStatement(MiracleParser.ContinueStatementContext ctx) {
        if (!MiracleScope.inScope(MiracleScope.ScopeType.SCOPE_LOOP)) {
            /**
             * TODO: Modified here to acutal rows and columns.
             */
            throw new MiracleExceptionControlStatement(-1, -1, "continue", "iteration");
        }
    }

    @Override
    public void enterBreakStatement(MiracleParser.BreakStatementContext ctx) {
        if (!MiracleScope.inScope(MiracleScope.ScopeType.SCOPE_LOOP)) {
            /**
             * TODO: Modified here to acutal rows and columns.
             */
            throw new MiracleExceptionControlStatement(-1, -1, "break", "iteration");
        }
    }

    @Override
    public void enterReturnStatement(MiracleParser.ReturnStatementContext ctx) {
        if (!MiracleScope.inScope(MiracleScope.ScopeType.SCOPE_FUNC)) {
            /**
             * TODO: Modified here to acutal rows and columns.
             */
            throw new MiracleExceptionControlStatement(-1, -1, "return", "function");
        }
    }
}
