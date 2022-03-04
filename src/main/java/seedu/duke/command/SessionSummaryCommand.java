package seedu.duke.command;

import seedu.duke.data.Profile;
import seedu.duke.ui.TextUI;

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
     * @param ui      A user interface to which the command will read its input from and print its output to.
     * @param profile A Profile object from which Session, Activity and other objects are used to run the command.
     */
    @Override
    public void run(TextUI ui, Profile profile) {
        
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
