package seedu.splitlah.data;

import seedu.splitlah.ui.TextUI;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a manager that manages the UI and Profile objects of the application.
 */
public class Manager {
    
    private TextUI ui;
    private Profile profile;
    private static String LOGGER_FILE_NAME = "SplitLah";
    public static Logger logger = Logger.getLogger(LOGGER_FILE_NAME);


    /**
     * Constructor to create a Manager object.
     */
    public Manager() {
        ui = new TextUI();
        profile = new Profile();
    }

    /**
     * Returns a TextUI object for user inputs and outputs.
     *
     * @return A TextUI object.
     */
    public TextUI getUi() {
        return ui;
    }

    /**
     * Returns a Profile object that stores user created sessions.
     *
     * @return A Profile object.
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Returns a Logger object to records logs.
     *
     * @return A Logger object.
     */
    public static Logger getLogger() {
        return logger;
    }
}
