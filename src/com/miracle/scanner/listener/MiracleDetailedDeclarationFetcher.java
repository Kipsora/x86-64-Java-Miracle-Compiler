package com.miracle.scanner.listener;

import com.miracle.astree.node.MiracleASTreeNode;
import com.miracle.astree.node.statement.declaration.MiracleASTreeMemberDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;
import com.miracle.astree.node.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.cstree.MiracleParser;
import com.miracle.scanner.MiracleEnvironmentManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MiracleDetailedDeclarationFetcher extends MiracleRuntimeMaintainer {
    private Stack<List<MiracleASTreeNode>> path = new Stack<>();

    public MiracleDetailedDeclarationFetcher() {
        path.push(new LinkedList<>());
    }

    @Override
    public void enterTypename(MiracleParser.TypenameContext ctx) {
        super.enterTypename(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void enterClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        super.enterClassDeclarationStatement(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        LinkedList<MiracleASTreeMemberDeclaration> tmp = new LinkedList<>();
        for (MiracleASTreeNode entry : path.pop()) {
            tmp.add((MiracleASTreeMemberDeclaration) entry);
        }
        MiracleEnvironmentManager.getClass(ctx.IDENTIFIER().getText()).setChildren(tmp);
        super.exitClassDeclarationStatement(ctx);
    }

    @Override
    public void exitTypename(MiracleParser.TypenameContext ctx) {
        path.pop();
        int dimension = 0;
        if (ctx.IDENTIFIER() != null) {
            String identifier = ctx.IDENTIFIER().getText();
            if (!MiracleASTreeBuiltinTYPE.contains(new MiracleASTreeTypename(identifier))) {
                MiracleEnvironmentManager.getClass(identifier);
            }
            for (int i = 1; i < ctx.getChildCount(); i++) {
                if (ctx.getChild(i).getText().equals("[")) {
                    dimension++;
                }
            }
            path.peek().add(new MiracleASTreeTypename(ctx.IDENTIFIER().getText(), dimension));
        } else {
            for (int i = 1; i < ctx.getChildCount(); i++) {                          // custom types
                if (ctx.getChild(i).getText().equals("[")) {
                    dimension++;
                }
            }
            path.peek().add(new MiracleASTreeTypename(ctx.BASETYPE().getText(), dimension));
        }
        super.exitTypename(ctx);
    }

    @Override
    public void enterVariableDeclarationStatement(MiracleParser.VariableDeclarationStatementContext ctx) {
        super.enterVariableDeclarationStatement(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitVariableDeclarationStatement(MiracleParser.VariableDeclarationStatementContext ctx) {
        MiracleASTreeTypename type = (MiracleASTreeTypename) path.pop().get(0);
        super.exitVariableDeclarationStatement(ctx);
        MiracleEnvironmentManager.declareVariable(ctx.IDENTIFIER().getText(), type);
        path.peek().add(MiracleEnvironmentManager.getVariable(ctx.IDENTIFIER().getText()));
    }

    @Override
    public void enterFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx) {
        super.enterFunctionDeclarationStatement(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        MiracleASTreeTypename type = (MiracleASTreeTypename) children.get(0);
        List<MiracleASTreeVariableDeclaration> arguments = new LinkedList<>();
        for (int i = 1; i < ctx.IDENTIFIER().size(); i++) {
            MiracleEnvironmentManager.declareVariable(ctx.IDENTIFIER(i).getText(),
                    (MiracleASTreeTypename) children.get(i));
            arguments.add(MiracleEnvironmentManager.getVariable(ctx.IDENTIFIER(i).getText()));
        }
        super.exitFunctionDeclarationStatement(ctx);
        MiracleEnvironmentManager.declareFunction(ctx.IDENTIFIER(0).getText(), type, arguments);
        path.peek().add(MiracleEnvironmentManager.getFunction(ctx.IDENTIFIER(0).getText()));
    }
}
