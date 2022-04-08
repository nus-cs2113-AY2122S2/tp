package arcs.parser;


import arcs.commands.customer.AddCustomerCommand;
import arcs.commands.customer.DeleteCustomerCommand;
import arcs.commands.customer.FindCustomerCommand;
import arcs.commands.customer.ListCustomerCommand;
import arcs.commands.flightbooking.BookCommand;
import arcs.commands.flightbooking.DeleteBookingCommand;
import arcs.commands.flightbooking.ListBookingCommand;
import arcs.commands.menuitem.FindMenuItemTypeCommand;
import arcs.commands.menuitem.FindMenuItemNameCommand;
import arcs.commands.menuitem.AddMenuItemCommand;
import arcs.commands.menuitem.ListMenuItemsCommand;
import arcs.commands.menuitem.DeleteMenuItemCommand;
import arcs.commands.route.AddRouteCommand;
import arcs.commands.route.DeleteRouteCommand;
import arcs.commands.route.FindRouteCommand;
import arcs.commands.route.ListRouteCommand;
import arcs.commands.Command;
import arcs.commands.ExitCommand;
import arcs.commands.UndefinedCommand;
import arcs.commands.staff.AddStaffCommand;
import arcs.commands.staff.DeleteStaffCommand;

public class Parser {


    public Command parseCommand(String userInput) {


        assert userInput != null : "User input is null";
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
        case AddMenuItemCommand.COMMAND_WORD:
            command = prepareAddMenuItem(argumentLine);
            break;
        case DeleteMenuItemCommand.COMMAND_WORD:
            command = prepareDeleteMenuItemCommand(argumentLine);
            break;
        case ListMenuItemsCommand.COMMAND_WORD:
            command = new ListMenuItemsCommand();
            break;
        case FindMenuItemNameCommand.COMMAND_WORD:
            command = new FindMenuItemNameCommand(argumentLine);
            break;
        case FindMenuItemTypeCommand.COMMAND_WORD:
            command = new FindMenuItemTypeCommand(argumentLine);
            break;
        case AddCustomerCommand.COMMAND_WORD:
            command = CustomerParser.prepareAddCustomerCommand(argumentLine);
            break;
        case ListCustomerCommand.COMMAND_WORD:
            command = new ListCustomerCommand();
            break;
        case DeleteCustomerCommand.COMMAND_WORD:
            command = CustomerParser.prepareDeleteCustomerCommand(argumentLine);
            break;
        case FindCustomerCommand.COMMAND_WORD:
            command = new FindCustomerCommand(argumentLine);
            break;
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand();
            break;
        case BookCommand.COMMAND_WORD:
            command = FlightBookingParser.prepareBookCommand(argumentLine);
            break;
        case ListBookingCommand.COMMAND_WORD:
            command = new ListBookingCommand();
            break;
        case DeleteBookingCommand.COMMAND_WORD:
            command = FlightBookingParser.prepareDeleteBookingCommand(argumentLine);
            break;
        case AddStaffCommand.COMMAND_WORD:
            command = StaffParser.prepareAddStaffCommand(argumentLine);;
            break;
        case DeleteStaffCommand.COMMAND_WORD:
            command = StaffParser.prepareDeleteStaffCommand(argumentLine);;
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
        String flightId = null;
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
                flightId = value;
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
        return new AddRouteCommand(flightId, date, time, from, to, capacity);
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

    public Command prepareAddMenuItem(String argumentLine) {
        if (argumentLine == null || argumentLine.isEmpty()) {
            return new AddMenuItemCommand(null,null,null);
        }
        String[] args = argumentLine.split(" ");
        String menuItemName = null;
        String menuItemType = null;
        String menuItemPrice = null;
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
            case "name":
                menuItemName = value;
                //replace underscore separator with space
                menuItemName = menuItemName.replace("_", " ");
                break;
            case "type":
                menuItemType = value;
                break;
            case "price":
                menuItemPrice = value;
                break;
            default:
                break;
            }
        }
        System.out.println("here..");
        return new AddMenuItemCommand(menuItemName,menuItemType,menuItemPrice);
    }

    public Command prepareDeleteMenuItemCommand(String argumentLine) {
        if (argumentLine == null || argumentLine.isEmpty()) {
            return new UndefinedCommand("Index is not specified");
        }
        Command result;
        try {
            int index = Integer.parseInt(argumentLine);
            result = new DeleteMenuItemCommand(index);
        } catch (NumberFormatException e) {
            result = new UndefinedCommand("Index should be an integer.");
        }
        return result;
    }

}
