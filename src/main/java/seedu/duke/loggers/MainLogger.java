package seedu.duke.loggers;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainLogger {
    private static final Logger logger = Logger.getLogger("Main");

    public static void logInfo(Object o, String msg) {
        logger.log(Level.INFO, String.format("%s: %s", o.getClass().getSimpleName(), msg));
    }

    public static void logWarning(Object o, String msg) {
        logger.log(Level.WARNING, String.format("%s: %s", o.getClass().getSimpleName(), msg));
    }
}
