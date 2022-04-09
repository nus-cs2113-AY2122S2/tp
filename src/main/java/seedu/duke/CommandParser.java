package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.customercommands.AddSatisfactionCommand;
import seedu.duke.command.customercommands.AverageSatisfactionCommand;
import seedu.duke.command.customercommands.ViewSatisfactionsCommand;
import seedu.duke.command.eventcommands.AddEventCommand;
import seedu.duke.command.eventcommands.DeleteEventCommand;
import seedu.duke.command.eventcommands.ViewEventsCommand;
import seedu.duke.command.itemcommands.AddItemCommand;
import seedu.duke.command.itemcommands.ViewItemsWithZeroPaxCommand;
import seedu.duke.command.itemcommands.SearchItemCommand;
import seedu.duke.command.itemcommands.UpdateItemPaxCommand;
import seedu.duke.command.itemcommands.DeleteItemCommand;
import seedu.duke.command.itemcommands.UpdateItemNameCommand;
import seedu.duke.command.itemcommands.ViewItemListCommand;
import seedu.duke.command.assigncommand.AssignHousekeeperCommand;
import seedu.duke.command.roomcommand.CheckAllRoomCommand;
import seedu.duke.command.housekeepercommands.ViewHousekeeperListCommand;
import seedu.duke.command.housekeepercommands.AgeIncreaseCommand;
import seedu.duke.command.housekeepercommands.AddHousekeeperCommand;
import seedu.duke.command.housekeepercommands.ResetAvailabilityCommand;
import seedu.duke.command.housekeepercommands.AddHousekeeperPerformanceCommand;
import seedu.duke.command.housekeepercommands.AddAvailabilityCommand;
import seedu.duke.command.housekeepercommands.ViewHousekeeperPerformancesCommand;
import seedu.duke.command.housekeepercommands.GetAvailableHousekeeperCommand;
import seedu.duke.command.housekeepercommands.DeleteHousekeeperCommand;
import seedu.duke.command.roomcommand.CheckInCommand;
import seedu.duke.command.roomcommand.CheckOutCommand;
import seedu.duke.command.roomcommand.CheckRoomCommand;
import seedu.duke.command.roomcommand.CheckRoomByLevelCommand;
import seedu.duke.command.roomcommand.CheckRoomByCatCommand;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidCommandException;

/**
 * Class that implements behavior of parsing user input and linking
 * that user input to a Command class.
 */

public class CommandParser {

    private static final String SPACE = " ";
    private static final String BYE = "bye";
    private static final String DELETE = "delete ";
    private static final String ADD = "add ";
    private static final String ADD_ITEM_COMMAND = "add item ";
    private static final String VIEW_ITEM_LIST_COMMAND = "view all items";
    private static final String VIEW_ITEMS_WITH_ZERO_PAX_COMMAND = "view items with zero pax";
    private static final String DELETE_ITEM_COMMAND = "delete item ";
    private static final String UPDATE_ITEM_PAX_COMMAND = "update item pax ";
    private static final String UPDATE_ITEM_NAME_COMMAND = "update item name ";
    private static final String ADD_HOUSEKEEPER_COMMAND = "add housekeeper ";
    private static final String SEARCH_ITEM_COMMAND = "search item ";
    private static final String ADD_PERFORMANCE_COMMAND = "add performance ";
    private static final String VIEW_PERFORMANCES_COMMAND = "view performances";
    private static final String ADD_AVAILABILITY_COMMAND = "availability ";
    private static final String ADD_SATISFACTION_COMMAND = "add satisfaction ";
    private static final String VIEW_SATISFACTIONS_COMMAND = "view satisfactions";
    private static final String AVERAGE_SATISFACTION_COMMAND = "average satisfaction";
    private static final String CHECK_IN = "check in ";
    private static final String CHECK_OUT = "check out ";
    private static final String CHECK_ROOM = "check room ";
    private static final String CHECK_ALL_ROOM = "check all room";
    private static final String CHECK_ROOM_BY_LEVEL = "check level ";
    private static final String CHECK_BY_CATEGORY = "check category ";
    private static final String ASSIGN_HOUSEKEEPER = "assign ";
    private static final String VIEW_HOUSEKEEPER_COMMAND = "view recorded housekeepers";
    private static final String VIEW_AVAILABLE_HOUSEKEEPER_DAY = "get available on ";
    private static final String RESET_AVAILABILITY = "is a new week";
    private static final String DELETE_PROFILE = "delete housekeeper ";
    private static final String UPDATE_AGE_BY_ONE = "is a new year";
    private static final String ADD_EVENT = "add event ";
    private static final String VIEW_EVENTS = "list events";
    private static final String DELETE_EVENT = "delete event ";

