package commands;

import records.Record;

import java.util.List;

public class SummaryCommand extends Command {
    /** Keyword to trigger list command. */
    public static final String COMMAND_WORD = "summary";

    /** Help message for list command. */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows all records based on their type."
            + "\nExample: " + COMMAND_WORD;

    /**
     * Executes the command and returns the result.
     *
     * @return Result of the command
     */
    @Override
    public CommandResult execute() {
        List<Record> allRecords = recordMgr.getAllRecords();
        return new CommandResult(getMessageForRecordListSummary(allRecords), allRecords);
    }
}
