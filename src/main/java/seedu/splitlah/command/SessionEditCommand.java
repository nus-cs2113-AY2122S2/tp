package seedu.splitlah.command;

import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Person;
import seedu.splitlah.data.PersonList;
import seedu.splitlah.data.Profile;
import seedu.splitlah.data.Session;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.ui.Message;
import seedu.splitlah.ui.TextUI;

import java.time.LocalDate;
import java.util.logging.Level;

/**
 * Represents a command which edits an existing Session object from a list of sessions managed by the Profile object.
 *
 * @author Roy
 */
public class SessionEditCommand extends Command {

    private static final String COMMAND_SUCCESS = "The session was edited successfully.";
    private static final String COMMAND_NO_EDITS_MADE = "The session was not edited.";

    private final int sessionId;
    private final String sessionName;
    private final String[] personNames;
    private final LocalDate sessionDate;

    /**
     * Initializes a SessionEditCommand object.
     *
     * @param sessionId   An integer that uniquely identifies a session.
     * @param sessionName A String object that represents the session name.
     * @param personNames An array of String objects that represents the involved persons for the session.
     * @param date        A LocalDate object that represents the date of the session.
     */
    public SessionEditCommand(int sessionId, String sessionName, String[] personNames, LocalDate date) {
        assert sessionId > 0 : Message.ASSERT_SESSIONEDIT_SESSION_ID_INVALID;
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.personNames = personNames;
        this.sessionDate = date;
    }

    /**
     * Runs the command to edit an existing Session object from the list of sessions managed by a Manager Object.
     *
     * @param manager A Manager object that manages the TextUI, Profile and Storage object.
     */
    @Override
    public void run(Manager manager) {
        TextUI ui = manager.getUi();
        Profile profile = manager.getProfile();
        Session session;
        try {
            session = profile.getSession(sessionId);
        } catch (InvalidDataException invalidDataException) {
            ui.printlnMessageWithDivider(invalidDataException.getMessage());
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_PROFILE_SESSION_NOT_IN_LIST);
            return;
        }
        assert session != null : Message.ASSERT_SESSIONEDIT_SESSION_IS_NULL;
        PersonList newPersonList = null;
        boolean isPersonNamesEdited = false;
        if (personNames != null) {
            boolean hasDuplicates = PersonList.hasNameDuplicates(personNames);
            if (hasDuplicates) {
                ui.printlnMessage(Message.ERROR_PERSONLIST_DUPLICATE_NAME_IN_SESSION);
                Manager.getLogger().log(Level.FINEST, Message.LOGGER_PERSONLIST_NAME_DUPLICATE_EXISTS_IN_EDITSESSION);
                return;
            }
            newPersonList = new PersonList(personNames);
            if (personNames.length != newPersonList.getSize()) {
                ui.printlnMessage(Message.ERROR_PERSONLIST_CONTAINS_INVALID_NAME);
                Manager.getLogger().log(Level.FINEST,Message.LOGGER_PERSONLIST_INVALID_NAME_EXISTS_IN_EDITSESSION);
                return;
            }
            if (!newPersonList.isSuperset(session.getPersonArrayList())) {
                ui.printlnMessageWithDivider(Message.ERROR_SESSIONEDIT_INVALID_PERSONLIST);
                return;
            }
            if (!session.getPersonList().isSuperset(newPersonList.getPersonList())) {
                isPersonNamesEdited = true;
            }
        }
        boolean isSessionNameEdited = false;
        if (sessionName != null) {
            boolean isSessionExists = profile.hasSessionName(sessionName);
            boolean hasSameSessionName = sessionName.equalsIgnoreCase(session.getSessionName());
            if (!hasSameSessionName && isSessionExists) {
                ui.printlnMessage(Message.ERROR_PROFILE_DUPLICATE_SESSION);
                return;
            }
            if (!hasSameSessionName) {
                isSessionNameEdited = true;
            }
        }
        boolean isSessionDateEdited = false;
        if (sessionDate != null) {
            if (!session.getDateCreated().equals(sessionDate)) {
                session.setDateCreated(sessionDate);
                isSessionDateEdited = true;
            }
        }
        if (isPersonNamesEdited) {
            for (Person person : newPersonList.getPersonList()) {
                session.addPerson(person);
            }
        }
        if (isSessionNameEdited) {
            session.setSessionName(sessionName);
        }
        manager.saveProfile();
        if (isPersonNamesEdited || isSessionNameEdited || isSessionDateEdited) {
            ui.printlnMessageWithDivider(COMMAND_SUCCESS + "\n" + session);
        } else {
            ui.printlnMessageWithDivider(COMMAND_NO_EDITS_MADE);
        }
        Manager.getLogger().log(Level.FINEST, Message.LOGGER_SESSIONEDIT_SESSION_EDITED);
    }
}
