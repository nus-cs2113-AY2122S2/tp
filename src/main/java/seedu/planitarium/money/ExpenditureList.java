package seedu.planitarium.money;

import seedu.planitarium.ProjectLogger;

import java.util.ArrayList;
import java.util.logging.Level;

public class ExpenditureList extends MoneyList {

    private ArrayList<Expenditure> expenditureArrayList;
    private int numberOfExpenditures;

    private static final String LOG_CLASS_NAME = ExpenditureList.class.getSimpleName();
    private static final String LOG_FILE_PATH = LOG_CLASS_NAME + ".log";
    private static final String LOG_INIT = "Logger for " + LOG_CLASS_NAME + " initialised.";
    private static final String LOG_ADD_EXP = "addExpenditure()";
    private static final String LOG_GET_EXP_VAL = "getExpenditureValue()";
    private static final String LOG_GET_NUM_EXP = "getNumberOfExpenditures()";
    private static final String LOG_GET_TOTAL_EXP = "getTotalExpenditure()";
    private static final String LOG_PRINT_LIST = "printExpenditureList()";

    /**
     * Creates a new ExpenditureList object.
     */
    public ExpenditureList() {
        this.expenditureArrayList = new ArrayList<>();
        logger = new ProjectLogger(LOG_CLASS_NAME, LOG_FILE_PATH);
        logger.getLogger().log(Level.INFO, LOG_INIT);
    }

    /**
     * Adds an expenditure record to the expenditure list.
     *
     * @param description The description of what the user had spent on
     * @param amount The cost for this expenditure
     */
    public void addExpenditure(String description, double amount) {
        logger.getLogger().log(Level.INFO, LOG_ADD_EXP);
        assert (description != null);
        assert (amount >= 0);
        logger.getLogger().log(Level.INFO, LOG_ASSERT_PASSED);
        this.expenditureArrayList.add(new Expenditure(description, amount));
        numberOfExpenditures++;
    }

    /**
     * Returns the cost of a specific expenditure based on its index on the list.
     *
     * @param index The index of the expenditure on the person's expenditure list
     * @return The cost of the expenditure
     */
    public double getExpenditureValue(int index) {
        logger.getLogger().log(Level.INFO, LOG_GET_EXP_VAL);
        assert (index > ARRAY_INDEX);
        assert (index <= numberOfExpenditures);
        logger.getLogger().log(Level.INFO, LOG_ASSERT_PASSED);
        return expenditureArrayList.get(index - 1).getAmount();
    }

    /**
     * Removes an expenditure entry based on its index on the list.
     *
     * @param index The index of the expenditure on the person's expenditure list
     */
    public void remove(int index) {
        logger.getLogger().log(Level.INFO, LOG_REMOVE);
        assert (index > ARRAY_INDEX);
        assert (index <= numberOfExpenditures);
        logger.getLogger().log(Level.INFO, LOG_ASSERT_PASSED);
        expenditureArrayList.remove(index - 1);
        numberOfExpenditures--;
    }

    /**
     * Returns the total cost of all expenditure in the person's list.
     *
     * @return The total cost of all expenditure in the list
     */
    public double getTotalExpenditure() {
        logger.getLogger().log(Level.INFO, LOG_GET_TOTAL_EXP);
        double totalAmount = 0;
        for (Expenditure item : expenditureArrayList) {
            totalAmount += item.amount;
        }
        return totalAmount;
    }

    /**
     * Prints all expenditure entry in the person's list.
     */
    public void printExpenditureList() {
        logger.getLogger().log(Level.INFO, LOG_PRINT_LIST);
        int listIndex = 1;
        for (Expenditure item : expenditureArrayList) {
            System.out.println(listIndex++ + ". " + item);
        }
    }

    /**
     * Returns the number of entries in the person's expenditure list.
     * @return The number of expenditure entries
     */
    public int getNumberOfExpenditures() {
        logger.getLogger().log(Level.INFO, LOG_GET_NUM_EXP);
        return numberOfExpenditures;
    }

    /**
     * Returns the description of a specified expenditure index based on the
     * person's expenditure list.
     *
     * @param index The index of the expenditure on the list
     * @return The description of the expenditure
     */
    public String getDescription(int index) {
        logger.getLogger().log(Level.INFO, LOG_DESC);
        assert (index > ARRAY_INDEX);
        assert (index <= numberOfExpenditures);
        logger.getLogger().log(Level.INFO, LOG_ASSERT_PASSED);
        return expenditureArrayList.get(index - 1).getDescription();
    }

    public ArrayList<Expenditure> getExpenditureArrayList() {
        return expenditureArrayList;
    }
}
