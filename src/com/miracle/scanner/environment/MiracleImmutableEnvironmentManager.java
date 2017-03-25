package com.miracle.scanner.environment;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.util.HashMap;
import java.util.Stack;

public final class MiracleImmutableEnvironmentManager extends MiracleEnvironmentManager {
    private static HashMap<String, ImmutablePair<Integer, MiracleIdentifier>> idMap = new HashMap<>();
    private static Stack<ImmutableTriple<String, Integer, ImmutablePair<Integer, MiracleIdentifier>>> stack = new Stack<>();

    @Override
    public void enterScope(ScopeType type) {
        scopeNumber++;
        scopes.push(ImmutableTriple.of(type, (short) (scopes.peek().getMiddle() | (1 << type.ordinal())), scopeNumber));
        if (scopeIdMap.containsKey(scopeNumber)) {
            scopeIdMap.get(scopeNumber).forEach((key, value) -> {
                stack.push(ImmutableTriple.of(key, scopeNumber, idMap.getOrDefault(key, null)));
                idMap.put(key, value);
            });
        }
    }

    public static boolean contain(String id) {
        return idMap.containsKey(id);
    }

    @Override
    public void exitScope() {
        while (!stack.empty() && stack.peek().getMiddle().equals(scopes.peek().getRight())) {
            if (stack.peek().getRight() == null) {
                idMap.remove(stack.peek().getLeft());
            } else {
                idMap.put(stack.peek().getLeft(), stack.peek().getRight());
            }
        }
        scopes.pop();
    }


}
