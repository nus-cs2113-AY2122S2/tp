package seedu.planitarium.money;

import seedu.planitarium.ProjectLogger;

public abstract class MoneyList {

    protected ProjectLogger logger;

    public static final int ARRAY_INDEX = 0;
    protected static final String LOG_ASSERT_PASSED = "Assertion passed.";
    protected static final String LOG_REMOVE = "remove()";
    protected static final String LOG_DESC = "getDescription()";

    /**
     * Creates a new Money Object.
     */
    public MoneyList() {
    }
}
