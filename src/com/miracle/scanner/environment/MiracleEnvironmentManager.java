package com.miracle.scanner.environment;

import com.miracle.astree.node.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeFunctionDeclaration;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class MiracleEnvironmentManager {
    static final HashSet<String> KEYWORD = new HashSet<>(Arrays.asList(new String[]{
            "int", "bool", "string", "null", "void",
            "true", "false", "if", "for", "while", "break",
            "continue", "return", "new", "class", "this"
    }));
    static final HashSet<String> BUILTIN = new HashSet<>(Arrays.asList(new String[]{
            "print", "println", "getString", "getInt", "toString"
    }));
    static int scopeNumber;
    static Stack<ImmutableTriple<ScopeType, Short, Integer>> scopes = new Stack<>();

    static HashMap<Integer, HashMap<String, ImmutablePair<Boolean, MiracleASTreeClassDeclaration>>> classMap = new HashMap<>();
    static HashMap<Integer, HashMap<String, ImmutablePair<Boolean, MiracleASTreeFunctionDeclaration>>> funcMap = new HashMap<>();

    public static ScopeType getCurrentScopeType() {
        return scopes.peek().getLeft();
    }

    public static boolean inScope(ScopeType type) {
        return ((scopes.peek().getMiddle() >> type.ordinal()) & 1) == 1;
    }

    public void initScope() {
        scopeNumber = 0;
        scopes.push(ImmutableTriple.of(ScopeType.SCOPE_GLOBAL, (short) (1 << ScopeType.SCOPE_GLOBAL.ordinal()), scopeNumber));
    }

    public void enterScope(ScopeType type) {
        scopeNumber++;
        scopes.push(ImmutableTriple.of(type, (short) (scopes.peek().getMiddle() | (1 << type.ordinal())), scopeNumber));
    }

    public void exitScope() {
        scopes.pop();
    }

    public enum ScopeType {
        SCOPE_GLOBAL, SCOPE_FUNC, SCOPE_CLASS, SCOPE_ITER, SCOPE_BLOCK
    }
}
