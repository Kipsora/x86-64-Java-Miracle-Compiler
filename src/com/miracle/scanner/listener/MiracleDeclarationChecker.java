package com.miracle.scanner.listener;

import com.miracle.cstree.MiracleParser;
import com.miracle.scanner.MiracleEnvironmentManager;

public class MiracleDeclarationChecker extends MiracleRuntimeMaintainer {

    @Override
    public void enterVariableDeclarationStatement(MiracleParser.VariableDeclarationStatementContext ctx) {
        MiracleEnvironmentManager.declareVariable(ctx.IDENTIFIER().getText());
        super.enterVariableDeclarationStatement(ctx);
    }

    @Override
    public void enterClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        MiracleEnvironmentManager.declareClass(ctx.IDENTIFIER().getText());
        super.enterClassDeclarationStatement(ctx);
    }

    @Override
    public void enterFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx) {
        MiracleEnvironmentManager.declareFunction(ctx.IDENTIFIER(0).getText());
        super.enterFunctionDeclarationStatement(ctx);
    }
}
