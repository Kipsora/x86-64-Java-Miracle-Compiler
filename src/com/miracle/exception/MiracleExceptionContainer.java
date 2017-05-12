package com.miracle.exception;

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

public class MiracleExceptionContainer {
    private List<String> container = new LinkedList<>();

    public void add(String exception) {
        container.add(exception);
    }

    public void except(PrintStream stream) {
        if (!container.isEmpty()) {
            container.forEach((element) -> stream.println("Miracle: Error: " + element));
            System.exit(1);
        }
    }

    public static RuntimeException fatal(String message) throws RuntimeException {
        return new RuntimeException("Miracle: FATAL Error: " + message);
    }
}
