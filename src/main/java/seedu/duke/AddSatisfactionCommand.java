package seedu.duke;

import java.util.logging.Logger;
import java.util.logging.Level;

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
    private Satisfaction satisfaction;
    private Ui ui = new Ui();
    private static Logger logger = Logger.getLogger("satisfactionLogger");

    /**
     * Extracts the customer name and satisfaction value from user input,
     * uses these two values to create a corresponding Satisfaction object.
     *
     * @param commandStringWithoutCommand The user input (should be of form "[customerName] [satisfactionValue]").
     * @throws HotelLiteManagerException If there is an error in user input (e.g. empty customer name, empty
     *                                   satisfaction value, or invalid satisfaction value).
     */
    public AddSatisfactionCommand(String commandStringWithoutCommand) throws HotelLiteManagerException {
        String customerName = "";
        int satisfactionValue = 0;
        try {
            customerName = extractCustomerName(commandStringWithoutCommand);
            satisfactionValue = extractSatisfactionValue(commandStringWithoutCommand);
        } catch (EmptySatisfactionCustomerException e) {
            logger.log(Level.WARNING, "Customer name was found to be empty.");
            throw new EmptySatisfactionCustomerException();
        } catch (EmptySatisfactionValueException e) {
            logger.log(Level.WARNING, "Satisfaction value was found to be empty.");
            throw new EmptySatisfactionValueException();
        } catch (InvalidSatisfactionValueException e) {
            logger.log(Level.WARNING, "Satisfaction value was found to be invalid.");
            throw new InvalidSatisfactionValueException();
        }
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
        String[] splitInput = userInput.split(" ");
        if (splitInput.length == 0) {
            throw new EmptySatisfactionCustomerException();
        }
        String customerName = "";
        for (int i = 0; i < splitInput.length - 1; i++) {
            customerName += splitInput[i] + " ";
        }
        customerName = customerName.trim();
        if (customerName.isEmpty()) {
            throw new EmptySatisfactionCustomerException();
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
        String[] splitInput = userInput.split(" ");
        if (splitInput.length < 2) {
            throw new EmptySatisfactionValueException();
        }
        String satisfactionString = splitInput[splitInput.length - 1].trim();
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
    public void execute(HousekeeperList housekeeperList, SatisfactionList satisfactionList,
                        AssignmentMap assignmentMap, RoomList roomList,
                        ItemList listOfItems, Ui ui) throws HotelLiteManagerException, WrongCommandException {
        satisfactionList.addSatisfaction(satisfaction);
        ui.printAddSatisfactionAcknowledgementMessage(satisfactionList, satisfaction);

    }

    public Satisfaction getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(Satisfaction satisfaction) {
        this.satisfaction = satisfaction;
    }
}