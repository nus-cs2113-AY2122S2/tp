package arcs.parser;


import arcs.commands.route.*;
import arcs.commands.Command;
import arcs.commands.ExitCommand;
import arcs.commands.UndefinedCommand;
import arcs.commands.menu.AddMenuItemCommand;
import arcs.commands.menu.MenuItemCommand;
import arcs.commands.menu.PrintMenuItemsCommand;
import arcs.commands.menu.RemoveMenuItemCommand;
import arcs.data.RouteManager;
import arcs.data.exception.ArcsException;
import arcs.data.menu.MenuItemList;
import arcs.ui.MainUi;
import arcs.ui.MenuUi;

public class Parser {

    public static final String INVALID_MENU_ITEM_ERROR = "Please enter a valid index";

    private MenuUi menuUi;
    private RouteManager routeManager;

    public Command parseMainMenuInput(String fullCommand, MainUi mainUi, MenuItemList menuItems,
                                      RouteManager routeManager) throws ArcsException {
        Command command;
        switch (fullCommand.trim()) {
        case MenuItemCommand.COMMAND_WORD:
            menuUi = new MenuUi();
            command = parseUserMenuInput(menuUi.readCommand(), menuItems, menuUi);
            break;
        case FlightRouteCommand.COMMAND_WORD:
            String userCommandText = mainUi.getUserCommand();
            command = parseCommand(userCommandText);
            command.setData(routeManager);
            break;
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand(mainUi);
            break;
        default:
            throw new ArcsException(arcs.commands.Command.COMMAND_ERROR);
        }
        return command;
    }

    public Command parseUserMenuInput(String fullCommand, MenuItemList menuItems, MenuUi ui) throws ArcsException {
        Command command;
        switch (fullCommand.trim()) {
        case AddMenuItemCommand.COMMAND_WORD:
            command = new AddMenuItemCommand(ui,menuItems);
            break;
        case PrintMenuItemsCommand.COMMAND_WORD:
            command = new PrintMenuItemsCommand(ui,menuItems);
            break;
        case RemoveMenuItemCommand.COMMAND_WORD:
            command = new RemoveMenuItemCommand(ui,menuItems);
            break;
        default:
            throw new ArcsException(arcs.commands.Command.COMMAND_ERROR);
        }
        return command;
    }

    public Command parseCommand(String userInput) {
        String[] fullInput = userInput.split(" ", 2);
        String commandWord = fullInput[0];
        String argumentLine = fullInput.length > 1 ? fullInput[1].trim() : null;
        Command command;

        switch (commandWord) {
        case AddRouteCommand.COMMAND_WORD:
            command = prepareAddRoute(argumentLine);
            break;
        case ListRouteCommand.COMMAND_WORD:
            command = new ListRouteCommand();
            break;
        case DeleteRouteCommand.COMMAND_WORD:
            command = prepareDeleteRouteCommand(argumentLine);
            break;
        case FindRouteCommand.COMMAND_WORD:
            command = prepareFindRouteCommand(argumentLine);
            break;
        default:
            command = new UndefinedCommand();
            break;
        }
        return command;
    }

    private Command prepareAddRoute(String argumentLine) {
        if (argumentLine == null || argumentLine.isEmpty()) {
            return new AddRouteCommand(null, null, null, null, null, 0);
        }
        String[] args = argumentLine.split(" ");
        String fId = null;
        String date = null;
        String time = null;
        String from = null;
        String to = null;
        int capacity = 0;
        for (String arg: args) {
            arg = arg.trim();
            if (arg.isEmpty()) {
                continue;
            }
            String[] argSplit = arg.split("/", 2);
            if (argSplit.length < 2) {
                continue;
            }
            String field = argSplit[0].trim();
            String value = argSplit[1].trim();
            switch (field) {
            case "fid":
                fId = value;
                break;
            case "fd":
                date = value;
                break;
            case "ft":
                time = value;
                break;
            case "d":
                to = value;
                break;
            case "s":
                from = value;
                break;
            case "c":
                capacity = Integer.parseInt(value);
                break;
            default:
                break;
            }
        }
        return new AddRouteCommand(fId, date, time, from, to, capacity);
    }

    public Command prepareDeleteRouteCommand(String argumentLine) {
        if (argumentLine == null || argumentLine.isEmpty()) {
            return new UndefinedCommand("Index is not specified");
        }
        Command result;
        try {
            int index = Integer.parseInt(argumentLine);
            result = new DeleteRouteCommand(index);
        } catch (NumberFormatException e) {
            result = new UndefinedCommand("Index should be an integer.");
        }

        return result;
    }

    public Command prepareFindRouteCommand(String argumentLine) {
        if (argumentLine == null || argumentLine.isEmpty()) {
            return new FindRouteCommand(null, null, null, null);
        }
        String[] args = argumentLine.split(" ");
        String date = null;
        String to = null;
        String from = null;
        String time = null;

        for (String arg: args) {
            arg = arg.trim();
            if (arg.isEmpty()) {
                continue;
            }
            String[] argSplit = arg.split("/", 2);
            if (argSplit.length < 2) {
                continue;
            }
            String field = argSplit[0].trim();
            String value = argSplit[1].trim();
            switch (field) {
            case "fd":
                date = value;
                break;
            case "ft":
                time = value;
                break;
            case "d":
                to = value;
                break;
            case "s":
                from = value;
                break;
            default:
                break;
            }
        }
        return new FindRouteCommand(date, to, from, time);
    }

    /**
     * Returns if user input is yes.
     *
     * @param choice User input.
     * @return True if yes, false otherwise.
     */
    public boolean isYes(String choice) {
        if (choice.equalsIgnoreCase("YES")) {
            return true;
        }
        else {
            return false;
        }
    }
}
