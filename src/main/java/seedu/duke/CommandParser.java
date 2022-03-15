package seedu.duke;

public class CommandParser {
    private static final String BYE = "bye";
    private static final String ADD_ITEM_COMMAND = "Add Item";
    private static final String ADD_HOUSEKEEPER_COMMAND = "Add Housekeeper ";
    private static final String ADD_AVAILABILITY_COMMAND = "Availability ";
    private static final String VIEW_HOUSEKEEPER_COMMAND = "View Recorded Housekeeper";

    public Command parse(String commandString) throws WrongCommandException, HotelLiteManagerException {
        Command userCommand = null;
        if (commandString.equals(BYE)) {
            return new ExitCommand();
        } else if (commandString.startsWith(ADD_ITEM_COMMAND)) {
            String commandStringWithoutCommand = commandString.replace(ADD_ITEM_COMMAND, "");
            userCommand = new AddItemCommand(commandStringWithoutCommand);
        } else if (commandString.startsWith((ADD_HOUSEKEEPER_COMMAND))) {
            String commandStringWithoutCommand = commandString.replace(ADD_HOUSEKEEPER_COMMAND, "");
            userCommand = new AddHousekeeperCommand(commandStringWithoutCommand);
        } else {
            throw new WrongCommandException("Invalid Command");
        }
        return userCommand;
    }
}
