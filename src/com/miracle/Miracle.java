package com.miracle;

import com.miracle.astree.ASTree;
import com.miracle.astree.visitor.ClassFetcher;
import com.miracle.astree.visitor.MemberFetcher;
import com.miracle.astree.visitor.ScopeBuilder;
import com.miracle.astree.visitor.SemanticAnalyser;
import com.miracle.cstree.parser.MiracleLexer;
import com.miracle.cstree.parser.MiracleParser;
import com.miracle.exception.CSTreeErrorHandler;
import com.miracle.exception.ExceptionContainer;
import com.miracle.intermediate.Root;
import com.miracle.intermediate.visitor.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.cli.*;

import java.io.*;

public class Miracle {
    private final boolean printASTree;
    private final boolean printIR;
    private final InputStream inputStream;
    private final PrintStream outputStream;

    private final PrintStream errorStream = System.err;

    private final ExceptionContainer exceptionContainer = new ExceptionContainer(
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
            throw ExceptionContainer.getRuntimeException(exception.getMessage());
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
            parser.addErrorListener(new CSTreeErrorHandler(exceptionContainer));
            MiracleParser.MiracleContext cstree = parser.miracle();
            exceptionContainer.judge();
            return cstree;
        } catch (IOException e) {
            throw ExceptionContainer.getRuntimeException(e.getMessage());
        }
    }

    private ASTree getASTree(MiracleParser.MiracleContext cstree) throws IOException {
        ASTree.Builder builder = new ASTree.Builder(exceptionContainer);
        new ParseTreeWalker().walk(builder, cstree);
        ASTree astree = builder.build();
        astree.accept(new ScopeBuilder());
        astree.accept(new ClassFetcher(exceptionContainer));
        astree.accept(new MemberFetcher(exceptionContainer));
        astree.accept(new SemanticAnalyser(exceptionContainer));
        exceptionContainer.judge();
        return astree;
    }

    private Root getIR(ASTree astree) {
        Root.Builder builder = new Root.Builder();
        astree.accept(builder);
        return builder.build();
    }

    private void run() throws IOException {
        try {
            MiracleParser.MiracleContext cstree = getCSTree();
            ASTree astree = getASTree(cstree);
            if (this.printASTree) {
                com.miracle.astree.visitor.Printer printer = new com.miracle.astree.visitor.Printer();
                astree.accept(printer);
                outputStream.println(printer.getOutput());
                return;
            }
            Root ir = getIR(astree);
            ir.accept(new LLTransformer());
            if (this.printIR) {
                ir.accept(new RegisterCollector());
                Printer generator = new Printer();
                ir.accept(generator);
                outputStream.println(generator.getOutput());
                return;
            }
            DirectAllocator allocator = new DirectAllocator();
            ir.accept(allocator);
            ir.accept(new RegisterCollector());
            X64Printer printer = new X64Printer();
            ir.accept(printer);
            outputStream.println(printer.getOutput());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
