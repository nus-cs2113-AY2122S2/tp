package commands;

import exception.IllegalValueException;

import manager.ExpenseManager;

import records.Product;
import records.Record;
import records.Subscription;

/**
 * Adds a record to the RecordManager.
 */
public class AddCommand extends Command {
    /** Keyword to trigger add command. */
    public static final String COMMAND_WORD = "add";

    /** Help message for add command. */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a record to the RecordManager.\n"
            + "Parameters: i/ITEM_NAME p/PRICE d/DATE t/CATEGORY (if product) r/RENEWAL (if subscription)\n"
            + "Example: " + COMMAND_WORD
            + " product i/handphone cover p/$10 t/accessory d/14022022\n"
            +  "Example: " + COMMAND_WORD
            + " subscription i/Netflips p/$10 d/14022022 r/14032022";

    /** Success message for add command. */
    private static final String MESSAGE_SUCCESS = "New record added: %1$s";

    /** Record to add to the list of records. */
    private Record toAdd;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public void AddProductCommand(String name, double price,
                      String date, String productType) throws IllegalValueException {
        this.toAdd = new Product(name, price, date, productType);
        ExpenseManager.addToExpense(price);
    }

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public void AddSubscriptionCommand(String name, double price,
                             String date, String renewal) throws IllegalValueException {
        this.toAdd = new Subscription(name, price, date, renewal);
        ExpenseManager.addToExpense(price);
    }

    /**
     * Executes the command and returns the result.
     *
     * @return Result of the command
     */
    @Override
    public CommandResult execute() {
        recordMgr.addRecord(toAdd);

        String newTotalExpense = "\nTotal expense: " + ExpenseManager.getTotalExpense();

        if (limitMgr.isExceedLimit(ExpenseManager.getTotalExpense())) {
            String warning = "\nWARNING: You have exceeded the spending limit of " + limitMgr.getLimit() + "!!";
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd) + newTotalExpense + warning);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd) + newTotalExpense);
    }
}
