package com.miracle.scanner.environment;

import com.miracle.exceptions.MiracleExceptionDuplicateDeclaration;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.util.HashMap;

public final class MiracleMutableEnvironmentManager extends MiracleEnvironmentManager {
    private static int identifierNumber;

    @Override
    public void initScope() {
        scopeNumber = identifierNumber = 0;
        scopes.push(ImmutableTriple.of(ScopeType.SCOPE_GLOBAL, (short) (1 << ScopeType.SCOPE_GLOBAL.ordinal()), scopeNumber));
    }

    public static void declare(String identifier, MiracleIdentifier value) {
        int scope = scopes.peek().getRight();
        if (scopeIdMap.containsKey(scope)) {
            if (scopeIdMap.get(scope).containsKey(identifier)) {
                throw new MiracleExceptionDuplicateDeclaration(
                        value.getIdentifierType(),
                        scopeIdMap.get(scope).get(identifier).getRight().getIdentifierType(),
                        identifier);
            }
            identifierNumber++;
            scopeIdMap.get(scope).put(identifier, ImmutablePair.of(identifierNumber, value));
        } else {
            identifierNumber++;
            HashMap<String, ImmutablePair<Integer, MiracleIdentifier>> newmap = new HashMap<>();
            newmap.put(identifier, ImmutablePair.of(identifierNumber, value));
            scopeIdMap.put(scope, newmap);
        }
    }
}
