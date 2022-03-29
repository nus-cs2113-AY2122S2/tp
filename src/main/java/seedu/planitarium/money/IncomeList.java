//@@author tjiarong

package seedu.planitarium.money;

import seedu.planitarium.ProjectLogger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;

public class IncomeList extends MoneyList {

    private ArrayList<Income> incomeArrayList;
    private int numberOfIncomes;

    private static final String LOG_CLASS_NAME = IncomeList.class.getSimpleName();
    private static final String LOG_FILE_PATH = LOG_CLASS_NAME + ".log";
    protected static final ProjectLogger logger = new ProjectLogger(LOG_CLASS_NAME, LOG_FILE_PATH);

    private static final String LOG_INIT = "Logger for " + LOG_CLASS_NAME + " initialised.";
    private static final String LOG_ADD_INC = "addIncome()";
    private static final String LOG_GET_INC_VAL = "getIncomeValue()";
    private static final String LOG_GET_NUM_INC = "getNumberOfIncomes()";
    private static final String LOG_GET_TOTAL_INC = "getTotalIncome()";
    private static final String LOG_PRINT_LIST = "printIncomeList()";
    private static final String LOG_EDIT_INC = "editIncome()";

    /**
     * Creates a new Income Object.
     */
    public IncomeList() {
        this.incomeArrayList = new ArrayList<>();
        logger.log(Level.INFO, LOG_INIT);
    }

    /**
     * Creates and add a new income object to the income list.
     * @param description The description of the user's income
     * @param amount The income amount
     * @param isPermanent The recurring status of the income
     */
    public void addIncome(String description, double amount, boolean isPermanent) {
        logger.log(Level.INFO, LOG_ADD_INC);
        assert (description != null);
        assert (amount >= 0);
        logger.log(Level.INFO, LOG_ASSERT_PASSED);
        this.incomeArrayList.add(new Income(description, amount, isPermanent));
        numberOfIncomes++;
    }

    /**
     * Removes an income object from list of income.
     * @param index The index of the income on the person's income list
     */
    public void remove(int index) {
        logger.log(Level.INFO, LOG_REMOVE);
        assert (index > ARRAY_INDEX);
        assert (index <= numberOfIncomes);
        logger.log(Level.INFO, LOG_ASSERT_PASSED);
        incomeArrayList.remove(index - 1);
        numberOfIncomes--;
    }

    /**
     * Returns the total income amount in the person's list.
     * @return The total amount of all income in the list
     */
    public double getTotalIncome() {
        logger.log(Level.INFO, LOG_GET_TOTAL_INC);
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
        logger.log(Level.INFO, LOG_PRINT_LIST);
        int listIndex = 1;
        for (Income item : incomeArrayList) {
            System.out.println(listIndex++ + ". " + item);
        }
    }

    /**
     * Returns the number of income in the person's income list.
     * @return The number of income
     */
    public int getNumberOfIncomes() {
        logger.log(Level.INFO, LOG_GET_NUM_INC);
        return numberOfIncomes;
    }

    /**
     * Returns the amount of an income object in the list.
     * @param index The index of the income on the person's income list
     * @return The income amount
     */
    public double getIncomeValue(int index) {
        logger.log(Level.INFO, LOG_GET_INC_VAL);
        assert (index > ARRAY_INDEX);
        assert (index <= numberOfIncomes);
        logger.log(Level.INFO, LOG_ASSERT_PASSED);
        return incomeArrayList.get(index - 1).getAmount();
    }

    /**
     * Returns the description of an income object from a
     * person's Income list.
     * @param index The index of the income on the list
     * @return The description of the income
     */
    public String getDescription(int index) {
        logger.log(Level.INFO, LOG_DESC);
        assert (index > ARRAY_INDEX);
        assert (index <= numberOfIncomes);
        logger.log(Level.INFO, LOG_ASSERT_PASSED);
        return incomeArrayList.get(index - 1).getDescription();
    }

    /**
     * Returns the date of an income object from a
     * person's Income list.
     * @param index The index of the income on the list
     * @return The date of the income
     */
    public LocalDate getInitDate(int index) {
        logger.log(Level.INFO, LOG_DATE);
        assert (index > ARRAY_INDEX);
        assert (index <= numberOfIncomes);
        logger.log(Level.INFO, LOG_ASSERT_PASSED);
        return incomeArrayList.get(index - 1).getInitDate();
    }

    /**
     * Returns the recurring status of an income object from a
     * person's Income list.
     * @param index The index of the income on the list
     * @return The recurring status of the income
     */
    public boolean isPermanent(int index) {
        logger.log(Level.INFO, LOG_PERM);
        assert (index > ARRAY_INDEX);
        assert (index <= numberOfIncomes);
        logger.log(Level.INFO, LOG_ASSERT_PASSED);
        return incomeArrayList.get(index - 1).isPermanent();
    }

    /**
     * Returns the current income list.
     * @return The current income list
     */
    public ArrayList<Income> getIncomeArrayList() {
        return incomeArrayList;
    }

    /**
     * Edits the income object's attribute based on the user's input values.
     * @param index The income object to be updated
     * @param description The new description, if any
     * @param amount The new amount, if any
     * @param isPermanent The new recurring status, if any
     */
    public void editIncome(int index, String description, double amount, Boolean isPermanent) {
        logger.log(Level.INFO, LOG_EDIT_INC);
        assert (index > ARRAY_INDEX);
        assert (index <= numberOfIncomes);
        logger.log(Level.INFO, LOG_ASSERT_PASSED);
        editIncDesc(index, description);
        editIncAmount(index, amount);
        editIncPerm(index, isPermanent);
    }

    /**
     * Edits the income's recurring status.
     * @param index The income's index in the list
     * @param isPermanent The income's recurring status
     */
    private void editIncPerm(int index, Boolean isPermanent) {
        if (isPermanent != null) {
            incomeArrayList.get(index - 1).setPermanent(isPermanent);
        }
    }

    /**
     * Edits the income's amount.
     * @param index The income's index in the list
     * @param isPermanent The income's amount
     */
    private void editIncAmount(int index, Double amount) {
        if (amount != null) {
            incomeArrayList.get(index - 1).setAmount(amount);
        }
    }

    /**
     * Edits the income's description.
     * @param index The income's index in the list
     * @param isPermanent The income's description.
     */
    private void editIncDesc(int index, String description) {
        if (description != null) {
            incomeArrayList.get(index - 1).setDescription(description);
        }
    }

    /**
     * Search through income list for matching description or amount.
     * @param description The user's search string.
     */
    public void find(String description) {
        logger.log(Level.INFO, LOG_FIND);
        for (Income item : incomeArrayList) {
            matchString(description, item);
        }
    }

    /**
     * Check if income's description or amount contains input string.
     * @param description The user's search string.
     * @param item The income object
     */
    private void matchString(String description, Income item) {
        if (item.getDescription().contains(description)
                || Double.toString(item.getAmount()).contains(description)) {
            System.out.println(item);
        }
    }

    /**
     * Iterates through income list and removes all expired income.
     */
    public void updateList() {
        for (Income item : incomeArrayList) {
            checkIncomeDate(item);
        }
    }

    /**
     * Check and remove income if income is expired. In this case, it is
     * defined as any income not created this month.
     * @param item The income object
     */
    private void checkIncomeDate(Income item) {
        LocalDate itemDate = item.getInitDate();
        if (itemDate.getYear() <= LocalDate.now().getYear()
                && itemDate.getMonthValue() < LocalDate.now().getMonthValue()) {
            incomeArrayList.remove(item);
        }
    }
}