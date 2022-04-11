//@@author teanweijun

package seedu.planitarium;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import seedu.planitarium.global.UI;

public class ProjectLogger {
    private Logger logger;
    private static final String INIT_ERROR_MESSAGE = "Oops, logger can't be initialised!";

    public ProjectLogger(String className, String fileName) {
        logger = Logger.getLogger(className);
        try {
            initialiseLogger(fileName);
        } catch (IOException e) {
            UI ui = new UI();
            ui.printMsg(INIT_ERROR_MESSAGE);
        }
    }

    /**
     * Initialize a logger for the program.
     * 
     * @throws IOException if there's exception for reading or writing.
     */
    private void initialiseLogger(String fileName) throws IOException {
        logger.setUseParentHandlers(false);
        this.logger.setLevel(Level.ALL);
        FileHandler fh = new FileHandler(fileName);
        fh.setFormatter(new SimpleFormatter());
        fh.setLevel(Level.ALL);
        this.logger.addHandler(fh);
    }

    public void log(Level level, String message) {
        logger.log(level, message);
    }
}
