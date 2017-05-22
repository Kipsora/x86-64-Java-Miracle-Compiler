package com.miracle.intermediate;

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
import com.miracle.intermediate.instruction.MiracleIRBinaryArithmetic;
import com.miracle.intermediate.instruction.MiracleIRCall;
import com.miracle.intermediate.instruction.MiracleIRMove;
import com.miracle.intermediate.instruction.MiracleIRPrefixArithmetic;
import com.miracle.intermediate.number.*;
import com.miracle.intermediate.structure.MiracleIRBasicBlock;
import com.miracle.intermediate.structure.MiracleIRFunction;
import com.miracle.intermediate.visitor.MiracleIRVisitor;
import com.miracle.symbol.MiracleSymbol;
import com.miracle.symbol.MiracleSymbolFunctionType;

import java.util.*;

import static com.miracle.symbol.MiracleSymbolTable.*;

public class MiracleIR extends MiracleIRNode {
    public final Map<String, MiracleIRStaticVariable> globalVariable;
    public final Map<String, MiracleIRStaticString> globalString;
    public final Map<String, MiracleIRFunction> globalFunction;

    public MiracleIR(Map<String, MiracleIRStaticVariable> globalVariable,
                     Map<String, MiracleIRStaticString> globalString,
                     Map<String, MiracleIRFunction> globalFunction) {
        this.globalVariable = globalVariable;
        this.globalString = globalString;
        this.globalFunction = globalFunction;
    }


