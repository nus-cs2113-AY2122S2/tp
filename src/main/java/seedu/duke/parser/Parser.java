package seedu.duke.parser;

import seedu.duke.data.Item;
import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.DescCommand;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.common.Messages;

public class Parser {

    /**
     * Manages the user input.
     * 
     * @param userCommand User input
     * @return A command that the user requested for to be executed
     * @throws InvMgrException Exception used for handling invalid inputs.
     */
    public static Command parse(String userCommand) throws InvMgrException {
        if (userCommand.startsWith("exit")) {
            return new ExitCommand();

        } else if (userCommand.startsWith("add")) {
            Item item = createItem(userCommand);
            return new AddCommand(item);

        } else if (userCommand.startsWith("delete")) {
            String[] splitString = userCommand.split(" ");
            assert splitString.length == 2 : "input should be delete with an integer behind";
            String deleteIndex = splitString[1];
            assert isNum(deleteIndex) : "delete should have a integer behind it";
            return new DeleteCommand(Integer.parseInt(deleteIndex) - 1);

        } else if (userCommand.startsWith("list")) {
            return new ListCommand();

        } else if (userCommand.startsWith("help")) {
            return new HelpCommand();

        } else if (userCommand.startsWith("desc")) {
            String[] splitString = userCommand.split(" ");
            assert splitString.length == 2 : "input should be desc with an integer behind";
            String descIndex = splitString[1];
            assert isNum(descIndex) : "desc should have a integer behind it";
            return new DescCommand(Integer.parseInt(descIndex) - 1);
        } else {
            throw new InvMgrException(Messages.INVALID_COMMAND);
       
        }
    }

    /**
     * Method used to get the name of the object being added.
     * 
     * @param itemString User input split by "/"
     * @return name of the item
     */
    private static String getName(String[] itemString) {
        String nameString = itemString[1];
        int nameStringLength = nameString.length();
        return nameString.substring(0, nameStringLength - 2);
    }

    /**
     * Method used to get the quantity of the object being added.
     * 
     * @param itemString User input split by "/"
     * @return quantity of the item
     */
    private static int getQuantity(String[] itemString) {
        String quantityString = itemString[2];
        int quantityStringLength = quantityString.length();
        assert isNum(quantityString) : "quantity should be a number";
        return Integer.parseInt(quantityString.substring(0, quantityStringLength - 2));
    }

    /**
     * Method used to get the description of the object being added.
     * 
     * @param itemString User input split by "/"
     * @return description of the item
     */
    private static String getDesc(String[] itemString) {
        String descString = itemString[3];
        int descStringLength = descString.length();
        return descString.substring(0, descStringLength - 2);
    }

    /**
     * Method used to create item for AddCommand to handle.
     * 
     * @param userCommand User input
     * @return item to be added into itemList
     */
    private static Item createItem(String userCommand) {
        String[] itemString = userCommand.split("/");
        assert itemString.length == 4 : "userCommand should have 3 '/' inside it";
        String name = getName(itemString);
        int quantity = getQuantity(itemString);
        String desc = getDesc(itemString);
        return new Item(name, quantity, desc);
    }

    /**
     * Method to check if a String is a number.
     *
     * @param number String that is being checked.
     * @return Boolean value whether it is a number.
     */
    private static boolean isNum(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
