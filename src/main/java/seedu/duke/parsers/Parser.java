package seedu.duke.parsers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ParseException;
import seedu.duke.util.StringConstants;


/**
 * Represents a Parser that parse a {@code Command}.
 */
public abstract class Parser {
    protected static final String EXIT_COMMAND_WORD = StringConstants.EXIT_COMMAND_WORD;
    protected static final String ADD_COMMAND_WORD = StringConstants.ADD_COMMAND_WORD;
    protected static final String DELETE_COMMAND_WORD = StringConstants.DELETE_COMMAND_WORD;
    protected static final String LIST_COMMAND_WORD = StringConstants.LIST_COMMAND_WORD;
    protected static final String MARK_COMMAND_WORD = StringConstants.MARK_COMMAND_WORD;
    protected static final String RESET_COMMAND_WORD = StringConstants.RESET_COMMAND_WORD;
    protected static final String HELP_COMMAND_WORD = StringConstants.HELP_COMMAND_WORD;

    protected String commandFormat;
    protected HashMap<String, String> parsedCommand;
    protected HashSet<String> groupNames;

    public Parser() {
        groupNames = new HashSet<String>();
        parsedCommand = new HashMap<>();
    }

    /**
     * Parses the provided user input and returns the relevant Command object.
     */
    public abstract Command parseCommand(String userInput) throws ModHappyException;

    /**
     * Parses string into groups based on commandFormat.
     * @throws ModHappyException if the provided string does not match the pattern
     */
    public HashMap<String, String> parseString(String userInput) throws ModHappyException {
        final Pattern commandPattern = Pattern.compile(commandFormat);
        final Matcher matcher = commandPattern.matcher(userInput.trim());

        if (!matcher.matches()) {
            throw new ParseException();
        }
        for (Object groupName : groupNames) {
            try {
                parsedCommand.put(groupName.toString(), matcher.group(groupName.toString()).trim());
            } catch (Exception e) {
                parsedCommand.put(groupName.toString(), null);
            }
        }
        return parsedCommand;
    }

}