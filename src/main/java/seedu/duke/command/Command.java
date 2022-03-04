package seedu.duke.command;

import seedu.duke.data.Profile;
import seedu.duke.ui.TextUI;

public abstract class Command {
    public abstract void run(TextUI ui, Profile profile);
}