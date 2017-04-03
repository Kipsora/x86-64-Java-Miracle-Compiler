package com.miracle.scanner;

import com.miracle.astree.node.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;
import com.miracle.astree.node.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.exceptions.MiracleExceptionConflictWithKeyword;
import com.miracle.exceptions.MiracleExceptionDuplicateDeclaration;
import com.miracle.exceptions.MiracleExceptionUndefinedIdentifier;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.util.*;

public class MiracleEnvironmentManager {
    private static int scopeNumber = 0;

    private static final HashSet<String> MiracleKEYWORD = new HashSet<String>() {{
        add("bool");
        add("int");
        add("string");
        add("null");
        add("void");
        add("true");
        add("false");
        add("if");
        add("for");
        add("while");
        add("break");
        add("continue");
        add("return");
        add("new");
        add("class");
        add("this");
    }};

    private static List<HashMap<String, MiracleASTreeClassDeclaration>> mapclass = new ArrayList<>();
    private static List<HashMap<String, MiracleASTreeFunctionDeclaration>> mapfunc = new ArrayList<>();
    private static List<HashMap<String, MiracleASTreeVariableDeclaration>> mapvari = new ArrayList<>();

    private static HashMap<String, ImmutablePair<Integer, MiracleASTreeClassDeclaration>> declaredClass = new HashMap<>();
    private static HashMap<String, ImmutablePair<Integer, MiracleASTreeFunctionDeclaration>> declaredFunction = new HashMap<>();
    private static HashMap<String, ImmutablePair<Integer, MiracleASTreeVariableDeclaration>> declaredVariable = new HashMap<>();

    private static Stack<ImmutableTriple<Integer, String, MiracleASTreeClassDeclaration>> recclass = new Stack<>();
    private static Stack<ImmutableTriple<Integer, String, MiracleASTreeFunctionDeclaration>> recfunc = new Stack<>();
    private static Stack<ImmutableTriple<Integer, String, MiracleASTreeVariableDeclaration>> recvari = new Stack<>();

    private static Stack<ImmutableTriple<Integer, Boolean, Boolean>> scope = new Stack<>();

    private static MiracleEnvironmentManager instance = new MiracleEnvironmentManager();

    private MiracleEnvironmentManager() {
    }

    private static void checkDeclaration(String identifier, String type) {
        if (MiracleKEYWORD.contains(identifier)) {
            throw new MiracleExceptionConflictWithKeyword(identifier);
        }
        if (mapclass.get(scope.peek().getLeft()).containsKey(identifier)) {
            throw new MiracleExceptionDuplicateDeclaration("class", type, identifier);
        }
        if (mapfunc.get(scope.peek().getLeft()).containsKey(identifier)) {
            throw new MiracleExceptionDuplicateDeclaration("function", type, identifier);
        }
        if (scope.peek().getRight()) {
            if (mapvari.get(scope.peek().getLeft()).containsKey(identifier)) {
                throw new MiracleExceptionDuplicateDeclaration("variable", type, identifier);
            }
        } else {
            if (declaredVariable.containsKey(identifier)
                    && declaredVariable.get(identifier).getLeft().equals(scope.peek().getLeft())) {
                throw new MiracleExceptionDuplicateDeclaration("variable", type, identifier);
            }
        }
    }

    public static void init() {
        scopeNumber = 0;
    }

    public static boolean inMemberScope() {
        return scope.peek().getMiddle();
    }

    public static void newscope(boolean member) {
        if (!scope.empty()) {
            scope.push(ImmutableTriple.of(scopeNumber++, scope.peek().getMiddle() | member, member));
        } else {
            scope.push(ImmutableTriple.of(scopeNumber++, member, member));
        }
        if (mapclass.size() < scopeNumber) {
            mapclass.add(new HashMap<>());
        }
        mapclass.get(scope.peek().getLeft()).forEach((key, value) -> {
            if (declaredClass.containsKey(key)) {
                recclass.push(ImmutableTriple.of(scope.peek().getLeft(), key, declaredClass.get(key).getRight()));
            } else {
                recclass.push(ImmutableTriple.of(scope.peek().getLeft(), key, null));
            }
            declaredClass.put(key, ImmutablePair.of(scope.peek().getLeft(), value));
        });
        if (mapfunc.size() < scopeNumber) {
            mapfunc.add(new HashMap<>());
        }
        mapfunc.get(scope.peek().getLeft()).forEach((key, value) -> {
            if (declaredFunction.containsKey(key)) {
                recfunc.push(ImmutableTriple.of(scope.peek().getLeft(), key, declaredFunction.get(key).getRight()));
            } else {
                recfunc.push(ImmutableTriple.of(scope.peek().getLeft(), key, null));
            }
            declaredFunction.put(key, ImmutablePair.of(scope.peek().getLeft(), value));
        });
        if (mapvari.size() < scopeNumber) {
            mapvari.add(new HashMap<>());
        }
        if (scope.peek().getRight()) {
            mapvari.get(scope.peek().getLeft()).forEach((key, value) -> {
                if (declaredVariable.containsKey(key)) {
                    recvari.push(ImmutableTriple.of(scope.peek().getLeft(), key, declaredVariable.get(key).getRight()));
                } else {
                    recvari.push(ImmutableTriple.of(scope.peek().getLeft(), key, null));
                }
                declaredVariable.put(key, ImmutablePair.of(scope.peek().getLeft(), value));
            });
        }
    }

