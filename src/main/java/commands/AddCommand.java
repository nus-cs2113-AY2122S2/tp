package commands;

import data.exception.IllegalValueException;
import data.exception.DuplicateDataException;
import data.record.Record;
import data.record.RecordList;

/**
 * Adds a record to the RecordManager.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a record to the RecordManager. "
            + "Parameters: add i/ITEM_NAME p/PRICE c/CATEGORY d/DATE\n"
            + "Example: " + COMMAND_WORD
            + " i/handphone cover p/$10 c/accessory d/14022022";

    public static final String MESSAGE_SUCCESS = "New record added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This record already exists in the RecordManager";

    private final Record toAdd;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public AddCommand(String name, double price, String category,
                      String date) throws IllegalValueException {
        this.toAdd = new Record(name, price, category, date);
    }

    public Record getRecord() {
        return toAdd;
    }

    @Override
    public CommandResult execute() {
        recordMgr.addRecord(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

}
