package seedu.mindmymoney.userfinancial;

import seedu.mindmymoney.MindMyMoneyException;

/** Class for representing validation errors. */
public class ValidationException extends MindMyMoneyException {
    public ValidationException(String s) {
        super(s);
    }
}
