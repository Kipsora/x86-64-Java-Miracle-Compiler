package com.miracle.scanner;

import com.miracle.astree.MiracleASTree;
import com.miracle.cstree.MiracleLexer;
import com.miracle.cstree.MiracleParser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Stack;

public class MiracleScanner {
    private static class MiracleTable{
        private static Integer idCounter = 0;
        private static HashMap<String, int> map;
        private static Stack<Pair<String, >> stack;

        public static Integer getId(String id) throws Exception {
            if (map.containsKey(id)) {
                return map[id];
            } else {
                throw Exception("Identifier " + id + ' is not defined.');
            }
        }
        public static void register(String id) throws Exception {
            if (map.containsKey(id)) {
                throw Exception("Identifier " + id + ' has alrea')
            }
        }
    }

    private static MiracleASTree build(MiracleParser.MiracleContext ctx) { return new MiracleASTree(); }
    public static MiracleASTree scan(InputStream stream) throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(stream);
        MiracleLexer lexer = new MiracleLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MiracleParser parser = new MiracleParser(tokens);
        return build(parser.miracle());
    }
}