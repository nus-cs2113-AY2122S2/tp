package seedu.splitlah.command;

import seedu.splitlah.data.Group;
import seedu.splitlah.data.Manager;
import seedu.splitlah.data.PersonList;
import seedu.splitlah.data.Session;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.ui.Message;

import java.time.LocalDate;
import java.util.logging.Level;

/**
 * Represents a command which edits an existing Session object from a list of sessions managed by the Profile object.
 *
 * @author Roy
 */
public class SessionEditCommand extends Command {

    private static final String COMMAND_SUCCESS = "The session was edited successfully.";

    private final int sessionId;
    private final String sessionName;
    private final String[] personNames;
    private final LocalDate sessionDate;
    private final int groupId;

    public SessionEditCommand(int sessionId, String sessionName, String[] personNames, LocalDate date, int groupId) {
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.personNames = personNames;
        this.sessionDate = date;
        this.groupId = groupId;
    }

    /**
     * Runs the command to edit an existing Session object from the list of sessions managed by a Manager Object.
     *
     * @param manager A Manager object that manages the TextUI, Profile and Storage object.
     */
    @Override
    public void run(Manager manager) {

    }
}
