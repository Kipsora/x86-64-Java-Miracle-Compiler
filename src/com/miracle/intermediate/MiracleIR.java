package com.miracle.intermediate;

import com.miracle.MiracleOption;
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
import com.miracle.intermediate.instruction.MiracleIRCall;
import com.miracle.intermediate.instruction.MiracleIRCompare;
import com.miracle.intermediate.instruction.MiracleIRHeapAllocate;
import com.miracle.intermediate.instruction.MiracleIRMove;
import com.miracle.intermediate.instruction.arithmetic.MiracleIRBinaryArithmetic;
import com.miracle.intermediate.instruction.arithmetic.MiracleIRPrefixArithmetic;
import com.miracle.intermediate.instruction.fork.MiracleIRBranch;
import com.miracle.intermediate.instruction.fork.MiracleIRJump;
import com.miracle.intermediate.instruction.fork.MiracleIRReturn;
import com.miracle.intermediate.number.*;
import com.miracle.intermediate.structure.MiracleIRBasicBlock;
import com.miracle.intermediate.structure.MiracleIRFunction;
import com.miracle.intermediate.visitor.MiracleIRVisitor;
import com.miracle.symbol.*;
import org.apache.commons.lang3.tuple.ImmutablePair;

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
        private Map<String, MiracleIRStaticVariable> globalVariable;
        private Map<String, MiracleIRStaticString> globalString;
        private Map<String, MiracleIRFunction> globalFunction;

        private MiracleIRBasicBlock curBasicBlock;
        private MiracleIRBasicBlock loopCondBlock;
        private MiracleIRBasicBlock loopExitBlock;
        private MiracleIRFunction curFunction;

        private MiracleSymbolClassType curClass;

        private int countTmpRegister;
        private int countBlock;

        @Override
        public void visit(MiracleASTreeFunctionDeclaration functionDeclaration) {
            curFunction = functionDeclaration.getSymbol().getAddress();
            globalFunction.put(functionDeclaration.identifier, curFunction);
            curBasicBlock = curFunction.getEntryBasicBlock();
            functionDeclaration.parameters.forEach(element -> element.accept(this));
            functionDeclaration.body.forEach(element -> element.accept(this));
            if (!curBasicBlock.isForked()) {
                curBasicBlock.addSuccBasicBlock(curFunction.getExitBasicBlock());
                curBasicBlock.setFork(new MiracleIRJump(curFunction.getExitBasicBlock()));
            }
            curFunction.getExitBasicBlock().setFork(new MiracleIRReturn());
            curFunction = null;
        }

        @Override
        public void visit(MiracleASTreeClassDeclaration classDeclaration) {
            curClass = classDeclaration.getSymbol();
            if (classDeclaration.constructorDeclaration != null) {
                classDeclaration.constructorDeclaration.accept(this);
            }
            classDeclaration.functionDeclarations.forEach(element -> element.accept(this));
            curClass = null;
        }

        @Override
        public void visit(MiracleASTree astree) {
            globalString = new HashMap<>();
            globalVariable = new HashMap<>();
            globalFunction = new HashMap<>();
            MiracleIRFunction miracleEntryFunction = new MiracleIRFunction(
                    ".__start",
                    new MiracleSymbolFunctionType(__builtin_void, null)
            );
            globalFunction.put(".__start", miracleEntryFunction);
            curFunction = miracleEntryFunction;
            curBasicBlock = miracleEntryFunction.getEntryBasicBlock();
            astree.declarations.forEach(element -> {
                if (element instanceof MiracleASTreeVariableDeclaration) {
                    element.accept(this);
                }
            });
            MiracleIRVirtualRegister register = miracleEntryFunction.buffer.require(".t" + String.valueOf(countTmpRegister++), MiracleOption.INT_SIZE);
            curBasicBlock = null;
            curFunction = null;

            astree.declarations.forEach(element -> {
                if (!(element instanceof MiracleASTreeVariableDeclaration)) {
                    element.accept(this);
                }
            });

            miracleEntryFunction.getEntryBasicBlock().tail.prepend(new MiracleIRCall(
                    ((MiracleASTreeFunctionDeclaration) astree.getScope().resolve("main")).getSymbol().getAddress(),
                    new LinkedList<>(), register, null
            ));
        }

        @Override
        public void visit(MiracleASTreeVariableDeclaration variableDeclaration) {
            if (variableDeclaration.getMemberFrom() == null) {
                if (variableDeclaration.getScope().getParentSymbolTable() == null) {
                    MiracleIRStaticVariable register = new MiracleIRStaticVariable(variableDeclaration);
                    globalVariable.put(register.name, register);
                    variableDeclaration.setAddress(register);
                } else if (curFunction != null) {
                    variableDeclaration.setAddress(curFunction.buffer.require(variableDeclaration));
                } else {
                    throw new RuntimeException("unexpected case");
                }
                if (variableDeclaration.expression != null) {
                    variableDeclaration.expression.accept(this);
                    curBasicBlock.tail.prepend(new MiracleIRMove(
                            variableDeclaration.getAddress(),
                            variableDeclaration.expression.getResultNumber()
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
            selection.expression.accept(this);
            MiracleIRBasicBlock passBlock = new MiracleIRBasicBlock("if_pass_" + String.valueOf(countBlock++), curFunction, false, false);
            MiracleIRBasicBlock failBlock = new MiracleIRBasicBlock("if_fail_" + String.valueOf(countBlock++), curFunction, false, false);
            MiracleIRBasicBlock bothBlock = new MiracleIRBasicBlock("if_both_" + String.valueOf(countBlock++), curFunction, false, false);
            MiracleIRNumber number = selection.expression.getResultNumber();
            if (number instanceof MiracleIRImmediate) {
                MiracleIRDirectRegister tmp = curFunction.buffer.require(
                        ".t" + String.valueOf(countTmpRegister++),
                        number.getNumberSize()
                );
                curBasicBlock.tail.prepend(new MiracleIRMove(tmp, number));
                number = tmp;
            }
            curBasicBlock.addSuccBasicBlock(passBlock);
            curBasicBlock.addSuccBasicBlock(failBlock);
            curBasicBlock.setFork(new MiracleIRBranch(
                    (MiracleIRRegister) number,
                    passBlock, failBlock
            ));
            curBasicBlock = passBlock;
            if (selection.branchTrue != null) {
                selection.branchTrue.accept(this);
            }
            if (!curBasicBlock.isForked()) {
                curBasicBlock.addSuccBasicBlock(bothBlock);
                curBasicBlock.setFork(new MiracleIRJump(bothBlock));
            }

            curBasicBlock = failBlock;
            if (selection.branchFalse != null) {
                selection.branchFalse.accept(this);
            }
            if (!curBasicBlock.isForked()) {
                curBasicBlock.addSuccBasicBlock(bothBlock);
                curBasicBlock.setFork(new MiracleIRJump(bothBlock));
            }
            curBasicBlock = bothBlock;
        }

        @Override
        public void visit(MiracleASTreeIteration iteration) {
            if (iteration.initializeExpression != null) {
                iteration.initializeExpression.accept(this);
            }
            MiracleIRBasicBlock condBlock = new MiracleIRBasicBlock("iter_cond_" + String.valueOf(countBlock++), curFunction, false, false);
            curBasicBlock.addSuccBasicBlock(condBlock);
            curBasicBlock.setFork(new MiracleIRJump(condBlock));

            curBasicBlock = condBlock;
            MiracleIRBasicBlock bodyBlock = new MiracleIRBasicBlock("iter_body_" + String.valueOf(countBlock++), curFunction, false, false);
            MiracleIRBasicBlock exitBlock = new MiracleIRBasicBlock("iter_exit_" + String.valueOf(countBlock++), curFunction, false, false);
            if (iteration.conditionExpression != null) {
                iteration.conditionExpression.accept(this);

                MiracleIRNumber number = iteration.conditionExpression.getResultNumber();
                if (number instanceof MiracleIRImmediate) {
                    MiracleIRDirectRegister tmp = curFunction.buffer.require(
                            ".t" + String.valueOf(countTmpRegister++),
                            number.getNumberSize()
                    );
                    curBasicBlock.tail.prepend(new MiracleIRMove(tmp, number));
                    number = tmp;
                }

                curBasicBlock.addSuccBasicBlock(bodyBlock);
                curBasicBlock.addSuccBasicBlock(exitBlock);
                curBasicBlock.setFork(new MiracleIRBranch(
                        (MiracleIRDirectRegister) number,
                        bodyBlock, exitBlock
                ));
            } else {
                curBasicBlock.setFork(new MiracleIRJump(bodyBlock));
                curBasicBlock.addSuccBasicBlock(bodyBlock);
            }
            MiracleIRBasicBlock oldBodyBlock = loopCondBlock;
            MiracleIRBasicBlock oldExitBlock = loopExitBlock;
            loopCondBlock = condBlock;
            loopExitBlock = exitBlock;

            curBasicBlock = bodyBlock;
            if (iteration.body != null) {
                iteration.body.accept(this);
            }

            if (iteration.incrementExpression != null) {
                iteration.incrementExpression.accept(this);
            }

            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(new MiracleIRJump(condBlock));
            }

            curBasicBlock = exitBlock;

            loopExitBlock = oldExitBlock;
            loopCondBlock = oldBodyBlock;
        }

        @Override
        public void visit(MiracleASTreeBreak breakLiteral) {
            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(new MiracleIRJump(loopExitBlock));
            }
        }

        @Override
        public void visit(MiracleASTreeContinue continueLiteral) {
            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(new MiracleIRJump(loopCondBlock));
            }
        }

        @Override
        public void visit(MiracleASTreeReturn returnLiteral) {
            if (returnLiteral.expression != null) {
                returnLiteral.expression.accept(this);
                curBasicBlock.tail.prepend(new MiracleIRMove(
                        curFunction.retRegister,
                        returnLiteral.expression.getResultNumber()
                ));
            }
            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(new MiracleIRJump(curFunction.getExitBasicBlock()));
                curBasicBlock.addSuccBasicBlock(curFunction.getExitBasicBlock());
            }
        }

        @Override
        public void visit(MiracleASTreeVariable variable) {
            MiracleSymbol symbol = variable.getScope().resolve(variable.identifier);
            if (symbol instanceof MiracleASTreeVariableDeclaration) {
                if (((MiracleASTreeVariableDeclaration) symbol).getMemberFrom() != null) {
                    // if a variable in class has no this before, then I will force to add it
                    variable.setResultNumber(new MiracleIROffsetRegister(
                            curFunction.selfRegister,
                            new MiracleIRImmediate(
                                    curClass.getVariableOffset(variable.identifier),
                                    MiracleOption.INT_SIZE
                            ),
                            null,
                            curClass.getVariable(variable.identifier).getRegisterSize()
                    ));
                } else {
                    variable.setResultNumber(((MiracleASTreeVariableDeclaration) symbol).getAddress());
                }
            } else if (symbol instanceof MiracleASTreeFunctionDeclaration) {
                variable.setResultNumber(((MiracleASTreeFunctionDeclaration) symbol)
                        .getSymbol().getAddress().retRegister
                );
            } else if (symbol instanceof MiracleSymbolFunctionType) {
                variable.setResultNumber(((MiracleSymbolFunctionType) symbol)
                        .getAddress().retRegister
                );
            } else {
                throw new RuntimeException("unexpected case");
            }
        }

        @Override
        public void visit(MiracleASTreeCall call) {
            call.function.accept(this);
            MiracleSymbolFunctionType type = (MiracleSymbolFunctionType) call.function.getResultType();
            List<MiracleIRNumber> parameters = new LinkedList<>();
            call.parameters.forEach(element -> {
                element.accept(this);
                parameters.add(element.getResultNumber());
            });
            MiracleIRRegister selfRegister = null;
            if (call.function instanceof MiracleASTreeField) {
                selfRegister = (MiracleIRRegister) ((MiracleASTreeField) call.function).expression.getResultNumber();
            }
            if (type.getReturnType().isSameType(__builtin_void)) {
                curBasicBlock.tail.prepend(new MiracleIRCall(
                        type.getAddress(), parameters,
                        null, selfRegister
                ));
            } else {
                MiracleIRRegister register = curFunction.buffer.require(
                        ".t" + String.valueOf(countTmpRegister++),
                        type.getReturnType().getRegisterSize()
                );
                curBasicBlock.tail.prepend(new MiracleIRCall(
                        type.getAddress(), parameters,
                        register, selfRegister
                ));
                call.setResultNumber(register);
            }
        }

        @Override
        public void visit(MiracleASTreeSubscript subscript) {
            subscript.base.accept(this);
            subscript.coordinate.accept(this);
            int size = ((MiracleSymbolArrayType) subscript.base.getResultType()).subscript().getRegisterSize();
            MiracleIRNumber tmpCoor = subscript.coordinate.getResultNumber();
            if (!(tmpCoor instanceof MiracleIRDirectRegister)) {
                MiracleIRVirtualRegister victim = curFunction.buffer.require(
                        ".t" + String.valueOf(countTmpRegister++),
                        subscript.coordinate.getResultType().getRegisterSize()
                );
                curBasicBlock.tail.prepend(new MiracleIRMove(victim, tmpCoor));
                tmpCoor = victim;
            }
            MiracleIRNumber tmpBase = subscript.base.getResultNumber();
            if (!(tmpBase instanceof MiracleIRDirectRegister)) {
                MiracleIRVirtualRegister victim = curFunction.buffer.require(
                        ".t" + String.valueOf(countTmpRegister++),
                        subscript.base.getResultType().getRegisterSize()
                );
                curBasicBlock.tail.prepend(new MiracleIRMove(victim, tmpBase));
                tmpBase = victim;
            }

            subscript.setResultNumber(new MiracleIROffsetRegister(
                    (MiracleIRDirectRegister) tmpBase, null,
                    ImmutablePair.of((MiracleIRDirectRegister) tmpCoor, new MiracleIRImmediate(size, MiracleOption.INT_SIZE)),
                    size
            ));
        }

        private void addRelation(MiracleASTreeBinaryExpression expression) {
            MiracleIRCompare.Types operator;
            switch (expression.operator) {
                case REQ:
                    operator = MiracleIRCompare.Types.REQ;
                    break;
                case LEQ:
                    operator = MiracleIRCompare.Types.LEQ;
                    break;
                case RT:
                    operator = MiracleIRCompare.Types.RT;
                    break;
                case LT:
                    operator = MiracleIRCompare.Types.LT;
                    break;
                case NEQ:
                    operator = MiracleIRCompare.Types.NEQ;
                    break;
                case EQL:
                    operator = MiracleIRCompare.Types.EQL;
                    break;
                default:
                    throw new RuntimeException("unsupported operator");
            }
            MiracleIRDirectRegister register = curFunction.buffer.require(
                    ".t" + String.valueOf(countTmpRegister++),
                    MiracleOption.INT_SIZE
            );
            if (expression.left.getResultType().isSameType(__builtin_string)) {
                curBasicBlock.tail.prepend(new MiracleIRCall(
                        __builtin_strcmp.getAddress(),
                        Arrays.asList(
                                expression.left.getResultNumber(),
                                expression.right.getResultNumber()
                        ),
                        register,
                        null
                ));
                curBasicBlock.tail.prepend(new MiracleIRCompare(operator, register,
                        new MiracleIRImmediate(0, MiracleOption.INT_SIZE), register
                ));
            } else {
                curBasicBlock.tail.prepend(new MiracleIRCompare(
                        operator,
                        expression.left.getResultNumber(),
                        expression.right.getResultNumber(),
                        register
                ));
            }
            expression.setResultNumber(register);
        }

        private void addArithmetic(MiracleASTreeBinaryExpression expression) {
            MiracleIRBinaryArithmetic.Types operator;
            switch (expression.operator) {
                case DISJ:
                    operator = MiracleIRBinaryArithmetic.Types.OR;
                    break;
                case CONJ:
                    operator = MiracleIRBinaryArithmetic.Types.AND;
                    break;
                case XOR:
                    operator = MiracleIRBinaryArithmetic.Types.XOR;
                    break;
                case AND:
                    operator = MiracleIRBinaryArithmetic.Types.AND;
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
                case SHL:
                    operator = MiracleIRBinaryArithmetic.Types.SHL;
                    break;
                case DIV:
                    operator = MiracleIRBinaryArithmetic.Types.DIV;
                    break;
                case OR:
                    operator = MiracleIRBinaryArithmetic.Types.OR;
                    break;
                case MUL:
                    operator = MiracleIRBinaryArithmetic.Types.MUL;
                    break;
                case ADD:
                    operator = MiracleIRBinaryArithmetic.Types.ADD;
                    break;
                default:
                    throw new RuntimeException("unsupported operator");
            }
            MiracleIRDirectRegister register = curFunction.buffer.require(
                    ".t" + String.valueOf(countTmpRegister++),
                    MiracleOption.INT_SIZE
            );
            curBasicBlock.tail.prepend(new MiracleIRMove(
                    register, expression.left.getResultNumber()
            ));
            if (operator.equals(MiracleIRBinaryArithmetic.Types.MOD)) {
                MiracleIRDirectRegister subtrahend = curFunction.buffer.require(
                        ".t" + String.valueOf(countTmpRegister++),
                        MiracleOption.INT_SIZE
                );
                curBasicBlock.tail.prepend(new MiracleIRMove(
                        subtrahend, expression.left.getResultNumber()
                ));
                curBasicBlock.tail.prepend(new MiracleIRBinaryArithmetic(
                        MiracleIRBinaryArithmetic.Types.DIV,
                        subtrahend, expression.right.getResultNumber()
                ));
                curBasicBlock.tail.prepend(new MiracleIRBinaryArithmetic(
                        MiracleIRBinaryArithmetic.Types.MUL,
                        subtrahend, expression.right.getResultNumber()
                ));
                curBasicBlock.tail.prepend(new MiracleIRBinaryArithmetic(
                        MiracleIRBinaryArithmetic.Types.SUB,
                        register, subtrahend
                ));
            } else {
                curBasicBlock.tail.prepend(new MiracleIRBinaryArithmetic(
                        operator, register, expression.right.getResultNumber()
                ));
            }
            expression.setResultNumber(register);
        }

        private void addAssignment(MiracleASTreeBinaryExpression expression) {
            curBasicBlock.tail.prepend(new MiracleIRMove(
                    (MiracleIRRegister) expression.left.getResultNumber(),
                    expression.right.getResultNumber()
            ));
        }

        private void addStringConcat(MiracleASTreeBinaryExpression expression) {
            MiracleIRDirectRegister register = curFunction.buffer.require(
                    ".t" + String.valueOf(countTmpRegister++),
                    MiracleOption.INT_SIZE
            );
            curBasicBlock.tail.prepend(new MiracleIRCall(
                    __builtin_strcat.getAddress(),
                    Arrays.asList(
                            expression.left.getResultNumber(),
                            expression.right.getResultNumber()
                    ),
                    register, null
            ));
            expression.setResultNumber(register);
        }

        @Override
        public void visit(MiracleASTreeBinaryExpression binaryExpression) {
            binaryExpression.left.accept(this);
            binaryExpression.right.accept(this);
            switch (binaryExpression.operator) {
                case EQL:
                case NEQ:
                case LT:
                case RT:
                case LEQ:
                case REQ:
                    addRelation(binaryExpression);
                    break;
                case DISJ:
                case CONJ:
                case XOR:
                case AND:
                case SHR:
                case SUB:
                case MOD:
                case SHL:
                case DIV:
                case OR:
                case MUL:
                    addArithmetic(binaryExpression);
                    break;
                case ADD:
                    if (binaryExpression.left.getResultType().isSameType(__builtin_string)) {
                        addStringConcat(binaryExpression);
                    } else {
                        addArithmetic(binaryExpression);
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
            MiracleIRNumber number = prefixExpression.expression.getResultNumber();
            switch (prefixExpression.operator) {
                case INC:
                    operator = MiracleIRPrefixArithmetic.Types.ADD;
                    break;
                case DEC:
                    operator = MiracleIRPrefixArithmetic.Types.SUB;
                    break;
                case REV:
                    operator = MiracleIRPrefixArithmetic.Types.REV;
                    break;
                case NEGATIVE:
                    operator = MiracleIRPrefixArithmetic.Types.MINUS;
                    if (number instanceof MiracleIRImmediate) {
                        prefixExpression.setResultNumber(new MiracleIRImmediate(
                                -((MiracleIRImmediate) number).value,
                                number.getNumberSize()
                        ));
                        return;
                    }
                    break;
                case NEGATE:
                    MiracleIRDirectRegister register = curFunction.buffer.require(
                            ".t" + String.valueOf(countTmpRegister++),
                            MiracleOption.INT_SIZE
                    );
                    curBasicBlock.tail.prepend(new MiracleIRCompare(
                            MiracleIRCompare.Types.EQL,
                            number, new MiracleIRImmediate(0, number.getNumberSize()),
                            register
                    ));
                    prefixExpression.setResultNumber(register);
                    return;
                case POSITIVE:
                    prefixExpression.setResultType(prefixExpression.expression.getResultType());
                    return;
                default:
                    throw new RuntimeException("unsupported operator");
            }
            curBasicBlock.tail.prepend(new MiracleIRPrefixArithmetic((MiracleIRRegister) number, operator));
            prefixExpression.setResultNumber(prefixExpression.expression.getResultNumber());
        }

        @Override
        public void visit(MiracleASTreeSuffixExpression suffixExpression) {
            suffixExpression.expression.accept(this);
            MiracleIRPrefixArithmetic.Types operator;
            switch (suffixExpression.operator) {
                case INC:
                    operator = MiracleIRPrefixArithmetic.Types.ADD;
                    break;
                case DEC:
                    operator = MiracleIRPrefixArithmetic.Types.SUB;
                    break;
                default:
                    throw new RuntimeException("unsupported operator");
            }
            MiracleIRDirectRegister register = curFunction.buffer.require(
                    ".t" + String.valueOf(countTmpRegister++),
                    MiracleOption.INT_SIZE
            );
            curBasicBlock.tail.prepend(new MiracleIRMove(
                    register, suffixExpression.expression.getResultNumber()
            ));
            curBasicBlock.tail.prepend(new MiracleIRPrefixArithmetic(
                    (MiracleIRRegister) suffixExpression.expression.getResultNumber(),
                    operator
            ));
            suffixExpression.setResultNumber(register);
        }

        @Override
        public void visit(MiracleASTreeNew newNode) {
            MiracleSymbolVariableType type = newNode.variableType.getType();
            if (newNode.expressions.isEmpty()) {
                MiracleIRDirectRegister register = curFunction.buffer.require(
                        ".t" + String.valueOf(countTmpRegister++),
                        MiracleOption.POINTER_SIZE
                );
                curBasicBlock.tail.prepend(new MiracleIRHeapAllocate(
                        register, type.getMemorySize(),
                        new MiracleIRImmediate(1, MiracleOption.INT_SIZE)
                ));
                if (type instanceof MiracleSymbolClassType) {
                    MiracleSymbolFunctionType constructor = type.getMethod("");
                    if (constructor != null) {
                        curBasicBlock.tail.prepend(new MiracleIRCall(
                                constructor.getAddress(),
                                Collections.emptyList(),
                                null, register
                        ));
                    }
                }
                newNode.setResultNumber(register);
            } else { // Array Type
                MiracleIRDirectRegister register = curFunction.buffer.require(
                        ".t" + String.valueOf(countTmpRegister++),
                        MiracleOption.POINTER_SIZE
                );
                List<MiracleASTreeExpression> nextExprs = new LinkedList<>(newNode.expressions);
                MiracleASTreeExpression expression = nextExprs.remove(0);
                if (expression == null) return;
                expression.accept(this);

                curBasicBlock.tail.prepend(new MiracleIRHeapAllocate(
                        register, MiracleOption.POINTER_SIZE,
                        expression.getResultNumber()
                ));
                newNode.setResultNumber(register);

                if (!nextExprs.isEmpty()) {
                    MiracleIRDirectRegister iterreg = curFunction.buffer.require("new_init_iter_" + String.valueOf(countTmpRegister++), MiracleOption.INT_SIZE);
                    curBasicBlock.tail.prepend(new MiracleIRBinaryArithmetic(
                            MiracleIRBinaryArithmetic.Types.XOR,
                            iterreg, iterreg
                    ));
                    MiracleIRBasicBlock newInitCondBlock = new MiracleIRBasicBlock("new_init_cond_" + String.valueOf(countBlock++), curFunction, false, false);
                    MiracleIRBasicBlock newInitExitBlock = new MiracleIRBasicBlock("new_init_exit_" + String.valueOf(countBlock++), curFunction, false, false);
                    MiracleIRDirectRegister flagreg = curFunction.buffer.require("new_init_flag_" + String.valueOf(countTmpRegister++), MiracleOption.INT_SIZE);
                    if (!curBasicBlock.isForked()) {
                        curBasicBlock.addSuccBasicBlock(newInitCondBlock);
                        curBasicBlock.setFork(new MiracleIRJump(newInitCondBlock));
                    }

                    curBasicBlock = newInitCondBlock;
                    curBasicBlock.tail.prepend(new MiracleIRCompare(
                            MiracleIRCompare.Types.LT, iterreg,
                            expression.getResultNumber(), flagreg
                    ));
                    MiracleIRBasicBlock newInitBodyBlock = new MiracleIRBasicBlock("new_init_body_" + String.valueOf(countBlock++), curFunction, false, false);

                    curBasicBlock.addSuccBasicBlock(newInitBodyBlock);
                    curBasicBlock.addSuccBasicBlock(newInitExitBlock);
                    curBasicBlock.setFork(new MiracleIRBranch(flagreg, newInitBodyBlock, newInitExitBlock));

                    curBasicBlock = newInitBodyBlock;
                    MiracleASTreeNew nextNew = new MiracleASTreeNew(newNode.variableType, nextExprs, null);
                    nextNew.setScope(newNode.getScope());
                    nextNew.accept(this);
                    curBasicBlock.tail.prepend(new MiracleIRMove(
                            new MiracleIROffsetRegister(register, null,
                                    ImmutablePair.of(iterreg, new MiracleIRImmediate(MiracleOption.POINTER_SIZE, MiracleOption.INT_SIZE)),
                                    MiracleOption.POINTER_SIZE
                            ),
                            nextNew.getResultNumber()
                    ));
                    curBasicBlock.addSuccBasicBlock(newInitCondBlock);
                    curBasicBlock.setFork(new MiracleIRJump(newInitCondBlock));
                    curBasicBlock = newInitExitBlock;
                }
            }
        }

        @Override
        public void visit(MiracleASTreeStringConstant stringConstant) {
            if (globalFunction.containsKey(stringConstant.value)) {
                stringConstant.setResultNumber(globalString.get(stringConstant.value));
            } else {
                MiracleIRStaticString register = new MiracleIRStaticString(
                        ".str" + String.valueOf(countTmpRegister++),
                        stringConstant.value
                );
                globalString.put(stringConstant.value, register);
                stringConstant.setResultNumber(register);
            }
        }

        @Override
        public void visit(MiracleASTreeIntegerConstant integerConstant) {
            integerConstant.setResultNumber(new MiracleIRImmediate(
                    Integer.parseInt(integerConstant.value), MiracleOption.INT_SIZE
            ));
        }

        @Override
        public void visit(MiracleASTreeBooleanConstant booleanConstant) {
            if (booleanConstant.value.equals("true")) {
                booleanConstant.setResultNumber(new MiracleIRImmediate(1, MiracleOption.BOOL_SIZE));
            } else {
                booleanConstant.setResultNumber(new MiracleIRImmediate(0, MiracleOption.BOOL_SIZE));
            }
        }

        @Override
        public void visit(MiracleASTreeNullConstant nullConstant) {
            nullConstant.setResultNumber(new MiracleIRImmediate(0, MiracleOption.POINTER_SIZE));
        }

        @Override
        public void visit(MiracleASTreeThis thisNode) {
            thisNode.setResultNumber(curFunction.selfRegister);
        }

        @Override
        public void visit(MiracleASTreeField field) {
            field.expression.accept(this);
            MiracleSymbolFunctionType functionType = field.expression.getResultType().getMethod(field.identifier);
            if (functionType != null) {
                field.setResultNumber(null);
            } else {
                MiracleSymbolType type = field.expression.getResultType();
                MiracleIRNumber number = field.expression.getResultNumber();
                if (number instanceof MiracleIROffsetRegister) {
                    MiracleIRVirtualRegister register = curFunction.buffer.require(
                            ".t" + String.valueOf(countTmpRegister++),
                            number.getNumberSize()
                    );
                    curBasicBlock.tail.prepend(new MiracleIRMove(register, number));
                    number = register;
                }
                field.setResultNumber(new MiracleIROffsetRegister(
                        (MiracleIRDirectRegister) number,
                        new MiracleIRImmediate(
                                ((MiracleSymbolClassType) type).getVariableOffset(field.identifier),
                                MiracleOption.INT_SIZE
                        ),
                        null,
                        ((MiracleSymbolClassType) type).getVariable(field.identifier).getRegisterSize()
                ));
            }
        }

        @Override
        public void visit(MiracleASTreeTypeNode typeNode) {

        }

        public MiracleIR build() {
            return new MiracleIR(globalVariable, globalString, globalFunction);
        }
    }
}
