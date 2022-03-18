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
            + "Parameters: add product i/ITEM_NAME p/PRICE t/CATEGORY d/DATE" + System.lineSeparator()
            + "Example: " + COMMAND_WORD
            + " add product i/handphone cover p/$10 t/accessory d/14022022";

    private static final String MESSAGE_SUCCESS = "New record added: %1$s";

    private Record toAdd;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public void AddProductCommand(String name, double price,
                      String date, String productType) throws IllegalValueException {
        this.toAdd = new Product(name, price, date, productType);
        super.totalExpense += price;
        //if (limitMgr.ifExceedLimit(super.totalExpense)) limitMgr.displayWarning();
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
        //if (limitMgr.ifExceedLimit(super.totalExpense)) limitMgr.displayWarning();
    }

    @Override
    public CommandResult execute() {
        recordMgr.addRecord(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

}
