package seedu.duke;

public class CommandParser {
    private static final String BYE = "bye";
    private static final String ADD_ITEM_COMMAND = "Add Item";
    private static final String CHECK_IN = "check in";
    private static final String CHECK_OUT = "check out";
    private static final String CHECK_ROOM = "check room";
    private static final String CHECK_ALL_ROOM = "check all room";
    private static final String CHECK_ROOM_BY_LEVEL = "check level room";

    public Command parse(String commandString) throws WrongCommandException, HotelLiteManagerException {
        Command userCommand = null;
        if (commandString.equals(BYE)) {
            return new ExitCommand();
        } else if (commandString.startsWith(ADD_ITEM_COMMAND)) {
            String commandStringWithoutCommand = commandString.replace(ADD_ITEM_COMMAND, "");
            userCommand = new AddItemCommand(commandStringWithoutCommand);
        } else if (commandString.startsWith(CHECK_IN)) {
            String commandStringWithoutCommand = commandString.replace(CHECK_IN, "");
            userCommand = new CheckInCommand(commandStringWithoutCommand);
        } else if (commandString.startsWith(CHECK_OUT)) {
            String commandStringWithoutCommand = commandString.replace(CHECK_OUT, "");
            userCommand = new CheckOutCommand(commandStringWithoutCommand);
        } else if (commandString.startsWith(CHECK_ROOM)) {
            String commandStringWithoutCommand = commandString.replace(CHECK_ROOM, "");
            userCommand = new CheckRoomCommand(commandStringWithoutCommand);
        } else if (commandString.startsWith(CHECK_ALL_ROOM)) {
            userCommand = new CheckAllRoomCommand();
        } else if (commandString.startsWith(CHECK_ROOM_BY_LEVEL)) {
            String commandStringWithoutCommand = commandString.replace(CHECK_ROOM_BY_LEVEL, "");
            userCommand = new CheckRoomByLevelCommand(commandStringWithoutCommand);
        } else {
            throw new WrongCommandException("Invalid Command");
        }
        return userCommand;
    }
}
