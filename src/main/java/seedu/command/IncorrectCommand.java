package seedu.command;

/**
 * Represents an incorrect command. Upon execution, provide some feedback to user
 *
 * @author Shun Yao
 */
public class IncorrectCommand extends Command {

    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(feedbackToUser);
    }

    public boolean equals(IncorrectCommand other) {
        return feedbackToUser.equals(other.feedbackToUser);
    }

}
