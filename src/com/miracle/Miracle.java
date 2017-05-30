package com.miracle;

import com.miracle.astree.ASTree;
import com.miracle.astree.visitor.*;
import com.miracle.cstree.parser.MiracleLexer;
import com.miracle.cstree.parser.MiracleParser;
import com.miracle.exception.CSTreeErrorHandler;
import com.miracle.exception.ExceptionContainer;
import com.miracle.intermediate.Root;
import com.miracle.intermediate.visitor.*;
import com.miracle.intermediate.visitor.allocator.SimpleAllocator;
import com.miracle.intermediate.visitor.irtranser.HLIRTransformer;
import com.miracle.intermediate.visitor.irtranser.LLIRTransformer;
import com.miracle.intermediate.visitor.irtranser.MLIRTransformer;
import com.miracle.intermediate.visitor.printer.IRPrinter;
import com.miracle.intermediate.visitor.printer.LMHIRPrinter;
import com.miracle.intermediate.visitor.ssa.SSAConstructor;
import com.miracle.intermediate.visitor.ssa.SSADestructor;
import com.miracle.intermediate.visitor.ssa.SSAOptimizer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.cli.*;

import java.io.*;

public class Miracle {
    private final boolean isPrintASTree;
    private final boolean isPrintLLevelIR;
    private final boolean isPrintMLevelIR;
    private final boolean isPrintHLevelIR;
    private final boolean isSSADisabled;

    private final InputStream inputStream;
    private final PrintStream outputStream;
    private final PrintStream errorStream = System.err;

    private final ExceptionContainer exceptionContainer = new ExceptionContainer(
            errorStream
    );

    private Miracle(String args[]) {
        boolean isPrintASTree;
        boolean isPrintHLevelIR;
        boolean isPrintMLevelIR;
        boolean isPrintLLevelIR;
        boolean isSSADisabled;
        FileInputStream inputStream;
        FileOutputStream outputStream;
        try {
            Options options = new Options();
            options.addOption("h", "help", false, "show this help information");
            options.addOption(null, "no-ssa", false, "disable single static assignment");
            options.addOption(null, "show-astree", false, "show abstract syntax tree");
            options.addOption(null, "show-high-ir", false,
                    "show high level intermediate representation code");
            options.addOption(null, "show-middle-ir", false,
                    "show middle level intermediate representation code");
            options.addOption(null, "show-low-ir", false,
                    "show low level intermediate representation code");
            options.addOption("i", "input", true, "specify input file");
            options.addOption("o", "output", true, "specify output file");
            CommandLine line = new DefaultParser().parse(options, args);
            if (line.hasOption("help")) {
                HelpFormatter helpFormatter = new HelpFormatter();
                helpFormatter.setLeftPadding(2);
                helpFormatter.printHelp("miracle", options);
                System.exit(0);
            }
            isPrintASTree = line.hasOption("show-astree");
            isPrintHLevelIR = line.hasOption("show-high-ir");
            isPrintMLevelIR = line.hasOption("show-middle-ir");
            isPrintLLevelIR = line.hasOption("show-low-ir");
            isSSADisabled = line.hasOption("no-ssa");
            inputStream = line.hasOption("input") ? new FileInputStream(line.getOptionValue("input")) : null;
            outputStream = line.hasOption("output") ? new FileOutputStream(line.getOptionValue("output")) : null;
        } catch (FileNotFoundException | ParseException exception) {
            throw ExceptionContainer.getRuntimeException(exception.getMessage());
        }

        this.isPrintASTree = isPrintASTree;
        this.isPrintLLevelIR = isPrintLLevelIR;
        this.isPrintMLevelIR = isPrintMLevelIR;
        this.isPrintHLevelIR = isPrintHLevelIR;
        this.isSSADisabled = isSSADisabled;
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

    private Root intermediate(ASTree astree) {
        Root.Builder builder = new Root.Builder();
        astree.accept(builder);
        return builder.build();
    }

    private void printIR(Root ir, IRPrinter visitor) throws IOException {
        ir.accept(visitor);
        outputStream.println(visitor.getOutput());
        System.exit(0);
    }

    private void printASTree(ASTree astree) {
        ASTreePrinter ASTreePrinter = new ASTreePrinter();
        astree.accept(ASTreePrinter);
        outputStream.println(ASTreePrinter.getOutput());
        System.exit(0);
    }

    private ASTree semantic() throws IOException {
        ASTree astree = getASTree(getCSTree());
        if (this.isPrintASTree) printASTree(astree);
        return astree;
    }

    private Root optimize(Root ir) throws IOException {
        ir.accept(new HLIRTransformer());
        /*if (!isSSADisabled) {
            ir.accept(new SSAConstructor());
            ir.accept(new SSAOptimizer());
            ir.accept(new SSADestructor());
        }*/
        if (this.isPrintHLevelIR) printIR(ir, new LMHIRPrinter());
        ir.accept(new MLIRTransformer());
        if (this.isPrintMLevelIR) printIR(ir, new LMHIRPrinter());
        ir.accept(new SimpleAllocator());
        return ir;
    }

    private void generate(Root ir) throws IOException {
        ir.accept(new LLIRTransformer());
        if (this.isPrintLLevelIR) printIR(ir, new LMHIRPrinter());
        printIR(ir, new X64Printer("./utility/builtin.mx"));
    }

    private void run() throws IOException {
        try {
            generate(optimize(intermediate(semantic())));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