    @Override
    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }

    public static class Builder implements MiracleASTreeVisitor {
        private final Map<String, MiracleIRStaticVariable> globalVariable = new HashMap<>();
        private final Map<String, MiracleIRStaticString> globalString = new HashMap<>();
        private final Map<String, MiracleIRFunction> globalFunction = new HashMap<>();

        private MiracleIRBasicBlock curBB;
        private MiracleIRFunction curFunc;
        private boolean isParameter;
        private int countTmpVars;
        private int countStaticString;
        private int countRetReg;

        public MiracleIR build() {
            return new MiracleIR(globalVariable, globalString, globalFunction);
        }

        @Override
        public void visit(MiracleASTreeFunctionDeclaration functionDeclaration) {
            if (functionDeclaration.getScope().getParentSymbolTable() == null) {
                List<MiracleIRVirtualRegister> parameters = new ArrayList<>();
                functionDeclaration.parameters.forEach(element ->
                        parameters.add(new MiracleIRVirtualRegister(element.identifier))
                );

                curFunc = functionDeclaration.getAddress();
                globalFunction.put(curFunc.identifier, curFunc);
                curBB = curFunc.getEntryBasicBlock();

                functionDeclaration.body.forEach(element -> element.accept(this));

                curFunc = null;

            } else {
                throw new RuntimeException("uncompleted method");
            }
        }

        @Override
        public void visit(MiracleASTreeClassDeclaration classDeclaration) {
            //throw new RuntimeException("uncompleted method");
        }

        @Override
        public void visit(MiracleASTree astree) {
            astree.declarations.forEach(element -> element.accept(this));
        }

        @Override
        public void visit(MiracleASTreeVariableDeclaration variableDeclaration) {
            if (!variableDeclaration.isMember) {
                if (variableDeclaration.getScope().getParentSymbolTable() == null) {
                    globalVariable.put(variableDeclaration.identifier,
                            (MiracleIRStaticVariable) variableDeclaration.getAddress()
                    );
                }
                if (variableDeclaration.expression != null) {
                    variableDeclaration.expression.accept(this);
                    curBB.tail.prepend(new MiracleIRMove(
                            variableDeclaration.getAddress(),
                            variableDeclaration.expression.getResultAddress()
                    ));
                }
            }
        }

        @Override
        public void visit(MiracleASTreeBlock block) {
            block.statements.forEach(element -> element.accept(this));
        }

        @Override
        public void visit(MiracleASTreeSelection selection) {
            throw new RuntimeException("uncompleted method");
        }

        @Override
        public void visit(MiracleASTreeIteration iteration) {
            throw new RuntimeException("uncompleted method");
        }

        @Override
        public void visit(MiracleASTreeBreak breakLiteral) {
            throw new RuntimeException("uncompleted method");
        }

        @Override
        public void visit(MiracleASTreeContinue continueLiteral) {
            throw new RuntimeException("uncompleted method");
        }

        @Override
        public void visit(MiracleASTreeReturn returnLiteral) {
            throw new RuntimeException("uncompleted method");
        }

        @Override
        public void visit(MiracleASTreeVariable variable) {
            MiracleSymbol symbol = variable.getScope().get(variable.identifier);
            if (symbol instanceof MiracleASTreeVariableDeclaration) {
                variable.setResultAddress(((MiracleASTreeVariableDeclaration) symbol).getAddress());
            } else if (symbol instanceof MiracleASTreeFunctionDeclaration) {
                variable.setResultAddress(null);
            } else if (symbol instanceof MiracleSymbolFunctionType) {
                variable.setResultType(null);
            } else {
                throw new RuntimeException("uncompleted method");
            }
        }

        @Override
        public void visit(MiracleASTreeCall call) {
            call.function.accept(this);
            call.parameters.forEach(element -> element.accept(this));
            List<MiracleIRRegister> parameters = new LinkedList<>();
            call.parameters.forEach(element ->
                    parameters.add((MiracleIRRegister) element.getResultAddress())
            );
            if (call.function instanceof MiracleASTreeVariable) {
                MiracleSymbol symbol = (call.getScope()).get(((MiracleASTreeVariable) call.function).identifier);
                if (symbol instanceof MiracleASTreeFunctionDeclaration) {
                    MiracleIRVirtualRegister register = null;
                    if (!((MiracleASTreeFunctionDeclaration) symbol).returnType.getType().isSameType(__builtin_void)) {
                        register = new MiracleIRVirtualRegister(".ret" + String.valueOf(countRetReg++));
                    }
                    curBB.tail.prepend(new MiracleIRCall(
                            ((MiracleASTreeFunctionDeclaration) symbol).getAddress(),
                            parameters,
                            register
                    ));
                    call.setResultAddress(register);
                } else if (symbol instanceof MiracleSymbolFunctionType) {
                    MiracleIRVirtualRegister register = null;
                    if (!((MiracleSymbolFunctionType) symbol).getReturnType().isSameType(__builtin_void)) {
                        register = new MiracleIRVirtualRegister(".ret" + String.valueOf(countRetReg++));
                    }
                    curBB.tail.prepend(new MiracleIRCall(
                            ((MiracleSymbolFunctionType) symbol).getAddress(),
                            parameters,
                            register
                    ));
                    call.setResultAddress(register);
                }
            } else {
                throw new RuntimeException("uncompleted method");
            }
        }

        @Override
        public void visit(MiracleASTreeSubscript subscript) {
            throw new RuntimeException("uncompleted method");
        }

        void addAssignment(MiracleASTreeBinaryExpression expression) {
            curBB.tail.prepend(new MiracleIRMove(
                    (MiracleIRRegister) expression.left.getResultAddress(),
                    expression.right.getResultAddress()
            ));
        }

        void addBinaryArithmetic(MiracleASTreeBinaryExpression expression) {
            MiracleIRBinaryArithmetic.Types operator;
            switch (expression.operator) {
                case ADD:
                    operator = MiracleIRBinaryArithmetic.Types.ADD;
                    break;
                case XOR:
                    operator = MiracleIRBinaryArithmetic.Types.XOR;
                    break;
                case MUL:
                    operator = MiracleIRBinaryArithmetic.Types.MUL;
                    break;
                case DIV:
                    operator = MiracleIRBinaryArithmetic.Types.DIV;
                    break;
                case SHL:
                    operator = MiracleIRBinaryArithmetic.Types.SHL;
                    break;
                case SHR:
                    operator = MiracleIRBinaryArithmetic.Types.SHR;
                    break;
                case SUB:
                    operator = MiracleIRBinaryArithmetic.Types.SUB;
                    break;
                case MOD:
                    operator = MiracleIRBinaryArithmetic.Types.MOD;
                    break;
                case AND:
                    operator = MiracleIRBinaryArithmetic.Types.AND;
                    break;
                case CONJ:
                    operator = MiracleIRBinaryArithmetic.Types.AND;
                    break;
                case OR:
                    operator = MiracleIRBinaryArithmetic.Types.OR;
                    break;
                case DISJ:
                    operator = MiracleIRBinaryArithmetic.Types.OR;
                    break;
                default:
                    throw new RuntimeException("unsupported operator");
            }
            MiracleIRVirtualRegister tmpVar = new MiracleIRVirtualRegister(".t" + String.valueOf(countTmpVars++));
            curBB.tail.prepend(new MiracleIRMove(
                    tmpVar,
                    expression.left.getResultAddress()
            ));
            curBB.tail.prepend(new MiracleIRBinaryArithmetic(
                    operator,
                    tmpVar,
                    expression.right.getResultAddress()
            ));
            expression.setResultAddress(tmpVar);
        }

        @Override
        public void visit(MiracleASTreeBinaryExpression binaryExpression) {
            binaryExpression.left.accept(this);
            binaryExpression.right.accept(this);
            switch (binaryExpression.operator) {
                case AND:
                case MOD:
                case SUB:
                case SHR:
                case SHL:
                case DIV:
                case OR:
                case MUL:
                case XOR:
                case CONJ:
                case DISJ:
                    addBinaryArithmetic(binaryExpression);
                    break;
                case ADD:
                    if (binaryExpression.left.getResultType().isSameType(__builtin_string)) {
                        MiracleIRVirtualRegister register = new MiracleIRVirtualRegister(".ret" + String.valueOf(countRetReg++));
                        curBB.tail.prepend(new MiracleIRCall(
                                __builtin_strcat.getAddress(),
                                new LinkedList<MiracleIRRegister>() {{
                                    add((MiracleIRVirtualRegister) binaryExpression.left.getResultAddress());
                                    add((MiracleIRVirtualRegister) binaryExpression.right.getResultAddress());
                                }},
                                register
                        ));
                        binaryExpression.setResultAddress(register);
                    } else {
                        addBinaryArithmetic(binaryExpression);
                    }
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
            prefixExpression.expression.accept(this);
            MiracleIRPrefixArithmetic.Types operator;
            switch (prefixExpression.operator) {
                case POSITIVE:
                    return;
                case NEGATE:
                    operator = MiracleIRPrefixArithmetic.Types.NEG;
                    break;
                case INC:
                    operator = MiracleIRPrefixArithmetic.Types.ADD;
                    break;
                case DEC:
                    operator = MiracleIRPrefixArithmetic.Types.SUB;
                    break;
                default:
                    throw new RuntimeException("unsupported operator");
            }
            curBB.tail.prepend(new MiracleIRPrefixArithmetic(
                    (MiracleIRVirtualRegister) prefixExpression.expression.getResultAddress(),
                    operator
            ));
        }

        @Override
        public void visit(MiracleASTreeSuffixExpression suffixExpression) {
            throw new RuntimeException("uncompleted method");
        }

        @Override
        public void visit(MiracleASTreeNew newNode) {
            throw new RuntimeException("uncompleted method");
        }

        @Override
        public void visit(MiracleASTreeStringConstant stringConstant) {
            MiracleIRStaticString address = new MiracleIRStaticString(
                    "str" + String.valueOf(countStaticString++),
                    stringConstant.value
            );
            globalString.put(stringConstant.value, address);
            stringConstant.setResultAddress(address);
        }

        @Override
        public void visit(MiracleASTreeIntegerConstant integerConstant) {
            integerConstant.setResultAddress(new MiracleIRImmediate(
                    Integer.parseInt(integerConstant.value)
            ));
        }

        @Override
        public void visit(MiracleASTreeBooleanConstant booleanConstant) {
            if (booleanConstant.value.equals("true")) {
                booleanConstant.setResultAddress(new MiracleIRImmediate(1));
            } else {
                booleanConstant.setResultAddress(new MiracleIRImmediate(0));
            }
        }

        @Override
        public void visit(MiracleASTreeNullConstant nullConstant) {
            nullConstant.setResultAddress(new MiracleIRImmediate(0));
        }

        @Override
        public void visit(MiracleASTreeThis thisNode) {
            throw new RuntimeException("unexpected statment in ir");
        }

        @Override
        public void visit(MiracleASTreeField field) {
            throw new RuntimeException("uncompleted method");
        }

        @Override
        public void visit(MiracleASTreeTypeNode typeNode) {
        }
    }
}
