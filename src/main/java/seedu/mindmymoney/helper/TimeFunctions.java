package seedu.mindmymoney.helper;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.ValidationRegexTypes;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Container for functions needed to calculate and format time.
 */
public class TimeFunctions {

    /**
     * Checks if date input format is valid.
     *
     * @param input The string of the date input.
     * @return true if format is valid, false otherwise.
     */
    public static boolean isValidInput(String input) {
        if (input.matches(ValidationRegexTypes.VALIDATION_REGEX_D)
                || input.matches(ValidationRegexTypes.VALIDATION_REGEX_M)
                || input.matches(ValidationRegexTypes.VALIDATION_REGEX_Y)) {
            return true;
        }
        return false;

    }
}
