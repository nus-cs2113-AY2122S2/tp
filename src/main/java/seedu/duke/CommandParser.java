package seedu.duke;

public class CommandParser {
    private static final String BYE = "bye";
    private static final String ADD_ITEM_COMMAND = "Add Item";
    public static final String VIEW_ITEM_LIST_COMMAND = "View Item In Inventory";

    public Command parse(String commandString) throws WrongCommandException, HotelLiteManagerException {
        Command userCommand = null;
        if (commandString.equals(BYE)) {
            return new ExitCommand();
        } else if (commandString.startsWith(ADD_ITEM_COMMAND)) {
            String commandStringWithoutCommand = commandString.replace(ADD_ITEM_COMMAND, "");
            userCommand = new AddItemCommand(commandStringWithoutCommand);
        } else if (commandString.equals(VIEW_ITEM_LIST_COMMAND)) {
            userCommand = new ViewItemListCommand();
        } else {
            throw new WrongCommandException("Invalid Command");
        }
        return userCommand;
    }
}
