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
import com.miracle.exception.ExceptionContainer;
import com.miracle.intermediate.number.DirectRegister;
import com.miracle.symbol.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static com.miracle.symbol.SymbolTable.*;

public class SemanticAnalyser implements ASTreeVisitor {
    private final ExceptionContainer exceptionContainer;

    private ClassDeclaration currentClass = null;
    private FunctionDeclaration currentFunction = null;
    private Stack<Iteration> loopStack = new Stack<>();

    public SemanticAnalyser(ExceptionContainer exceptionContainer) {
        this.exceptionContainer = exceptionContainer;
    }

    @Override
    public void visit(FunctionDeclaration functionDeclaration) {
        currentFunction = functionDeclaration;
        functionDeclaration.parameters.forEach(e -> e.accept(this));
        if (functionDeclaration.body != null) {
            functionDeclaration.body.forEach(e -> e.accept(this));
        }
        if (functionDeclaration.getScope().getParentSymbolTable() == null) {
            List<DirectRegister> parameters = new LinkedList<>();
            functionDeclaration.parameters.forEach(element ->
                    parameters.add((DirectRegister) element.getAddress())
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
    public void visit(ClassDeclaration classDeclaration) {
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
    public void visit(ASTree astree) {
        astree.declarations.forEach(element -> element.accept(this));
        Symbol function = astree.getScope().resolve("main");
        if (function == null || !(function instanceof FunctionDeclaration)) {
            exceptionContainer.add("the main function is not found",
                    null);
        } else {
            SymbolVariableType type = ((FunctionDeclaration) function).returnType.getType();
            if (type == null || !type.isSameType(__builtin_int)) {
                exceptionContainer.add("the main function must return `int`",
                        ((FunctionDeclaration) function).returnType.startPosition);
            }
            if (!((FunctionDeclaration) function).parameters.isEmpty()) {
                exceptionContainer.add("the parameters of the main function must be empty",
                        ((FunctionDeclaration) function).parameters.get(0).identifierPosition);
            }
        }
    }

    @Override
    public void visit(VariableDeclaration variableDeclaration) {
        if (variableDeclaration.typenode.getType() == null) {
            variableDeclaration.typenode.accept(this);
        }
        SymbolVariableType baseType = variableDeclaration.typenode.getType();
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
            if (variableDeclaration.getMemberFrom() != null) {
                exceptionContainer.add("cannot initialize member variable here",
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
    public void visit(Block block) {
        block.statements.forEach(element -> element.accept(this));
    }

    @Override
    public void visit(Selection selection) {
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
    public void visit(Iteration iteration) {
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
    public void visit(Break breakLiteral) {
        if (loopStack.empty()) {
            exceptionContainer.add("break literal must be in iteration statment",
                    breakLiteral.position);
        } else {
            breakLiteral.setIteration(loopStack.peek());
        }
    }

    @Override
    public void visit(Continue continueLiteral) {
        if (loopStack.empty()) {
            exceptionContainer.add("break literal must be in iteration statment",
                    continueLiteral.position);
        } else {
            continueLiteral.setIteration(loopStack.peek());
        }
    }

    @Override
    public void visit(ReturnStatement returnLiteral) {
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
                    if (currentFunction.returnType == null || (returnLiteral.expression.getResultType() != null &&
                            !returnLiteral.expression.getResultType().isSameType(currentFunction.returnType.getType()))) {
                        exceptionContainer.add("the return value differs from declaration",
                                returnLiteral.expression.position);
                    } else {
                        returnLiteral.setFunction(currentFunction);
                    }
                } else {
                    if (currentFunction.returnType != null && currentFunction.returnType.getType() != null &&
                            !currentFunction.returnType.getType().isSameType(__builtin_void)) {
                        exceptionContainer.add("the return value differs from declaration",
                                returnLiteral.position);
                    } else {
                        returnLiteral.setFunction(currentFunction);
                    }
                }
            }
        }
    }

    @Override
    public void visit(Variable variable) {
        Symbol type = variable.getScope().resolve(variable.identifier);
        if (type == null) {
            exceptionContainer.add("cannot find identifier named \"" + variable.identifier + "\"",
                    variable.position);
        }
        if (type instanceof FunctionDeclaration) {
            variable.setResultType(((FunctionDeclaration) type).getSymbol());
        } else if (type instanceof VariableDeclaration) {
            variable.setMutable();
            variable.setResultType(((VariableDeclaration) type).typenode.getType());
        } else if (type instanceof SymbolFunctionType) {
            variable.setResultType((SymbolType) type);
        } else if (type instanceof SymbolPrimitiveType) {
            variable.setResultType((SymbolType) type);
            variable.setMutable();
        } else {
            exceptionContainer.add("identifier \"" + variable.identifier + "\" is not declared as a valid expression",
                    variable.position);
        }
    }

    @Override
    public void visit(CallExpression callExpression) {
        callExpression.function.accept(this);
        if (!(callExpression.function.getResultType() instanceof SymbolFunctionType)) {
            exceptionContainer.add("expression is not a function, thus not callable",
                    callExpression.function.position);
        } else {
            SymbolFunctionType type = (SymbolFunctionType) callExpression.function.getResultType();
            if (type != null) {
                List<SymbolVariableType> argType = type.getArgType();
                if (callExpression.parameters.size() != argType.size()) {
                    exceptionContainer.add("function needs " + String.valueOf(argType.size()) + " parameter(s), but found " + String.valueOf(callExpression.parameters.size()) + " parameter(s)",
                            callExpression.position
                    );
                } else {
                    for (int i = 0, size = argType.size(); i < size; i++) {
                        Expression node = callExpression.parameters.get(i);
                        node.accept(this);
                        SymbolType exprType = node.getResultType();
                        if (argType.get(i) == null) {
                            System.err.println("Fuck");
                        }
                        if (argType.get(i) != null && exprType != null && !exprType.isSameType(argType.get(i))) {
                            exceptionContainer.add("function needs parameter of type `" + argType.get(i).toPrintableString() + "`, but `" + exprType.toPrintableString() + "` was found",
                                    node.position);
                        }
                    }
                    callExpression.setResultType(type.getReturnType());
                }
            }
        }
    }

    @Override
    public void visit(Subscript subscript) {
        subscript.base.accept(this);
        Symbol type = subscript.base.getResultType();
        if (type != null) {
            if (!(type instanceof SymbolArrayType)) {
                exceptionContainer.add("subscription can only be applied to array object",
                        subscript.base.position
                );
            } else {
                subscript.setResultType(((SymbolArrayType) type).subscript());
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
    public void visit(BinaryExpression binaryExpression) {
        binaryExpression.left.accept(this);
        binaryExpression.right.accept(this);
        SymbolType lType = binaryExpression.left.getResultType();
        SymbolType rType = binaryExpression.right.getResultType();
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
    public void visit(PrefixExpression prefixExpression) {
        prefixExpression.expression.accept(this);
        SymbolType type = prefixExpression.expression.getResultType();
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
    public void visit(SuffixExpression suffixExpression) {
        suffixExpression.expression.accept(this);
        SymbolType type = suffixExpression.expression.getResultType();
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
    public void visit(New newNode) {
        newNode.variableType.accept(this);
        if (newNode.variableType.getType() == null) return;
        if (!(newNode.variableType.getType() instanceof SymbolBaseType)) {
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
                Expression expression = newNode.expressions.get(i);
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
                    SymbolType type = expression.getResultType();
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
                    newNode.setResultType(new SymbolArrayType(
                            (SymbolBaseType) newNode.variableType.getType(),
                            newNode.expressions.size()
                    ));
                }
            }
        }
    }

    @Override
    public void visit(StringConstant stringConstant) {
        stringConstant.setResultType(__builtin_string);
    }

    @Override
    public void visit(IntegerConstant integerConstant) {
        integerConstant.setResultType(__builtin_int);
    }

    @Override
    public void visit(BooleanConstant booleanConstant) {
        booleanConstant.setResultType(__builtin_bool);
    }

    @Override
    public void visit(NullConstant nullConstant) {
        nullConstant.setResultType(__builtin_null);
    }

    @Override
    public void visit(This thisNode) {
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
    public void visit(Field field) {
        field.expression.accept(this);
        SymbolType exprType = field.expression.getResultType();
        if (exprType == null) return;
        if (exprType instanceof SymbolVariableType) {
            SymbolFunctionType functionType = exprType.getMethod(field.identifier);
            if (functionType != null) {
                field.setResultType(functionType);
                return;
            }
            if (exprType instanceof SymbolClassType) {
                SymbolVariableType variableType = ((SymbolClassType) exprType).getVariable(field.identifier);
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
    public void visit(TypeNode typeNode) {
        Symbol result = typeNode.getScope().resolve(typeNode.typename);
        if (result == null) {
            exceptionContainer.add("cannot find identifier \"" + typeNode.typename + "\"",
                    typeNode.startPosition
            );
        } else if (result instanceof SymbolPrimitiveType) {
            if (typeNode.dimension == 0) {
                typeNode.setType((SymbolVariableType) result);
            } else {
                typeNode.setType(new SymbolArrayType(
                        (SymbolBaseType) result,
                        typeNode.dimension
                ));
            }
        } else if (result instanceof ClassDeclaration) {
            if (typeNode.dimension == 0) {
                typeNode.setType(((ClassDeclaration) result).getSymbol());
            } else {
                typeNode.setType(new SymbolArrayType(
                        ((ClassDeclaration) result).getSymbol(),
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
