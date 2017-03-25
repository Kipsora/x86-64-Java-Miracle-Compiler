package com.miracle.scanner;

import com.miracle.cstree.MiracleLexer;
import com.miracle.cstree.MiracleParser;
import com.miracle.exceptions.MiracleException;
import com.miracle.scanner.listener.MiracleExpressionListener;
import com.miracle.scanner.listener.MiracleSyntaxErrorListener;
import com.miracle.scanner.scope.MiracleScope;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.InputStream;

public class MiracleScanner {
    public static void scan(InputStream stream) throws IOException {
        MiracleLexer lexer = new MiracleLexer(new ANTLRInputStream(stream));
        MiracleParser parser = new MiracleParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(new MiracleSyntaxErrorListener());
        ParseTreeWalker walker = new ParseTreeWalker();

        try {
            MiracleScope.initScope();
            walker.walk(new MiracleExpressionListener(), parser.miracle());
            MiracleScope.exitScope();
        } catch (MiracleException e) {
            System.out.print(e.getMessage());
        }
    }
}