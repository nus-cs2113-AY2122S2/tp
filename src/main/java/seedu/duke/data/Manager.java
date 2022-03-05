package seedu.duke.data;

import seedu.duke.ui.TextUI;

/**
 * Represents a manager that manages the UI and Profile objects of the application.
 */
public class Manager {
    private TextUI ui;
    private Profile profile;

    /**
     * Constructor to create a Manager object.
     */
    public Manager() {
        ui = new TextUI();
        profile = new Profile();
    }

    /**
     * Returns a TextUI object for user inputs and outputs.
     * @return A TextUI object.
     */
    public TextUI getUi() {
        return ui;
    }

    /**
     * Returns a Profile object that stores user created sessions.
     * @return
     */
    public Profile getProfile() {
        return profile;
    }
}
