package seedu.duke.parsers;

import java.util.HashMap;

import seedu.duke.commands.Command;
import seedu.duke.commands.OptionCommand;
import seedu.duke.exceptions.GeneralParseException;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.util.StringConstants;

//@@author Ch40gRv1-Mu
/**
 * This Parser supports the "option" command.
 */
public class OptionParser extends Parser {
    // Unescaped Regex for testing
    // ((?<configurationGroupWord>[A-Z_]+)(=(?<newValue>\w+))?)?(?<invalid>.*)
    private static final String OPTION_FORMAT = "((?<configurationGroupWord>[A-Z_]+)(=(?<newValue>\\w+))?)?"
            + "(?<invalid>.*)";
    private static final String CONFIGURATION_GROUP_WORD = StringConstants.CONFIGURATION_GROUP_WORD;
    private static final String NEW_VALUE = StringConstants.NEW_VALUE;

    public OptionParser() {
        super();
        this.commandFormat = OPTION_FORMAT;
        groupNames.add(CONFIGURATION_GROUP_WORD);
        groupNames.add(NEW_VALUE);
        groupNames.add(INVALID);
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
     * Parses the user input and extracts the parameters based on the command format.
     * @param userInput User input of the configuration word and its value to be set
     * @return A new {@code OptionCommand} object to view or set an option
     * @throws ModHappyException If there is an error parsing the command
     */
    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        HashMap<String, String> parsedArguments = parseString(userInput);
        String configurationGroupWord = parsedArguments.get(CONFIGURATION_GROUP_WORD);
        String newValue = parsedArguments.get(NEW_VALUE);
        checksForExcessArg();
        return new OptionCommand(configurationGroupWord, newValue);
    }
}
