package commands;

public class LimitCommand extends Command {

    public static final String COMMAND_WORD = "set";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Set a total spending limit for a month.\n"
            + "Parameters: LIMIT" + System.lineSeparator()
            + "Example: " + COMMAND_WORD + " 500";

    private static final String MESSAGE_SET_LIMIT_SUCCESS = "Limit Set: %1$s";

    private double limit;

    public LimitCommand(double limit) {
        this.limit = limit;
    }

    @Override
    public CommandResult execute() {
        limitMgr.setLimit(limit);
        return new CommandResult(String.format(MESSAGE_SET_LIMIT_SUCCESS, limit));
    }
}
