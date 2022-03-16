package seedu.duke;

public class CommandParser {
    private static final String BYE = "bye";
    private static final String ADD_ITEM_COMMAND = "Add Item";
    public static final String VIEW_ITEM_LIST_COMMAND = "View Item In Inventory";
    private static final String ADD_HOUSEKEEPER_COMMAND = "Add Housekeeper ";
    private static final String ADD_AVAILABILITY_COMMAND = "Availability ";
    private static final String VIEW_HOUSEKEEPER_COMMAND = "View Recorded Housekeeper";
    public static final String ADD_SATISFACTION_COMMAND = "Add Satisfaction";
    public static final String VIEW_SATISFACTIONS_COMMAND = "View Satisfactions";
    private static final String CHECK_IN = "check in";
    private static final String CHECK_OUT = "check out";
    private static final String CHECK_ROOM = "check room";
    private static final String CHECK_ALL_ROOM = "check all room";
    private static final String CHECK_ROOM_BY_LEVEL = "check level room";
    private static final String CHECK_BY_CATEGORY = "check category room";

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
        } else if (commandString.equals(VIEW_ITEM_LIST_COMMAND)) {
            userCommand = new ViewItemListCommand();
        } else if (commandString.startsWith((ADD_HOUSEKEEPER_COMMAND))) {
            commandStringWithoutCommand = commandString.replace(ADD_HOUSEKEEPER_COMMAND, "");
            userCommand = new AddHousekeeperCommand(commandStringWithoutCommand);
        } else if (commandString.startsWith(CHECK_IN)) {
            commandStringWithoutCommand = commandString.replace(CHECK_IN, "");
            userCommand = new CheckInCommand(commandStringWithoutCommand);
        } else if (commandString.startsWith(CHECK_OUT)) {
            commandStringWithoutCommand = commandString.replace(CHECK_OUT, "");
            userCommand = new CheckOutCommand(commandStringWithoutCommand);
        } else if (commandString.startsWith(CHECK_BY_CATEGORY)) {
            commandStringWithoutCommand = commandString.replace(CHECK_BY_CATEGORY, "");
            userCommand = new CheckRoomByCatCommand(commandStringWithoutCommand);
        } else if (commandString.startsWith(CHECK_ROOM)) {
            commandStringWithoutCommand = commandString.replace(CHECK_ROOM, "");
            userCommand = new CheckRoomCommand(commandStringWithoutCommand);
        } else if (commandString.startsWith(CHECK_ALL_ROOM)) {
            userCommand = new CheckAllRoomCommand();
        } else if (commandString.startsWith(CHECK_ROOM_BY_LEVEL)) {
            commandStringWithoutCommand = commandString.replace(CHECK_ROOM_BY_LEVEL, "");
            userCommand = new CheckRoomByLevelCommand(commandStringWithoutCommand);
        } else {
            throw new WrongCommandException("Invalid Command");
        }
        return userCommand;
    }

}



