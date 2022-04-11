package seedu.splitlah.command;

import seedu.splitlah.data.Group;
import seedu.splitlah.data.Manager;
import seedu.splitlah.data.PersonList;
import seedu.splitlah.data.Profile;
import seedu.splitlah.data.Session;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.ui.Message;
import seedu.splitlah.ui.TextUI;

import java.time.LocalDate;
import java.util.logging.Level;

/**
 * Represents a command that creates a Session object from user input and stores it in the Profile object.
 *
 * @author Roy
 */
public class SessionCreateCommand extends Command {

    private static final String COMMAND_SUCCESS = "The session was created successfully.";
  
    private final String sessionName;
    private final String[] personNames;
    private final LocalDate sessionDate;
    private final int groupId;

    /**
     * Initializes a SessionCreateCommand object.
     *
     * @param sessionName A String object that represents the session name.
     * @param personNames An array of String objects that represents the involved persons for the session.
     * @param date        A LocalDate object that represents the date of the session.
     * @param groupId     An integer that represents the group unique identifier.
     */
    public SessionCreateCommand(String sessionName, String[] personNames, LocalDate date, int groupId) {
        assert sessionName != null : Message.ASSERT_SESSIONCREATE_SESSION_NAME_NULL;
        assert personNames != null || groupId != -1 :
                Message.ASSERT_SESSIONCREATE_MISSING_PERSONLIST_AND_GROUP_DELIMITERS;
        assert date != null : Message.ASSERT_SESSIONCREATE_SESSION_DATE_NULL;
        this.sessionName = sessionName;
        this.personNames = personNames;
        this.sessionDate = date;
        this.groupId = groupId;
    }

    /**
     * Runs the command to create a Session object to be stored in the list of sessions managed by the Profile object.
     * Checks if array of names has duplicates and if session name exists.
     * If check fails, no session is created and prints error message.
     * Else a session is created and prints success message.
     *
     * @param manager A Manager object that manages the TextUI, Profile and Storage object.
     */
    @Override
    public void run(Manager manager) {
        TextUI ui = manager.getUi();
        Profile profile = manager.getProfile();

        PersonList personList = new PersonList();
        if (personNames != null) {
            boolean hasDuplicates = PersonList.hasNameDuplicates(personNames);
            if (hasDuplicates) {
                ui.printlnMessage(Message.ERROR_PERSONLIST_DUPLICATE_NAME_IN_SESSION);
                Manager.getLogger().log(Level.FINEST,Message.LOGGER_PERSONLIST_NAME_DUPLICATE_EXISTS_IN_CREATESESSION);
                return;
            }
            personList = new PersonList(personNames);
            if (personNames.length != personList.getSize()) {
                ui.printlnMessage(Message.ERROR_PERSONLIST_CONTAINS_INVALID_NAME);
                Manager.getLogger().log(Level.FINEST,Message.LOGGER_PERSONLIST_INVALID_NAME_EXISTS_IN_CREATESESSION);
                return;
            }
        }

        Group group = null;
        if (groupId != -1) {
            try {
                group = profile.getGroup(groupId);
                personList.mergeListOfPersons(group.getPersonList());
            } catch (InvalidDataException dataException) {
                ui.printlnMessage(dataException.getMessage());
                return;
            }
        }

        boolean isSessionExists = profile.hasSessionName(sessionName);
        if (isSessionExists) {
            ui.printlnMessage(Message.ERROR_PROFILE_DUPLICATE_SESSION);
            Manager.getLogger().log(Level.FINEST,Message.LOGGER_SESSIONCREATE_DUPLICATE_NAMES_IN_SESSION_LIST);
            return;
        }

        int newSessionId = profile.getNewSessionId();
        Session newSession = new Session(sessionName, newSessionId, sessionDate, personList, group);
        profile.addSession(newSession);
        manager.saveProfile();
        ui.printlnMessageWithDivider(COMMAND_SUCCESS + "\n" + newSession);
        Manager.getLogger().log(Level.FINEST,Message.LOGGER_SESSIONCREATE_SESSION_ADDED + newSessionId);
    }
}
