package com.miracle;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.visitor.MiracleASTreeClassFetcher;
import com.miracle.astree.visitor.MiracleASTreeSemanticChecker;
import com.miracle.astree.visitor.MiracleASTreePrinter;
import com.miracle.astree.visitor.MiracleASTreeScopeBuilder;
import com.miracle.cstree.parser.MiracleLexer;
import com.miracle.cstree.parser.MiracleParser;
import com.miracle.exception.MiracleExceptionContainer;
import com.miracle.symbol.MiracleSymbolTable;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.cli.*;

import java.io.*;

public class Miracle {
    final private boolean printASTree;
    final private boolean printIR;
    final private InputStream inputStream;
    final private OutputStream outputStream;

    final private PrintStream errorStream = System.err;
    final private MiracleExceptionContainer exceptionContainer = new MiracleExceptionContainer();

    private Miracle(String args[]) {
        boolean printASTree;
        boolean printIR;
        boolean hasInputStream;
        boolean hasOutputStream;
        FileInputStream inputStream;
        FileOutputStream outputStream;
        try {
            Options options = new Options();
            options.addOption("h", "help", false, "except this help information");
            options.addOption(null, "astree", false, "except abstract syntax tree");
            options.addOption(null, "intermediate", false,
                    "except intermediate represention code");
            options.addOption("i", "input", true, "specify input file");
            options.addOption("o", "output", true, "specify output file");
            CommandLine line = new DefaultParser().parse(options, args);
            if (line.hasOption("help")) {
                new HelpFormatter().printHelp("miracle", options);
                System.exit(0);
            }
            printASTree = line.hasOption("astree");
            printIR = line.hasOption("intermediate");
            hasInputStream = line.hasOption("input");
            hasOutputStream = line.hasOption("output");
            inputStream = line.hasOption("input") ? new FileInputStream(line.getOptionValue("input")) : null;
            outputStream = line.hasOption("output") ? new FileOutputStream(line.getOptionValue("output")) : null;
        } catch (FileNotFoundException | ParseException exception) {
            throw MiracleExceptionContainer.fatal(exception.getMessage());
        }
        this.printASTree = printASTree;
        this.printIR = printIR;
        this.inputStream = !hasInputStream ? System.in : inputStream;
        this.outputStream = !hasOutputStream ? System.out : outputStream;
    }

    private MiracleASTree getASTree() {
        try {
            MiracleParser parser = new MiracleParser(new CommonTokenStream(
                    new MiracleLexer(new ANTLRInputStream(inputStream))));
            parser.setErrorHandler(new BailErrorStrategy());
            MiracleASTree.Builder builder = new MiracleASTree.Builder();
            new ParseTreeWalker().walk(builder, parser.miracle());
            return builder.build();
        } catch (IOException e) {
            throw MiracleExceptionContainer.fatal(e.getMessage());
        }
    }

    private void run() {
        MiracleASTree tree = getASTree();
        if (this.printASTree) {
            tree.accept(new MiracleASTreePrinter());
        }
        MiracleSymbolTable symbolTable = new MiracleSymbolTable(null);
        tree.accept(new MiracleASTreeScopeBuilder(symbolTable));
        tree.accept(new MiracleASTreeClassFetcher(exceptionContainer, symbolTable));
        exceptionContainer.except(errorStream);
        tree.accept(new MiracleASTreeSemanticChecker(exceptionContainer, symbolTable));
        exceptionContainer.except(errorStream);
    }

    public static void main(String args[]) {
        new Miracle(args).run();
    }
}
