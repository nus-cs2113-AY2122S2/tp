package seedu.duke.parsers;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.exceptions.EmptyParamException;
import seedu.duke.exceptions.GeneralParseException;
import seedu.duke.exceptions.InvalidNumberException;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.InvalidCompulsoryParameterException;
import seedu.duke.exceptions.MissingNumberException;
import seedu.duke.exceptions.MissingCompulsoryParameterException;
import seedu.duke.util.NumberConstants;
import seedu.duke.util.StringConstants;

import java.util.HashMap;
import java.util.Objects;

/**
 * This Parser supports the "add mod" command.
 */
public class AddModuleParser extends AddParser {
    private static final String MODULE_CODE = StringConstants.MODULE_CODE;
    private static final String MODULE_DESCRIPTION = StringConstants.MODULE_DESCRIPTION;
    private static final String MODULE_DESCRIPTION_STR = StringConstants.MODULE_DESCRIPTION_STR;
    private static final String MODULAR_CREDIT = StringConstants.MODULAR_CREDIT;
    private static final int MAXIMUM_MODULAR_CREDITS = NumberConstants.MAXIMUM_MODULAR_CREDITS;
    private static final int MINIMUM_MODULAR_CREDITS = NumberConstants.MINIMUM_MODULAR_CREDITS;
    private String userInput;

    // Unescaped regex for testing (split across a few lines):
    // (mod\s+(?<moduleCode>\w+)(\s+(?<modularCredit>-?\d+)(?=(\s+-d\s+\"[^\"]+\")|.*$))(\s+(-d\s+\"
    // (?<moduleDescription>[^\"]+)\"))?)(?<invalid>.*)

    /* Explanation for regex:
     *
     * mod\s+(?<moduleCode>\w+)                          -- matches [mod moduleCode]
     *                                                      Same as above, note that moduleCode does not require "",
     *                                                      but must also be a single word composed of [a-zA-Z0-9_].
     *
     * (\s+(?<modularCredit>-?\d+)(?=(\s+-d\s+\"[^\"]+\")|.*$)) -- matches [modularCredit]
     *                                                             Must be a number
     *
     * (\s+(-d\s+\"(?<moduleDescription>[^\"]+)\"))?)(?<invalid>.*)    -- matches [-d "moduleDescription"] if present.
     *                                                                    Optional
     *                                                                    Does not accept " as a valid character.
     *
     * (?<invalid>.*)                                    -- matches [invalid]
     *                                                      Any other excess inputs
     */

    private static final String ADD_FORMAT = "(mod\\s+(?<moduleCode>\\w+)(\\s+(?<modularCredit>-?\\d+)"
            + "(?=(\\s+-d\\s+\\\"[^\\\"]+\\\")|.*$))(\\s+(-d\\s+\\\""
            + "(?<moduleDescription>[^\\\"]+)\\\"))?)(?<invalid>.*)";
    private static final String WORD_CHAR_ONLY = StringConstants.WORD_CHAR_ONLY;
    private static final String UNRESTRICTED_INT = StringConstants.UNRESTRICTED_INT;

    public AddModuleParser() {
        super();
        // See also https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        commandFormat = ADD_FORMAT;
        groupNames.add(MODULE_CODE);
        groupNames.add(MODULE_DESCRIPTION);
        groupNames.add(MODULAR_CREDIT);
        groupNames.add(INVALID);
    }

    //@@author heekit73098
    /**
     * Determines the error that the user made in its command based on the compulsory parameters.
     * It first checks if the user input has a module code, and if the code is made up of only word characters.
     * Then it checks if the user input has a modular credit, and if the modular credit is an unrestricted integer
     * @throws MissingCompulsoryParameterException if module code is missing
     * @throws MissingNumberException if modular credit is missing
     * @throws InvalidNumberException if the modular credit is not in unrestricted integer format
     * @throws InvalidCompulsoryParameterException if the module code is not made up of only word characters
     */
    @Override
    public void determineError() throws MissingCompulsoryParameterException, MissingNumberException,
            InvalidNumberException, InvalidCompulsoryParameterException {
        String moduleCode;
        String modularCredit;

        try {
            moduleCode = userInput.split(SPACE)[FIRST_INDEX];
        } catch (IndexOutOfBoundsException e) {
            throw new MissingCompulsoryParameterException(MODULE_CODE_STR);
        }
        if (!moduleCode.matches(WORD_CHAR_ONLY)) {
            throw new InvalidCompulsoryParameterException(MODULE_CODE_STR, moduleCode);
        }
        try {
            modularCredit = userInput.split(SPACE)[SECOND_INDEX];
        } catch (IndexOutOfBoundsException e) {
            throw new MissingNumberException();
        }
        if (!modularCredit.matches(UNRESTRICTED_INT)) {
            throw new InvalidNumberException(modularCredit);
        }
        throw new InvalidCompulsoryParameterException();
    }

    /**
     * Parses the modular credit from a string to an integer, with checks on its validity.
     * @param modularCreditStr the string representation of the modular credit
     * @return the modular credits as an integer
     * @throws InvalidNumberException if the string cannot be parsed into an integer,
     *                                or if the credits is not in the range of 0 to 20 inclusive
     */
    private int parseModularCredit(String modularCreditStr) throws InvalidNumberException {
        int modularCredit;
        try {
            modularCredit = Integer.parseInt(modularCreditStr);
            if (modularCredit > MAXIMUM_MODULAR_CREDITS || modularCredit < MINIMUM_MODULAR_CREDITS) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            throw new InvalidNumberException(modularCreditStr);
        }
        return modularCredit;
    }

    private void checkForEmptyDescription(String moduleDescription) throws EmptyParamException {
        if (!Objects.isNull(moduleDescription) && moduleDescription.isBlank()) {
            throw new EmptyParamException(MODULE_DESCRIPTION_STR);
        }
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        this.userInput = userInput;
        HashMap<String, String> parsedArguments = parseString(userInput);
        final String moduleCode = parsedArguments.get(MODULE_CODE);
        final String moduleDescription = parsedArguments.get(MODULE_DESCRIPTION);
        final String modularCreditStr = parsedArguments.get(MODULAR_CREDIT);

        if (!Objects.isNull(moduleCode)) {
            int modularCredit = parseModularCredit(modularCreditStr);
            checkForEmptyDescription(moduleDescription);
            checksForExcessArg();
            return new AddCommand(AddCommand.AddObjectType.MODULE, moduleCode, moduleDescription, modularCredit);
        }
        throw new GeneralParseException();
    }
}
