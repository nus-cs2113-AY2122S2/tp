package seedu.duke.data;public class Manager {
package seedu.duke.data;

import seedu.duke.ui.TextUI;

public class Manager {
    private TextUI ui;
    private Profile profile;
    public Manager() {
        ui = new TextUI();
        profile = new Profile();
    }
    public TextUI getUi() {
        return ui;
    }
    public Profile getProfile() {
        return profile;
    }
}
