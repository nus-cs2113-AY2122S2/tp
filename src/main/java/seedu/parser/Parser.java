package seedu.parser;

import java.util.ArrayList;

import seedu.command.AddCommand;
import seedu.command.ModificationCommand;
import seedu.command.UpdateCommand;
import seedu.command.ListCommand;
import seedu.command.IncorrectCommand;
import seedu.command.CheckCommand;
import seedu.command.DeleteCommand;
import seedu.command.HelpCommand;
import seedu.command.Command;
import seedu.command.SaveCommand;
import seedu.command.ByeCommand;

import java.util.Collections;
import java.util.Locale;
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
    public static final Pattern CHECK_COMMAND_FORMAT = Pattern.compile("[Nn]/(?<itemName>.+)\\s*");
    public static final Pattern DELETE_COMMAND_FORMAT = Pattern.compile("[Ss]/(?<serialNumber>.+)\\s*");
    public static final Pattern TYPE_ENUM_FORMAT = Pattern.compile("[Tt]/(?<equipmentType>\\w+)\\s*");
    /**
     * Extracts first n-1 tags for debugging and assumes that the last tag contains the whole string, refer to:
     * <a href="https://regex101.com/r/7rho4H/1"> Regex101</a> for demo.*/
    public static final Pattern MODIFICATION_ARGUMENT_FORMAT = Pattern.compile(
            "((?:[sntcSNTC]|[pP][fF]|[pP][dD])" // argument tag
                    + "\\/" // argument delimiter
                    + "[\\w\\s\\-\\.]+)" // actual argument value
                    + "\\s+" // argument space before next delimiter
                    + "(?=[sntcSNTC]|[pP][fF]|[pP][dD])" // next delimiter
    );
    /**
     * Extracts last tag for debugging.
     *
     * <p>See also {@link Parser#MODIFICATION_ARGUMENT_FORMAT} for Regex demonstration.*/
    public static final Pattern MODIFICATION_ARGUMENT_TRAILING_FORMAT = Pattern.compile(
            "(?<!\\w)" // require a previous pattern
                    + "(?:[sntcSNTC]|[pP][fF]|[pP][dD])" // argument tag
                    + "\\/" // argument delimiter
                    + "([\\w\\s\\-\\.]+)" // last argument value
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

        // only arguments is trimmed because commandWord is split on the first space
        String commandWord = commandAndArgument.get(0);
        String arguments = commandAndArgument.get(1).trim();

        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            try {
                args = extractArguments(arguments);
                return new AddCommand(args);
            } catch (IncompleteCommandException e) {
                return new IncorrectCommand(AddCommand.COMMAND_WORD + AddCommand.COMMAND_DESCRIPTION);
            } catch (NumberFormatException e) {
                return new IncorrectCommand(ModificationCommand.INVALID_COST_MESSAGE);
            } catch (IllegalArgumentException e) {
                return new IncorrectCommand(ModificationCommand.INVALID_TYPE_MESSAGE);
            }
        case CheckCommand.COMMAND_WORD:
            try {
                args = prepareCheck(arguments);
                return new CheckCommand(args);
            } catch (IncompleteCommandException e) {
                return new IncorrectCommand(CheckCommand.COMMAND_WORD + CheckCommand.COMMAND_DESCRIPTION);
            }
        case DeleteCommand.COMMAND_WORD:
            try {
                args = prepareDelete(arguments);
                return new DeleteCommand(args);
            } catch (IncompleteCommandException e) {
                return new IncorrectCommand(DeleteCommand.COMMAND_WORD + DeleteCommand.COMMAND_DESCRIPTION);
            }
        case UpdateCommand.COMMAND_WORD:
            try {
                args = extractArguments(arguments);
                return new UpdateCommand(args);
            } catch (IncompleteCommandException e) {
                return new IncorrectCommand(UpdateCommand.COMMAND_WORD + UpdateCommand.COMMAND_DESCRIPTION);
            } catch (NumberFormatException e) {
                return new IncorrectCommand(ModificationCommand.INVALID_COST_MESSAGE);
            } catch (IllegalArgumentException e) {
                return new IncorrectCommand(ModificationCommand.INVALID_TYPE_MESSAGE);
            }
        case ListCommand.COMMAND_WORD:
            if (arguments == null) {
                return new ListCommand();
            } else {
                args = new ArrayList<>(Collections.singleton(arguments.toUpperCase(Locale.ROOT)));
                return new ListCommand(args);
            }
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case SaveCommand.COMMAND_WORD:
            return new SaveCommand();
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        default:
            return new IncorrectCommand(INCORRECT_COMMAND_FORMAT);
        }

    }

    /**
     * Break down a command into the command term to be parsed and the remainder of the arguments.
     * Assumes command term and remainder arguments are delimited by minimally one space.
     * If first element is "list", "help", "save" or "bye" remainder arguments can be empty, in which case a null
     * second object will be passed in.
     *
     * @param userInput String to be split into substrings
     * @return ArrayList of String, first element being the command term and the second element being arguments
     * @throws IncompleteCommandException if no space is found
     */
    public ArrayList<String> splitCommandTerm(String userInput) throws IncompleteCommandException {
        ArrayList<String> resultArrayList = new ArrayList<>();
        userInput = userInput.trim();

        // Checks for list/help/save command word first
        switch (userInput.toLowerCase(Locale.ROOT)) {
        case ListCommand.COMMAND_WORD:
            resultArrayList.add(ListCommand.COMMAND_WORD);
            resultArrayList.add(null);
            return resultArrayList;
        case HelpCommand.COMMAND_WORD:
            resultArrayList.add(HelpCommand.COMMAND_WORD);
            resultArrayList.add(null);
            return resultArrayList;
        case SaveCommand.COMMAND_WORD:
            resultArrayList.add(SaveCommand.COMMAND_WORD);
            resultArrayList.add(null);
            return resultArrayList;
        case ByeCommand.COMMAND_WORD:
            resultArrayList.add(ByeCommand.COMMAND_WORD);
            resultArrayList.add(null);
            return resultArrayList;
        default:
            break;
        }

        // Match terms against syntax
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput);
        // Guard against no match
        if (!matcher.matches()) {
            throw new IncompleteCommandException("Could not find space delimiter between command and arguments!");
        }
        // Match and return ArrayList appropriately
        resultArrayList.add(matcher.group("commandWord").toLowerCase(Locale.ROOT));
        resultArrayList.add(matcher.group("arguments"));
        return resultArrayList;
    }

    /**
     * Prepare argument for CheckCommand by removing the preceding "n/" prefix.
     *
     * @param args String to be split into substrings
     * @return ArrayList of one element (assumes rest of string is item name)
     * @throws IncompleteCommandException if no match found
     */
    protected ArrayList<String> prepareCheck(String args) throws IncompleteCommandException {
        final Matcher matcher = CHECK_COMMAND_FORMAT.matcher(args);
        if (!matcher.matches()) {
            throw new IncompleteCommandException("Check command values are incomplete or missing!");
        }
        return new ArrayList<>(Collections.singleton(matcher.group("itemName")));
    }

    /**
     * Prepare argument for DeleteCommand by removing the preceding "s/" prefix.
     *
     * @param args String to be split into substrings
     * @return ArrayList of one element (assumes rest of string is serial number)
     * @throws IncompleteCommandException if no match found
     */
    protected ArrayList<String> prepareDelete(String args) throws IncompleteCommandException {
        final Matcher matcher = DELETE_COMMAND_FORMAT.matcher(args);
        if (!matcher.matches()) {
            throw new IncompleteCommandException("Delete command values are incomplete or missing!");
        }
        return new ArrayList<>(Collections.singleton(matcher.group("serialNumber")));
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
        String argumentToAdd;
        String argument;
        ArrayList<String> splitArguments = new ArrayList<>();
        try {
            Matcher matcher = MODIFICATION_ARGUMENT_FORMAT.matcher(args);
            while (matcher.find()) {
                argument = matcher.group();
                argumentToAdd = setArgumentTagsToLower(argument.trim());
                splitArguments.add(argumentToAdd);
                lastIndex = matcher.end();
            }
            matcher.usePattern(MODIFICATION_ARGUMENT_TRAILING_FORMAT);
            matcher.find(lastIndex);
            argument = matcher.group();
            argumentToAdd = setArgumentTagsToLower(argument.trim());
            splitArguments.add(argumentToAdd);
        } catch (IllegalStateException e) {
            throw new IncompleteCommandException("No parameters found!");
        }

        for (int i = splitArguments.size() - 1; i >= 0; i--) {
            String argumentPair = splitArguments.get(i);
            Matcher matcher = TYPE_ENUM_FORMAT.matcher(argumentPair);
            if (matcher.matches()) {
                splitArguments.remove(argumentPair);
                splitArguments.add("t/" + matcher.group("equipmentType").toUpperCase(Locale.ROOT));
                return splitArguments;
            }
        }

        return splitArguments;
    }

    private static String setArgumentTagsToLower(String argument) {
        int slashIndex = argument.indexOf("/");
        return argument.substring(0, slashIndex).toLowerCase(Locale.ROOT) + argument.substring(slashIndex);
    }

    @Deprecated
    public static final Pattern ADD_COMMAND_FORMAT = Pattern.compile(
            "n\\/(?<itemName>.+)" + "\\s+"
                    + "s\\/(?<serialNumber>.+)" + "\\s+"
                    + "t\\/(?<equipmentType>.+)" + "\\s+"
                    + "c\\/(?<cost>.+)" + "\\s+"
                    + "pf\\/(?<purchasedFrom>.+)" + "\\s+"
                    + "pd\\/(?<purchasedDate>.+)"
    );

    /**
     * Prepare arguments for AddCommand by splitting up the arguments into different parts.
     *
     * <p>Index:
     *
     * <p>0. <code> equipmentName </code>: String of equipment name
     *
     * <p>1. <code> serialNumber </code>: String of unique serial number
     *
     * <p>2. <code> type </code>: String representation of enumerated class
     *
     * <p>3. <code> cost </code>: String representation of double value, "$" optional but "," delimiter forbidden
     *
     * <p>4. <code> purchasedFrom </code>: String of vendor name, suggest adhering to one consistent naming scheme
     *
     * <p>5. <code> purchasedDate </code>: String representation for now, possibility for future support
     *
     * @param args String to be split into substrings
     * @return ArrayList of arguments
     * @throws IncompleteCommandException if no match found
     * @deprecated Use extractArguments as it is more robust in conjunction with subclasses of ModificationCommand
     */
    @Deprecated
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

    @Deprecated
    private static boolean hasSlashDelimiter(String argument) {
        return argument.contains("/");
    }



}
