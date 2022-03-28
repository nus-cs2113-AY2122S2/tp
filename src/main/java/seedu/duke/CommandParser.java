package seedu.duke;

/**
 * Class that implements behavior of parsing user input and linking
 * that user input to a Command class.
 */

public class CommandParser {

    private static final String BYE = "bye";
    private static final String ADD_ITEM_COMMAND = "Add Item ";
    private static final String VIEW_ITEM_LIST_COMMAND = "View Item In Inventory";
    private static final String DELETE_ITEM_COMMAND = "Delete Item ";
    private static final String UPDATE_ITEM_PAX_COMMAND = "Update Item Pax ";
    private static final String UPDATE_ITEM_NAME_COMMAND = "Update Item Name ";
    private static final String SEARCH_ITEM_COMMAND = "Search Item ";
    private static final String ADD_HOUSEKEEPER_COMMAND = "Add Housekeeper ";
    private static final String ADD_PERFORMANCE_COMMAND = "add housekeeper performance";
    private static final String VIEW_PERFORMANCES_COMMAND = "view housekeeper performances";
    private static final String ADD_AVAILABILITY_COMMAND = "Availability ";
    public static final String ADD_SATISFACTION_COMMAND = "add satisfaction";
    public static final String VIEW_SATISFACTIONS_COMMAND = "view satisfactions";
    public static final String AVERAGE_SATISFACTION_COMMAND = "average satisfaction";
    private static final String CHECK_IN = "check in";
    private static final String CHECK_OUT = "check out";
    private static final String CHECK_ROOM = "check room";
    private static final String CHECK_ALL_ROOM = "check all";
    private static final String CHECK_ROOM_BY_LEVEL = "check level";
    private static final String CHECK_BY_CATEGORY = "check category";
    private static final String ASSIGN_HOUSEKEEPER = "Assign";
    private static final String VIEW_HOUSEKEEPER_COMMAND = "View Recorded Housekeeper";
    private static final String VIEW_AVAILABLE_HOUSEKEEPER_DAY = "get available on ";
    private static final String RESET_AVAILABILITY = "is a new week";
    private static final String DELETE_PROFILE = "delete housekeeper ";
    private static final String UPDATE_AGE_BY_ONE = "is a new year";


    /**
     * Parses the user-provided command and creates the relevant Command object.
     *
     * @param commandString User input to be parsed and turned into a Command object.
     * @return The relevant Command object created based on the user input.
     * @throws WrongCommandException     If the user input cannot be recognized as a Command object.
     * @throws HotelLiteManagerException If there is an error in user input that prevents it from being parsed into
     *                                   the relevant Command object.
     */
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
        } else if (commandString.startsWith(AVERAGE_SATISFACTION_COMMAND)) {
            userCommand = new AverageSatisfactionCommand();
        } else if (commandString.startsWith(ADD_ITEM_COMMAND)) {
            commandStringWithoutCommand = commandString.replace(ADD_ITEM_COMMAND, "");
            userCommand = new AddItemCommand(commandStringWithoutCommand);
        } else if (commandString.equals(VIEW_ITEM_LIST_COMMAND)) {
            userCommand = new ViewItemListCommand();
        } else if (commandString.startsWith(DELETE_ITEM_COMMAND)) {
            commandStringWithoutCommand = commandString.replace(DELETE_ITEM_COMMAND, "");
            userCommand = new DeleteItemCommand(commandStringWithoutCommand);
        } else if (commandString.startsWith((ADD_HOUSEKEEPER_COMMAND))) {
            commandStringWithoutCommand = commandString.replace(ADD_HOUSEKEEPER_COMMAND, "");
            userCommand = new AddHousekeeperCommand(commandStringWithoutCommand);
        } else if (commandString.startsWith(ADD_PERFORMANCE_COMMAND)) {
            commandStringWithoutCommand = commandString.replace(ADD_PERFORMANCE_COMMAND, "");
            userCommand = new AddHousekeeperPerformanceCommand(commandStringWithoutCommand);
        } else if (commandString.startsWith(VIEW_PERFORMANCES_COMMAND)) {
            userCommand = new ViewHousekeeperPerformancesCommand();
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
        } else if (commandString.startsWith(UPDATE_ITEM_PAX_COMMAND)) {
            commandStringWithoutCommand = commandString.replace(UPDATE_ITEM_PAX_COMMAND, "");
            userCommand = new UpdateItemPaxCommand(commandStringWithoutCommand);
        } else if (commandString.startsWith(UPDATE_ITEM_NAME_COMMAND)) {
            commandStringWithoutCommand = commandString.replace(UPDATE_ITEM_NAME_COMMAND, "");
            userCommand = new UpdateItemNameCommand(commandStringWithoutCommand);
        } else if (commandString.startsWith(ADD_AVAILABILITY_COMMAND)) {
            commandStringWithoutCommand = commandString.replace(ADD_AVAILABILITY_COMMAND, "");
            userCommand = new AddAvailabilityCommand(commandStringWithoutCommand);
        } else if (commandString.contains((VIEW_HOUSEKEEPER_COMMAND))) {
            userCommand = new ViewHousekeeperListCommand();
        } else if (commandString.startsWith(ASSIGN_HOUSEKEEPER)) {
            commandStringWithoutCommand = commandString.replace(ASSIGN_HOUSEKEEPER, "");
            userCommand = new AssignHousekeeperCommand(commandStringWithoutCommand);
        } else if (commandString.startsWith(VIEW_AVAILABLE_HOUSEKEEPER_DAY)) {
            commandStringWithoutCommand = commandString.replace(VIEW_AVAILABLE_HOUSEKEEPER_DAY, "");
            userCommand = new GetAvailableHousekeeper(commandStringWithoutCommand);
        } else if (commandString.startsWith(RESET_AVAILABILITY)) {
            userCommand = new ResetAvailabilityCommand();
        } else if (commandString.startsWith(DELETE_PROFILE)) {
            commandStringWithoutCommand = commandString.replace(DELETE_PROFILE, "");
            userCommand = new DeleteHousekeeperCommand(commandStringWithoutCommand);
        } else if (commandString.startsWith(UPDATE_AGE_BY_ONE)) {
            userCommand = new AgeIncreaseCommand();
        } else if (commandString.startsWith(SEARCH_ITEM_COMMAND)){
            commandStringWithoutCommand = commandString.replace(SEARCH_ITEM_COMMAND, "");
            userCommand = new SearchItemCommand(commandStringWithoutCommand);
        } else {
            throw new WrongCommandException("Error! Invalid Command.");
        }
        return userCommand;
    }

}



