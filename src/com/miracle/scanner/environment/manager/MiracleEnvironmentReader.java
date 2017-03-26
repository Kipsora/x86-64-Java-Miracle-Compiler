package com.miracle.scanner.environment.manager;

import com.miracle.exceptions.MiracleExceptionIdentifierShadowError;
import com.miracle.scanner.environment.identifier.*;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.util.HashMap;
import java.util.Stack;

public final class MiracleEnvironmentReader extends MiracleEnvironmentManager {
    private static HashMap<String, ImmutablePair<Integer, MiracleIdentifierFunction>> curFuncMap = new HashMap<>();
    private static HashMap<String, ImmutablePair<Integer, MiracleIdentifierVariable>> curVarMap = new HashMap<>();
    private static HashMap<String, ImmutablePair<Integer, MiracleIdentifierClass>> curClassMap = new HashMap<>();
    private static Stack<ImmutableTriple<String, Integer, ImmutablePair<Integer, MiracleIdentifierVariable>>> varStack = new Stack<>();
    private static Stack<ImmutableTriple<String, Integer, ImmutablePair<Integer, MiracleIdentifierFunction>>> funcStack = new Stack<>();
    private static Stack<ImmutableTriple<String, Integer, ImmutablePair<Integer, MiracleIdentifierClass>>> classStack = new Stack<>();

    private static void mergeMap(int scope) {
        if (varMap.containsKey(scope)) {
            varMap.get(scope).forEach((key, value) -> {
                if (curVarMap.containsKey(key) && !curVarMap.get(key).getRight().getCoverable()) {
                    throw new MiracleExceptionIdentifierShadowError(key, "variable",
                            curVarMap.get(key).getRight().getIdentifierType());
                }
                if (curClassMap.containsKey(key) && !curClassMap.get(key).getRight().getCoverable()) {
                    throw new MiracleExceptionIdentifierShadowError(key, "variable",
                            curClassMap.get(key).getRight().getIdentifierType());
                }
                if (curFuncMap.containsKey(key) && !curFuncMap.get(key).getRight().getCoverable()) {
                    throw new MiracleExceptionIdentifierShadowError(key, "variable",
                            curFuncMap.get(key).getRight().getIdentifierType());
                }
                varStack.push(ImmutableTriple.of(key, scope, curVarMap.getOrDefault(key, null)));
                curVarMap.put(key, value);
            });
        }
        if (classMap.containsKey(scope)) {
            classMap.get(scope).forEach((key, value) -> {
                if (curVarMap.containsKey(key) && !curVarMap.get(key).getRight().getCoverable()) {
                    throw new MiracleExceptionIdentifierShadowError(key, "class",
                            curVarMap.get(key).getRight().getIdentifierType());
                }
                if (curClassMap.containsKey(key) && !curClassMap.get(key).getRight().getCoverable()) {
                    throw new MiracleExceptionIdentifierShadowError(key, "class",
                            curClassMap.get(key).getRight().getIdentifierType());
                }
                if (curFuncMap.containsKey(key) && !curFuncMap.get(key).getRight().getCoverable()) {
                    throw new MiracleExceptionIdentifierShadowError(key, "class",
                            curFuncMap.get(key).getRight().getIdentifierType());
                }
                classStack.push(ImmutableTriple.of(key, scope, curClassMap.getOrDefault(key, null)));
                curClassMap.put(key, value);
            });
        }
        if (funcMap.containsKey(scope)) {
            funcMap.get(scope).forEach((key, value) -> {
                if (curVarMap.containsKey(key) && !curVarMap.get(key).getRight().getCoverable()) {
                    throw new MiracleExceptionIdentifierShadowError(key, "function",
                            curVarMap.get(key).getRight().getIdentifierType());
                }
                if (curClassMap.containsKey(key) && !curClassMap.get(key).getRight().getCoverable()) {
                    throw new MiracleExceptionIdentifierShadowError(key, "function",
                            curClassMap.get(key).getRight().getIdentifierType());
                }
                if (curFuncMap.containsKey(key) && !curFuncMap.get(key).getRight().getCoverable()) {
                    throw new MiracleExceptionIdentifierShadowError(key, "function",
                            curFuncMap.get(key).getRight().getIdentifierType());
                }
                funcStack.push(ImmutableTriple.of(key, scope, curFuncMap.getOrDefault(key, null)));
                curFuncMap.put(key, value);
            });
        }
    }

    public static boolean contain(String id) {
        return curClassMap.containsKey(id) || curVarMap.containsKey(id) || curFuncMap.containsKey(id);
    }

    public static MiracleIdentifier get(String id) {
        if (curClassMap.containsKey(id)) return curClassMap.get(id).getRight();
        if (curVarMap.containsKey(id)) return curVarMap.get(id).getRight();
        if (curFuncMap.containsKey(id)) return curFuncMap.get(id).getRight();
        return null;
    }

    @Override
    public void initScope() {
        super.initScope();
        mergeMap(scopeNumber);
    }

    @Override
    public void enterScope(ScopeType type) {
        super.enterScope(type);
        mergeMap(scopeNumber);
    }

    @Override
    public void exitScope() {
        int scope = scopes.peek().getRight();
        while (!varStack.empty() && varStack.peek().getMiddle().equals(scope)) {
            if (varStack.peek().getRight() == null) {
                curVarMap.remove(varStack.peek().getLeft());
            } else {
                curVarMap.put(varStack.peek().getLeft(), varStack.peek().getRight());
            }
            varStack.pop();
        }
        while (!classStack.empty() && classStack.peek().getMiddle().equals(scope)) {
            if (classStack.peek().getRight() == null) {
                curClassMap.remove(classStack.peek().getLeft());
            } else {
                curClassMap.put(classStack.peek().getLeft(), classStack.peek().getRight());
            }
            classStack.pop();
        }
        while (!funcStack.empty() && funcStack.peek().getMiddle().equals(scope)) {
            if (funcStack.peek().getRight() == null) {
                curFuncMap.remove(funcStack.peek().getLeft());
            } else {
                curFuncMap.put(funcStack.peek().getLeft(), funcStack.peek().getRight());
            }
            funcStack.pop();
        }
        super.exitScope();
    }
}
