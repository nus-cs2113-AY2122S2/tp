package seedu.splitlah.command;

import seedu.splitlah.data.Manager;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;

/**
 * Represents a command that deletes a Session object indicated by the user input from a Profile object.
 *
 * @author Roy
 */
public class SessionDeleteCommand extends Command {

    public static final String COMMAND_TEXT = "session /delete";

    private static final String COMMAND_FORMAT = "Syntax: session /delete /sid <SESSIONID>";

    private int sessionId;

    /**
     * Initializes a SessionDeleteCommand.
     *
     * @param sessionId An integer that uniquely identifies a session.
     */
    public SessionDeleteCommand(int sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Prepares user arguments for session delete command.
     *
     * @param commandArgs The user's arguments.
     * @return A SessionDeleteCommand object if session id was found in user arguments,
     *      an InvalidCommand object otherwise.
     */
    public static Command prepare(String commandArgs) {
        try {
            int sessionId = Parser.parseSessionId(commandArgs);
            return new SessionDeleteCommand(sessionId);
        } catch (InvalidFormatException formatException) {
            String invalidCommandMessage = formatException.getMessage() + "\n" + COMMAND_FORMAT;
            return new InvalidCommand(invalidCommandMessage);
        }
    }

    /**
     * Runs the command to delete a session.
     *
     * @param manager A Manager object that manages the TextUI and Profile object.
     */
    @Override
    public void run(Manager manager) {

    }
}
