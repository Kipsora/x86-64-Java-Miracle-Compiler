package com.miracle.scanner.environment;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.util.HashMap;
import java.util.Stack;

public class MiracleEnvironmentManager {
    static int scopeNumber;
    static Stack<ImmutableTriple<ScopeType, Short, Integer>> scopes = new Stack<>();

    static HashMap<Integer, HashMap<String, ImmutablePair<Integer, MiracleIdentifier>>> scopeIdMap = new HashMap<>();

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

    public static ScopeType getCurrentScopeType() {
        return scopes.peek().getLeft();
    }

    public static boolean inScope(ScopeType type) {
        return ((scopes.peek().getMiddle() >> type.ordinal()) & 1) == 1;
    }

    public enum ScopeType {
        SCOPE_VAR, SCOPE_GLOBAL, SCOPE_FUNC, SCOPE_CLASS, SCOPE_ITER, SCOPE_BLOCK
    }
}
