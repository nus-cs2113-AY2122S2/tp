package seedu.duke.command.customercommands;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

import seedu.duke.ListContainer;
import seedu.duke.ParsingUtils;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.DuplicateCommandException;
import seedu.duke.exceptions.EmptySatisfactionValueException;
import seedu.duke.exceptions.EmptySatisfactionCustomerException;
import seedu.duke.exceptions.InvalidSatisfactionValueException;
import seedu.duke.exceptions.InvalidSatisfactionCustomerNameException;
import seedu.duke.exceptions.RepeatCustomerException;
import seedu.duke.itemlists.ItemList;
import seedu.duke.satisfactionlists.Satisfaction;
import seedu.duke.satisfactionlists.SatisfactionList;
import seedu.duke.Ui;
import seedu.duke.command.Command;
import seedu.duke.storage.ItemListFileManager;
import seedu.duke.storage.SatisfactionListFileManager;


/**
 * Class that implements execution behavior for adding a Satisfaction
 * object (e.g. when user types "Add Satisfaction [customerName] [satisfactionValue]").
 * Constructor creates the Satisfaction object from user input, and execute
 * method overrides the Command class's execute method to add the Satisfaction
 * object to the given satisfactionList (data structure that stores all the
 * Satisfaction objects). Each Satisfaction object corresponds to a customer and
 * a satisfaction rating from1 to 5 inclusive.
 */

public class AddSatisfactionCommand extends Command {
    private static final String DELIMITER = "/";
    private Satisfaction satisfaction;
    private static Logger logger = Logger.getLogger("satisfactionLogger");
    private static final String ADD_SATISFACTION_COMMAND = "add satisfaction";
    private ParsingUtils parsingUtils = new ParsingUtils();

    /**
     * Extracts the customer name and satisfaction value from user input,
     * uses these two values to create a corresponding Satisfaction object.
     *
     * @param userInput The user input (should be of form "[customerName] [satisfactionValue]").
     * @throws HotelLiteManagerException If there is an error in user input (e.g. empty customer name, empty
     *                                   satisfaction value, or invalid satisfaction value).
     */
    public AddSatisfactionCommand(String userInput) throws HotelLiteManagerException {
        userInput = userInput.toLowerCase();
        if (userInput.contains(ADD_SATISFACTION_COMMAND)) {
            throw new DuplicateCommandException();
        }
        if (!userInput.contains(DELIMITER)) {
            logger.log(Level.WARNING, "A '/' character is needed to separate the customer's name "
                    + "from their rating.");
            throw new InvalidCommandException();
        }
        if (parsingUtils.countSlashes(userInput) > 1) {
            logger.log(Level.WARNING, "More than one '/' character detected. There should only be a single '/'"
                    + " that separates the customer's name from their rating.");
            throw new InvalidCommandException();
        }
        if (userInput.trim().equals(DELIMITER)) {
            logger.log(Level.WARNING, "Customer name and satisfaction value were both found to be empty.");
            throw new InvalidCommandException();
        }
        String customerName = extractCustomerName(userInput);
        int satisfactionValue = extractSatisfactionValue(userInput);
        assert (!customerName.isEmpty()) : "The customer's name should be non-empty.";
        assert (satisfactionValue <= 5 && satisfactionValue >= 1) : "The satisfaction value must be an integer between"
                + "1 and 5.";
        Satisfaction satisfaction = new Satisfaction(customerName, satisfactionValue);
        setSatisfaction(satisfaction);
    }


    /**
     * Helper method for AddSatisfactionCommand. Extracts the customer's name
     * from the user input.
     *
     * @param userInput The user input (should be of form "[customerName] [satisfactionValue]").
     * @return The name of the customer (String).
     * @throws HotelLiteManagerException If there is an error in user input (the customer's name is empty).
     */
    private String extractCustomerName(String userInput) throws HotelLiteManagerException {
        String[] splitInput = userInput.split(DELIMITER);
        String customerName = "";
        try {
            customerName = splitInput[0].trim();
            if (customerName.isEmpty()) {
                throw new EmptySatisfactionCustomerException();
            }
            if (!parsingUtils.isAlpha(customerName)) {
                throw new InvalidSatisfactionCustomerNameException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new EmptySatisfactionValueException();
        }
        return customerName;
    }

    /**
     * Helper method for AddSatisfactionCommand. Extracts the customer's satisfaction value
     * from user input.
     *
     * @param userInput The user input (should be of form "[customerName] [satisfactionValue]").
     * @return The customer's satisfaction value (should be an integer from 1-5 inclusive).
     * @throws HotelLiteManagerException If there is an error in user input (the satisfaction value is
     *                                   empty or is not an integer between 1 and 5).
     */
    private int extractSatisfactionValue(String userInput) throws HotelLiteManagerException {
        String[] splitInput = userInput.split(DELIMITER);
        if (splitInput.length < 2) {
            throw new EmptySatisfactionValueException();
        }
        String satisfactionString = splitInput[1].trim();
        int satisfactionValue;
        try {
            satisfactionValue = Integer.parseInt(satisfactionString);
        } catch (NumberFormatException e) {
            throw new InvalidSatisfactionValueException();
        }
        if (satisfactionValue < 1 || satisfactionValue > 5) {
            throw new InvalidSatisfactionValueException();
        }
        return satisfactionValue;
    }


    @Override
    /**
     * Override of execute command in Command class.
     * Adds the Satisfaction object created in the constructor
     * to the given list of Satisfaction objects.
     * @param satisfactionList The given list of Satisfaction objects.
     * @param roomList The given list of Room objects. N/A for this class, but
     *                 must be included for the execution override.
     * @param itemList The given list of Item objects. N/A for this class, but
     *                 must be included for the execution override.
     * @param ui The user interface for this execution method.
     */
    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException, IOException {
        SatisfactionList satisfactionList = listContainer.getSatisfactionList();
        if (satisfactionList.isCustomerInSatisfactionList(satisfaction.getCustomerName())) {
            throw new RepeatCustomerException();
        }
        satisfactionList.addSatisfaction(satisfaction);
        ui.printAddSatisfactionAcknowledgementMessage(satisfactionList, satisfaction);
    }

    public void writeSatisfactionListToFile(ListContainer listContainer) throws IOException {
        SatisfactionList satisfactionList = listContainer.getSatisfactionList();
        SatisfactionListFileManager satisfactionListFileManager = new SatisfactionListFileManager();
        satisfactionListFileManager.save(satisfactionList);
    }


    public Satisfaction getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(Satisfaction satisfaction) {
        this.satisfaction = satisfaction;
    }
}