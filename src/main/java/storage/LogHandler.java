package storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Handles the management of the logs. In particular, it contains methods to create the log file
 * as well as to direct the Logger objects used in the various classes in WerkIt! to write to the
 * log file specified in this class. Note that Logger objects will still need to be instantiated
 * in the classes that needs to be logged.
 */
public class LogHandler {
    public static final String LOGS_DIRECTORY_NAME = "werkItLogs";
    public static final String LOGS_FILENAME = "logs.log";

    private static FileHandler logFileHandler;
    private static String workingDirectory = System.getProperty(FileManager.USER_WORKING_DIRECTORY_PROPERTY);
    private static Path logFileDirectory = Paths.get(workingDirectory, LOGS_DIRECTORY_NAME);
    private static Path logFilePath = Paths.get(workingDirectory, LOGS_DIRECTORY_NAME, LOGS_FILENAME);

    /**
     * Resets the LogManager behaviour to allow logs to be written to the specified log file.
     * In addition, if necessary, creates the directory and file that will contain the logs.
     * This method should be preferably called the moment the program is started (i.e. located
     * at the start of the main method).
     */
    public static void startLogHandler() {
        LogManager.getLogManager().reset();

        try {
            checkAndCreateLogsDirectory();
        } catch (IOException e) {
            System.out.println("Logs directory could not be created. Exiting program...");
            System.exit(-1);
        }

        assert (Files.exists(logFileDirectory)) : "Logs directory does not exist.";

        try {
            logFileHandler = new FileHandler(logFilePath.toString(), true);
            logFileHandler.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            System.out.println("WARNING! Log file could not be opened. You may continue to use the\n"
                    + "program, but any logs generated in this application's session will not be\n"
                    + "logged to the file.");
        }
    }

    /**
     * Directs the Logger object specified as the parameter to use the FileHandler specified in this
     * class as its log handler.
     *
     * @param logger The Logger object to direct it to a handler.
     */
    public static void linkToFileLogger(Logger logger) {
        logger.addHandler(logFileHandler);
    }

    /**
     * Checks if the required directory to store the log file exists. If it does not, the directory
     * will be created.
     *
     * @throws IOException If the required directory cannot be created.
     */
    public static void checkAndCreateLogsDirectory() throws IOException {
        if (!Files.exists(logFileDirectory)) {
            Files.createDirectory(logFileDirectory);
        }
    }
}
