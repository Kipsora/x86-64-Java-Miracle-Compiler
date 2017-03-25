package com.miracle.scanner.environment;

import com.miracle.exceptions.MiracleExceptionIdentifierUnshadowable;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.util.HashMap;
import java.util.Stack;

public final class MiracleImmutableEnvironmentManager extends MiracleEnvironmentManager {
    private static HashMap<String, ImmutablePair<Integer, MiracleIdentifier>> idMap = new HashMap<>();
    private static Stack<ImmutableTriple<String, Integer, ImmutablePair<Integer, MiracleIdentifier>>> stack = new Stack<>();

    private static void mergeMap(int scope) {
        if (scopeIdMap.containsKey(scope)) {
            scopeIdMap.get(scope).forEach((key, value) -> {
                if (idMap.containsKey(key) && !idMap.get(key).getRight().getCoverable()) {
                    throw new MiracleExceptionIdentifierUnshadowable(key);
                }
                stack.push(ImmutableTriple.of(key, scope, idMap.getOrDefault(key, null)));
                idMap.put(key, value);
            });
        }
    }

    @Override
    public void initScope() {
        super.initScope();
        mergeMap(scopeNumber);
    }

    @Override
    public void enterScope(ScopeType type) {
        scopeNumber++;
        scopes.push(ImmutableTriple.of(type, (short) (scopes.peek().getMiddle() | (1 << type.ordinal())), scopeNumber));
        mergeMap(scopeNumber);
    }

    public static boolean contain(String id) {
        return idMap.containsKey(id);
    }

    public static MiracleIdentifier get(String id) {
        return idMap.get(id).getRight();
    }

    @Override
    public void exitScope() {
        while (!stack.empty() && stack.peek().getMiddle().equals(scopes.peek().getRight())) {
            if (stack.peek().getRight() == null) {
                idMap.remove(stack.peek().getLeft());
            } else {
                idMap.put(stack.peek().getLeft(), stack.peek().getRight());
            }
            stack.pop();
        }
        scopes.pop();
    }


}
