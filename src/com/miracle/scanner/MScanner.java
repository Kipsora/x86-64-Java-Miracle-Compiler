package com.miracle.scanner;

import com.miracle.exceptions.MException;
import com.miracle.scanner.listener.MSyntaxErrorListener;
import com.miracle.scanner.scope.MScope;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.InputStream;

public class MScanner {
    public static void scan(InputStream stream) throws IOException {
        MLexer lexer = new MLexer(new ANTLRInputStream(stream));
        MParser parser = new MParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(new MSyntaxErrorListener());
        ParseTreeWalker walker = new ParseTreeWalker();

        try {
            MScope.initScope();
            walker.walk(new MBaseListener(), parser.miracle());
            MScope.exitScope();
        } catch (MException e) {
            System.out.print(e.getMessage());
        }
    }
}