package ARCS.parser;

import ARCS.commands.*;

public class Parser {
    public Command parseCommand(String userInput) {
        String[] fullInput = userInput.split(" ", 2);
        String commandWord = fullInput[0];
        String argumentLine = fullInput.length > 1? fullInput[1].trim() : null;
        Command command;

        switch (commandWord) {
        case AddRouteCommand.COMMAND_WORD:
            command = prepareAddRoute(argumentLine);
            break;
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand();
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
}
