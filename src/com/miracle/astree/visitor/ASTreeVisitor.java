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

public interface ASTreeVisitor {
    void visit(FunctionDeclaration functionDeclaration);

    void visit(ClassDeclaration classDeclaration);

    void visit(ASTree astree);

    void visit(VariableDeclaration variableDeclaration);

    void visit(Block block);

    void visit(Selection selection);

    void visit(Iteration iteration);

    void visit(Break breakLiteral);

    void visit(Continue continueLiteral);

    void visit(ReturnStatement returnLiteral);

    void visit(Variable variable);

    void visit(CallExpression callExpression);

    void visit(Subscript subscript);

    void visit(BinaryExpression binaryExpression);

    void visit(PrefixExpression prefixExpression);

    void visit(SuffixExpression suffixExpression);

    void visit(New newNode);

    void visit(StringConstant stringConstant);

    void visit(IntegerConstant integerConstant);

    void visit(BooleanConstant booleanConstant);

    void visit(NullConstant nullConstant);

    void visit(This thisNode);

    void visit(Field field);

    void visit(TypeNode typeNode);
}
