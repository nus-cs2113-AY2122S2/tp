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
        boolean isSessionExist = manager.getProfile().hasSessionId(sessionId);
        if (!isSessionExist) {
            manager.getUi().printlnMessage(Message.ERROR_PROFILE_SESSION_NOT_IN_LIST);
            return;
        }

        PersonList personList = new PersonList();
        if (personNames != null) {
            boolean hasDuplicates = PersonList.hasNameDuplicates(personNames);
            if (hasDuplicates) {
                manager.getUi().printlnMessage(Message.ERROR_PROFILE_DUPLICATE_NAME);
                return;
            }
            personList.convertToPersonList(personNames);
        }
        Group group = null;
        if (groupId != -1) {
            try {
                group = manager.getProfile().getGroup(groupId);
                personList.mergeListOfPersons(group.getPersonList());
            } catch (InvalidDataException dataException) {
                manager.getUi().printlnMessage(dataException.getMessage());
                return;
            }
        }
        try {
            manager.getProfile().removeSession(sessionId);
            Session newSession = new Session(sessionName, sessionId, sessionDate, personList, group);
            manager.getProfile().addSession(newSession);
            manager.saveProfile();
            manager.getUi().printlnMessageWithDivider(COMMAND_SUCCESS + "\n" + newSession);
            Manager.getLogger().log(Level.FINEST,Message.LOGGER_SESSIONEDIT_SESSION_EDITED + sessionId);
        } catch (InvalidDataException invalidDataException) {
            manager.getUi().printlnMessage(invalidDataException.getMessage());
        }
    }
}
