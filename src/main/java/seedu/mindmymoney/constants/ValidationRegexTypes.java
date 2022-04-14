package seedu.mindmymoney.constants;

/**
 * Container for validation regex types used in commands.
 */
public class ValidationRegexTypes {
    public static final String VALIDATION_REGEX_D =
        "^([0][1-9]|[12][0-9]|3[01])/([0][1-9]|1[012])/([0-9][0-9][0-9][0-9])$";
    public static final String VALIDATION_REGEX_M = "^([0][1-9]|1[012])/([0-9][0-9][0-9][0-9])$";
    public static final String VALIDATION_REGEX_Y = "^([0-9][0-9][0-9][0-9])$";
}
