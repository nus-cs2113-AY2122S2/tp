package commands;

import common.Messages;

/**
 * Deletes a record identified using it's last displayed index from the RecordBook.
 */
public class DeleteCommand extends Command {
    /** Keyword to trigger delete command */
    public static final String COMMAND_WORD = "delete";

    /** Help message for delete command */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the record identified by the index number used in the last record listing.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    /** Success message for delete command */
    private static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Record: %1$s";

    /** Record to be removed from the list of records */
    private final int toDelete;

    /**
     * Command to delete specified record
     *
     * @param index Index (in <code>records</code>) of the record to remove
     */
    public DeleteCommand(int index) {
        toDelete = index;
    }

    /**
     * Executes the command and returns the result.
     *
     * @return Result of the command.
     */
    @Override
    public CommandResult execute() {
        try {
            // updates the total expense
            totalExpense -= recordMgr.getRecordByIndex(toDelete).getPrice();

            recordMgr.deleteRecord(toDelete);

            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, toDelete + 1));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_RECORD_DISPLAYED_INDEX);
        }
    }
}
