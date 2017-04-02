package com.miracle.scanner.listener;

import com.miracle.astree.node.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;
import com.miracle.astree.node.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.cstree.MiracleBaseListener;
import com.miracle.cstree.MiracleParser;
import com.miracle.scanner.MiracleEnvironmentManager;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.HashSet;
import java.util.LinkedList;

public abstract class MiracleRuntimeMaintainer extends MiracleBaseListener {
    private static int row;
    private static int column;

    public static final MiracleASTreeTypename MiracleASTreeINT =
            new MiracleASTreeTypename("int");
    public static final MiracleASTreeTypename MiracleASTreeBOOLEAN =
            new MiracleASTreeTypename("bool");
    public static final MiracleASTreeTypename MiracleASTreeSTRING =
            new MiracleASTreeTypename("string");
    static final MiracleASTreeTypename MiracleASTreeVOID =
            new MiracleASTreeTypename("void");
    static final HashSet MiracleASTreeBuiltinTYPE =
            new HashSet<MiracleASTreeTypename>() {{
                add(MiracleASTreeINT);
                add(MiracleASTreeBOOLEAN);
                add(MiracleASTreeSTRING);
                add(MiracleASTreeVOID);
            }};

    static final MiracleASTreeFunctionDeclaration MiracleASTreeTOSTRING =
            new MiracleASTreeFunctionDeclaration(
                    MiracleASTreeSTRING, "toString",
                    new LinkedList<MiracleASTreeVariableDeclaration>() {{
                        add(new MiracleASTreeVariableDeclaration("x", MiracleASTreeINT));
                    }}
            );
    static final MiracleASTreeFunctionDeclaration MiracleASTreePRINT =
            new MiracleASTreeFunctionDeclaration(
                    MiracleASTreeVOID, "print",
                    new LinkedList<MiracleASTreeVariableDeclaration>() {{
                        add(new MiracleASTreeVariableDeclaration("x", MiracleASTreeSTRING));
                    }}
            );
    static final MiracleASTreeFunctionDeclaration MiracleASTreePRINTLN =
            new MiracleASTreeFunctionDeclaration(
                    MiracleASTreeVOID, "println",
                    new LinkedList<MiracleASTreeVariableDeclaration>() {{
                        add(new MiracleASTreeVariableDeclaration("x", MiracleASTreeSTRING));
                    }}
            );
    static final MiracleASTreeFunctionDeclaration MiracleASTreeGETSTRING =
            new MiracleASTreeFunctionDeclaration(
                    MiracleASTreeSTRING, "getString",
                    new LinkedList<>()
            );
    static final MiracleASTreeFunctionDeclaration MiracleASTreeGETINT =
            new MiracleASTreeFunctionDeclaration(
                    MiracleASTreeINT, "getInt",
                    new LinkedList<>()
            );
    static final MiracleASTreeFunctionDeclaration MiracleASTreeSIZE =
            new MiracleASTreeFunctionDeclaration(
                    MiracleASTreeINT, "size",
                    new LinkedList<>()
            );
    static final MiracleASTreeFunctionDeclaration MiracleASTreeLENGTH =
            new MiracleASTreeFunctionDeclaration(
                    MiracleASTreeINT, "length",
                    new LinkedList<>()
            );
    static final MiracleASTreeFunctionDeclaration MiracleASTreeSUBSTRING =
            new MiracleASTreeFunctionDeclaration(
                    MiracleASTreeSTRING, "substring",
                    new LinkedList<MiracleASTreeVariableDeclaration>() {{
                        add(new MiracleASTreeVariableDeclaration("l", MiracleASTreeINT));
                        add(new MiracleASTreeVariableDeclaration("r", MiracleASTreeINT));
                    }}
            );
    static final MiracleASTreeFunctionDeclaration MiracleASTreePARSEINT =
            new MiracleASTreeFunctionDeclaration(
                    MiracleASTreeINT, "parseInt",
                    new LinkedList<>()
            );
    static final MiracleASTreeFunctionDeclaration MiracleASTreeORD =
            new MiracleASTreeFunctionDeclaration(
                    MiracleASTreeINT, "ord",
                    new LinkedList<MiracleASTreeVariableDeclaration>() {{
                        add(new MiracleASTreeVariableDeclaration("l", MiracleASTreeINT));
                    }}
            );

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
