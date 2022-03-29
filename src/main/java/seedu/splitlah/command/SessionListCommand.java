package seedu.splitlah.command;

import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Session;
import seedu.splitlah.parser.ParserUtils;
import seedu.splitlah.ui.Message;
import seedu.splitlah.ui.TableFormatter;
import seedu.splitlah.ui.TextUI;

import java.util.ArrayList;

/**
 * Represents a command that displays the details of each Session object within a Profile object.
 *
 * @author Roy
 */
public class SessionListCommand extends Command {

    private static final String SESSION_LIST_HEADER = "List of Sessions";

    /**
     * Runs the command to list all existing sessions managed by the Profile Object.
     *
     * @param manager A Manager object that manages the TextUI, Profile and Storage object.
     */
    @Override
    public void run(Manager manager) { 
        TextUI ui = manager.getUi();
        String sessionListSummary = manager.getProfile().getSessionListSummaryString();
        ui.printlnMessage(sessionListSummary);
    }
}