    public static void exitscope() {
        while (!recclass.empty() && recclass.peek().getLeft().equals(scope.peek().getLeft())) {
            if (recclass.peek().getRight() == null) {
                declaredClass.remove(recclass.peek().getMiddle());
            } else {
                declaredClass.put(recclass.peek().getMiddle(), ImmutablePair.of(recclass.peek().getLeft(), recclass.peek().getRight()));
            }
            recclass.pop();
        }
        while (!recfunc.empty() && recfunc.peek().getLeft().equals(scope.peek().getLeft())) {
            if (recfunc.peek().getRight() == null) {
                declaredFunction.remove(recfunc.peek().getMiddle());
            } else {
                declaredFunction.put(recfunc.peek().getMiddle(), ImmutablePair.of(recfunc.peek().getLeft(), recfunc.peek().getRight()));
            }
            recfunc.pop();
        }
        while (!recvari.empty() && recvari.peek().getLeft().equals(scope.peek().getLeft())) {
            if (recvari.peek().getRight() == null) {
                declaredVariable.remove(recvari.peek().getMiddle());
            } else {
                declaredVariable.put(recvari.peek().getMiddle(), ImmutablePair.of(recvari.peek().getLeft(), recvari.peek().getRight()));
            }
            recvari.pop();
        }
        scope.pop();
    }

    public static void declareClass(String identifier) {
        checkDeclaration(identifier, "class");
        MiracleASTreeClassDeclaration node = new MiracleASTreeClassDeclaration(identifier);
        mapclass.get(scope.peek().getLeft()).put(identifier, node);
        if (declaredClass.containsKey(identifier)) {
            recclass.push(ImmutableTriple.of(scope.peek().getLeft(), identifier, declaredClass.get(identifier).getRight()));
        } else {
            recclass.push(ImmutableTriple.of(scope.peek().getLeft(), identifier, null));
        }
        declaredClass.put(identifier, ImmutablePair.of(scope.peek().getLeft(), node));
    }

    public static void declareFunction(String identifier, MiracleASTreeTypename returnType,
                                       List<MiracleASTreeVariableDeclaration> arguments) {
        checkDeclaration(identifier, "function");
        MiracleASTreeFunctionDeclaration node = new MiracleASTreeFunctionDeclaration(returnType, identifier, arguments);
        mapfunc.get(scope.peek().getLeft()).put(identifier, node);
        if (declaredFunction.containsKey(identifier)) {
            recfunc.push(ImmutableTriple.of(scope.peek().getLeft(), identifier, declaredFunction.get(identifier).getRight()));
        } else {
            recfunc.push(ImmutableTriple.of(scope.peek().getLeft(), identifier, null));
        }
        declaredFunction.put(identifier, ImmutablePair.of(scope.peek().getLeft(), node));
    }

    public static void declareVariable(String identifier, MiracleASTreeTypename type) {
        checkDeclaration(identifier, "variable");
        MiracleASTreeVariableDeclaration node = new MiracleASTreeVariableDeclaration(identifier, type);
        mapvari.get(scope.peek().getLeft()).put(identifier, node);
        if (declaredVariable.containsKey(identifier)) {
            recvari.push(ImmutableTriple.of(scope.peek().getLeft(), identifier, declaredVariable.get(identifier).getRight()));
        } else {
            recvari.push(ImmutableTriple.of(scope.peek().getLeft(), identifier, null));
        }
        declaredVariable.put(identifier, ImmutablePair.of(scope.peek().getLeft(), node));
    }

    public static void fetchVariable(String identifier) {
        if (!mapvari.get(scope.peek().getLeft()).containsKey(identifier)) {
            throw new MiracleExceptionUndefinedIdentifier(identifier);
        }
        if (!scope.peek().getRight()) {
            if (declaredVariable.containsKey(identifier)) {
                recvari.push(ImmutableTriple.of(scope.peek().getLeft(), identifier, declaredVariable.get(identifier).getRight()));
            } else {
                recvari.push(ImmutableTriple.of(scope.peek().getLeft(), identifier, null));
            }
            declaredVariable.put(identifier, ImmutablePair.of(scope.peek().getLeft(),
                    mapvari.get(scope.peek().getLeft()).get(identifier)));
        }
    }

    public static MiracleASTreeClassDeclaration getClass(String identifier) {
        if (!declaredClass.containsKey(identifier)) {
            throw new MiracleExceptionUndefinedIdentifier(identifier);
        }
        return declaredClass.get(identifier).getRight();
    }

    public static MiracleASTreeFunctionDeclaration getFunction(String identifier) {
        if (!declaredFunction.containsKey(identifier)) {
            throw new MiracleExceptionUndefinedIdentifier(identifier);
        }
        return declaredFunction.get(identifier).getRight();
    }

    public static MiracleASTreeVariableDeclaration getVariable(String identifier) {
        if (!declaredVariable.containsKey(identifier)) {
            throw new MiracleExceptionUndefinedIdentifier(identifier);
        }
        return declaredVariable.get(identifier).getRight();
    }

    public static boolean containVariable(String identifier) {
        return declaredVariable.containsKey(identifier);
    }

    public static boolean containClass(String identifier) {
        return declaredClass.containsKey(identifier);
    }

    public static boolean containFunction(String identifier) {
        return declaredFunction.containsKey(identifier);
    }

    public static boolean contain(String identifier) {
        return containFunction(identifier) || containVariable(identifier) || containClass(identifier);
    }
}
