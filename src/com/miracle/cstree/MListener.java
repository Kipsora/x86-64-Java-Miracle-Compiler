package com.miracle.cstree;// Generated from Miracle.g4 by ANTLR 4.6
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MParser}.
 */
public interface MListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MParser#miracle}.
	 * @param ctx the parse tree
	 */
	void enterMiracle(MParser.MiracleContext ctx);
	/**
	 * Exit a parse tree produced by {@link MParser#miracle}.
	 * @param ctx the parse tree
	 */
	void exitMiracle(MParser.MiracleContext ctx);
	/**
	 * Enter a parse tree produced by {@link MParser#classDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclarationStatement(MParser.ClassDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MParser#classDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclarationStatement(MParser.ClassDeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MParser#functionDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclarationStatement(MParser.FunctionDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MParser#functionDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclarationStatement(MParser.FunctionDeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MParser#variableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarationStatement(MParser.VariableDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MParser#variableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarationStatement(MParser.VariableDeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(MParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(MParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MParser#memberDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclarationStatement(MParser.MemberDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MParser#memberDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclarationStatement(MParser.MemberDeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectionStatement(MParser.SelectionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectionStatement(MParser.SelectionStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forStatement}
	 * labeled alternative in {@link MParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(MParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forStatement}
	 * labeled alternative in {@link MParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(MParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link MParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(MParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link MParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(MParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueStatement}
	 * labeled alternative in {@link MParser#controlStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(MParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueStatement}
	 * labeled alternative in {@link MParser#controlStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(MParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakStatement}
	 * labeled alternative in {@link MParser#controlStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(MParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakStatement}
	 * labeled alternative in {@link MParser#controlStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(MParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStatement}
	 * labeled alternative in {@link MParser#controlStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(MParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStatement}
	 * labeled alternative in {@link MParser#controlStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(MParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(MParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(MParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStatement(MParser.EmptyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStatement(MParser.EmptyStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MParser#typename}.
	 * @param ctx the parse tree
	 */
	void enterTypename(MParser.TypenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MParser#typename}.
	 * @param ctx the parse tree
	 */
	void exitTypename(MParser.TypenameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constantExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpression(MParser.ConstantExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constantExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpression(MParser.ConstantExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xorExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterXorExpression(MParser.XorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xorExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitXorExpression(MParser.XorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subscriptExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSubscriptExpression(MParser.SubscriptExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subscriptExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSubscriptExpression(MParser.SubscriptExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNewExpression(MParser.NewExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNewExpression(MParser.NewExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpression(MParser.AssignExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpression(MParser.AssignExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code braceExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBraceExpression(MParser.BraceExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code braceExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBraceExpression(MParser.BraceExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multDivExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultDivExpression(MParser.MultDivExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multDivExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultDivExpression(MParser.MultDivExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMemberExpression(MParser.MemberExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMemberExpression(MParser.MemberExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterVariableExpression(MParser.VariableExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitVariableExpression(MParser.VariableExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compareExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCompareExpression(MParser.CompareExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compareExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCompareExpression(MParser.CompareExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpression(MParser.OrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpression(MParser.OrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(MParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(MParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicAndExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicAndExpression(MParser.LogicAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicAndExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicAndExpression(MParser.LogicAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code prefixExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPrefixExpression(MParser.PrefixExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code prefixExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPrefixExpression(MParser.PrefixExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicOrExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicOrExpression(MParser.LogicOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicOrExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicOrExpression(MParser.LogicOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addSubExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpression(MParser.AddSubExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addSubExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpression(MParser.AddSubExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code suffixExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSuffixExpression(MParser.SuffixExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code suffixExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSuffixExpression(MParser.SuffixExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code shlShrExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterShlShrExpression(MParser.ShlShrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code shlShrExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitShlShrExpression(MParser.ShlShrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equalityExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(MParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalityExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(MParser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallExpression(MParser.FunctionCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallExpression(MParser.FunctionCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(MParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpression}
	 * labeled alternative in {@link MParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(MParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(MParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link MParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(MParser.ConstantContext ctx);
}