    /**
     * Parses the user-provided command and creates the relevant Command object.
     *
     * @param userInput User input to be parsed and turned into a Command object.
     * @return The relevant Command object created based on the user input.
     * @throws HotelLiteManagerException If there is an error in user input that prevents it from being parsed into
     *                                   the relevant Command object.
     */
    public Command parse(String userInput) throws HotelLiteManagerException {
        String userInputLowerCase = userInput.toLowerCase();
        Command userCommand = null;
        String userInputLowerCaseWithoutCommand;
        if (userInputLowerCase.startsWith(SPACE)) {
            throw new InvalidCommandException();
        } else if (userInputLowerCase.startsWith(ADD)) {
            userCommand = parseAdd(userInputLowerCase);
        } else if (userInputLowerCase.startsWith(DELETE)) {
            userCommand = parseDelete(userInputLowerCase);
        } else if (userInputLowerCase.trim().equals(VIEW_SATISFACTIONS_COMMAND)) {
            userCommand = new ViewSatisfactionsCommand();
        } else if (userInputLowerCase.equals(AVERAGE_SATISFACTION_COMMAND)) {
            userCommand = new AverageSatisfactionCommand();
        } else if (userInputLowerCase.startsWith(VIEW_ITEM_LIST_COMMAND)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.replace(VIEW_ITEM_LIST_COMMAND, "");
            userInputLowerCaseWithoutCommand = userInputLowerCaseWithoutCommand.trim();
            userCommand = new ViewItemListCommand(userInputLowerCaseWithoutCommand);
        } else if (userInputLowerCase.startsWith(VIEW_ITEMS_WITH_ZERO_PAX_COMMAND)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.replace(VIEW_ITEMS_WITH_ZERO_PAX_COMMAND,
                    "");
            userInputLowerCaseWithoutCommand = userInputLowerCaseWithoutCommand.trim();
            userCommand = new ViewItemsWithZeroPaxCommand(userInputLowerCaseWithoutCommand);
        } else if (userInputLowerCase.trim().equals(VIEW_PERFORMANCES_COMMAND)) {
            userCommand = new ViewHousekeeperPerformancesCommand();
        } else if (userInputLowerCase.startsWith(CHECK_IN)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.replace(CHECK_IN, "");
            userCommand = new CheckInCommand(userInputLowerCaseWithoutCommand);
        } else if (userInputLowerCase.startsWith(CHECK_OUT)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.replace(CHECK_OUT, "");
            userCommand = new CheckOutCommand(userInputLowerCaseWithoutCommand);
        } else if (userInputLowerCase.startsWith(CHECK_BY_CATEGORY)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.replace(CHECK_BY_CATEGORY, "");
            userCommand = new CheckRoomByCatCommand(userInputLowerCaseWithoutCommand);
        } else if (userInputLowerCase.startsWith(CHECK_ROOM)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.replace(CHECK_ROOM, "");
            userCommand = new CheckRoomCommand(userInputLowerCaseWithoutCommand);
        } else if (userInputLowerCase.trim().equals(CHECK_ALL_ROOM)) {
            userCommand = new CheckAllRoomCommand();
        } else if (userInputLowerCase.startsWith(CHECK_ROOM_BY_LEVEL)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.replace(CHECK_ROOM_BY_LEVEL, "");
            userCommand = new CheckRoomByLevelCommand(userInputLowerCaseWithoutCommand);
        } else if (userInputLowerCase.contains((VIEW_HOUSEKEEPER_COMMAND))) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.trim();
            userCommand = new ViewHousekeeperListCommand(userInputLowerCaseWithoutCommand);
        } else if (userInputLowerCase.startsWith(VIEW_EVENTS)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.replace(VIEW_EVENTS, "");
            userCommand = new ViewEventsCommand(userInputLowerCaseWithoutCommand);
        } else {
            userCommand = parseMiscellaneous(userInputLowerCase);
        }
        return userCommand;
    }

    /**
     * Parses the user-provided delete-related command and creates the relevant Command object.
     *
     * @param userInputLowerCase User input to be parsed and turned into a delete-related Command object.
     * @return The relevant Command object created based on the user input.
     * @throws HotelLiteManagerException If there is an error in user input that prevents it from being parsed into
     *                                   the relevant Command object.
     */
    public Command parseDelete(String userInputLowerCase) throws HotelLiteManagerException {
        Command userCommand = null;
        String userInputLowerCaseWithoutCommand;
        if (userInputLowerCase.startsWith(DELETE_ITEM_COMMAND)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.replace(DELETE_ITEM_COMMAND, "");
            userCommand = new DeleteItemCommand(userInputLowerCaseWithoutCommand);
        } else if (userInputLowerCase.startsWith(DELETE_PROFILE)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.replaceFirst(DELETE_PROFILE, "");
            userCommand = new DeleteHousekeeperCommand(userInputLowerCaseWithoutCommand);
        } else if (userInputLowerCase.startsWith(DELETE_EVENT)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.replace(DELETE_EVENT, "");
            userCommand = new DeleteEventCommand(userInputLowerCaseWithoutCommand);
        } else {
            throw new InvalidCommandException();
        }
        return userCommand;
    }

    /**
     * Parses the user-provided add-related command and creates the relevant Command object.
     *
     * @param userInputLowerCase User input to be parsed and turned into a delete-related Command object.
     * @return The relevant Command object created based on the user input.
     * @throws HotelLiteManagerException If there is an error in user input that prevents it from being parsed into
     *                                   the relevant Command object.
     */
    public Command parseAdd(String userInputLowerCase) throws HotelLiteManagerException {
        Command userCommand = null;
        String userInputLowerCaseWithoutCommand;
        if (userInputLowerCase.startsWith(ADD_SATISFACTION_COMMAND)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.replaceFirst(ADD_SATISFACTION_COMMAND, "")
                    .trim();
            userCommand = new AddSatisfactionCommand(userInputLowerCaseWithoutCommand);
        } else if (userInputLowerCase.startsWith(ADD_ITEM_COMMAND)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.replace(ADD_ITEM_COMMAND, "");
            userCommand = new AddItemCommand(userInputLowerCaseWithoutCommand);
        } else if (userInputLowerCase.startsWith((ADD_HOUSEKEEPER_COMMAND))) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.replaceFirst(ADD_HOUSEKEEPER_COMMAND, "");
            userCommand = new AddHousekeeperCommand(userInputLowerCaseWithoutCommand);
        } else if (userInputLowerCase.startsWith(ADD_PERFORMANCE_COMMAND)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.replaceFirst(ADD_PERFORMANCE_COMMAND, "");
            userCommand = new AddHousekeeperPerformanceCommand(userInputLowerCaseWithoutCommand);
        } else if (userInputLowerCase.startsWith(ADD_EVENT)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.replace(ADD_EVENT, "");
            userCommand = new AddEventCommand(userInputLowerCaseWithoutCommand);
        } else {
            throw new InvalidCommandException();
        }
        return userCommand;
    }

    /**
     * Parses the user-provided commands that are neither an add, delete, view nor room-related commands. It then
     * creates the relevant Command object.
     *
     * @param userInputLowerCase User input to be parsed and turned into the relevant Command object.
     * @return The relevant Command object created based on the user input.
     * @throws HotelLiteManagerException If there is an error in user input that prevents it from being parsed into
     *                                   the relevant Command object or if the command is invalid.
     */
    public Command parseMiscellaneous(String userInputLowerCase) throws HotelLiteManagerException {
        Command userCommand = null;
        String userInputLowerCaseWithoutCommand;
        if (userInputLowerCase.equals(BYE)) {
            userCommand = new ExitCommand();
        } else if (userInputLowerCase.startsWith(ADD_AVAILABILITY_COMMAND)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.replace(ADD_AVAILABILITY_COMMAND, "");
            userCommand = new AddAvailabilityCommand(userInputLowerCaseWithoutCommand);
        } else if (userInputLowerCase.startsWith(VIEW_AVAILABLE_HOUSEKEEPER_DAY)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.replaceFirst(VIEW_AVAILABLE_HOUSEKEEPER_DAY,
                    "");
            userCommand = new GetAvailableHousekeeperCommand(userInputLowerCaseWithoutCommand);
        } else if (userInputLowerCase.startsWith(RESET_AVAILABILITY)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.trim();
            userCommand = new ResetAvailabilityCommand(userInputLowerCaseWithoutCommand);
        } else if (userInputLowerCase.startsWith(UPDATE_AGE_BY_ONE)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.trim();
            userCommand = new AgeIncreaseCommand(userInputLowerCaseWithoutCommand);
        } else if (userInputLowerCase.startsWith(ASSIGN_HOUSEKEEPER)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.replace(ASSIGN_HOUSEKEEPER, "");
            userCommand = new AssignHousekeeperCommand(userInputLowerCaseWithoutCommand);
        } else if (userInputLowerCase.startsWith(SEARCH_ITEM_COMMAND)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.replace(SEARCH_ITEM_COMMAND, "");
            userCommand = new SearchItemCommand(userInputLowerCaseWithoutCommand);
        } else if (userInputLowerCase.startsWith(UPDATE_ITEM_PAX_COMMAND)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.replace(UPDATE_ITEM_PAX_COMMAND, "");
            userCommand = new UpdateItemPaxCommand(userInputLowerCaseWithoutCommand);
        } else if (userInputLowerCase.startsWith(UPDATE_ITEM_NAME_COMMAND)) {
            userInputLowerCaseWithoutCommand = userInputLowerCase.replace(UPDATE_ITEM_NAME_COMMAND, "");
            userCommand = new UpdateItemNameCommand(userInputLowerCaseWithoutCommand);
        } else {
            throw new InvalidCommandException();
        }
        return userCommand;
    }

}



