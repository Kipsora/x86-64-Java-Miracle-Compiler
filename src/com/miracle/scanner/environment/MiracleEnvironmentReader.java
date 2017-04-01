package com.miracle.scanner.environment;

import com.miracle.astree.node.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeVariableDeclaration;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.util.HashMap;
import java.util.Stack;

public class MiracleEnvironmentReader extends MiracleEnvironmentManager {
    private static HashMap<String, ImmutablePair<Boolean, MiracleASTreeFunctionDeclaration>> curFuncMap = new HashMap<>();
    private static HashMap<String, ImmutablePair<Boolean, MiracleASTreeVariableDeclaration>> curVarMap = new HashMap<>();
    private static HashMap<String, ImmutablePair<Boolean, MiracleASTreeClassDeclaration>> curClassMap = new HashMap<>();
    private static Stack<ImmutableTriple<String, Integer, ImmutablePair<Boolean, MiracleASTreeVariableDeclaration>>> varStack = new Stack<>();
    private static Stack<ImmutableTriple<String, Integer, ImmutablePair<Boolean, MiracleASTreeFunctionDeclaration>>> funcStack = new Stack<>();
    private static Stack<ImmutableTriple<String, Integer, ImmutablePair<Boolean, MiracleASTreeClassDeclaration>>> classStack = new Stack<>();

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
        return curClassMap.containsKey(id) || curVarMap.containsKey(id) || curFuncMap.containsKey(id);
    }

    public static MiracleASTreeDeclaration get(String id) {
        if (curClassMap.containsKey(id)) return curClassMap.get(id).getRight();
        if (curVarMap.containsKey(id)) return curVarMap.get(id).getRight();
        if (curFuncMap.containsKey(id)) return curFuncMap.get(id).getRight();
        return null;
    }

    public static void declare(String identifier, boolean coverable, MiracleASTreeVariableDeclaration value) {
        varStack.push(ImmutableTriple.of(identifier, scopes.peek().getRight(),
                curVarMap.getOrDefault(identifier, null)));
        curVarMap.put(identifier, ImmutablePair.of(coverable, value));
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
