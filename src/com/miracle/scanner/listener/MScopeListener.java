package com.miracle.scanner.listener;

import com.miracle.cstree.MBaseListener;
import com.miracle.cstree.MParser;
import com.miracle.scanner.scope.MScope;

public class MScopeListener extends MBaseListener {
    @Override
    public void enterBlockStatement(MParser.BlockStatementContext ctx) {
        MScope.addScope(MScope.ScopeType.SCOPE_BLOCK);
    }

    @Override
    public void exitBlockStatement(MParser.BlockStatementContext ctx) {
        MScope.exitScope();
    }

    @Override
    public void enterClassDeclarationStatement(MParser.ClassDeclarationStatementContext ctx) {
        MScope.addScope(MScope.ScopeType.SCOPE_CLASS);
        MScope.addIdentifier(ctx.IDENTIFIER(0).getText(), ctx.IDENTIFIER(0).getSymbol().getText());
    }

    @Override
    public void exitClassDeclarationStatement(MParser.ClassDeclarationStatementContext ctx) {
        MScope.exitScope();
    }
}
