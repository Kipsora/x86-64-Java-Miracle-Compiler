package com.miracle.astree.visitor;

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
import com.miracle.exception.MiracleExceptionContainer;
import com.miracle.intermediate.number.MiracleIRDirectRegister;
import com.miracle.symbol.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static com.miracle.symbol.MiracleSymbolTable.*;

public class MiracleASTreeSemanticAnalyser implements MiracleASTreeVisitor {
    private final MiracleExceptionContainer exceptionContainer;

    private MiracleASTreeClassDeclaration currentClass = null;
    private MiracleASTreeFunctionDeclaration currentFunction = null;
    private Stack<MiracleASTreeIteration> loopStack = new Stack<>();

    public MiracleASTreeSemanticAnalyser(MiracleExceptionContainer exceptionContainer) {
        this.exceptionContainer = exceptionContainer;
    }

    @Override
    public void visit(MiracleASTreeFunctionDeclaration functionDeclaration) {
        currentFunction = functionDeclaration;
        functionDeclaration.parameters.forEach(e -> e.accept(this));
        if (functionDeclaration.body != null) {
            functionDeclaration.body.forEach(e -> e.accept(this));
        }
        if (functionDeclaration.getScope().getParentSymbolTable() == null) {
            List<MiracleIRDirectRegister> parameters = new LinkedList<>();
            functionDeclaration.parameters.forEach(element ->
                    parameters.add((MiracleIRDirectRegister) element.getAddress())
            );
            functionDeclaration.getSymbol().setAddress(functionDeclaration.identifier);
        } else if (functionDeclaration.getMemberFrom() != null) {
            functionDeclaration.getSymbol().setAddress(
                    '@' + functionDeclaration.getMemberFrom().identifier
                            + '.' + functionDeclaration.identifier
            );
        } else {
            throw new RuntimeException("unexpected case");
        }
        currentFunction = null;
    }

    @Override
    public void visit(MiracleASTreeClassDeclaration classDeclaration) {
        currentClass = classDeclaration;
        if (classDeclaration.constructorDeclaration != null) {
            classDeclaration.constructorDeclaration.accept(this);
        }
        if (classDeclaration.functionDeclarations != null) {
            classDeclaration.functionDeclarations.forEach(element -> element.accept(this));
        }
        if (classDeclaration.variableDeclarations != null) {
            classDeclaration.variableDeclarations.forEach(element -> element.accept(this));
        }
        currentClass = null;
    }

    @Override
    public void visit(MiracleASTree astree) {
        astree.declarations.forEach(element -> element.accept(this));
        MiracleSymbol function = astree.getScope().get("main");
        if (function == null || !(function instanceof MiracleASTreeFunctionDeclaration)) {
            exceptionContainer.add("the main function is not found",
                    null);
        } else {
            MiracleSymbolVariableType type = ((MiracleASTreeFunctionDeclaration) function).returnType.getType();
            if (type == null || !type.isSameType(__builtin_int)) {
                exceptionContainer.add("the main function must return `int`",
                        ((MiracleASTreeFunctionDeclaration) function).returnType.startPosition);
            }
            if (!((MiracleASTreeFunctionDeclaration) function).parameters.isEmpty()) {
                exceptionContainer.add("the parameters of the main function must be empty",
                        ((MiracleASTreeFunctionDeclaration) function).parameters.get(0).identifierPosition);
            }
        }
    }

    @Override
    public void visit(MiracleASTreeVariableDeclaration variableDeclaration) {
        variableDeclaration.typenode.accept(this);
        MiracleSymbolVariableType baseType = variableDeclaration.typenode.getType();
        boolean flag = true;
        if (baseType == null) return;
        if (baseType.isSameType(__builtin_void)) {
            exceptionContainer.add("cannot declare a variable of type `void`",
                    variableDeclaration.typenode.startPosition);
            flag = false;
        }
        if (variableDeclaration.expression != null) {
            variableDeclaration.expression.accept(this);
            if (variableDeclaration.expression.getResultType() != null &&
                    !variableDeclaration.typenode.getType().isSameType(variableDeclaration.expression.getResultType())) {
                exceptionContainer.add("the type of initialization expression differs from declaration",
                        variableDeclaration.expression.position);
                flag = false;
            }
        }
        if (flag && variableDeclaration.getMemberFrom() == null) {
            if (!variableDeclaration.getScope().put(variableDeclaration)) {
                this.exceptionContainer.add("duplicate declarations of identifier \""
                                + variableDeclaration.identifier + "\"",
                        variableDeclaration.identifierPosition
                );
            }
        }
    }

