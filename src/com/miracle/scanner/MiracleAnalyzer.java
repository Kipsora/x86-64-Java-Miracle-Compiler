package com.miracle.scanner;

import com.miracle.cstree.MiracleBaseListener;
import com.miracle.cstree.MiracleParser;
import com.miracle.cstree.MiracleParser.ExpressionContext;
import com.miracle.exceptions.MiracleCompilationError;
import org.antlr.v4.runtime.tree.TerminalNode;

public class MiracleAnalyzer extends MiracleBaseListener {
    @Override
    public void enterVariableDeclarationStatement(MiracleParser.VariableDeclarationStatementContext ctx) {
        TerminalNode tmp = ctx.IDENTIFIER();
        if (MiracleScope.duplicatesIdentifier(tmp.toString())) {
            throw new MiracleCompilationError(tmp.getSymbol(), "Duplicates identifier " + tmp.toString() + " was found.");
        }
        if (ctx.expression() == null) {
            MiracleScope.addIdentifier(ctx.IDENTIFIER().toString(), new MiracleVariableIdentifier(ctx.typename().toString(), null));
        } else {
            MiracleScope.addIdentifier(ctx.IDENTIFIER().toString(), new MiracleVariableIdentifier(ctx.typename().toString(), ctx.expression().toString()));
        }
    }

    @Override
    public void enterSelectionStatement(MiracleParser.SelectionStatementContext ctx) {
        ExpressionContext tmp = ctx.expression();
        switch (tmp.getRuleIndex()) {
            case 1:
                if (tmp.getToken() )
        }
        System.out.println("Rule Index: " + ctx.expression().getRuleIndex());
    }

    @Override
    public void enterClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        TerminalNode tmp = ctx.IDENTIFIER(0);
        if (MiracleScope.duplicatesIdentifier(tmp.toString())) {
            throw new MiracleCompilationError(tmp.getSymbol(), "Duplicates identifier " + tmp.toString() + " was found.");
        }
        MiracleScope.addIdentifier(tmp.toString(), new MiracleClassIdentifier());
        MiracleScope.addScope(MiracleScope.ScopeType.SCOPE_CLASS);
    }

    @Override
    public void enterVariableExpression(MiracleParser.VariableExpressionContext ctx) {
        TerminalNode tmp = ctx.IDENTIFIER();
        if (!MiracleScope.containsIdentifier(tmp.toString())) {
            throw new MiracleCompilationError(tmp.getSymbol(), "Identifier " + tmp.toString() + " was not found.");
        }
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
    public void exitClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        MiracleScope.exitScope();
    }

    @Override
    public void enterBlockStatement(MiracleParser.BlockStatementContext ctx) {
        MiracleScope.addScope(MiracleScope.ScopeType.SCOPE_BLOCK);
    }

    @Override
    public void exitBlockStatement(MiracleParser.BlockStatementContext ctx) {
        MiracleScope.exitScope();
    }
}
