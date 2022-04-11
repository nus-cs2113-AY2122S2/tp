package seedu.duke.parsers;

import java.util.HashMap;
import java.util.HashSet;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.GeneralParseException;
import seedu.duke.exceptions.UnknownConfigurationGroupWordException;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.UnknownCommandException;
import seedu.duke.exceptions.UnsupportedResultTypeException;
import seedu.duke.exceptions.WrongDurationFormatException;
import seedu.duke.util.StringConstants;

/**
 * This Parser distinguishes between various command words.
 */
public class ModHappyParser extends Parser {
    private static final String ARGUMENT = StringConstants.ARGUMENT;
    private static final String COMMAND_WORD = StringConstants.COMMAND_WORD;
    private static final String MOD_HAPPY_COMMAND_FORMAT = "(?<commandWord>\\S+)"
            + "\\s*(?<arguments>.*)";

    //@@author Ch40gRv1-Mu
    public ModHappyParser() {
        super();
        // See also https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        this.commandFormat = MOD_HAPPY_COMMAND_FORMAT;
        groupNames = new HashSet<>();
        parsedCommand = new HashMap<>();
        groupNames.add(COMMAND_WORD);
        groupNames.add(ARGUMENT);
    }

    /**
     * Throws GeneralParseException as the user input does not match the regex.
     * @throws GeneralParseException as it has no compulsory parameters.
     */
    @Override
    public void determineError() throws GeneralParseException {
        throw new GeneralParseException();
    }

    /**
     * Extract the command word from the user input and invoke the relevant command-specific parser.
     * @return a Command instance associated with the user input
     * @throws GeneralParseException if the user input does not match the string
     * @throws ModHappyException if the command word was not recognised
     */
    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        try {
            HashMap<String, String> parsedCommand = parseString(userInput);
            Parser commandParser = getCommandParser(parsedCommand.get(COMMAND_WORD));
            return commandParser.parseCommand(parsedCommand.get(ARGUMENT));
        } catch (GeneralParseException | UnknownConfigurationGroupWordException | UnsupportedResultTypeException e) {
            throw e;
        } catch (WrongDurationFormatException e) {
            throw new WrongDurationFormatException();
        } catch (ModHappyException e) {
            throw new UnknownCommandException(userInput);
        } catch (Exception e) {
            throw new UnknownCommandException(userInput);
        }
    }

    /**
     * Returns the Parser object for parsing commands associated with the given command word.
     * If the command takes no arguments, null is returned.
     * @param commandWord the command word (e.g. "add")
     * @return an instance of the relevant Parser object or null
     * @throws UnknownCommandException if commandWord does not correspond with any command
     */
    private Parser getCommandParser(String commandWord) throws UnknownCommandException {
        switch (commandWord) {
        case (EXIT_COMMAND_WORD):
        case (GPA_COMMAND_WORD):
        case (SAVE_COMMAND_WORD):
        case (RESET_COMMAND_WORD):
            // Intentional fallthrough
            return new NoArgumentParser(commandWord);
        case (LIST_COMMAND_WORD):
            return new ListParser();
        case (ADD_COMMAND_WORD):
            return AddParser.getParser(parsedCommand.get(ARGUMENT).split(WHITESPACES)[0]);
        case (DELETE_COMMAND_WORD):
            return new DeleteParser();
        case (MARK_COMMAND_WORD):
            return new MarkParser();
        case (EDIT_COMMAND_WORD):
            return EditParser.getParser(parsedCommand.get(ARGUMENT).split(WHITESPACES)[0]);
        case (HELP_COMMAND_WORD):
            return new HelpParser();
        case (TAG_COMMAND_WORD):
            return new TagParser();
        case (GRADE_COMMAND_WORD):
            return new GradeParser();
        case (OPTION_COMMAND_WORD):
            return new OptionParser();
        default:
            throw new UnknownCommandException();
        }
    }
}
