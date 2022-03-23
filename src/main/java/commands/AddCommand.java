package commands;

import exception.IllegalValueException;
import records.Product;
import records.Record;
import records.Subscription;


/**
 * Adds a record to the RecordManager.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a record to the RecordManager.\n"
            + "Parameters: i/ITEM_NAME p/PRICE d/DATE t/CATEGORY (if product) r/RENEWAL (if subscription)\n"
            + "Example: " + COMMAND_WORD
            + " product i/handphone cover p/$10 t/accessory d/14022022\n"
            +  "Example: " + COMMAND_WORD
            + " subscription i/Netflips p/$10 d/14022022 r/14032022";

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

    @Override
    public CommandResult execute() {
        System.out.println("||Total expense: " + totalExpense);
        if (limitMgr.ifExceedLimit(super.totalExpense)) limitMgr.displayWarning();
        recordMgr.addRecord(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

}
