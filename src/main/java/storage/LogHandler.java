package storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogHandler {
    public static final String LOGS_DIRECTORY_NAME = "werkItLogs";
    public static final String LOGS_FILENAME = "logs.log";

    private static FileHandler logFileHandler;
    private static String workingDirectory = System.getProperty(FileManager.USER_WORKING_DIRECTORY_PROPERTY);
    private static Path logFileDirectory = Paths.get(workingDirectory, LOGS_DIRECTORY_NAME);
    private static Path logFilePath = Paths.get(workingDirectory, LOGS_DIRECTORY_NAME, LOGS_FILENAME);

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

    public static void linkToFileLogger(Logger logger) {
        logger.addHandler(logFileHandler);
    }

    public static void checkAndCreateLogsDirectory() throws IOException {
        if (!Files.exists(logFileDirectory)) {
            Files.createDirectory(logFileDirectory);
        }
    }
}
