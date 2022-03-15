package seedu.duke.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainLogger {
    private static final Logger logger = Logger.getLogger("Main");

    public static void logInfo(String msg) {
        logger.log(Level.INFO, msg);
    }
}
