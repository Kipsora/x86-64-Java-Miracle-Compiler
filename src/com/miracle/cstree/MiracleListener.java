package com.miracle.cstree;// Generated from /home/kipsora/IdeaProjects/x86-64-Miralce-Java-Compiler/antlr/Miracle.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MiracleParser}.
 */
public interface MiracleListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MiracleParser#miracle}.
	 * @param ctx the parse tree
	 */
	void enterMiracle(MiracleParser.MiracleContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiracleParser#miracle}.
	 * @param ctx the parse tree
	 */
	void exitMiracle(MiracleParser.MiracleContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiracleParser#classDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiracleParser#classDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiracleParser#functionDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiracleParser#functionDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiracleParser#variableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarationStatement(MiracleParser.VariableDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiracleParser#variableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarationStatement(MiracleParser.VariableDeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiracleParser#constructorDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterConstructorDeclarationStatement(MiracleParser.ConstructorDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiracleParser#constructorDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitConstructorDeclarationStatement(MiracleParser.ConstructorDeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiracleParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(MiracleParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiracleParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(MiracleParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiracleParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MiracleParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiracleParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MiracleParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiracleParser#selectionIfStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectionIfStatement(MiracleParser.SelectionIfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiracleParser#selectionIfStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectionIfStatement(MiracleParser.SelectionIfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiracleParser#selectionElseStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectionElseStatement(MiracleParser.SelectionElseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiracleParser#selectionElseStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectionElseStatement(MiracleParser.SelectionElseStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forStatement}
	 * labeled alternative in {@link MiracleParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(MiracleParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forStatement}
	 * labeled alternative in {@link MiracleParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(MiracleParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link MiracleParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(MiracleParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link MiracleParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(MiracleParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueStatement}
	 * labeled alternative in {@link MiracleParser#controlStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(MiracleParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueStatement}
	 * labeled alternative in {@link MiracleParser#controlStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(MiracleParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakStatement}
	 * labeled alternative in {@link MiracleParser#controlStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(MiracleParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakStatement}
	 * labeled alternative in {@link MiracleParser#controlStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(MiracleParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStatement}
	 * labeled alternative in {@link MiracleParser#controlStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(MiracleParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStatement}
	 * labeled alternative in {@link MiracleParser#controlStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(MiracleParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiracleParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(MiracleParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiracleParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(MiracleParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiracleParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStatement(MiracleParser.EmptyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiracleParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStatement(MiracleParser.EmptyStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiracleParser#typename}.
	 * @param ctx the parse tree
	 */
	void enterTypename(MiracleParser.TypenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiracleParser#typename}.
	 * @param ctx the parse tree
	 */
	void exitTypename(MiracleParser.TypenameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryExpression}
	 * labeled alternative in {@link MiracleParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpression(MiracleParser.BinaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExpression}
	 * labeled alternative in {@link MiracleParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpression(MiracleParser.BinaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code prefixExpression}
	 * labeled alternative in {@link MiracleParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPrefixExpression(MiracleParser.PrefixExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code prefixExpression}
	 * labeled alternative in {@link MiracleParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPrefixExpression(MiracleParser.PrefixExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constantExpression}
	 * labeled alternative in {@link MiracleParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpression(MiracleParser.ConstantExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constantExpression}
	 * labeled alternative in {@link MiracleParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpression(MiracleParser.ConstantExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subscriptExpression}
	 * labeled alternative in {@link MiracleParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSubscriptExpression(MiracleParser.SubscriptExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subscriptExpression}
	 * labeled alternative in {@link MiracleParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSubscriptExpression(MiracleParser.SubscriptExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code suffixExpression}
	 * labeled alternative in {@link MiracleParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSuffixExpression(MiracleParser.SuffixExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code suffixExpression}
	 * labeled alternative in {@link MiracleParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSuffixExpression(MiracleParser.SuffixExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newExpression}
	 * labeled alternative in {@link MiracleParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNewExpression(MiracleParser.NewExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newExpression}
	 * labeled alternative in {@link MiracleParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNewExpression(MiracleParser.NewExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code braceExpression}
	 * labeled alternative in {@link MiracleParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBraceExpression(MiracleParser.BraceExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code braceExpression}
	 * labeled alternative in {@link MiracleParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBraceExpression(MiracleParser.BraceExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link MiracleParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallExpression(MiracleParser.FunctionCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link MiracleParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallExpression(MiracleParser.FunctionCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableExpression}
	 * labeled alternative in {@link MiracleParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterVariableExpression(MiracleParser.VariableExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableExpression}
	 * labeled alternative in {@link MiracleParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitVariableExpression(MiracleParser.VariableExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiracleParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(MiracleParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiracleParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(MiracleParser.ConstantContext ctx);
}