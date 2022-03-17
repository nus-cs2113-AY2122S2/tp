package commands;

import data.record.Record;

import java.util.List;


/**
 * Lists all records in the RecordManager to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all records in the RecordManager as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        List<Record> allRecords = recordMgr.getAllRecords();
        return new CommandResult(getMessageForRecordListSummary(allRecords), allRecords);
    }
}
