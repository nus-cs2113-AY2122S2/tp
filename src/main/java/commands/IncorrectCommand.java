package commands;

/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */
public class IncorrectCommand extends Command {
    /** Message to display to user. */
    private final String feedbackToUser;

    /**
     * Construct <code>IncorrectCommand</code> object.
     *
     * @param feedbackToUser Message to display to user
     */
    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    /**
     * Executes the command and returns the result.
     *
     * @return Result of the command
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(feedbackToUser);
    }
}
