package com.miracle;

import com.miracle.cstree.MiracleSourcePosition;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

public class MiracleExceptionContainer {
    private final PrintStream ostream;
    private List<ImmutablePair<MiracleSourcePosition, String>> container = new LinkedList<>();

    MiracleExceptionContainer(PrintStream ostream) {
        this.ostream = ostream;
    }

    static RuntimeException getRuntimeException(String message) throws RuntimeException {
        return new RuntimeException("Miracle: FATAL Error: " + message);
    }

    public void add(String message, MiracleSourcePosition position) {
        container.add(ImmutablePair.of(position, message));
    }

    void judge() {
        if (!container.isEmpty()) {
            container.sort((x, y) -> {
                MiracleSourcePosition xPosition = x.getLeft();
                MiracleSourcePosition yPosition = y.getLeft();
                if (xPosition.row < yPosition.row) return -1;
                if (xPosition.row > yPosition.row) return 1;
                if (xPosition.column < yPosition.column) return -1;
                if (xPosition.column > yPosition.column) return 1;
                return 0;
            });
            container.forEach((element) ->
                    ostream.println("Miracle: Error: " + element.getLeft().toPrintableString()
                            + ": " + element.getRight())
            );
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
