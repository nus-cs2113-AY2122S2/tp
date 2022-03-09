package seedu.splitlah.command;

import seedu.splitlah.data.Manager;

/**
 * Represents a command that displays the details of each Session object within a Profile object.
 *
 * @author Roy
 */
public class SessionListCommand extends Command {

    public static final String COMMAND_TEXT = "session /list";

    private static final String COMMAND_FORMAT = "Syntax: session /list";

    /**
     * Prints the list of sessions previously created by the user.
     *
     * @param manager A manager object that gets the TextUI and Profile object to print the list of sessions.
     */
    @Override
    public void run(Manager manager) {

    }
}
