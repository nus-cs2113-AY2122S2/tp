package seedu.parser;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import seedu.command.AddCommand;
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
    /**
     * Defines regex used to match argument pairs in the command line.
     *
     * <p>An argument pair is defined as an argument tag, a slash "/", minimally a backtick "`",
     * an argument value and minimally a final backtick "`".
     *
     * <p>See also {@link Parser#MODIFICATION_ARGUMENT_FORMAT} for Regex use and demonstration.
     */
    private static final String ARGUMENT_PAIR_REGEX =
            "(?:[sntcSNTC]|[pP][fF]|[pP][dD])" // non-capturing group for argument tag
                    + "\\/" // argument delimiter
                    + "`+" //  backticks to enclose string
                    + "[\\w\\s\\-\\.]+" // actual argument value
                    + "`+"; //  backticks to enclose string
    /**
     * Extracts first n-1 tags for debugging and assumes that the last tag contains the whole string, refer to:
     * <a href="https://regex101.com/r/dMwMWw/1"> Regex101</a> for demo.
     */
    public static final Pattern MODIFICATION_ARGUMENT_FORMAT = Pattern.compile(
            "(" + ARGUMENT_PAIR_REGEX + ")" + "\\s+" // argument space before next delimiter
    );
    /**
     * Extracts last tag for debugging.
     *
     * <p>See also {@link Parser#MODIFICATION_ARGUMENT_FORMAT} for Regex use and demonstration.
     */
    public static final Pattern MODIFICATION_ARGUMENT_TRAILING_FORMAT = Pattern.compile(
            "(?<![\\w`])" // require a previous pattern
                    + ARGUMENT_PAIR_REGEX + "$" // require end of string
    );
    public static final Pattern CHECK_COMMAND_FORMAT = Pattern.compile("^(?<itemName>" + ARGUMENT_PAIR_REGEX + ")$");
    public static final Pattern DELETE_COMMAND_FORMAT = Pattern.compile("^(?<serialNumber>" + "[Ss]"
            + "\\/" // argument delimiter
            + "`+" //  backticks to enclose string
            + "[\\w\\s\\-\\.]+" // actual argument value
            + "`+" //  backticks to enclose string
            + ")$");
    public static final String UNRECOGNISED_COMMAND_MESSAGE = "Command word not recognised. " + System.lineSeparator()
            + "Please use one of the following: "
            + AddCommand.COMMAND_WORD + ", " + UpdateCommand.COMMAND_WORD + ", " + ListCommand.COMMAND_WORD + ", "
            + CheckCommand.COMMAND_WORD + ", " + DeleteCommand.COMMAND_WORD + ", " + HelpCommand.COMMAND_WORD + ", "
            + SaveCommand.COMMAND_WORD + ", " + ByeCommand.COMMAND_WORD + ".";
    public static final String MISSING_COMMAND_WORD_DELIMITER_MESSAGE = UNRECOGNISED_COMMAND_MESSAGE
            + System.lineSeparator() + "If including additional arguments, please separate them with a space.";

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
            return new IncorrectCommand(MISSING_COMMAND_WORD_DELIMITER_MESSAGE);
        }

        // only arguments is trimmed because commandWord is split on the first space
        String commandWord = commandAndArgument.get(0);
        String arguments = commandAndArgument.get(1);
        if (arguments != null) {
            arguments = arguments.trim();
        }

        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            try {
                args = extractArguments(arguments);
                return new AddCommand(args);
            } catch (IncompleteCommandException e) {
                return new IncorrectCommand(AddCommand.COMMAND_WORD + AddCommand.COMMAND_DESCRIPTION);
            } catch (NumberFormatException e) {
                return new IncorrectCommand(Command.INCORRECT_COST_FORMAT);
            } catch (IllegalArgumentException e) {
                return new IncorrectCommand(Command.INCORRECT_ENUM_TYPE);
            } catch (MissingAttributeException e) {
                return new IncorrectCommand(e.getMessage()
                        + System.lineSeparator()
                        + AddCommand.COMMAND_WORD
                        + AddCommand.COMMAND_DESCRIPTION);
            } catch (DateTimeParseException e) {
                return new IncorrectCommand(Command.INVALID_DATE_MESSAGE);
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
                return new IncorrectCommand(Command.INCORRECT_COST_FORMAT);
            } catch (IllegalArgumentException e) {
                return new IncorrectCommand(Command.INCORRECT_ENUM_TYPE);
            } catch (DateTimeParseException e) {
                return new IncorrectCommand(Command.INVALID_DATE_MESSAGE);
            }
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case SaveCommand.COMMAND_WORD:
            return new SaveCommand();
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        default:
            return new IncorrectCommand(UNRECOGNISED_COMMAND_MESSAGE);
        }

    }

    /**
     * Break down a command into the command term to be parsed and the remainder of the arguments.
     * Assumes command term and remainder arguments are delimited by minimally one space.
     *
     * <p>If first element is <code>list</code>, <code>help</code>, <code>save</code> or <code>bye</code>,
     * remainder arguments can be empty, in which case a null second object will be passed in.
     * If there exists a second element in such a case, it is split as per normal, but it will be ignored in
     * {@link Parser#parseCommand(String)}.
     *
     * @param userInput String to be split into substrings
     * @return ArrayList of 2 String, first element command term and second element arguments
     * @throws IncompleteCommandException if no space is found
     */
    public ArrayList<String> splitCommandTerm(String userInput) throws IncompleteCommandException {
        ArrayList<String> resultArrayList = new ArrayList<>();
        userInput = userInput.trim();

        // Checks for list/help/save/bye command word first
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
     * Prepare argument for CheckCommand by matching the preceding "n/" prefix, setting it to lowercase and verifying
     * that there are no additional tags.
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
        String argumentPair = reformatArgumentPair(matcher.group("itemName"));
        return new ArrayList<>(Collections.singleton(argumentPair));
    }

    /**
     * Prepare argument for DeleteCommand by matching the preceding "s/" prefix, setting it to lowercase and verifying
     * that there are no additional tags.
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
        String argumentPair = reformatArgumentPair(matcher.group("serialNumber"));
        return new ArrayList<>(Collections.singleton(argumentPair));
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
                argumentToAdd = reformatArgumentPair(argument.trim());
                splitArguments.add(argumentToAdd);
                lastIndex = matcher.end();
            }
            matcher.usePattern(MODIFICATION_ARGUMENT_TRAILING_FORMAT);
            matcher.find(lastIndex);
            argument = matcher.group();
            argumentToAdd = reformatArgumentPair(argument.trim());
            splitArguments.add(argumentToAdd);
        } catch (IllegalStateException e) {
            throw new IncompleteCommandException(IncompleteCommandException.NO_PARAMETERS_FOUND);
        }

        return splitArguments;
    }

    private static String reformatArgumentPair(String argument) {
        int slashIndex = argument.indexOf("/");
        String newString = argument.substring(0, slashIndex).toLowerCase(Locale.ROOT) + argument.substring(slashIndex);
        return newString.replace("`", "");
    }

}
