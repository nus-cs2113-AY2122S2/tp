package commands;

public class LimitCommand extends Command {
    /** Keyword to trigger set limit command. */
    public static final String COMMAND_WORD = "set";

    /** Help message for set limit command. */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Set a total spending limit for a month."
            + "\nParameters: LIMIT"
            + "\nExample: " + COMMAND_WORD + " 500";

    /** Success message for set limit command. */
    private static final String MESSAGE_SET_LIMIT_SUCCESS = "Limit Set: %1$s";

    /** New limit value */
    private double limit;

    /**
     * Constructs a <code>LimitCommand</code> object.
     *
     * @param limit New limit value
     */
    public LimitCommand(double limit) {
        this.limit = limit;
    }

    /**
     * Executes the command and returns the result.
     *
     * @return Result of the command
     */
    @Override
    public CommandResult execute() {
        limitMgr.setLimit(limit);
        return new CommandResult(String.format(MESSAGE_SET_LIMIT_SUCCESS, limit));
    }
}
