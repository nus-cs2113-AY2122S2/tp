package parser;

import java.lang.reflect.Array;
import java.util.ArrayList;

import seedu.command.*;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse user input
 * Referenced https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
 */
public class Parser {

    /**
     * Format to parse command by breaking it up into two segments: command and arguments: these can then be separately
     * passed into arguments.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)\\s+(?<arguments>.*)");
    public static final Pattern ADD_COMMAND_FORMAT = Pattern.compile(
            "n/(?<itemName>.+)" + "\\s+" +
                    "sn/(?<serialNumber>.+)" + "\\s+" +
                    "t/(?<equipmentType>.+)" + "\\s+" +
                    "c/\\$?(?<cost>[\\d,\\.]+)" + "\\s+" + // TODO: maybe have error checking formula separately?
                    "pf/(?<purchasedFrom>.+)" + "\\s+" +
                    "pd/(?<purchasedDate>.+)"
    );
    public static final Pattern VIEW_COMMAND_FORMAT = Pattern.compile("n/(?<itemName>.+)");
    public static final Pattern DELETE_COMMAND_FORMAT = Pattern.compile("s/(?<itemName>.+)");
    public static final String MESSAGE_INCOMPLETE_COMMAND_MISSING_DELIMITER =
            "Please split your command into arguments with each argument seperated by spaces!";

    public Command parseCommand(String userInput) throws IncompleteCommandException {
        // TODO: replace void with Command
        ArrayList<String> commandAndArgument = null;
        ArrayList<String> args = null;
        try {
            commandAndArgument = splitCommandTerm(userInput);
        } catch (IncompleteCommandException e) {
            return new IncorrectCommand(MESSAGE_INCOMPLETE_COMMAND_MISSING_DELIMITER);
        }

        switch (commandAndArgument.get(0)) {
        case AddCommand.COMMAND_WORD:
            // TODO: replace "add" with constant as defined in AddCommand
            try {
                args = prepareAdd(commandAndArgument.get(1));
                return new AddCommand(args);
            } catch (IncompleteCommandException e) {
                return new IncorrectCommand(AddCommand.COMMAND_WORD + AddCommand.COMMAND_DESCRIPTION);
            }
            break;
        case CheckCommand.COMMAND_WORD:
            try {
                args = prepareView(commandAndArgument.get(1));
                return new CheckCommand(args);
            } catch (IncompleteCommandException e) {
                return new IncorrectCommand(CheckCommand.COMMAND_WORD + CheckCommand.COMMAND_DESCRIPTION);
            }
            break;
        case DeleteCommand.COMMAND_WORD:
            try {
                args = prepareDelete(commandAndArgument.get(1));
                return new DeleteCommand(args);
            } catch (IncompleteCommandException e) {
                return new IncorrectCommand(DeleteCommand.COMMAND_WORD + DeleteCommand.COMMAND_DESCRIPTION);
            }


        }

    }

    /**
     * Prepare arguments for AddCommand by splitting up the arguments into different parts
     * <p>
     * Index:
     * 0. <code> equipmentName </code>: String of equipment name
     * 1. <code> serialNumber </code>: String of unique serial number
     * 2. <code> type </code>: String representation of enumerated class
     * 3. <code> cost </code>: String representation of double value, "$" optional but "," delimiter forbidden
     * 4. <code> purchasedFrom </code>: String of vendor name, suggest adhering to one consistent naming scheme
     * 5. <code> purchasedDate </code>: String representation for now, possibility for future support
     *
     * @param args
     * @return ArrayList of arguments
     * @throws IncompleteCommandException if no match found
     */
    protected ArrayList<String> prepareAdd(String args) throws IncompleteCommandException {
        final Matcher matcher = ADD_COMMAND_FORMAT.matcher(args.trim());
        // validate arg string format
        if (!matcher.matches()) {
            throw new IncompleteCommandException("Add command values are incomplete or missing!");
        }
        ArrayList results = new ArrayList<String>();
        for (int i = 0; i < matcher.groupCount(); i++) {
            results.add(matcher.group());
        }
        return results;
    }

    /**
     * Prepare argument for CheckCommand by removing the preceeding "n/" prefix
     * @param args
     * @return ArrayList of one element (assumes rest of string is item name)
     * @throws IncompleteCommandException
     */
    protected ArrayList<String> prepareView(String args) throws IncompleteCommandException {
        final Matcher matcher = VIEW_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new IncompleteCommandException("View command values are incomplete or missing!");
        }
        return new ArrayList<String>(Collections.singleton(matcher.group()));
    }

    /**
     * Prepare argument for DeleteCommand by removing the preceeding "s/" prefix
     * @param args
     * @return ArrayList of one element (assumes rest of string is serial number)
     * @throws IncompleteCommandException
     */
    protected ArrayList<String> prepareDelete(String args) throws IncompleteCommandException {
        final Matcher matcher = DELETE_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new IncompleteCommandException("Delete command values are incomplete or missing!");
        }
        return new ArrayList<String>(Collections.singleton(matcher.group()));
    }

    /**
     * Break down a command into the command term to be parsed and the remainder of the arguments.
     * Assumes command term and remainder arguments are delimited by minimally one space.
     *
     * @param userInput
     * @return ArrayList of String, first element being the command term and the second element being arguments
     * @throws IncompleteCommandException if no space is found
     */
    public ArrayList<String> splitCommandTerm(String userInput) throws IncompleteCommandException {
        ArrayList<String> resultArrayList = new ArrayList<>();
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        // guard against no match
        if (!matcher.matches()) {
            throw new IncompleteCommandException("Could not find space delimiter between command and arguments!");
        }
        resultArrayList.add(matcher.group("commandWord"));
        resultArrayList.add(matcher.group("arguments"));
        return resultArrayList;
    }


}
