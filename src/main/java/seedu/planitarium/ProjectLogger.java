package seedu.planitarium;

import ui.UI;

import java.io.IOException;
import java.util.logging.*;

public class ProjectLogger {
    private Logger logger;
    private static final String INIT_ERROR_MESSAGE = "Oops, logger can't be initialised!";

    public ProjectLogger(String className, String fileName) {
        this.logger = Logger.getLogger(className);
        try {
            initialiseLogger(fileName);
        } catch (IOException e) {
            UI ui = new UI();
            ui.printMsg(INIT_ERROR_MESSAGE);
        }
    }

    public Logger getLogger() {
        return this.logger;
    }

    /**
     * Initialize a logger for the program
     * @throws IOException if there's exception for reading or writing.
     */
    private void initialiseLogger(String fileName) throws IOException {
        LogManager.getLogManager().reset();
        this.logger.setLevel(Level.ALL);
        FileHandler fh = new FileHandler(fileName);
        fh.setFormatter(new SimpleFormatter());
        fh.setLevel(Level.ALL);
        this.logger.addHandler(fh);
    }
}
