package seedu.planitarium.money;

import seedu.planitarium.ProjectLogger;

import java.util.ArrayList;
import java.util.logging.Level;

public abstract class MoneyList {

    protected ArrayList<Money> moneyArrayList;
    protected ProjectLogger logger;

    public static final int ARRAY_INDEX = 0;
    protected static final String LOG_FILE_PATH = LOG_CLASS_NAME + ".log";
    protected static final String LOG_INIT = "Logger for " + LOG_CLASS_NAME + " initialised.";
    protected static final String LOG_ASSERT_PASSED = "Assertion passed.";
    protected static final String LOG_REMOVE = "remove()";
    protected static final String LOG_DESC = "getDescription()";

    /**
     * Creates a new Money Object.
     */
    public MoneyList() {
    }
}
