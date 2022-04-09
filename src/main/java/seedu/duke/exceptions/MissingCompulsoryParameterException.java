package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author heekit73098
public class MissingCompulsoryParameterException extends GeneralParseException {
    private static final String ERROR_STRING = StringConstants.ERROR_PARSE_MISSING_PARAM;

    public MissingCompulsoryParameterException(String parameter) {
        super(ERROR_MESSAGE + String.format(ERROR_STRING, parameter));
    }
}
