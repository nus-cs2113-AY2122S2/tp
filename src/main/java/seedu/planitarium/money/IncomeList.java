package seedu.planitarium.money;

import seedu.planitarium.ProjectLogger;

import java.util.ArrayList;
import java.util.logging.Level;

public class IncomeList extends MoneyList {

    private ArrayList<Income> incomeArrayList;
    private int numberOfIncomes;

    private static final String LOG_CLASS_NAME = IncomeList.class.getSimpleName();
    private static final String LOG_FILE_PATH = LOG_CLASS_NAME + ".log";
    private static final String LOG_INIT = "Logger for " + LOG_CLASS_NAME + " initialised.";
    private static final String LOG_ADD_INC = "addIncome()";
    private static final String LOG_GET_INC_VAL = "getIncomeValue()";
    private static final String LOG_GET_NUM_INC = "getNumberOfIncomes()";
    private static final String LOG_GET_TOTAL_INC = "getTotalIncome()";
    private static final String LOG_PRINT_LIST = "printIncomeList()";

    /**
     * Creates a new Income Object.
     */
    public IncomeList() {
        this.incomeArrayList = new ArrayList<>();
        logger = new ProjectLogger(LOG_CLASS_NAME, LOG_FILE_PATH);
        logger.getLogger().log(Level.INFO, LOG_INIT);
    }

    /**
     * Creates and add a new income object to the income list.
     * @param description The description of the user's income
     * @param amount The income amount
     */
    public void addIncome(String description, double amount) {
        logger.getLogger().log(Level.INFO, LOG_ADD_INC);
        assert (description != null);
        assert (amount >= 0);
        logger.getLogger().log(Level.INFO, LOG_ASSERT_PASSED);
        this.incomeArrayList.add(new Income(description, amount));
        numberOfIncomes++;
    }

    /**
     * Returns the amount of an income object in the list.
     * @param index The index of the income on the person's income list
     * @return The income amount
     */
    public double getIncomeValue(int index) {
        logger.getLogger().log(Level.INFO, LOG_GET_INC_VAL);
        assert (index > ARRAY_INDEX);
        assert (index <= numberOfIncomes);
        logger.getLogger().log(Level.INFO, LOG_ASSERT_PASSED);
        return incomeArrayList.get(index - 1).getAmount();
    }

    /**
     * Removes an income object from list of income.
     * @param index The index of the income on the person's income list
     */
    public void remove(int index) {
        logger.getLogger().log(Level.INFO, LOG_REMOVE);
        assert (index > ARRAY_INDEX);
        assert (index <= numberOfIncomes);
        logger.getLogger().log(Level.INFO, LOG_ASSERT_PASSED);
        incomeArrayList.remove(index - 1);
        numberOfIncomes--;
    }

    /**
     * Returns the description of an income object from a
     * person's Income list.
     * @param index The index of the income on the list
     * @return The description of the income
     */
    public String getDescription(int index) {
        logger.getLogger().log(Level.INFO, LOG_DESC);
        assert (index > ARRAY_INDEX);
        assert (index <= numberOfIncomes);
        logger.getLogger().log(Level.INFO, LOG_ASSERT_PASSED);
        return incomeArrayList.get(index - 1).getDescription();
    }

    /**
     * Returns the number of income in the person's income list.
     * @return The number of income
     */
    public int getNumberOfIncomes() {
        logger.getLogger().log(Level.INFO, LOG_GET_NUM_INC);
        return numberOfIncomes;
    }

    /**
     * Returns the total income amount in the person's list.
     * @return The total amount of all income in the list
     */
    public double getTotalIncome() {
        logger.getLogger().log(Level.INFO, LOG_GET_TOTAL_INC);
        double totalAmount = 0;
        for (Income item : incomeArrayList) {
            totalAmount += item.amount;
        }
        return totalAmount;
    }

    /**
     * Prints all income in the person's income list.
     */
    public void printIncomeList() {
        logger.getLogger().log(Level.INFO, LOG_PRINT_LIST);
        int listIndex = 1;
        for (Income item : incomeArrayList) {
            System.out.println(listIndex++ + ". " + item);
        }
    }

    public ArrayList<Income> getIncomeArrayList() {
        return incomeArrayList;
    }
}