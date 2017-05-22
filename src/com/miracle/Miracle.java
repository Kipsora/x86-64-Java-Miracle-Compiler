package com.miracle;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.visitor.*;
import com.miracle.cstree.parser.MiracleLexer;
import com.miracle.cstree.parser.MiracleParser;
import com.miracle.exception.MiracleCSTreeErrorHandler;
import com.miracle.exception.MiracleExceptionContainer;
import com.miracle.intermediate.MiracleIR;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.cli.*;

import java.io.*;

public class Miracle {
    final private boolean printASTree;
    final private boolean printIR;
    final private InputStream inputStream;
    final private PrintStream outputStream;

    final private PrintStream errorStream = System.err;

    final private MiracleExceptionContainer exceptionContainer = new MiracleExceptionContainer(
            errorStream
    );

    private Miracle(String args[]) {
        boolean printASTree;
        boolean printIR;
        FileInputStream inputStream;
        FileOutputStream outputStream;
        try {
            Options options = new Options();
            options.addOption("h", "help", false, "show this help information");
            options.addOption(null, "show-astree", false, "show abstract syntax tree");
            options.addOption(null, "show-ir", false,
                    "show intermediate representation code");
            options.addOption("i", "input", true, "specify input file");
            options.addOption("o", "output", true, "specify output file");
            CommandLine line = new DefaultParser().parse(options, args);
            if (line.hasOption("help")) {
                HelpFormatter helpFormatter = new HelpFormatter();
                helpFormatter.setLeftPadding(2);
                helpFormatter.printHelp("miracle", options);
                System.exit(0);
            }
            printASTree = line.hasOption("show-astree");
            printIR = line.hasOption("show-ir");
            inputStream = line.hasOption("input") ? new FileInputStream(line.getOptionValue("input")) : null;
            outputStream = line.hasOption("output") ? new FileOutputStream(line.getOptionValue("output")) : null;
        } catch (FileNotFoundException | ParseException exception) {
            throw MiracleExceptionContainer.getRuntimeException(exception.getMessage());
        }

        this.printASTree = printASTree;
        this.printIR = printIR;
        this.inputStream = inputStream == null ? System.in : inputStream;
        this.outputStream = outputStream == null ? System.out : new PrintStream(outputStream);
    }

    public static void main(String args[]) throws IOException {
        new Miracle(args).run();
    }

    private MiracleParser.MiracleContext getCSTree() {
        try {
            ANTLRInputStream istream = new ANTLRInputStream(inputStream);
            exceptionContainer.setInputStream(istream.toString());
            MiracleParser parser = new MiracleParser(new CommonTokenStream(
                    new MiracleLexer(istream)
            ));
            parser.removeErrorListeners();
            parser.addErrorListener(new MiracleCSTreeErrorHandler(exceptionContainer));
            MiracleParser.MiracleContext cstree = parser.miracle();
            exceptionContainer.judge();
            return cstree;
        } catch (IOException e) {
            throw MiracleExceptionContainer.getRuntimeException(e.getMessage());
        }
    }

    private MiracleASTree getASTree(MiracleParser.MiracleContext cstree) throws IOException {
        MiracleASTree.Builder builder = new MiracleASTree.Builder(exceptionContainer);
        new ParseTreeWalker().walk(builder, cstree);
        MiracleASTree astree = builder.build();
        astree.accept(new MiracleASTreeScopeBuilder());
        astree.accept(new MiracleASTreeClassFetcher(exceptionContainer));
        astree.accept(new MiracleASTreeMemberFetcher(exceptionContainer));
        astree.accept(new MiracleASTreeSemanticAnalyser(exceptionContainer));
        exceptionContainer.judge();
        return astree;
    }

    private MiracleIR getIR(MiracleASTree astree) {
        MiracleIR.Builder builder = new MiracleIR.Builder();
        astree.accept(builder);
        return builder.build();
    }

    private void run() throws IOException {
        try {
            MiracleParser.MiracleContext cstree = getCSTree();
            MiracleASTree astree = getASTree(cstree);
            if (this.printASTree) {
                astree.accept(new MiracleASTreePrinter(outputStream));
            }
            /*MiracleIR ir = getIR(astree);
            if (this.printIR) {
                MiracleIRPrinter generator = new MiracleIRPrinter();
                ir.accept(generator);
                outputStream.println(generator.getOutput());
            }*/
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
