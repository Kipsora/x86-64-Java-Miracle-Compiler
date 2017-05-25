package com.miracle.astree.visitor;

import com.miracle.astree.ASTree;
import com.miracle.astree.base.TypeNode;
import com.miracle.astree.statement.*;
import com.miracle.astree.statement.declaration.ClassDeclaration;
import com.miracle.astree.statement.declaration.FunctionDeclaration;
import com.miracle.astree.statement.declaration.VariableDeclaration;
import com.miracle.astree.statement.expression.*;
import com.miracle.astree.statement.expression.constant.BooleanConstant;
import com.miracle.astree.statement.expression.constant.IntegerConstant;
import com.miracle.astree.statement.expression.constant.NullConstant;
import com.miracle.astree.statement.expression.constant.StringConstant;

public class BaseVisitor implements Visitor {
    @Override
    public void visit(FunctionDeclaration functionDeclaration) {

    }

    @Override
    public void visit(ClassDeclaration classDeclaration) {

    }

    @Override
    public void visit(ASTree astree) {

    }

    @Override
    public void visit(VariableDeclaration variableDeclaration) {

    }

    @Override
    public void visit(Block block) {

    }

    @Override
    public void visit(Selection selection) {

    }

    @Override
    public void visit(Iteration iteration) {

    }

    @Override
    public void visit(Break breakLiteral) {

    }

    @Override
    public void visit(Continue continueLiteral) {

    }

    @Override
    public void visit(Return returnLiteral) {

    }

    @Override
    public void visit(Variable variable) {

    }

    @Override
    public void visit(Call call) {

    }

    @Override
    public void visit(Subscript subscript) {

    }

    @Override
    public void visit(BinaryExpression binaryExpression) {

    }

    @Override
    public void visit(PrefixExpression prefixExpression) {

    }

    @Override
    public void visit(SuffixExpression suffixExpression) {

    }

    @Override
    public void visit(New newNode) {

    }

    @Override
    public void visit(StringConstant stringConstant) {

    }

    @Override
    public void visit(IntegerConstant integerConstant) {

    }

    @Override
    public void visit(BooleanConstant booleanConstant) {

    }

    @Override
    public void visit(NullConstant nullConstant) {

    }

    @Override
    public void visit(This thisNode) {

    }

    @Override
    public void visit(Field field) {

    }

    @Override
    public void visit(TypeNode typeNode) {

    }
}
