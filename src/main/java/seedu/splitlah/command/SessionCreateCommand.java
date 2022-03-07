package seedu.splitlah.command;

import seedu.splitlah.data.Manager;

import java.time.LocalDate;

/**
 * Represents a command that creates a session from user input
 * and stores into the profile object.
 *
 * @author Roy
 */
public class SessionCreateCommand extends Command {

    public static final String COMMAND_TEXT = "session /create";

    private static final String COMMAND_FORMAT = "Syntax: session /create /n <SESSIONNAME> /p <NAME1 NAME2…>";
    private static final String COMMAND_FORMAT = "Syntax: session /create /n <SESSIONNAME> /d <SESSIONDATE> /pl <NAME1 NAME2…>";

    private String sessionName;
    private String[] personNames;
    private LocalDate sessionDate;

    // Javadocs to be completed when implementing command.
    public SessionCreateCommand(String sessionName, String[] personNames, LocalDate date) {
        this.sessionName = sessionName;
        this.personNames = personNames;
        this.sessionDate = date;
    }

    /**
     * Returns the session name stored for session creation.
     *
     * @return A session name as String.
     */
    public String getSessionName() {
        return sessionName;
    }

    /**
     * Returns an array of people's names stored for session creation.
     *
     * @return An array of String for people's name.
     */
    public String[] getPersonNames() {
        return personNames;
    }

    /**
     * Returns the date information stored for session creation.
     *
     * @return A date as a LocalDate object.
     */
    public LocalDate getSessionDate() {
        return sessionDate;
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
     *
     * @param manager A Manager object that manages the TextUI and Profile object.
     */
    @Override
    public void run(Manager manager) {

    }
}
