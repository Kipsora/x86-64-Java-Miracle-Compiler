package com.miracle.astree.visitor;

import com.miracle.exception.MiracleExceptionContainer;

public class MiracleASTreeClassFetcher extends MiracleASTreeBaseVisitor {
    private MiracleExceptionContainer exceptionContainer;
    MiracleASTreeClassFetcher(MiracleExceptionContainer exceptionContainer) {
        this.exceptionContainer = exceptionContainer;
    }


}
