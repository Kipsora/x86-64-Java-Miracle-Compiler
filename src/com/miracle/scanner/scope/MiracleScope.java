package com.miracle.scanner.scope;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.util.HashMap;
import java.util.Stack;

public class MiracleScope {
    private static int scopeNumber = 0;
    private static int identifierNumber = 0;
    private static Stack<ImmutableTriple<ScopeType, Short, Integer>> scopes = new Stack<>();
    private static HashMap<String, ImmutablePair<Integer, MiracleIdentifier>> map = new HashMap<>();
    private static Stack<ImmutableTriple<String, Integer, ImmutablePair<Integer, MiracleIdentifier>>> stack = new Stack<>();

    public static void initScope() {
        scopes.push(ImmutableTriple.of(ScopeType.SCOPE_GLOBAL, (short) (1 << ScopeType.SCOPE_GLOBAL.ordinal()), 0));
    }

    public static void addScope(ScopeType type) {
        scopeNumber++;
        scopes.push(ImmutableTriple.of(type, (short) (scopes.peek().getMiddle() | (1 << type.ordinal())), scopeNumber));
    }

    public static void exitScope() {
        while (!stack.empty() && stack.peek().getMiddle().equals(scopes.peek().getRight())) {
            ImmutableTriple<String, Integer, ImmutablePair<Integer, MiracleIdentifier>> tmp = stack.peek();
            if (tmp.getRight() == null) {
                map.remove(tmp.getLeft());
            }
            scopes.pop();
        }
        scopes.pop();
    }

    public static boolean inScope(ScopeType type) {
        return ((scopes.peek().getMiddle() >> type.ordinal()) & 1) == 1;
    }

    public static int getScopeNumber() {
        return scopes.peek().getRight();
    }

    public static ScopeType getScopeType() {
        return scopes.peek().getLeft();
    }

    public static boolean containsIdentifier(String id) {
        return map.containsKey(id);
    }

    public static boolean duplicatesIdentifier(String id) {
        return map.containsKey(id) && map.get(id).getLeft().equals(scopes.peek().getRight());
    }

    public static boolean addIdentifier(String id, MiracleIdentifier value) {
        if (map.containsKey(id)) {
            ImmutablePair<Integer, MiracleIdentifier> tmp = map.get(id);
            if (tmp.getLeft().equals(scopes.peek().getRight())) {
                return false;
            }
            stack.push(ImmutableTriple.of(id, scopes.peek().getRight(), tmp));
        } else {
            stack.push(ImmutableTriple.of(id, scopes.peek().getRight(), null));
        }
        identifierNumber++;
        map.put(id, ImmutablePair.of(identifierNumber, value));
        return true;
    }

    public static ImmutablePair<ScopeType, ImmutablePair<Integer, MiracleIdentifier>> getIdentifier(String id) {
        return ImmutablePair.of(scopes.peek().getLeft(), map.getOrDefault(id, null));
    }

    public enum ScopeType {
        SCOPE_CLASS, SCOPE_LOOP, SCOPE_FUNC, SCOPE_GLOBAL, SCOPE_BLOCK, SCOPE_VAR
    }

}