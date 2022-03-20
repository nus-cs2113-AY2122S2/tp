package seedu.splitlah.command;

import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Person;
import seedu.splitlah.data.Session;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.ui.Message;

import java.time.LocalDate;
import java.util.ArrayList;
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
     * Converts a String object array of names to a list of Person objects.
     *
     * @return An ArrayList of Person objects.
     */
    private ArrayList<Person> convertToListOfPerson() {
        ArrayList<Person> personList = new ArrayList<>();
        for (String name : personNames) {
            Person newPerson = new Person(name);
            personList.add(newPerson);
        }
        return personList;
    }

    /**
     * Merges two ArrayList of persons into one ArrayList of persons.
     *
     * @param personList      An ArrayList of Person objects.
     * @param groupPersonList An Arraylist of Person objects from Group object.
     * @return  An ArrayList of Person objects.
     */
    private ArrayList<Person> mergeListOfPersons(ArrayList<Person> personList, ArrayList<Person> groupPersonList) {
        ArrayList<Person> mergedList = personList;
        for (Person person : groupPersonList) {
            if (!mergedList.contains(person)) {
                mergedList.add(person);
            }
        }
        return mergedList;
    }

    /**
     * Checks if String object array of names has duplicated names.
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
        assert commandArgs != null : Message.ASSERT_PARSER_COMMAND_ARGUMENTS_EMPTY;
        assert commandArgs != null : Message.ASSERT_PARSER_COMMAND_ARGUMENTS_NULL;
        try {
            String parsedSessionName = Parser.parseName(commandArgs);
            String[] parsedNames = Parser.parsePersonList(commandArgs);
            LocalDate parsedSessionDate = Parser.parseLocalDate(commandArgs);

            return new SessionCreateCommand(parsedSessionName, parsedNames, parsedSessionDate);
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
        boolean hasDuplicates = hasNameDuplicates();
        if (hasDuplicates) {
            manager.getUi().printlnMessage(Message.ERROR_PROFILE_DUPLICATE_NAME);
            return;
        }

        // TODO: Check if string[] names are actual names.
        ArrayList<Person> personList = convertToListOfPerson();

        boolean isSessionExists = manager.getProfile().hasSessionName(sessionName);
        if (isSessionExists) {
            manager.getUi().printlnMessage(Message.ERROR_PROFILE_DUPLICATE_SESSION);
            return;
        }
        int newSessionId = manager.getProfile().getNewSessionId();
        Session newSession = new Session(sessionName, newSessionId, sessionDate, personList);
        manager.getProfile().addSession(newSession);
        manager.getUi().printlnMessageWithDivider(COMMAND_SUCCESS + newSession);
        manager.getLogger().log(Level.FINEST,Message.LOGGER_SESSIONCREATE_SESSION_ADDED + newSessionId);
    }
}
