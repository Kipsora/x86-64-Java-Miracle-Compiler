package com.miracle.astree.expression;

import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeBinaryExpression extends MiracleASTreeExpression {
    public final MiracleASTreeExpression left;
    public final MiracleASTreeExpression right;
    public final OPERATOR operator;

    public MiracleASTreeBinaryExpression(MiracleASTreeExpression left,
                                         OPERATOR operator,
                                         MiracleASTreeExpression right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public enum OPERATOR {
        ADD, SUB, MUL, DIV, MOD,
        DOT, SHL, SHR, LT, RT,
        LEQ, REQ, EQL, NEQ, AND,
        XOR, OR, CONJ, DISJ, ASS;

        @Override
        public String toString() {
            if (this.equals(ADD)) {
                return "+";
            } else if (this.equals(SUB)) {
                return "-";
            } else if (this.equals(MUL)) {
                return "*";
            } else if (this.equals(DIV)) {
                return "/";
            } else if (this.equals(MOD)) {
                return "%";
            } else if (this.equals(DOT)) {
                return ".";
            } else if (this.equals(SHL)) {
                return "<<";
            } else if (this.equals(SHR)) {
                return ">>";
            } else if (this.equals(LT)) {
                return "<";
            } else if (this.equals(RT)) {
                return ">";
            } else if (this.equals(LEQ)) {
                return "<=";
            } else if (this.equals(REQ)) {
                return ">=";
            } else if (this.equals(EQL)) {
                return "==";
            } else if (this.equals(NEQ)) {
                return "!=";
            } else if (this.equals(AND)) {
                return "&";
            } else if (this.equals(OR)) {
                return "|";
            } else if (this.equals(XOR)) {
                return "^";
            } else if (this.equals(CONJ)) {
                return "&&";
            } else if (this.equals(DISJ)) {
                return "||";
            } else {
                return "=";
            }
        }
    }
}
