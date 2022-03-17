package commands;

import common.Messages;
import data.record.Record;

/**
 * Deletes a record identified using it's last displayed index from the RecordBook.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the record identified by the index number used in the last record listing.\n"
            + "Parameters: INDEX" + System.lineSeparator()
            + "Example: " + COMMAND_WORD + " 1";

    private static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Record: %1$s";

    private final int toDelete;

    public DeleteCommand(int index) {
        toDelete = index;
    }

    @Override
    public CommandResult execute() {
        try {
            recordMgr.deleteRecord(toDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, toDelete));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }
}
