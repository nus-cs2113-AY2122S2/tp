package seedu.duke.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.EditCommand;
import seedu.duke.exceptions.MissingCompulsoryParameterException;
import seedu.duke.exceptions.InvalidCompulsoryParameterException;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.util.StringConstants;

import java.util.HashMap;
import java.util.Objects;

/**
 * This Parser supports the "edit" command.
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
     * Determines the error that the user made in its command.
     * @throws ModHappyException based on the error that was made.
     */
    @Override
    public void determineError() throws ModHappyException {
        String moduleCode;
        String moduleDescription;
        try {
            moduleCode = userInput.split(SPACE)[FIRST_INDEX];
        } catch (IndexOutOfBoundsException e) {
            throw new MissingCompulsoryParameterException(MODULE_CODE_STR);
        }
        if (!moduleCode.matches(WORD_CHAR_ONLY)) {
            throw new InvalidCompulsoryParameterException(MODULE_CODE_STR, moduleCode);
        }
        try {
            moduleDescription = userInput.split(DESCRIPTION_FLAG)[FIRST_INDEX];
        } catch (IndexOutOfBoundsException e) {
            throw new MissingCompulsoryParameterException(MODULE_DESCRIPTION_STR);
        }
        if (!moduleDescription.matches(QUOTED_UNRESTRICTED_STR)) {
            throw new InvalidCompulsoryParameterException(MODULE_DESCRIPTION_STR, moduleDescription);
        }
        throw new InvalidCompulsoryParameterException();
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        this.userInput = userInput;
        HashMap<String, String> parsedArguments = parseString(userInput);
        String moduleCode = parsedArguments.get(MODULE_CODE);
        String moduleDescription = parsedArguments.get(MODULE_DESCRIPTION);
        if (!Objects.isNull(moduleCode)) {
            return new EditCommand(moduleCode, moduleDescription);
        }

        throw new ModHappyException();
    }

}
