package seedu.duke.loggers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MainLogger {
    private static MainLogger instance = null;
    private Logger logger;

    private MainLogger() {
        try {
            logger = Logger.getLogger("R.I.P.");
            Files.createDirectories(Path.of("logs"));
            FileHandler handler = new FileHandler(Path.of("logs", "application.log").toString(), true);
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
            logger.setUseParentHandlers(false);

        } catch (Exception e) {
            logWarning(this, "Unable to create logger!");
        }
    }

    private static MainLogger getInstance() {
        if (instance == null) {
            instance = new MainLogger();
        }
        return instance;
    }

    public static void logInfo(Object o, String msg) {
        getInstance().logger.log(Level.INFO, String.format("%s: %s", o.getClass().getSimpleName(), msg));
    }

    public static void logWarning(Object o, String msg) {
        getInstance().logger.log(Level.WARNING, String.format("%s: %s", o.getClass().getSimpleName(), msg));
    }
}
