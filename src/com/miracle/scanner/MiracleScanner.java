package com.miracle.scanner;

import com.miracle.cstree.MiracleLexer;
import com.miracle.cstree.MiracleParser;
import com.miracle.exceptions.MiracleCompilationError;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.InputStream;

public class MiracleScanner {
    public static void scan(InputStream stream) throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(stream);
        MiracleLexer lexer = new MiracleLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MiracleParser parser = new MiracleParser(tokens);
        ParseTree tree = parser.miracle();

        ParseTreeWalker walker = new ParseTreeWalker();
        MiracleAnalyzer analyzer = new MiracleAnalyzer();

        MiracleScope.initScope();

        try {
            walker.walk(analyzer, tree);
        } catch(MiracleCompilationError e) {
            System.out.println(e.getMessage());
        }
    }
}