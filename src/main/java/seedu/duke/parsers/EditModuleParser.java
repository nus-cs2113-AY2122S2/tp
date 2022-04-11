package seedu.duke.parsers;

import java.util.HashMap;
import java.util.Objects;

import seedu.duke.commands.Command;
import seedu.duke.commands.EditCommand;
import seedu.duke.exceptions.MissingCompulsoryParameterException;
import seedu.duke.exceptions.InvalidCompulsoryParameterException;
import seedu.duke.exceptions.InvalidFlagException;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.util.StringConstants;


//@@author heekit73098
/**
 * This Parser supports the "edit mod" command.
 */
public class EditModuleParser extends EditParser {

    private static final String MODULE_CODE = StringConstants.MODULE_CODE;
    private static final String MODULE_DESCRIPTION = StringConstants.MODULE_DESCRIPTION;
    private String userInput;

    // Unescaped regex for testing
    // (mod\s+(?<moduleCode>\w+?(?=(\s+-d\s+)))(\s+(-d\s+\"(?<moduleDescription>[^\"]*)\")))(?<invalid>.*)

    /* Explanation for regex:
     *
     * (mod\s+(?<moduleCode>\w+?(?=(\s+-d\s+)))              -- matches [mod moduleCode], matches flag -d
     *
     * (\s+(-d\s+\"(?<moduleDescription>[^\"]*)\")))         -- matches [-d "taskDescription"] can be empty
     *
     * (?<invalid>.*)                                        -- matches [invalid]
     *                                                          Any other excess inputs
     */
    private static final String EDIT_FORMAT = "(mod\\s+(?<moduleCode>\\w+?(?=(\\s+-d\\s+)))"
            + "(\\s+(-d\\s+\\\"(?<moduleDescription>[^\\\"]*)\\\")))(?<invalid>.*)";
    private static final String ANY_FLAG = StringConstants.ANY_FLAG;
    private static final String ANY_TEXT = StringConstants.ANY_TEXT;
    private static final String WORD_CHAR_ONLY = StringConstants.WORD_CHAR_ONLY;
    private static final String DESCRIPTION_FLAG = StringConstants.DESCRIPTION_FLAG;
    private static final String QUOTED_UNRESTRICTED_STR = StringConstants.QUOTED_UNRESTRICTED_STR;

    public EditModuleParser() {
        super();
        this.commandFormat = EDIT_FORMAT;
        groupNames.add(MODULE_CODE);
        groupNames.add(MODULE_DESCRIPTION);
        groupNames.add(INVALID);
    }

    /**
     * Determines the error that the user made in its command based on the compulsory parameters.
     * It will first check if the module code is present and if the module code is made up of word characters only.
     * Then it will check if the module description is present and if the flag is correct and the module description is
     * wrapped with double quotes.
     * @throws MissingCompulsoryParameterException If either module code is missing or module description is missing
     * @throws InvalidCompulsoryParameterException If either module code is not made up of all word characters or
     *                                             if module description is wrapped with double quotes
     * @throws InvalidFlagException If the flag used for the module description is incorrect
     */
    @Override
    public void determineError() throws MissingCompulsoryParameterException,
            InvalidCompulsoryParameterException, InvalidFlagException {
        checkForErrorInModuleCode();
        checkForErrorInModuleDescription();
        throw new InvalidCompulsoryParameterException();
    }

    /**
     * Checks if the error is in the module code.
     * It will check if the module code is present and if the module code is made up of word characters only.
     * @throws MissingCompulsoryParameterException If the module code is missing
     * @throws InvalidCompulsoryParameterException If the module code is not made up of word characters only
     */
    private void checkForErrorInModuleCode() throws MissingCompulsoryParameterException,
            InvalidCompulsoryParameterException {
        String moduleCode;
        try {
            moduleCode = userInput.split(WHITESPACES)[FIRST_INDEX];
        } catch (IndexOutOfBoundsException e) {
            throw new MissingCompulsoryParameterException(MODULE_CODE_STR);
        }
        if (!moduleCode.matches(WORD_CHAR_ONLY)) {
            throw new InvalidCompulsoryParameterException(MODULE_CODE_STR, moduleCode);
        }
    }

    /**
     * Checks if the error is in the module description.
     * It will check if the module description is present and if the flag is correct and the module description is
     * wrapped with double quotes.
     * @throws MissingCompulsoryParameterException If the module description is missing
     * @throws InvalidCompulsoryParameterException If the module description is not wrapped with double quotes
     * @throws InvalidFlagException If the flag used for the module description is invalid
     */
    private void checkForErrorInModuleDescription() throws MissingCompulsoryParameterException,
            InvalidCompulsoryParameterException, InvalidFlagException {
        String moduleDescription;
        try {
            moduleDescription = userInput.split(DESCRIPTION_FLAG)[FIRST_INDEX];
        } catch (IndexOutOfBoundsException e) {
            determineErrorInDescription();
            throw new MissingCompulsoryParameterException(MODULE_DESCRIPTION_STR);
        }
        if (!moduleDescription.matches(QUOTED_UNRESTRICTED_STR)) {
            throw new InvalidCompulsoryParameterException(MODULE_DESCRIPTION_STR, moduleDescription);
        }
    }

    /**
     * Determines the error in the module description.
     * It will first check if there is a description / flag.
     * Then it will check if the user input has a flag with its parameter wrapped in double quotes.
     * If there is, it means that the user has inputted the wrong flag.
     * @throws MissingCompulsoryParameterException if there is no description or flag
     * @throws InvalidFlagException if the user input the wrong flag
     */
    private void determineErrorInDescription() throws MissingCompulsoryParameterException, InvalidFlagException {
        String moduleFlag;
        try {
            moduleFlag = userInput.split(WHITESPACES)[SECOND_INDEX];
        } catch (IndexOutOfBoundsException e) {
            throw new MissingCompulsoryParameterException(MODULE_DESCRIPTION_STR);
        }

        if (userInput.matches(ANY_TEXT + ANY_FLAG + QUOTED_UNRESTRICTED_STR)) {
            throw new InvalidFlagException(moduleFlag);
        }
    }

    /**
     * Parses the user input and extracts the parameters based on the command format.
     * @param userInput User input of the module code and the module description
     * @return A new {@code EditCommand} object to edit the module description
     * @throws ModHappyException If there is an error parsing the command
     */
    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        this.userInput = userInput;
        HashMap<String, String> parsedArguments = parseString(userInput);
        String moduleCode = parsedArguments.get(MODULE_CODE);
        String moduleDescription = parsedArguments.get(MODULE_DESCRIPTION);
        if (!Objects.isNull(moduleCode)) {
            checksForExcessArg();
            return new EditCommand(moduleCode, moduleDescription);
        }
        throw new ModHappyException();
    }

}
