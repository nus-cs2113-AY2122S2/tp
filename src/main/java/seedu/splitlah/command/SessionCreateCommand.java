package seedu.splitlah.command;

import seedu.splitlah.data.Group;
import seedu.splitlah.data.Manager;
import seedu.splitlah.data.PersonList;
import seedu.splitlah.data.Session;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.ui.Message;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

/**
 * Represents a command that creates a Session object from user input and stores it in the Profile object.
 *
 * @author Roy
 */
public class SessionCreateCommand extends Command {

    public static final String COMMAND_TEXT = "session /create";

    public static final String COMMAND_FORMAT =
            "Syntax: session /create /n [SESSION_NAME] /d [SESSION_DATE] /pl [NAME1 NAME2 …] "
                    + "[</gid GROUP_ID>]";

    private static final String COMMAND_SUCCESS =
            "The session was created successfully.\n";

    public static final String[] COMMAND_DELIMITERS = { 
        Parser.NAME_DELIMITER,
        Parser.DATE_DELIMITER,
        Parser.PERSON_LIST_DELIMITER,
        Parser.GROUP_ID_DELIMITER
    };

    private String sessionName;
    private String[] personNames;
    private LocalDate sessionDate;
    private int groupId;

    /**
     * Initializes a SessionCreateCommand.
     *
     * @param sessionName A String object that represents the session name.
     * @param personNames A String object array that represents the involved persons for the session.
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
     * Checks if String array object of names has duplicated names.
     *
     * @return true if it contains duplicates, false otherwise.
     */
    private boolean hasNameDuplicates() {
        Set<String> nameSet = new HashSet<>();
        for (String name : personNames) {
            String nameToBeAdded = name.toLowerCase();
            if (!nameSet.add(nameToBeAdded)) {
                return true;
            }
        }
        assert nameSet.size() == personNames.length :
                Message.ASSERT_SESSIONCREATE_NAME_DUPLICATE_EXISTS_BUT_NOT_DETECTED;
        return false;
    }

    /**
     * Prepares user arguments for the creation of a SessionCreateCommand object.
     *
     * @param commandArgs A String object that represents the user's arguments.
     * @return A SessionCreateCommand object if session name, session date and person list were found in user arguments,
     *         an InvalidCommand object otherwise.
     */
    public static Command prepare(String commandArgs) {
        assert commandArgs != null : Message.ASSERT_PARSER_COMMAND_ARGUMENTS_NULL;

        boolean hasPersonListDelimiter = false;
        String [] parsedNames = null;
        try {
            parsedNames = Parser.parsePersonList(commandArgs);
            hasPersonListDelimiter = true;
        } catch (InvalidFormatException formatException) {
            if (!formatException.getMessage().equalsIgnoreCase(Message.ERROR_PARSER_DELIMITER_NOT_FOUND
                    + Parser.PERSON_LIST_DELIMITER)) {
                String invalidCommandMessage = formatException.getMessage() + "\n" + COMMAND_FORMAT;
                return new InvalidCommand(invalidCommandMessage);
            }
        }

        boolean hasGroupIdDelimiter = false;
        int groupId = -1;
        try {
            groupId = Parser.parseGroupId(commandArgs);
            hasGroupIdDelimiter = true;
        } catch (InvalidFormatException formatException) {
            if (!formatException.getMessage().equalsIgnoreCase(Message.ERROR_PARSER_DELIMITER_NOT_FOUND
                    + Parser.GROUP_ID_DELIMITER)) {
                String invalidCommandMessage = formatException.getMessage() + "\n" + COMMAND_FORMAT;
                return new InvalidCommand(invalidCommandMessage);
            }
        }

        boolean isMissingBothDelimiters = !hasPersonListDelimiter && !hasGroupIdDelimiter;
        if (isMissingBothDelimiters) {
            String invalidCommandMessage = Message.ERROR_SESSIONCREATE_MISSING_PERSONLIST_AND_GROUP_DELIMITERS + "\n"
                    + COMMAND_FORMAT;
            return new InvalidCommand(invalidCommandMessage);
        }

        try {
            String parsedSessionName = Parser.parseName(commandArgs);
            LocalDate parsedSessionDate = Parser.parseLocalDate(commandArgs);
            return new SessionCreateCommand(parsedSessionName, parsedNames, parsedSessionDate, groupId);
        } catch (InvalidFormatException formatException) {
            String invalidCommandMessage = formatException.getMessage() + "\n" + COMMAND_FORMAT;
            return new InvalidCommand(invalidCommandMessage);
        }
    }

    /**
     * Runs the command to create a Session object to be stored in the list of sessions managed by the Profile Object.
     * Checks if array of names has duplicates and if session name exists.
     * If check fails, no session is created and prints error message.
     * Else a session is created and prints success message.
     *
     * @param manager A Manager object that manages the TextUI and Profile object.
     */
    @Override
    public void run(Manager manager) {
        PersonList personList = new PersonList();
        if (personNames != null) {
            boolean hasDuplicates = hasNameDuplicates();
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

        boolean isSessionExists = manager.getProfile().hasSessionName(sessionName);
        if (isSessionExists) {
            manager.getUi().printlnMessage(Message.ERROR_PROFILE_DUPLICATE_SESSION);
            return;
        }
        int newSessionId = manager.getProfile().getNewSessionId();
        Session newSession = new Session(sessionName, newSessionId, sessionDate, personList, group);
        manager.getProfile().addSession(newSession);
        manager.getUi().printlnMessageWithDivider(COMMAND_SUCCESS + newSession);
        Manager.getLogger().log(Level.FINEST,Message.LOGGER_SESSIONCREATE_SESSION_ADDED + newSessionId);
    }
}