    @Override
    public void visit(MiracleASTreeBlock block) {
        block.statements.forEach(element -> element.accept(this));
    }

    @Override
    public void visit(MiracleASTreeSelection selection) {
        selection.expression.accept(this);
        if (selection.expression.getResultType() != null &&
                !selection.expression.getResultType().isSameType(__builtin_bool)) {
            exceptionContainer.add("conditional expresson must be `bool`",
                    selection.expression.position);
        }
        if (selection.branchTrue != null) {
            selection.branchTrue.accept(this);
        }
        if (selection.branchFalse != null) {
            selection.branchFalse.accept(this);
        }
    }

    @Override
    public void visit(MiracleASTreeIteration iteration) {
        loopStack.push(iteration);
        if (iteration.initializeExpression != null) {
            iteration.initializeExpression.accept(this);
        }
        if (iteration.conditionExpression != null) {
            iteration.conditionExpression.accept(this);
            if (iteration.conditionExpression.getResultType() != null &&
                    !iteration.conditionExpression.getResultType().isSameType(__builtin_bool)) {
                exceptionContainer.add("conditional expression must be `bool`",
                        iteration.conditionExpression.position);
            }
        }
        if (iteration.incrementExpression != null) {
            iteration.incrementExpression.accept(this);
        }
        if (iteration.body != null) {
            iteration.body.accept(this);
        }
        loopStack.pop();
    }

    @Override
    public void visit(MiracleASTreeBreak breakLiteral) {
        if (loopStack.empty()) {
            exceptionContainer.add("break literal must be in iteration statment",
                    breakLiteral.position);
        } else {
            breakLiteral.setIteration(loopStack.peek());
        }
    }

    @Override
    public void visit(MiracleASTreeContinue continueLiteral) {
        if (loopStack.empty()) {
            exceptionContainer.add("break literal must be in iteration statment",
                    continueLiteral.position);
        } else {
            continueLiteral.setIteration(loopStack.peek());
        }
    }

    @Override
    public void visit(MiracleASTreeReturn returnLiteral) {
        if (currentFunction == null) {
            exceptionContainer.add("return literal must be in function statment",
                    returnLiteral.position);
        } else {
            if (currentFunction.identifier == null) {
                if (returnLiteral.expression != null) {
                    returnLiteral.expression.accept(this);
                    if (returnLiteral.expression.getResultType() != null &&
                            !returnLiteral.expression.getResultType().isSameType(__builtin_void)) {
                        exceptionContainer.add("cannot return any number in constructor declaration",
                                returnLiteral.expression.position);
                    } else {
                        returnLiteral.setFunction(currentFunction);
                    }
                } else {
                    returnLiteral.setFunction(currentFunction);
                }

            } else {
                if (returnLiteral.expression != null) {
                    returnLiteral.expression.accept(this);
                    if (returnLiteral.expression.getResultType() != null &&
                            !returnLiteral.expression.getResultType().isSameType(currentFunction.returnType.getType())) {
                        exceptionContainer.add("the return number differs from declaration",
                                returnLiteral.expression.position);
                    } else {
                        returnLiteral.setFunction(currentFunction);
                    }
                } else {
                    if (currentFunction.returnType.getType() == null ||
                            !currentFunction.returnType.getType().isSameType(__builtin_void)) {
                        exceptionContainer.add("the return number differs from declaration",
                                returnLiteral.position);
                    } else {
                        returnLiteral.setFunction(currentFunction);
                    }
                }
            }
        }
    }

    @Override
    public void visit(MiracleASTreeVariable variable) {
        MiracleSymbol type = variable.getScope().get(variable.identifier);
        if (type == null) {
            exceptionContainer.add("cannot find identifier named \"" + variable.identifier + "\"",
                    variable.position);
        }
        if (type instanceof MiracleASTreeFunctionDeclaration) {
            variable.setResultType(((MiracleASTreeFunctionDeclaration) type).getSymbol());
        } else if (type instanceof MiracleASTreeVariableDeclaration) {
            variable.setMutable();
            variable.setResultType(((MiracleASTreeVariableDeclaration) type).typenode.getType());
        } else if (type instanceof MiracleSymbolFunctionType) {
            variable.setResultType((MiracleSymbolType) type);
        } else if (type instanceof MiracleSymbolPrimitiveType) {
            variable.setResultType((MiracleSymbolType) type);
            variable.setMutable();
        } else {
            exceptionContainer.add("identifier \"" + variable.identifier + "\" is not declared as a valid expression",
                    variable.position);
        }
    }

