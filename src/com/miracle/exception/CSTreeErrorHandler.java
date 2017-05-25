package com.miracle.exception;

import com.miracle.cstree.MiracleSourcePosition;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class CSTreeErrorHandler extends BaseErrorListener {
    private final ExceptionContainer exceptionContainer;

    public CSTreeErrorHandler(ExceptionContainer exceptionContainer) {
        this.exceptionContainer = exceptionContainer;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) {
        exceptionContainer.add(s, new MiracleSourcePosition(i, i1, i1 + ((CommonToken) o).getText().length()));
    }
}
