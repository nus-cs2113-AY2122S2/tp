package seedu.splitlah.data;

import seedu.splitlah.storage.Storage;
import seedu.splitlah.ui.Message;
import seedu.splitlah.ui.TextUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a manager that manages the UI and Profile objects of the application.
 */
public class Manager {
    
    private TextUI ui;
    private Profile profile;
    private Storage storage;
    private boolean isUsingStorage = false;

    private static final String LOGGER_FILE_NAME = "SplitLah";
    public static Logger logger = Logger.getLogger(LOGGER_FILE_NAME);

    /**
     * Constructor to create a Manager object.
     * Should only be called in Junit test files.
     */
    public Manager() {
        ui = new TextUI();
        profile = new Profile();
    }

    /**
     * Constructor to create a Manager object with Storage enabled.
     *
     * @param isUsingStorage A boolean to enable usage of Storage object.
     */
    public Manager(boolean isUsingStorage) {
        ui = new TextUI();
        this.isUsingStorage = isUsingStorage;
        if (isUsingStorage) {
            initializeStorage();
        }
    }

    /**
     * Initializes the Storage object to be managed by Manager object.
     * Checks if save file can be found or created before retrieving data from the save file.
     */
    private void initializeStorage() {
        storage = new Storage();
        boolean isDirectoryCreated = storage.hasDataDirectory();
        boolean isFileCreated = storage.hasDataFile();
        if (!isDirectoryCreated || !isFileCreated) {
            ui.printlnMessage(Message.ERROR_STORAGE_PATH_LOCATION_CREATION_FAILED);
            profile = new Profile();
            isUsingStorage = false;
        } else {
            loadFileSave();
        }
    }

    /**
     * Loads the data from the save file into the Profile object.
     */
    private void loadFileSave() {
        try {
            storage.loadStorage();
        } catch (IOException ioException) {
            logger.log(Level.FINEST, Message.LOGGER_STORAGE_FILE_ERROR);
            ui.printlnMessage(Message.ERROR_STORAGE_FILE_NOT_FOUND);
        } catch (ClassNotFoundException classNotFoundException) {
            logger.log(Level.FINEST,Message.LOGGER_STORAGE_CLASS_NOT_FOUND);
            ui.printlnMessage(Message.ERROR_STORAGE_CLASS_EXCEPTION_ISSUE);
        }
        profile = storage.getProfile();
    }

    /**
     * Saves the Profile object into the storage file.
     */
    public void saveProfile() {
        if (isUsingStorage) {
            try {
                storage.saveProfileToFile(profile);
            } catch (IOException exception) {
                ui.printlnMessage(Message.ERROR_STORAGE_DATA_NOT_SAVED);
            }
        }
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
