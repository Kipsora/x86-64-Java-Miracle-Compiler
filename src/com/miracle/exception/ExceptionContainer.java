package com.miracle.exception;

import com.miracle.cstree.SourcePosition;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

public class ExceptionContainer {
    private final PrintStream ostream;
    private BufferedReader source;
    private List<ImmutablePair<SourcePosition, String>> container = new LinkedList<>();

    public ExceptionContainer(PrintStream ostream) {
        this.ostream = ostream;
    }

    public static RuntimeException getRuntimeException(String message) throws RuntimeException {
        return new RuntimeException("Miracle: FATAL Error: " + message);
    }

    public void setInputStream(String source) throws IOException {
        this.source = new BufferedReader(new StringReader(source));
    }

    public void add(String message, SourcePosition position) {
        container.add(ImmutablePair.of(position, message));
    }

    public void judge() throws IOException {
        if (!container.isEmpty()) {
            container.sort((x, y) -> {
                SourcePosition xPosition = x.getLeft();
                SourcePosition yPosition = y.getLeft();
                if (xPosition == null) return -1;
                if (yPosition == null) return 1;
                if (xPosition.row < yPosition.row) return -1;
                if (xPosition.row > yPosition.row) return 1;
                if (xPosition.columnStart < yPosition.columnStart) return -1;
                if (xPosition.columnStart > yPosition.columnStart) return 1;
                return 0;
            });

            String line = null;
            for (int i = 0, j = 0, containerSize = container.size(); i < containerSize; i++) {
                ImmutablePair<SourcePosition, String> element = container.get(i);
                SourcePosition left = element.getLeft();
                if (left != null) {
                    while (j < element.getLeft().row) {
                        line = source.readLine();
                        j++;
                    }
                    int space = line.length() - line.trim().length();

                    ostream.println("Miracle:" + left.toPrintableString()
                            + ": error: " + element.getRight());
                    ostream.println("  " + line.trim());
                    for (int k = 0; k < left.columnStart - space + 2; k++) {
                        ostream.print(' ');
                    }
                    ostream.println('^');
                } else {
                    ostream.println("Miracle: error: " + element.getRight());
                }
            }
            System.exit(1);
        }
    }

    void judge(String message) {
        ostream.println(message);
        System.exit(1);
    }

    public void addFatal(String message) {
        container.add(ImmutablePair.of(null, message));
    }
}
