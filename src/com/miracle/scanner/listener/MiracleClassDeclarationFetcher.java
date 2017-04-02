package com.miracle.scanner.listener;

import com.miracle.cstree.MiracleParser;
import com.miracle.scanner.MiracleEnvironmentManager;

public class MiracleClassDeclarationFetcher extends MiracleRuntimeMaintainer {
    @Override
    public void exitClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        super.exitClassDeclarationStatement(ctx);
        MiracleEnvironmentManager.declareClass(ctx.IDENTIFIER().getText());
    }
}
