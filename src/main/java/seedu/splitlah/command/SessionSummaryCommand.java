package seedu.splitlah.command;

import seedu.splitlah.data.Manager;
import seedu.splitlah.exceptions.InvalidFormatException;

/**
 * Represents a command object that when run, produces a summary of expenditure for that session.
 *
 * @author Warren
 */
public class SessionSummaryCommand extends Command {
    public static final String COMMAND_TEXT = "session /summary";

    public static final String COMMAND_FORMAT = "Syntax: session /summary /sid <SESSIONID>";

    private int sessionId;

    // MISC CONSTANTS
    private static final int ZERO_INDEXING_OFFSET = 1;
    private static final double SMALL_DIFFERENCE_LIMIT = 0.0001;

    public static Command prepare(String commandArgs) {
        try {
            int sessionId = Parser.parseSessionId(commandArgs);
            return new SessionSummaryCommand(sessionId);
        } catch (InvalidFormatException exception) {
            String invalidCommandMessage = exception.getMessage() + "\n" + COMMAND_FORMAT;
            return new InvalidCommand(invalidCommandMessage);
        }
    }

    private static boolean isDifferenceSmall(double cost1, double cost2) {
        return Math.abs(cost1 - cost2) <= SMALL_DIFFERENCE_LIMIT;
    }
    
    private static boolean isValueSmall(double cost) {
        return Math.abs(cost) <= SMALL_DIFFERENCE_LIMIT;
    }

    /**
     * Runs the command with the session identifier as provided by the user input and prints a
     * summary of expenditure for the session specified by the session identifier.
     *
     * @param manager A Manager object that manages the TextUI and Profile object.
     */
    @Override
    public void run(Manager manager) {
        
    }

    /**
     * Default constructor, sets sessionId as specified by the provided value.
     * 
     * @param sessionId The session identifier number that uniquely identifies a session.
     */
    public SessionSummaryCommand(int sessionId) {
        this.sessionId = sessionId;
    }
}
