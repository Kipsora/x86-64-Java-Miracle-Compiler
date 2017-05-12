package com.miracle;

import com.miracle.astree.MiracleASTree;
import com.miracle.cstree.parser.MiracleLexer;
import com.miracle.cstree.parser.MiracleParser;
import com.miracle.exception.MiracleExceptionContainer;
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
        boolean printASTree = false;
        boolean printIR = false;
        boolean hasInputStream = false;
        boolean hasOutputStream = false;
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
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
            inputStream = new FileInputStream(line.getOptionValue("input"));
            outputStream = new FileOutputStream(line.getOptionValue("output"));
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
    }

    public static void main(String args[]) {
        new Miracle(args).run();
    }
}