    @Override
    public void visit(MiracleASTreeCall call) {
        call.function.accept(this);
        if (!(call.function.getResultType() instanceof MiracleSymbolFunctionType)) {
            exceptionContainer.add("expression is not a function, thus not callable",
                    call.function.position);
        } else {
            MiracleSymbolFunctionType type = (MiracleSymbolFunctionType) call.function.getResultType();
            if (type != null) {
                List<MiracleSymbolVariableType> argType = type.getArgType();
                if (call.parameters.size() != argType.size()) {
                    exceptionContainer.add("function needs " + String.valueOf(argType.size()) + " parameter(s), but found " + String.valueOf(call.parameters.size()) + " parameter(s)",
                            call.position
                    );
                } else {
                    for (int i = 0, size = argType.size(); i < size; i++) {
                        MiracleASTreeExpression node = call.parameters.get(i);
                        node.accept(this);
                        MiracleSymbolType exprType = node.getResultType();
                        if (exprType != null && !exprType.isSameType(argType.get(i))) {
                            exceptionContainer.add("function needs parameter of type `" + argType.get(i).toPrintableString() + "`, but `" + exprType.toPrintableString() + "` was found",
                                    node.position);
                        }
                    }
                    call.setResultType(type.getReturnType());
                }
            }
        }
    }

    @Override
    public void visit(MiracleASTreeSubscript subscript) {
        subscript.base.accept(this);
        MiracleSymbol type = subscript.base.getResultType();
        if (type != null) {
            if (!(type instanceof MiracleSymbolArrayType)) {
                exceptionContainer.add("subscription can only be applied to array object",
                        subscript.base.position
                );
            } else {
                subscript.setResultType(((MiracleSymbolArrayType) type).subscript());
                if (subscript.base.isMutable()) {
                    subscript.setMutable();
                }
            }
        }
        subscript.coordinate.accept(this);
        if (subscript.coordinate.getResultType() != null &&
                !subscript.coordinate.getResultType().isSameType(__builtin_int)) {
            exceptionContainer.add("expected `int`, but `" + subscript.coordinate.getResultType().toPrintableString() + "\" was found",
                    subscript.coordinate.position
            );
        }
    }

    @Override
    public void visit(MiracleASTreeBinaryExpression binaryExpression) {
        binaryExpression.left.accept(this);
        binaryExpression.right.accept(this);
        MiracleSymbolType lType = binaryExpression.left.getResultType();
        MiracleSymbolType rType = binaryExpression.right.getResultType();
        if (lType == null || rType == null) return;
        if (!lType.isSameType(rType)) {
            exceptionContainer.add("no match for operator `" + binaryExpression.operator + "` (operands are `" + lType.toPrintableString() + "` and `" + rType.toPrintableString() + "`)",
                    binaryExpression.operatorPosition
            );
            return;
        }
        boolean flag = true;
        switch (binaryExpression.operator) {
            case DIV:
            case AND:
            case MUL:
            case MOD:
            case SHL:
            case SHR:
            case SUB:
            case OR:
                if (!lType.isSameType(__builtin_int)) {
                    exceptionContainer.add("no match for operator `" + binaryExpression.operator + "` (operands are `" + lType.toPrintableString() + "` and `" + rType.toPrintableString() + "`)",
                            binaryExpression.left.position
                    );
                    flag = false;
                }
                if (!rType.isSameType(__builtin_int)) {
                    exceptionContainer.add("no match for operator `" + binaryExpression.operator + "` (operands are `" + lType.toPrintableString() + "` and `" + rType.toPrintableString() + "`)",
                            binaryExpression.right.position
                    );
                    flag = false;
                }
                if (flag) binaryExpression.setResultType(lType);
                break;
            case ADD:
                if (!lType.isSameType(__builtin_int) && !lType.isSameType(__builtin_string)) {
                    exceptionContainer.add("no match for operator `" + binaryExpression.operator + "` (operands are `" + lType.toPrintableString() + "` and `" + rType.toPrintableString() + "`)",
                            binaryExpression.left.position
                    );
                    flag = false;
                }
                if (!rType.isSameType(__builtin_int) && !rType.isSameType(__builtin_string)) {
                    exceptionContainer.add("no match for operator `" + binaryExpression.operator + "` (operands are `" + lType.toPrintableString() + "` and `" + rType.toPrintableString() + "`)",
                            binaryExpression.right.position
                    );
                    flag = false;
                }
                if (flag) binaryExpression.setResultType(lType);
                break;
            case LT:
            case RT:
            case EQL:
            case LEQ:
            case NEQ:
            case REQ:
                binaryExpression.setResultType(__builtin_bool);
                break;
            case XOR:
                if (!lType.isSameType(__builtin_int) && !lType.isSameType(__builtin_bool)) {
                    exceptionContainer.add("no match for operator `" + binaryExpression.operator + "` (operands are `" + lType.toPrintableString() + "` and `" + rType.toPrintableString() + "`)",
                            binaryExpression.left.position
                    );
                    flag = false;
                }
                if (!rType.isSameType(__builtin_int) && !rType.isSameType(__builtin_bool)) {
                    exceptionContainer.add("no match for operator `" + binaryExpression.operator + "` (operands are `" + lType.toPrintableString() + "` and `" + rType.toPrintableString() + "`)",
                            binaryExpression.right.position
                    );
                    flag = false;
                }
                if (flag) binaryExpression.setResultType(lType);
                break;
            case CONJ:
            case DISJ:
                if (!lType.isSameType(__builtin_bool)) {
                    exceptionContainer.add("no match for operator `" + binaryExpression.operator + "` (operands are `" + lType.toPrintableString() + "` and `" + rType.toPrintableString() + "`)",
                            binaryExpression.left.position
                    );
                    flag = false;
                }
                if (!rType.isSameType(__builtin_bool)) {
                    exceptionContainer.add("no match for operator `" + binaryExpression.operator + "` (operands are `" + lType.toPrintableString() + "` and `" + rType.toPrintableString() + "`)",
                            binaryExpression.right.position
                    );
                    flag = false;
                }
                if (flag) binaryExpression.setResultType(lType);
                break;
            case ASS:
                if (!binaryExpression.left.isMutable()) {
                    exceptionContainer.add("the rawval of left operand is not mutable",
                            binaryExpression.left.position
                    );
                    flag = false;
                }
                if (flag) {
                    binaryExpression.setResultType(rType);

                    binaryExpression.setMutable();
                }
                break;
            default:
                throw new RuntimeException("unsupported operator");
        }
    }

