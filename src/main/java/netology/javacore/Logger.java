package netology.javacore;

import java.util.ArrayDeque;
import java.util.Deque;

public final class Logger {

    protected static Logger singleton;
    protected Deque<Command> stack;

    private Logger() {
        stack = new ArrayDeque<>();
    }

    public static Logger getInstance() {
        if (singleton == null) {
            singleton = new Logger();
        }
        return singleton;
    }

}
