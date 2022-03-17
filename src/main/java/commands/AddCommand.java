package commands;

import data.exception.IllegalValueException;
import data.record.Product;
import data.record.Record;
import data.record.Subscription;

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

    private Record toAdd;

    public AddCommand(){

    }
    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public void AddProductCommand(String name, double price,
                      String date, String productType) throws IllegalValueException {
        this.toAdd = new Product(name, price, date, productType);
        super.totalExpense += price;
    }

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public void AddSubscriptionCommand(String name, double price,
                             String date, String renewal) throws IllegalValueException {
        this.toAdd = new Subscription(name, price, date, renewal);
        super.totalExpense += price;
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
