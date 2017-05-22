package com.miracle.intermediate;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.base.MiracleASTreeTypeNode;
import com.miracle.astree.statement.*;
import com.miracle.astree.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.astree.statement.expression.*;
import com.miracle.astree.statement.expression.constant.MiracleASTreeBooleanConstant;
import com.miracle.astree.statement.expression.constant.MiracleASTreeIntegerConstant;
import com.miracle.astree.statement.expression.constant.MiracleASTreeNullConstant;
import com.miracle.astree.statement.expression.constant.MiracleASTreeStringConstant;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.intermediate.instruction.MiracleIRBinary;
import com.miracle.intermediate.instruction.MiracleIRMove;
import com.miracle.intermediate.structure.MiracleIRBasicBlock;
import com.miracle.intermediate.structure.MiracleIRFunction;
import com.miracle.intermediate.value.*;
import com.miracle.intermediate.visitor.MiracleIRVisitor;
import com.miracle.symbol.MiracleSymbolTable;

import java.util.HashMap;
import java.util.Map;

public class MiracleIR extends MiracleIRNode {
    public final Map<String, MiracleIRFunction> globalFunctions;
    public final Map<String, MiracleIRStaticString> globalString;
    public final Map<String, MiracleIRStaticVariable> globalVariable;

    public MiracleIR(Map<String, MiracleIRFunction> globalFunctions,
                     Map<String, MiracleIRStaticString> globalString,
                     Map<String, MiracleIRStaticVariable> globalVariable) {
        this.globalFunctions = globalFunctions;
        this.globalString = globalString;
        this.globalVariable = globalVariable;
    }

    @Override
    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }

    public static class Builder implements MiracleASTreeVisitor {
        private Map<String, MiracleIRFunction> globalFunctions = new HashMap<>();
        private Map<String, MiracleIRStaticString> globalString = new HashMap<>();
        private Map<String, MiracleIRStaticVariable> globalVariable = new HashMap<>();

        private MiracleIRBasicBlock curBB;
        private MiracleIRFunction curFunc;
        private boolean isParameter;
        private MiracleSymbolTable scope;
        private int registerCounter;

        public MiracleIR build() {
            return new MiracleIR(globalFunctions, globalString, globalVariable);
        }

        @Override
        public void visit(MiracleASTreeFunctionDeclaration functionDeclaration) {
            scope = functionDeclaration.getScope();
            curFunc = new MiracleIRFunction(functionDeclaration.identifier);
            curBB = curFunc.entryBB;

            isParameter = true;
            functionDeclaration.parameters.forEach(element -> element.accept(this));
            isParameter = false;

            functionDeclaration.body.forEach(element -> element.accept(this));
            globalFunctions.put(curFunc.identifier, curFunc);

            curFunc = null;
            scope = scope.getParentSymbolTable();
        }

        @Override
        public void visit(MiracleASTreeClassDeclaration classDeclaration) {
            scope = classDeclaration.getScope();
            scope = scope.getParentSymbolTable();
        }

        @Override
        public void visit(MiracleASTree astree) {
            scope = astree.getScope();
            astree.declarations.forEach(element -> element.accept(this));
        }

        @Override
        public void visit(MiracleASTreeVariableDeclaration variableDeclaration) {
            if (curFunc != null) {

            } else {
                globalVariable.put(variableDeclaration.identifier, new MiracleIRStaticVariable(
                        variableDeclaration.identifier,
                        variableDeclaration.typenode.getType().getMemorySize(),
                        variableDeclaration
                ));
            }
        }

        @Override
        public void visit(MiracleASTreeBlock block) {
            block.statements.forEach(element -> element.accept(this));
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
            MiracleASTreeDeclaration declaration = (MiracleASTreeDeclaration) scope.get(variable.identifier);
            if (declaration instanceof MiracleASTreeVariableDeclaration) {
                variable.setResultAddress(((MiracleASTreeVariableDeclaration) declaration).getAddress());
            }
        }

        @Override
        public void visit(MiracleASTreeCall call) {

        }

        @Override
        public void visit(MiracleASTreeSubscript subscript) {

        }

        private void addArithmeticBinaryOperation(MiracleASTreeBinaryExpression binaryExpression) {
            MiracleIRBinary.Types operator;
            switch (binaryExpression.operator) {
                case ADD:
                    operator = MiracleIRBinary.Types.ADD;
                    break;
                case MUL:
                    operator = MiracleIRBinary.Types.MUL;
                    break;
                default:
                    throw new RuntimeException("unsupported operator");
            }
            MiracleIRRegister resultRegister = new MiracleIRVirtualRegister(String.valueOf(registerCounter++));
            binaryExpression.setResultAddress(resultRegister);
            MiracleIRAddress leftAddress = binaryExpression.left.getResultAddress();
            MiracleIRAddress rightAddress = binaryExpression.right.getResultAddress();
            if (leftAddress instanceof MiracleIRMemory) {
                curBB.append(new MiracleIRMove(leftAddress,
                        (MiracleIRRegister) (leftAddress = new MiracleIRVirtualRegister(String.valueOf(registerCounter++))),
                        binaryExpression.left.getResultType().getRegisterSize()
                ));
            }
            curBB.append(new MiracleIRBinary(
                    (MiracleIRRegister) leftAddress,
                    operator,
                    (MiracleIRRegister) rightAddress,
                    resultRegister
            ));
        }

        private void addAssignment(MiracleASTreeBinaryExpression binaryExpression) {
            curBB.append(new MiracleIRMove(
                    binaryExpression.left.getResultMemory(),
                    binaryExpression.right.getResultRegister(),
                    8
            ));
            binaryExpression.setResultAddress(binaryExpression.left.getResultMemory());
        }

        @Override
        public void visit(MiracleASTreeBinaryExpression binaryExpression) {
            binaryExpression.left.accept(this);
            binaryExpression.right.accept(this);
            switch (binaryExpression.operator) {
                case MUL:
                case OR:
                case AND:
                case DIV:
                case SHL:
                case SHR:
                case SUB:
                case MOD:
                case ADD:
                    addArithmeticBinaryOperation(binaryExpression);
                    break;
                case ASS:
                    addAssignment(binaryExpression);
                    break;
                default:
                    throw new RuntimeException("unsupported operator");
            }
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