    @Override
    public void visit(MiracleASTreePrefixExpression prefixExpression) {
        prefixExpression.expression.accept(this);
        MiracleSymbolType type = prefixExpression.expression.getResultType();
        if (type == null) return;
        boolean flag = true;
        switch (prefixExpression.operator) {
            case INC:
            case DEC:
                if (!type.isSameType(__builtin_int)) {
                    exceptionContainer.add("no match for operator `" + prefixExpression.operator + "` (operand is `" + type.toPrintableString() + "`)",
                            prefixExpression.expression.position
                    );
                    flag = false;
                }
                if (!prefixExpression.expression.isMutable()) {
                    exceptionContainer.add("the rawval of operand is not mutable",
                            prefixExpression.expression.position
                    );
                    flag = false;
                }
                if (flag) prefixExpression.setResultType(type);
                break;
            case NEGATE:
                if (!type.isSameType(__builtin_bool)) {
                    exceptionContainer.add("no match for operator `" + prefixExpression.operator + "` (operand is `" + type.toPrintableString() + "`)",
                            prefixExpression.expression.position
                    );
                    flag = false;
                }
                if (flag) prefixExpression.setResultType(type);
                break;
            case NEGATIVE:
            case POSITIVE:
            case REV:
                if (!type.isSameType(__builtin_int)) {
                    exceptionContainer.add("no match for operator `" + prefixExpression.operator + "` (operand is `" + type.toPrintableString() + "`)",
                            prefixExpression.expression.position
                    );
                    flag = false;
                }
                if (flag) prefixExpression.setResultType(type);
                break;
            default:
                throw new RuntimeException("unsupported operator");
        }
    }

    @Override
    public void visit(MiracleASTreeSuffixExpression suffixExpression) {
        suffixExpression.expression.accept(this);
        MiracleSymbolType type = suffixExpression.expression.getResultType();
        if (type == null) return;
        boolean flag = true;
        if (!type.isSameType(__builtin_int)) {
            exceptionContainer.add("no match for operator `" + suffixExpression.operator + "` (operand is `" + type.toPrintableString() + "`)",
                    suffixExpression.expression.position
            );
            flag = false;
        }
        if (!suffixExpression.expression.isMutable()) {
            exceptionContainer.add("the rawval of operand is not mutable",
                    suffixExpression.expression.position
            );
            flag = false;
        }
        if (flag) suffixExpression.setResultType(type);
    }

