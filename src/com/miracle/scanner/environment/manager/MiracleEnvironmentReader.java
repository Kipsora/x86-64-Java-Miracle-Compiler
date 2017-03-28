package com.miracle.scanner.environment.manager;

import com.miracle.astree.node.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.scanner.environment.identifier.MiracleIdentifier;
import com.miracle.scanner.environment.identifier.MiracleIdentifierVariable;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.util.HashMap;
import java.util.Stack;

public final class MiracleEnvironmentReader extends MiracleEnvironmentManager {
    private static int varNumber;
    private static HashMap<String, ImmutablePair<Integer, MiracleASTreeFunctionDeclaration>> curFuncMap = new HashMap<>();
    private static HashMap<String, ImmutablePair<Integer, MiracleASTreeVariableDeclaration>> curVarMap = new HashMap<>();
    private static HashMap<String, ImmutablePair<Integer, MiracleASTreeClassDeclaration>> curClassMap = new HashMap<>();
    private static Stack<ImmutableTriple<String, Integer, ImmutablePair<Integer, MiracleASTreeVariableDeclaration>>> varStack = new Stack<>();
    private static Stack<ImmutableTriple<String, Integer, ImmutablePair<Integer, MiracleASTreeFunctionDeclaration>>> funcStack = new Stack<>();
    private static Stack<ImmutableTriple<String, Integer, ImmutablePair<Integer, MiracleASTreeClassDeclaration>>> classStack = new Stack<>();

    private static void mergeMap(int scope) {
        if (classMap.containsKey(scope)) {
            classMap.get(scope).forEach((key, value) -> {
                classStack.push(ImmutableTriple.of(key, scope, curClassMap.getOrDefault(key, null)));
                curClassMap.put(key, value);
            });
        }
        if (funcMap.containsKey(scope)) {
            funcMap.get(scope).forEach((key, value) -> {
                funcStack.push(ImmutableTriple.of(key, scope, curFuncMap.getOrDefault(key, null)));
                curFuncMap.put(key, value);
            });
        }
    }

    public static boolean contain(String id) {
        return KEYWORD.contains(id) || BUILTIN.contains(id) || curClassMap.containsKey(id)
                || curVarMap.containsKey(id) || curFuncMap.containsKey(id);
    }

    public static MiracleIdentifier get(String id) {
        if (curClassMap.containsKey(id)) return curClassMap.get(id).getRight();
        if (curVarMap.containsKey(id)) return curVarMap.get(id).getRight();
        if (curFuncMap.containsKey(id)) return curFuncMap.get(id).getRight();
        return null;
    }

    public static void declare(String identifier, MiracleIdentifierVariable value) {
        varStack.push(ImmutableTriple.of(identifier, scopes.peek().getRight(),
                curVarMap.getOrDefault(identifier, null)));
        curVarMap.put(identifier, ImmutablePair.of(++varNumber, value));
    }

    @Override
    public void initScope() {
        varNumber = 0;
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
