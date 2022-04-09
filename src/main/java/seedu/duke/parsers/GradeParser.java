package seedu.duke.parsers;

import java.util.HashMap;
import java.util.Objects;

import seedu.duke.commands.Command;
import seedu.duke.commands.GradeCommand;
import seedu.duke.exceptions.InvalidModuleGradeException;
import seedu.duke.exceptions.MissingCompulsoryParameterException;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.InvalidCompulsoryParameterException;
import seedu.duke.util.StringConstants;

//@@author heekit73098
/**
 * This Parser supports the "grade" command.
 */
public class GradeParser extends Parser {
    public static final String MODULE_CODE = StringConstants.MODULE_CODE;
    public static final String MODULE_GRADE = StringConstants.MODULE_GRADE;
    private String userInput;

    // Unescaped regex for testing:
    // ((?<moduleCode>\\w+)(\\s+(?<moduleGrade>(?i)(CU|CS|[A-B][+-]?|[C-D][+]?|F|S|U)|
    // (?<invalidModuleGrade>.*))))(?<invalid>.*)
    private static final String GRADE_FORMAT = "((?<moduleCode>\\w+)(\\s+"
            + "(?<moduleGrade>(?i)(CU|CS|[A-B][+-]?|[C-D][+]?|F|S|U)|(?<invalidModuleGrade>.*))))(?<invalid>.*)";
    private static final String WORD_CHAR_ONLY = StringConstants.WORD_CHAR_ONLY;
    private static final String MODULE_GRADES_MATCH = StringConstants.MODULE_GRADES_MATCH;

    public GradeParser() {
        super();
        this.commandFormat = GRADE_FORMAT;
        groupNames.add(MODULE_CODE);
        groupNames.add(MODULE_GRADE);
        groupNames.add(INVALID);
        groupNames.add(INVALID_MODULE_GRADE);
    }

    /**
     * Determines the error that the user made in the grade command based on the compulsory parameters.
     * It will first check if the module code is present and if it is made up of only word characters.
     * Then it checks if the module grade entered is one of the grades specified.
     * @throws MissingCompulsoryParameterException if the module code is missing
     * @throws InvalidCompulsoryParameterException if the module code is not made up of only word characters
     * @throws InvalidModuleGradeException if the module grade is not valid or missing
     */
    @Override
    public void determineError() throws MissingCompulsoryParameterException,
            InvalidCompulsoryParameterException, InvalidModuleGradeException {
        String moduleCode;
        try {
            moduleCode = userInput.split(SPACE)[ZEROTH_INDEX];
        } catch (IndexOutOfBoundsException e) {
            throw new MissingCompulsoryParameterException(MODULE_CODE_STR);
        }
        if (!moduleCode.matches(WORD_CHAR_ONLY)) {
            throw new InvalidCompulsoryParameterException(MODULE_CODE_STR, moduleCode);
        }
        String moduleGrade;
        try {
            moduleGrade = userInput.split(SPACE)[FIRST_INDEX];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidModuleGradeException();
        }
        if (!moduleGrade.matches(MODULE_GRADES_MATCH)) {
            throw new InvalidModuleGradeException(moduleGrade);
        }
        throw new InvalidCompulsoryParameterException();
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        this.userInput = userInput;
        HashMap<String, String> parsedArguments = parseString(userInput);
        String moduleCode = parsedArguments.get(MODULE_CODE);
        String moduleGrade = parsedArguments.get(MODULE_GRADE).toUpperCase();
        if (!Objects.isNull(moduleCode)) {
            checksForExcessArg();
            return new GradeCommand(moduleCode, moduleGrade);
        }
        throw new ModHappyException();
    }
}
