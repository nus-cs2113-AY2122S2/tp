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

/**
 * Represents a command that creates a Session object from user input and stores it in the Profile object.
 *
 * @author Roy
 */
public class SessionCreateCommand extends Command {

    public static final String COMMAND_TEXT = "session /create";

    private static final String COMMAND_FORMAT =
            "Syntax: session /create /n <SESSIONNAME> /d <SESSIONDATE> /pl <NAME1 NAME2…>";

    private static final String COMMAND_SUCCESS =
            "The session was created successfully with session id of: ";

    private String sessionName;
    private String[] personNames;
    private LocalDate sessionDate;
    
    public SessionCreateCommand(String sessionName, String[] personNames, LocalDate date) {
        this.sessionName = sessionName;
        this.personNames = personNames;
        this.sessionDate = date;
    }

    /**
     * Converts list of names to a list of Person objects.
     *
     * @param personArray An array of person names
     * @return An arraylist of person objects
     */
    private static ArrayList<Person> convertToListOfPerson(String[] personArray) {
        ArrayList<Person> personList = new ArrayList<>();
        for (String name : personArray) {
            Person newPerson = new Person(name);
            personList.add(newPerson);
        }
        return personList;
    }

    /**
     * Checks to see if list of person has duplicate names.
     *
     * @return True if it contains duplicates, false otherwise.
     */
    private boolean hasNameDuplicates() {
        Set<String> nameSet = new HashSet<>();
        for (String name : personNames) {
            String nameToBeAdded = name.toLowerCase();
            // TODO: Check if string is an actual name.
            if (!nameSet.add(nameToBeAdded)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prepares user arguments for session create command.
     *
     * @param commandArgs The user's arguments.
     * @return A SessionCreateCommand object if session name, session date and person list were found in user arguments,
     *         an InvalidCommand object otherwise.
     */
    public static Command prepare(String commandArgs) {
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
     * Runs the command to create a session.
     * Checks if list of names has duplicates and if session name exists.
     * If check fails, no session is created and prints error message.
     * Else a session is created and prints success message.
     *
     * @param manager A Manager object that manages the TextUI and Profile object.
     */
    @Override
    public void run(Manager manager) {
        if (hasNameDuplicates()) {
            manager.getUi().printlnMessage(Message.ERROR_PROFILE_DUPLICATE_NAME);
            return;
        }
        ArrayList<Person> personList = convertToListOfPerson(this.personNames);

        boolean isSessionExists = manager.getProfile().hasSessionName(this.sessionName);
        if (isSessionExists) {
            manager.getUi().printlnMessage(Message.ERROR_PROFILE_DUPLICATE_SESSION);
            return;
        }

        int newSessionId = manager.getProfile().getNewSessionId();
        Session newSession = new Session(this.sessionName, newSessionId, this.sessionDate, personList);
        manager.getProfile().addSession(newSession);
        manager.getUi().printlnMessage(COMMAND_SUCCESS + newSessionId);
    }
}
