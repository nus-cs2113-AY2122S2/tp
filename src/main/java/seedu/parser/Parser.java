package seedu.parser;

import java.util.ArrayList;

import seedu.command.AddCommand;
import seedu.command.UpdateCommand;
import seedu.command.ListCommand;
import seedu.command.IncorrectCommand;
import seedu.command.CheckCommand;
import seedu.command.DeleteCommand;
import seedu.command.HelpCommand;
import seedu.command.Command;

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
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)\\s+(?<arguments>.+)");
    public static final Pattern ADD_COMMAND_FORMAT = Pattern.compile(
            "n\\/(?<itemName>.+)" + "\\s+"
                    + "s\\/(?<serialNumber>.+)" + "\\s+"
                    + "t\\/(?<equipmentType>.+)" + "\\s+"
                    + "c\\/(?<cost>.+)" + "\\s+"
                    + "pf\\/(?<purchasedFrom>.+)" + "\\s+"
                    + "pd\\/(?<purchasedDate>.+)"
    );
    public static final Pattern VIEW_COMMAND_FORMAT = Pattern.compile("n/(?<itemName>.+)");
    public static final Pattern DELETE_COMMAND_FORMAT = Pattern.compile("s/(?<itemName>.+)");
    // ARGUMENT_FORMAT extracts first n-1 tags
    public static final Pattern ARGUMENT_FORMAT = Pattern.compile(
            "((?:s|n|t|c|pf|pd)\\/[\\w\\s\\-]+?)\\s+(?=s|n|t|c|pf|pd)"
    );
    // ARGUMENT_TRAILING_FORMAT extracts last tag
    public static final Pattern ARGUMENT_TRAILING_FORMAT = Pattern.compile(
            "(?<!\\w)(?:s|n|t|c|pf|pd)\\/([\\w\\s\\-]+)"
    );
    public static final String MESSAGE_INCOMPLETE_COMMAND_MISSING_DELIMITER =
            "Please split your command into arguments with each argument seperated by spaces!";
    public static final String INCORRECT_COMMAND_FORMAT = "Incorrect Command format! Enter help for more information.";

    /**
     * Interpret the command requested by the user and returns a corresponding Command object.
     *
     * @param userInput Raw string of input values
     * @return command of parent class Command with parameters specified
     */
    public Command parseCommand(String userInput) {
        ArrayList<String> commandAndArgument;
        ArrayList<String> args;
        try {
            commandAndArgument = splitCommandTerm(userInput);
        } catch (IncompleteCommandException e) {
            return new IncorrectCommand(MESSAGE_INCOMPLETE_COMMAND_MISSING_DELIMITER);
        }

        switch (commandAndArgument.get(0)) {
        case AddCommand.COMMAND_WORD:
            try {
                args = prepareAdd(commandAndArgument.get(1));
                return new AddCommand(args);
            } catch (IncompleteCommandException e) {
                return new IncorrectCommand(AddCommand.COMMAND_WORD + AddCommand.COMMAND_DESCRIPTION);
            }
        case CheckCommand.COMMAND_WORD:
            try {
                args = prepareView(commandAndArgument.get(1));
                return new CheckCommand(args);
            } catch (IncompleteCommandException e) {
                return new IncorrectCommand(CheckCommand.COMMAND_WORD + CheckCommand.COMMAND_DESCRIPTION);
            }
        case DeleteCommand.COMMAND_WORD:
            try {
                args = prepareDelete(commandAndArgument.get(1));
                return new DeleteCommand(args);
            } catch (IncompleteCommandException e) {
                return new IncorrectCommand(DeleteCommand.COMMAND_WORD + DeleteCommand.COMMAND_DESCRIPTION);
            }
        case UpdateCommand.COMMAND_WORD:
            try {
                args = extractArguments(commandAndArgument.get(1));
                return new UpdateCommand(args);
            } catch (IncompleteCommandException e) {
                return new IncorrectCommand(UpdateCommand.COMMAND_WORD + UpdateCommand.COMMAND_DESCRIPTION);
            }
        case ListCommand.COMMAND_WORD:
            if (commandAndArgument.get(1) == null) {
                return new ListCommand();
            } else {
                return new ListCommand(new ArrayList<>(Collections.singleton(commandAndArgument.get(1))));
            }
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            return new IncorrectCommand(INCORRECT_COMMAND_FORMAT);
        }

    }

    /**
     * Break down a command into the command term to be parsed and the remainder of the arguments.
     * Assumes command term and remainder arguments are delimited by minimally one space.
     * If first element is "list", remainder arguments can be empty, in which case a null
     * second object will be passed in.
     *
     * @param userInput String to be split into substrings
     * @return ArrayList of String, first element being the command term and the second element being arguments
     * @throws IncompleteCommandException if no space is found
     */
    public ArrayList<String> splitCommandTerm(String userInput) throws IncompleteCommandException {
        ArrayList<String> resultArrayList = new ArrayList<>();
        userInput = userInput.trim();
        if (userInput.equals(ListCommand.COMMAND_WORD)) {
            resultArrayList.add("list");
            resultArrayList.add(null);
            return resultArrayList;
        }
        if (userInput.equals(HelpCommand.COMMAND_WORD)) {
            resultArrayList.add("help");
            resultArrayList.add(null);
            return resultArrayList;
        }
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput);
        // guard against no match
        if (!matcher.matches()) {
            throw new IncompleteCommandException("Could not find space delimiter between command and arguments!");
        }
        resultArrayList.add(matcher.group("commandWord"));
        resultArrayList.add(matcher.group("arguments"));
        return resultArrayList;
    }

    /**
     * Prepare arguments for AddCommand by splitting up the arguments into different parts.
     *
     * <p>* Index:
     * 0. <code> equipmentName </code>: String of equipment name
     * 1. <code> serialNumber </code>: String of unique serial number
     * 2. <code> type </code>: String representation of enumerated class
     * 3. <code> cost </code>: String representation of double value, "$" optional but "," delimiter forbidden
     * 4. <code> purchasedFrom </code>: String of vendor name, suggest adhering to one consistent naming scheme
     * 5. <code> purchasedDate </code>: String representation for now, possibility for future support
     *
     * @param args String to be split into substrings
     * @return ArrayList of arguments
     * @throws IncompleteCommandException if no match found
     */
    protected ArrayList<String> prepareAdd(String args) throws IncompleteCommandException {
        final Matcher matcher = ADD_COMMAND_FORMAT.matcher(args.trim());
        // validate arg string format
        int matchCount = matcher.groupCount();
        if (!matcher.matches()) {
            throw new IncompleteCommandException("Add command values are incomplete or missing!");
        }
        ArrayList<String> results = new ArrayList<>();
        for (int i = 1; i <= matchCount; i++) {
            String result = matcher.group(i);
            if (hasSlashDelimiter(result)) {
                throw new IncompleteCommandException("Use of '/' for purposes other than delimiter is forbidden!");
            }
            results.add(result);
        }
        return results;
    }

    /**
     * Prepare argument for CheckCommand by removing the preceding "n/" prefix.
     *
     * @param args String to be split into substrings
     * @return ArrayList of one element (assumes rest of string is item name)
     * @throws IncompleteCommandException if no match found
     */
    protected ArrayList<String> prepareView(String args) throws IncompleteCommandException {
        final Matcher matcher = VIEW_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new IncompleteCommandException("View command values are incomplete or missing!");
        }
        return new ArrayList<>(Collections.singleton(matcher.group(1)));
    }

    /**
     * Prepare argument for DeleteCommand by removing the preceding "s/" prefix.
     *
     * @param args String to be split into substrings
     * @return ArrayList of one element (assumes rest of string is serial number)
     * @throws IncompleteCommandException if no match found
     */
    protected ArrayList<String> prepareDelete(String args) throws IncompleteCommandException {
        final Matcher matcher = DELETE_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new IncompleteCommandException("Delete command values are incomplete or missing!");
        }
        return new ArrayList<>(Collections.singleton(matcher.group(1)));
    }

    /**
     * Splits main arguments into split tags with each substring.
     *
     * @param args String to be split into substrings
     * @return ArrayList of two elements
     * @throws IncompleteCommandException if no parameters found
     */
    protected ArrayList<String> extractArguments(String args) throws IncompleteCommandException {
        int lastIndex = 0;
        ArrayList<String> splitArguments = new ArrayList<>();
        try {
            Matcher matcher = ARGUMENT_FORMAT.matcher(args.trim());
            while (matcher.find()) {
                splitArguments.add(matcher.group().trim());
                lastIndex = matcher.end();
            }
            matcher.usePattern(ARGUMENT_TRAILING_FORMAT);
            matcher.find(lastIndex);
            splitArguments.add(matcher.group().trim());
        } catch (IllegalStateException e) {
            throw new IncompleteCommandException("No parameters found!");
        }
        return splitArguments;
    }

    private static boolean hasSlashDelimiter(String argument) {
        return argument.contains("/");
    }


}
