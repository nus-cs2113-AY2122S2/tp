package seedu.duke.command;

import seedu.duke.data.Profile;
import seedu.duke.ui.TextUI;

/**
 * Represents a generic command that the user has entered into the application
 * that can be run to produce a change or outcome.
 * @author Warren
 */
public abstract class Command {
    public abstract void run(TextUI ui, Profile profile);
}