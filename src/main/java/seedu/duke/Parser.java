package seedu.duke;

import seedu.duke.commands.*;
import seedu.duke.exceptions.MissingValueException;

import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.ErrorMessages.ERROR_INVALID_INDEX_FORMAT;
import static seedu.duke.ErrorMessages.ERROR_MISSING_PARAMETERS;
import static seedu.duke.ErrorMessages.ERROR_MISSING_VALUES;

public class Parser {
    private final String command;
    private final String arguments;
    public static Logger logger = Logger.getLogger(Parser.class.getName());

    private static final int NAME_INDEX = 0;
    private static final int TITLE_INDEX = 1;
    private static final int DAY_INDEX = 2;
    private static final int STARTTIME_INDEX = 3;
    private static final int ENDTIME_INDEX = 4;
    private static final int MODE_INDEX = 5;
    private static final String[] HEADINGS = {"n/", "l/", "d/", "st/", "et/", "m/"};
    private static final String[] DAYS = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};

    public Parser(String input) {
        this.command = getCommandFromInput(input);
        this.arguments = getArgumentsFromInput(input);
    }

    public Command parseCommand() {
        switch (command) {
        case AddCommand.COMMAND_WORD:
            return prepareAdd();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete();
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();
        default:
            return new HelpCommand();
        }
    }

    public Command prepareAdd() {
        // checks empty command description error
        // checks input format error
        try {
            String[] eventDescription = splitArguments();
            checkValidityOfArguments(eventDescription);
            String name = eventDescription[NAME_INDEX];
            String title = eventDescription[TITLE_INDEX];
            String day = eventDescription[DAY_INDEX].toLowerCase();
            int startTime = Integer.parseInt(eventDescription[STARTTIME_INDEX]);
            int endTime = Integer.parseInt(eventDescription[ENDTIME_INDEX]);
            String mode = eventDescription[MODE_INDEX].toLowerCase();
            return new AddCommand(name, title, day, startTime, endTime, mode);
        } catch (NumberFormatException nfe) {
            System.out.println(ERROR_INVALID_INDEX_FORMAT);
            return new HelpCommand(); // temporary
        } catch (IndexOutOfBoundsException ie) {
            System.out.println(ERROR_MISSING_PARAMETERS);
            return new HelpCommand();
        } catch (MissingValueException mve) {
            System.out.println(ERROR_MISSING_VALUES);
            return new HelpCommand();
        }
    }

    private void checkValidityOfArguments(String[] eventDescription) throws MissingValueException {
        for (int i = 0; i < MODE_INDEX; i++) {
            if (eventDescription[i].length() == 0) {
                throw new MissingValueException();
            }
        }
    }

    public Command prepareDelete() {
        try {
            int index = Integer.parseInt(arguments);
            return new DeleteCommand(index);
        } catch (NumberFormatException nfe) {
            System.out.println(ERROR_INVALID_INDEX_FORMAT);
            logger.log(Level.INFO, "Invalid index to delete Error detected.");
            return new HelpCommand();
        }
    }

    private String getCommandFromInput(String input) {
        return input.split(" ")[0].trim().toLowerCase();
    }

    private String getArgumentsFromInput(String input) {
        String str = "";
        int spaceIndex = input.trim().indexOf(" ");
        if (spaceIndex != -1) {
            str = input.substring(spaceIndex + 1).trim();
        }
        return str;
    }

    private String[] splitArguments() {
        String[] eventDescription = new String[6];
        int index = -1;
        for (String str : arguments.split(" ")) {
            if (checkHeadings(str) == -1) {
                eventDescription[index] += " " + str;
                eventDescription[index] = eventDescription[index].trim();
            } else {
                index = checkHeadings(str);
                eventDescription[index] = str.substring(str.indexOf("/") + 1);
            }
        }
        return eventDescription;
    }

    private int checkHeadings(String str) {
        for (int i = 0; i < HEADINGS.length; i++) {
            if (str.contains(HEADINGS[i])) {
                return i;
            }
        }
        return -1;
    }
}
