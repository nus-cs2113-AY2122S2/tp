package seedu.planitarium.money;

import seedu.planitarium.ProjectLogger;

import java.util.ArrayList;
import java.util.logging.Level;

public abstract class MoneyList {

    private ArrayList<Money> moneyArrayList;

    /**
     * Creates a new Money Object.
     */
    public MoneyList() {
        this.moneyArrayList = new ArrayList<>();
        logger = new ProjectLogger(LOG_CLASS_NAME, LOG_FILE_PATH);
        logger.getLogger().log(Level.INFO, LOG_INIT);
    }
}
