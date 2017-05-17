package com.miracle.intermediate.base;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.base.MiracleASTreeTypeNode;
import com.miracle.astree.statement.*;
import com.miracle.astree.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.astree.statement.expression.*;
import com.miracle.astree.statement.expression.constant.MiracleASTreeBooleanConstant;
import com.miracle.astree.statement.expression.constant.MiracleASTreeIntegerConstant;
import com.miracle.astree.statement.expression.constant.MiracleASTreeNullConstant;
import com.miracle.astree.statement.expression.constant.MiracleASTreeStringConstant;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.symbol.type.MiracleVariableType;

import java.util.List;

public class MiracleIRFunction extends MiracleIRBase {
    private final MiracleVariableType returnType;
    private MiracleIRBasicBlock entryBasicBlock;

    public MiracleIRFunction(MiracleVariableType returnType,
                             MiracleIRBasicBlock block) {
        this.returnType = returnType;
        this.entryBasicBlock = block;
    }

    public MiracleIRFunction(MiracleVariableType returnType) {
        this.returnType = returnType;
    }

    public MiracleIRBasicBlock getEntryBasicBlock() {
        return entryBasicBlock;
    }

    public void setEntryBasicBlock(MiracleIRBasicBlock entryBasicBlock) {
        this.entryBasicBlock = entryBasicBlock;
    }

    public static class Builder implements MiracleASTreeVisitor {
        private MiracleIRFunction currentFunction;
        private boolean isArgumentParameter;

        @Override
        public void visit(MiracleASTreeFunctionDeclaration functionDeclaration) {
            currentFunction = new MiracleIRFunction(functionDeclaration.returnType.type);

            isArgumentParameter = true;
            functionDeclaration.parameters.forEach(element -> element.accept(this));
            isArgumentParameter = false;

            if (functionDeclaration.body != null) { // non-builtin functions
                List<MiracleASTreeStatement> body = functionDeclaration.body;
                for (int i = 0, bodySize = body.size(); i < bodySize; i++) {
                    MiracleASTreeStatement element = body.get(i);
                    element.accept(this);
                }
            }
            currentFunction = null;
        }

        @Override
        public void visit(MiracleASTreeClassDeclaration classDeclaration) {

        }

        @Override
        public void visit(MiracleASTree astree) {

        }

        @Override
        public void visit(MiracleASTreeVariableDeclaration variableDeclaration) {

        }

        @Override
        public void visit(MiracleASTreeBlock block) {

        }

        @Override
        public void visit(MiracleASTreeSelection selection) {

        }

        @Override
        public void visit(MiracleASTreeIteration iteration) {

        }

        @Override
        public void visit(MiracleASTreeBreak breakLiteral) {

        }

        @Override
        public void visit(MiracleASTreeContinue continueLiteral) {

        }

        @Override
        public void visit(MiracleASTreeReturn returnLiteral) {

        }

        @Override
        public void visit(MiracleASTreeVariable variable) {

        }

        @Override
        public void visit(MiracleASTreeCall call) {

        }

        @Override
        public void visit(MiracleASTreeSubscript subscript) {

        }

        @Override
        public void visit(MiracleASTreeBinaryExpression binaryExpression) {

        }

        @Override
        public void visit(MiracleASTreePrefixExpression prefixExpression) {

        }

        @Override
        public void visit(MiracleASTreeSuffixExpression suffixExpression) {

        }

        @Override
        public void visit(MiracleASTreeNew newNode) {

        }

        @Override
        public void visit(MiracleASTreeStringConstant stringConstant) {

        }

        @Override
        public void visit(MiracleASTreeIntegerConstant integerConstant) {

        }

        @Override
        public void visit(MiracleASTreeBooleanConstant booleanConstant) {

        }

        @Override
        public void visit(MiracleASTreeNullConstant nullConstant) {

        }

        @Override
        public void visit(MiracleASTreeThis thisNode) {

        }

        @Override
        public void visit(MiracleASTreeField field) {

        }

        @Override
        public void visit(MiracleASTreeTypeNode typeNode) {

        }
    }
}
