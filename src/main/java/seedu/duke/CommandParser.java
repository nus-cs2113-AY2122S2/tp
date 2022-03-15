package seedu.duke;

public class CommandParser {
    private static final String BYE = "bye";
    private static final String ADD_ITEM_COMMAND = "Add Item";
    public static final String ADD_HOUSEKEEPER_COMMAND = "Add Housekeeper";
    public static final String ADD_SATISFACTION_COMMAND = "Add Satisfaction";
    public static final String VIEW_SATISFACTIONS_COMMAND = "View Satisfactions";
    public Command parse(String commandString) throws WrongCommandException, HotelLiteManagerException {
        Command userCommand = null;
        String commandStringWithoutCommand;
        if (commandString.equals(BYE)) {
            userCommand = new ExitCommand();
        } else if (commandString.startsWith(ADD_SATISFACTION_COMMAND)) {
            commandStringWithoutCommand = commandString.replace(ADD_SATISFACTION_COMMAND, "").trim();
            userCommand = new AddSatisfactionCommand(commandStringWithoutCommand);
        } else if (commandString.startsWith(VIEW_SATISFACTIONS_COMMAND)) {
            userCommand = new ViewSatisfactionsCommand();
        } else if (commandString.startsWith(ADD_ITEM_COMMAND)) {
            commandStringWithoutCommand = commandString.replace(ADD_ITEM_COMMAND, "");
            userCommand = new AddItemCommand(commandStringWithoutCommand);
        } else {
            throw new WrongCommandException("Invalid Command");
        }
        return userCommand;
    }
}
