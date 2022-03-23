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

    /**
     * Implement equals for JUnit comparisons
     * Source: https://www.geeksforgeeks.org/overriding-equals-method-in-java/
     * @param o Any object
     * @return If two commands are the same
     */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of IncorrectCommand or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof IncorrectCommand)) {
            return false;
        }

        // typecast o to IncorrectCommand so that we can compare data members
        IncorrectCommand c = (IncorrectCommand) o;

        // Compare the data members and return accordingly
        return this.feedbackToUser.equals(c.feedbackToUser);
    }

}
