package com.miracle;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.visitor.MiracleASTreePrinter;
import com.miracle.scanner.MiracleScanner;

public class Miracle {
    public static void main(String args[]) throws Exception {
        MiracleASTree tree = MiracleScanner.scan(System.in);
        tree.visit(new MiracleASTreePrinter());
    }
}
