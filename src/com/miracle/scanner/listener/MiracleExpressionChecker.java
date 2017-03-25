package com.miracle.scanner.listener;

import com.miracle.cstree.MiracleParser;
import com.miracle.scanner.environment.MiracleEnvironmentManager;

public class MiracleExpressionChecker extends MiracleRuntimeMaintainer {
    MiracleExpressionChecker(MiracleEnvironmentManager environment) {
        super(environment);
    }

    @Override
    public void enterConstantExpression(MiracleParser.ConstantExpressionContext ctx) {
    }

    @Override
    public void enterVariableExpression(MiracleParser.VariableExpressionContext ctx) {
    }

    @Override
    public void enterBraceExpression(MiracleParser.BraceExpressionContext ctx) {
    }

    @Override
    public void enterFunctionCallExpression(MiracleParser.FunctionCallExpressionContext ctx) {
    }

    @Override
    public void enterSubscriptExpression(MiracleParser.SubscriptExpressionContext ctx) {
    }

    @Override
    public void enterMemberExpression(MiracleParser.MemberExpressionContext ctx) {
    }

    @Override
    public void enterSuffixExpression(MiracleParser.SuffixExpressionContext ctx) {
    }

    @Override
    public void enterUnaryExpression(MiracleParser.UnaryExpressionContext ctx) {
    }

    @Override
    public void enterNewExpression(MiracleParser.NewExpressionContext ctx) {
    }

    @Override
    public void enterMultDivExpression(MiracleParser.MultDivExpressionContext ctx) {
    }

    @Override
    public void enterAddSubExpression(MiracleParser.AddSubExpressionContext ctx) {
    }

    @Override
    public void enterShlShrExpression(MiracleParser.ShlShrExpressionContext ctx) {
    }

    @Override
    public void enterCompareExpression(MiracleParser.CompareExpressionContext ctx) {
    }

    @Override
    public void enterEqualityExpression(MiracleParser.EqualityExpressionContext ctx) {
    }

    @Override
    public void enterAndExpression(MiracleParser.AndExpressionContext ctx) {
    }

    @Override
    public void enterXorExpression(MiracleParser.XorExpressionContext ctx) {
    }

    @Override
    public void enterOrExpression(MiracleParser.OrExpressionContext ctx) {
    }

    @Override
    public void enterLogicAndExpression(MiracleParser.LogicAndExpressionContext ctx) {
    }

    @Override
    public void enterLogicOrExpression(MiracleParser.LogicOrExpressionContext ctx) {
    }

    @Override
    public void enterAssignExpression(MiracleParser.AssignExpressionContext ctx) {
    }
}