    @Override
    public void visit(MiracleASTreeNew newNode) {
        newNode.variableType.accept(this);
        if (newNode.variableType.getType() == null) return;
        if (!(newNode.variableType.getType() instanceof MiracleSymbolBaseType)) {
            exceptionContainer.add("base type is required here",
                    newNode.variableType.startPosition
            );
        } else if (newNode.variableType.getType().isSameType(__builtin_void)) {
            exceptionContainer.add("cannot declare a variable of type `void`",
                    newNode.variableType.startPosition
            );
        } else {
            boolean flag = true;
            for (int i = 0, empty = 0; i < newNode.expressions.size(); i++) {
                MiracleASTreeExpression expression = newNode.expressions.get(i);
                if (expression == null) {
                    empty = 1;
                } else {
                    if (empty == 1) {
                        exceptionContainer.add("invalid declaration of interleaved array",
                                expression.position
                        );
                        flag = false;
                    }
                    expression.accept(this);
                    MiracleSymbolType type = expression.getResultType();
                    if (type != null && !type.isSameType(__builtin_int)) {
                        exceptionContainer.add("expected `int`, but `" + type.toPrintableString() + "` was found",
                                expression.position
                        );
                        flag = false;
                    }
                }
            }
            if (flag) {
                newNode.setMutable();
                if (newNode.expressions.isEmpty()) {
                    newNode.setResultType(newNode.variableType.getType());
                } else {
                    newNode.setResultType(new MiracleSymbolArrayType(
                            (MiracleSymbolBaseType) newNode.variableType.getType(),
                            newNode.expressions.size()
                    ));
                }
            }
        }
    }

    @Override
    public void visit(MiracleASTreeStringConstant stringConstant) {
        stringConstant.setResultType(__builtin_string);
    }

    @Override
    public void visit(MiracleASTreeIntegerConstant integerConstant) {
        integerConstant.setResultType(__builtin_int);
    }

    @Override
    public void visit(MiracleASTreeBooleanConstant booleanConstant) {
        booleanConstant.setResultType(__builtin_bool);
    }

    @Override
    public void visit(MiracleASTreeNullConstant nullConstant) {
        nullConstant.setResultType(__builtin_null);
    }

    @Override
    public void visit(MiracleASTreeThis thisNode) {
        if (currentClass == null) {
            exceptionContainer.add("invalid use of this literal",
                    thisNode.position
            );
        } else {
            thisNode.setMutable();
            thisNode.setResultType(currentClass.getSymbol());
        }
    }

    @Override
    public void visit(MiracleASTreeField field) {
        field.expression.accept(this);
        MiracleSymbolType exprType = field.expression.getResultType();
        if (exprType == null) return;
        if (exprType instanceof MiracleSymbolVariableType) {
            MiracleSymbolFunctionType functionType = exprType.getMethod(field.identifier);
            if (functionType != null) {
                field.setResultType(functionType);
                return;
            }
            if (exprType instanceof MiracleSymbolClassType) {
                MiracleSymbolVariableType variableType = ((MiracleSymbolClassType) exprType).getVariable(field.identifier);
                if (variableType != null) {
                    field.setResultType(variableType);
                    field.setMutable();
                    return;
                }
                exceptionContainer.add("class `" + exprType.toPrintableString() + "` has no member of \"" + field.identifier + "\"",
                        field.identifierPosition
                );
            } else {
                exceptionContainer.add("type `" + exprType.toPrintableString() + "` has no member of \"" + field.identifier + "\"",
                        field.identifierPosition
                );
            }
        } else {
            exceptionContainer.add("not match for operator `.` (operands are `" + exprType.toPrintableString() + "` and \"" + field.identifier + "\")",
                    field.operatorPosition
            );
        }
    }

    @Override
    public void visit(MiracleASTreeTypeNode typeNode) {
        MiracleSymbol result = typeNode.getScope().get(typeNode.typename);
        if (result == null) {
            exceptionContainer.add("cannot find identifier \"" + typeNode.typename + "\"",
                    typeNode.startPosition
            );
        } else if (result instanceof MiracleSymbolPrimitiveType) {
            if (typeNode.dimension == 0) {
                typeNode.setType((MiracleSymbolVariableType) result);
            } else {
                typeNode.setType(new MiracleSymbolArrayType(
                        (MiracleSymbolBaseType) result,
                        typeNode.dimension
                ));
            }
        } else if (result instanceof MiracleASTreeClassDeclaration) {
            if (typeNode.dimension == 0) {
                typeNode.setType(((MiracleASTreeClassDeclaration) result).getSymbol());
            } else {
                typeNode.setType(new MiracleSymbolArrayType(
                        ((MiracleASTreeClassDeclaration) result).getSymbol(),
                        typeNode.dimension
                ));
            }
        } else {
            exceptionContainer.add("cannot find class `" + typeNode.typename + "`",
                    typeNode.startPosition
            );
        }
    }
}
