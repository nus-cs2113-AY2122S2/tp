package commands;

import constants.Messages;
import records.Record;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * filter all records by name in the RecordManager that contains keyword to the user.
 */
public class FindCommand extends Command {
    /** Keyword to trigger find command. */
    public static final String COMMAND_WORD = "find";

    /** Help message for find command. */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all records in the RecordManager that contains keyword."
            + "\nExample: " + COMMAND_WORD + " <keyword>";

    private final String keyword;
    /**
     * Executes the command and returns the result.
     *
     * @return Result of the command
     */

    public FindCommand(String keyword){
        this.keyword = keyword;
    }
    @Override
    public CommandResult execute() {
        try {
            List<Record> allRecords = recordMgr.getAllRecords();
            ArrayList<Record> filteredRecords = filter(allRecords);
            return new CommandResult(getMessageForRecordListSummary(filteredRecords), filteredRecords);
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(Messages.MESSAGE_INVALID_RECORD_DISPLAYED_INDEX);
        }
    }
    public ArrayList<Record> filter(List<Record> allRecords){
        return (ArrayList<Record>) allRecords.stream().filter(record -> record.getName().toLowerCase().contains(keyword.toLowerCase())).collect(toList());
    }
}
