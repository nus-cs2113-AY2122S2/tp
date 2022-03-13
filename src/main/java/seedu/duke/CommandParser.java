package seedu.duke;

public class CommandParser {
    public static final String BYE = "bye";
    public static final String ADD_ITEM_COMMAND = "Add Item";

    public Command parse(String commandString) throws WrongCommandException, HotelLiteManagerException {
        Command userCommand = null;
        if (commandString.equals(BYE)) {
            return new ExitCommand();
        } else if (commandString.startsWith(ADD_ITEM_COMMAND)) {
            String commandStringWithoutCommand = commandString.replace(ADD_ITEM_COMMAND, "");
            userCommand = new AddItemCommand(commandStringWithoutCommand);
        } else {
            throw new WrongCommandException("Invalid Command");
        }
        return userCommand;
    }
}
