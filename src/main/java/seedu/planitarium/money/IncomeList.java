//@@author tjiarong

package seedu.planitarium.money;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.global.Constants;

import java.lang.invoke.ConstantBootstraps;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
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
     *
     * @param description The description of the user's income
     * @param amount      The income amount
     * @param isPermanent The recurring status of the income
     */
    public void addIncome(String description, Double amount, Boolean isPermanent) {
        logger.log(Level.INFO, LOG_ADD_INC);
        assert (description != null);
        assert (amount >= 0);
        logger.log(Level.INFO, LOG_ASSERT_PASSED);
        this.incomeArrayList.add(new Income(description, amount, isPermanent));
        numberOfIncomes++;
    }

    /**
     * Removes an income object from list of income.
     *
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
     *
     * @return The total amount of all income in the list
     */
    public Double getTotalIncome() {
        updateList();
        logger.log(Level.INFO, LOG_GET_TOTAL_INC);
        Double totalAmount = 0.0;
        for (Income item : incomeArrayList) {
            totalAmount += item.amount;
        }
        return totalAmount;
    }

    /**
     * Prints all income in the person's income list.
     */
    public void printIncomeList() {
        updateList();
        logger.log(Level.INFO, LOG_PRINT_LIST);
        int listIndex = 1;
        for (Income item : incomeArrayList) {
            System.out.println(Constants.INDENTATION + listIndex++ + ". " + item);
        }
    }

    /**
     * Returns the number of income in the person's income list.
     *
     * @return The number of income
     */
    public int getNumberOfIncomes() {
        updateList();
        logger.log(Level.INFO, LOG_GET_NUM_INC);
        return numberOfIncomes;
    }

    /**
     * Returns the amount of an income object in the list.
     *
     * @param index The index of the income on the person's income list
     * @return The income amount
     */
    public Double getIncomeValue(int index) {
        logger.log(Level.INFO, LOG_GET_INC_VAL);
        assert (index > ARRAY_INDEX);
        assert (index <= numberOfIncomes);
        logger.log(Level.INFO, LOG_ASSERT_PASSED);
        return incomeArrayList.get(index - 1).getAmount();
    }

    /**
     * Returns the description of an income object from a
     * person's Income list.
     *
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
     *
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
     *
     * @param index The index of the income on the list
     * @return The recurring status of the income
     */
    public Boolean isPermanent(int index) {
        logger.log(Level.INFO, LOG_PERM);
        assert (index > ARRAY_INDEX);
        assert (index <= numberOfIncomes);
        logger.log(Level.INFO, LOG_ASSERT_PASSED);
        return incomeArrayList.get(index - 1).isPermanent();
    }

    /**
     * Returns the current income list.
     *
     * @return The current income list
     */
    public ArrayList<Income> getIncomeArrayList() {
        return incomeArrayList;
    }

    /**
     * Edits the income object's attribute based on the user's input values.
     *
     * @param index       The income object to be updated
     * @param description The new description, if any
     * @param amount      The new amount, if any
     * @param isPermanent The new recurring status, if any
     */
    public void editIncome(int index, String description, Double amount, Boolean isPermanent) {
        logger.log(Level.INFO, LOG_EDIT_INC);
        assert (index > ARRAY_INDEX);
        assert (index <= numberOfIncomes);
        logger.log(Level.INFO, LOG_ASSERT_PASSED);
        Income item = incomeArrayList.get(index - 1);
        boolean isDescEdited = editIncDesc(item, description);
        boolean isAmountEdited = editIncAmount(item, amount);
        boolean isPermEdited = editIncPerm(item, isPermanent);
        printEditMsg(item, isDescEdited, isAmountEdited, isPermEdited);
    }

    private void printEditMsg(Income item, boolean isDescEdited, boolean isAmountEdited, boolean isPermEdited) {
        if (isDescEdited || isAmountEdited || isPermEdited) {
            System.out.println("Your Income have been edited");
            System.out.println(item);
            return;
        }
        System.out.println("No changes have been made.");
    }

    /**
     * Edits the income's recurring status.
     *
     * @param item        The income object in the list
     * @param isPermanent The income's recurring status
     * @return true if recurring status have been edited, false otherwise
     */
    private boolean editIncPerm(Income item, Boolean isPermanent) {
        Boolean itemPerm = item.isPermanent();
        if (isPermanent != null && itemPerm != isPermanent) {
            item.setPermanent(isPermanent);
            return true;
        }
        return false;
    }

    /**
     * Edits the income's amount.
     *
     * @param item   The income object in the list
     * @param amount The income's amount
     * @return true if amount have been edited, false otherwise
     */
    private boolean editIncAmount(Income item, Double amount) {
        Double itemAmount = item.getAmount();
        if (amount != null && itemAmount != amount) {
            item.setAmount(amount);
            return true;
        }
        return false;
    }

    /**
     * Edits the income's description.
     *
     * @param item        The income object in the list
     * @param description The income's description.
     * @return true if description have been edited, false otherwise
     */
    private boolean editIncDesc(Income item, String description) {
        String oldDesc = item.getDescription();
        if (description != null && !oldDesc.equals(description)) {
            item.setDescription(description);
            return true;
        }
        return false;
    }

    /**
     * Search through income list for matching description or amount.
     *
     * @param description The user's search string.
     */
    public void find(String description) {
        updateList();
        logger.log(Level.INFO, LOG_FIND);
        for (Income item : incomeArrayList) {
            matchString(description, item);
        }
    }

    /**
     * Check if income's description or amount contains input string.
     *
     * @param description The user's search string.
     * @param item        The income object
     */
    private void matchString(String description, Income item) {
        Boolean hasDescription = item.getDescription().contains(description);
        Boolean hasAmount = Double.toString(item.getAmount()).contains(description);
        if (hasDescription || hasAmount) {
            System.out.println(item);
        }
    }

    /**
     * Iterates through income list and removes all expired income.
     */
    public void updateList() {
        for (Iterator<Income> iterator = incomeArrayList.iterator(); iterator.hasNext(); ) {
            Income item = iterator.next();
            checkIncomeDate(iterator, item);
        }
    }

    /**
     * Check and remove income if income is expired. In this case, it is
     * defined as any income not created this month.
     *
     * @param item The income object
     */
    private void checkIncomeDate(Iterator<Income> iterator, Income item) {
        int dateBefore = item.getInitDate().compareTo(LocalDate.now().withDayOfMonth(1));
        if (dateBefore < 0 && !item.isPermanent()) {
            iterator.remove();
            numberOfIncomes--;
        }
    }

    /**
     * Set the init date of a given income object in the list.
     *
     * @param index    The index of the specified income
     * @param initDate Init date of the income
     */
    public void setIncomeInitDate(int index, LocalDate initDate) {
        incomeArrayList.get(index - 1).setInitDate(initDate);
    }
}