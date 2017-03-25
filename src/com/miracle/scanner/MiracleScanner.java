package com.miracle.scanner;

import com.miracle.exceptions.MiracleException;
import com.miracle.scanner.listener.MiracleSyntaxErrorListener;
import com.miracle.scanner.scope.MiracleScope;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.InputStream;

public class MiracleScanner {
    public static void scan(InputStream stream) throws IOException {
        MLexer lexer = new MLexer(new ANTLRInputStream(stream));
        MParser parser = new MParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(new MiracleSyntaxErrorListener());
        ParseTreeWalker walker = new ParseTreeWalker();

        try {
            MiracleScope.initScope();
            walker.walk(new MBaseListener(), parser.miracle());
            MiracleScope.exitScope();
        } catch (MiracleException e) {
            System.out.print(e.getMessage());
        }
    }
}