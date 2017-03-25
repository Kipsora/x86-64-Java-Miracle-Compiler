package com.miracle.scanner.listener;

import com.miracle.exceptions.MiracleExceptionSyntaxError;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class MiracleSyntaxErrorListener extends BaseErrorListener{
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        throw new MiracleExceptionSyntaxError(line, charPositionInLine + 1, msg);
    }
}
