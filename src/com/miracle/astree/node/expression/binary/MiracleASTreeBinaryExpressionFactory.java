package com.miracle.astree.node.expression.binary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;

public class MiracleASTreeBinaryExpressionFactory {
    private MiracleASTreeBinaryExpressionFactory() {}

    public static MiracleASTreeBinaryExpression getInstance(MiracleASTreeExpression left, String operator, MiracleASTreeExpression right) {
        switch (operator) {
            case "+":
                if (left.getType().equals("string")) {
                    return new MiracleASTreeStringConcat(left, right);
                } else {
                    return new MiracleASTreeBinaryIntegral(left, MiracleASTreeBinaryIntegral.OPERATOR.ADD, right);
                }
            case "-":
                return new MiracleASTreeBinaryIntegral(left, MiracleASTreeBinaryIntegral.OPERATOR.SUB, right);
            case "*":
                return new MiracleASTreeBinaryIntegral(left, MiracleASTreeBinaryIntegral.OPERATOR.MUL, right);
            case "/":
                return new MiracleASTreeBinaryIntegral(left, MiracleASTreeBinaryIntegral.OPERATOR.DIV, right);
            case "&":
                return new MiracleASTreeBinaryIntegral(left, MiracleASTreeBinaryIntegral.OPERATOR.AND, right);
            case "&&":
                return new MiracleASTreeLogic(left, MiracleASTreeLogic.OPERATOR.AND, right);
            case "|":
                return new MiracleASTreeBinaryIntegral(left, MiracleASTreeBinaryIntegral.OPERATOR.OR, right);
            case "||":
                return new MiracleASTreeLogic(left, MiracleASTreeLogic.OPERATOR.OR, right);
            case "^":
                if (left.getType().equals("int")) {
                    return new MiracleASTreeBinaryIntegral(left, MiracleASTreeBinaryIntegral.OPERATOR.XOR, right);
                } else {
                    return new MiracleASTreeLogic(left, MiracleASTreeLogic.OPERATOR.XOR, right);
                }
            case "%":
                return new MiracleASTreeBinaryIntegral(left, MiracleASTreeBinaryIntegral.OPERATOR.MOD, right);
            case "<":
                return new MiracleASTreeCompare(left, MiracleASTreeCompare.OPERATOR.L, right);
            case "<=":
                return new MiracleASTreeCompare(left, MiracleASTreeCompare.OPERATOR.LEQ, right);
            case ">":
                return new MiracleASTreeCompare(left, MiracleASTreeCompare.OPERATOR.R, right);
            case ">=":
                return new MiracleASTreeCompare(left, MiracleASTreeCompare.OPERATOR.REQ, right);
            case "==":
                return new MiracleASTreeCompare(left, MiracleASTreeCompare.OPERATOR.EQ, right);
            case "!=":
                return new MiracleASTreeCompare(left, MiracleASTreeCompare.OPERATOR.NEQ, right);
            case "=":
                return new MiracleASTreeAssign(left, right);
            case "<<":
                return new MiracleASTreeBinaryIntegral(left, MiracleASTreeBinaryIntegral.OPERATOR.SHL, right);
            case ">>":
                return new MiracleASTreeBinaryIntegral(left, MiracleASTreeBinaryIntegral.OPERATOR.SHR, right);
            default:
                return null;
        }
    }
}
