package com.miracle.scanner;

import com.miracle.astree.MiracleASTree;
import com.miracle.cstree.MiracleLexer;
import com.miracle.cstree.MiracleParser;
import com.miracle.exceptions.MiracleException;
import com.miracle.scanner.listener.MiracleASTreeBuilder;
import com.miracle.scanner.listener.MiracleClassDeclarationFetcher;
import com.miracle.scanner.listener.MiracleDetailedDeclarationFetcher;
import com.miracle.scanner.listener.MiracleSyntaxErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.InputStream;

public class MiracleScanner {
    public static MiracleASTree scan(InputStream stream) throws Exception {
        try {
            MiracleLexer lexer = new MiracleLexer(new ANTLRInputStream(stream));
            MiracleParser parser = new MiracleParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new MiracleSyntaxErrorListener());
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(new MiracleClassDeclarationFetcher(), parser.miracle());
            parser.reset();
            walker.walk(new MiracleDetailedDeclarationFetcher(), parser.miracle());
            parser.reset();
            MiracleASTreeBuilder builder = new MiracleASTreeBuilder();
            walker.walk(builder, parser.miracle());
            return builder.getTree();
        } catch (MiracleException e) {
            throw new Exception(e.getMessage());
        }
    }
}