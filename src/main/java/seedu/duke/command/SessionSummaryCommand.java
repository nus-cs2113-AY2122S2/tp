package seedu.duke.command;

import seedu.duke.data.Manager;

/**
 * Represents a command object that when run, produces a summary of expenditure for that session.
 * 
 * @author Warren
 */
public class SessionSummaryCommand extends Command {
    public static final String COMMAND_TEXT = "session /summary";
    
    public static final String COMMAND_FORMAT = "Syntax: session /summary /sid <SESSIONID>";
    
    private int sessionId;

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
