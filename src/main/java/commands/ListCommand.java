package commands;

import records.Record;

import java.util.List;

/**
 * Lists all records in the RecordManager to the user.
 */
public class ListCommand extends Command {
    /** Keyword to trigger list command. */
    public static final String COMMAND_WORD = "list";

    /** Help message for list command. */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all records in the RecordManager as a list with index numbers."
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
