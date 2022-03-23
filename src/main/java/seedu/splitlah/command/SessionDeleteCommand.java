package seedu.splitlah.command;

import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Session;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.parser.ParserUtils;
import seedu.splitlah.ui.Message;

import java.util.logging.Level;

/**
 * Represents a command that deletes a Session object indicated by the user input from a Profile object.
 *
 * @author Roy
 */
public class SessionDeleteCommand extends Command {

    public static final String COMMAND_TEXT = "session /delete";

    public static final String COMMAND_FORMAT = "Syntax: session /delete /sid [SESSION_ID]";

    private static final String COMMAND_SUCCESS =
            "The session was deleted successfully.";

    public static final String[] COMMAND_DELIMITERS = {
        ParserUtils.SESSION_ID_DELIMITER
    };

    private int sessionId;

    /**
     * Initializes a SessionDeleteCommand object.
     *
     * @param sessionId An integer that uniquely identifies a session.
     */
    public SessionDeleteCommand(int sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Prepares user arguments for the creation of a SessionDeleteCommand object.
     *
     * @param commandArgs A String object that represents the user's arguments.
     * @return A SessionDeleteCommand object if session id was found in user arguments,
     *         an InvalidCommand object otherwise.
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
     * Runs the command to delete a Session object to from the list of sessions managed by the Profile Object.
     *
     * @param manager A Manager object that manages the TextUI and Profile object.
     */
    @Override
    public void run(Manager manager) {
        Session session = null;
        try {
            session = manager.getProfile().getSession(sessionId);
            manager.getProfile().removeSession(session);
            manager.saveProfile();
            manager.getUi().printlnMessageWithDivider(COMMAND_SUCCESS);
            manager.getLogger().log(Level.FINEST, Message.LOGGER_SESSIONDELETE_SESSION_REMOVED + sessionId);
        } catch (InvalidDataException dataException) {
            manager.getUi().printlnMessage(dataException.getMessage());
        }
    }
}
