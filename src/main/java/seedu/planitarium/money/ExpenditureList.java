//@@author HansHengGit

package seedu.planitarium.money;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.category.Category;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;

public class ExpenditureList extends MoneyList {

    private ArrayList<Expenditure> expenditureArrayList;
    private int numberOfExpenditures;

    private static final String LOG_CLASS_NAME = ExpenditureList.class.getSimpleName();
    private static final String LOG_FILE_PATH = LOG_CLASS_NAME + ".log";
    protected static final ProjectLogger logger = new ProjectLogger(LOG_CLASS_NAME, LOG_FILE_PATH);

    private static final String LOG_INIT = "Logger for " + LOG_CLASS_NAME + " initialised.";
    private static final String LOG_ADD_EXP = "addExpenditure()";
    private static final String LOG_GET_EXP_VAL = "getExpenditureValue()";
    private static final String LOG_GET_NUM_EXP = "getNumberOfExpenditures()";
    private static final String LOG_GET_TOTAL_EXP = "getTotalExpenditure()";
    private static final String LOG_PRINT_LIST = "printExpenditureList()";
    private static final String LOG_GET_CAT = "getCategory()";
    private static final String LOG_EDIT_EXP = "editExpenditure()";

    /**
     * Creates a new ExpenditureList object.
     */
    public ExpenditureList() {
        this.expenditureArrayList = new ArrayList<>();
        logger.log(Level.INFO, LOG_INIT);
    }

    /**
     * Adds an expenditure record to the expenditure list.
     *
     * @param description The description of what the user had spent on
     * @param amount      The cost for this expenditure
     * @param category    The integer label of the category
     * @param isPermanent The recurring status of the expenditure
     */
    public void addExpenditure(String description, double amount, int category, boolean isPermanent) {
        logger.log(Level.INFO, LOG_ADD_EXP);
        assert (description != null);
        assert (amount >= 0);
        logger.log(Level.INFO, LOG_ASSERT_PASSED);
        this.expenditureArrayList.add(new Expenditure(description, amount, category, isPermanent));
        numberOfExpenditures++;
    }

    /**
     * Removes an expenditure entry based on its index on the list.
     *
     * @param index The index of the expenditure on the person's expenditure list
     */
    public void remove(int index) {
        logger.log(Level.INFO, LOG_REMOVE);
        assert (index > ARRAY_INDEX);
        assert (index <= numberOfExpenditures);
        logger.log(Level.INFO, LOG_ASSERT_PASSED);
        expenditureArrayList.remove(index - 1);
        numberOfExpenditures--;
    }

    /**
     * Returns the total cost of all expenditure in the person's list.
     *
     * @return The total cost of all expenditure in the list
     */
    public double getTotalExpenditure() {
        logger.log(Level.INFO, LOG_GET_TOTAL_EXP);
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
        logger.log(Level.INFO, LOG_PRINT_LIST);
        int listIndex = 1;
        for (Expenditure item : expenditureArrayList) {
            System.out.println(listIndex++ + ". " + item);
        }
    }

    /**
     * Returns the number of entries in the person's expenditure list.
     *
     * @return The number of expenditure entries
     */
    public int getNumberOfExpenditures() {
        logger.log(Level.INFO, LOG_GET_NUM_EXP);
        return numberOfExpenditures;
    }

    /**
     * Returns the cost of a specific expenditure based on its index on the list.
     *
     * @param index The index of the expenditure on the person's expenditure list
     * @return The cost of the expenditure
     */
    public double getExpenditureValue(int index) {
        logger.log(Level.INFO, LOG_GET_EXP_VAL);
        assert (index > ARRAY_INDEX);
        assert (index <= numberOfExpenditures);
        logger.log(Level.INFO, LOG_ASSERT_PASSED);
        return expenditureArrayList.get(index - 1).getAmount();
    }

    /**
     * Returns the description of a specified expenditure index based on the
     * person's expenditure list.
     *
     * @param index The index of the expenditure on the list
     * @return The description of the expenditure
     */
    public String getDescription(int index) {
        logger.log(Level.INFO, LOG_DESC);
        assert (index > ARRAY_INDEX);
        assert (index <= numberOfExpenditures);
        logger.log(Level.INFO, LOG_ASSERT_PASSED);
        return expenditureArrayList.get(index - 1).getDescription();
    }

    public ArrayList<Expenditure> getExpenditureArrayList() {
        return expenditureArrayList;
    }

    //@@author tjiarong

    /**
     * Returns the date of an expenditure object from a
     * person's Expenditure list.
     *
     * @param index The index of the expenditure on the list
     * @return The date of the expenditure
     */
    public LocalDate getInitDate(int index) {
        logger.log(Level.INFO, LOG_DATE);
        assert (index > ARRAY_INDEX);
        assert (index <= numberOfExpenditures);
        logger.log(Level.INFO, LOG_ASSERT_PASSED);
        return expenditureArrayList.get(index - 1).getInitDate();
    }

    /**
     * Returns the recurring status of an expenditure object from a
     * person's Expenditure list.
     *
     * @param index The index of the expenditure on the list
     * @return The recurring of the expenditure
     */
    public boolean isPermanent(int index) {
        logger.log(Level.INFO, LOG_PERM);
        assert (index > ARRAY_INDEX);
        assert (index <= numberOfExpenditures);
        logger.log(Level.INFO, LOG_ASSERT_PASSED);
        return expenditureArrayList.get(index - 1).isPermanent();
    }

    /**
     * Returns the category of an expenditure object from a
     * person's Expenditure list.
     *
     * @param index The index of the expenditure on the list
     * @return The category of the expenditure
     */
    public String getCategory(int index) {
        logger.log(Level.INFO, LOG_GET_CAT);
        assert (index > ARRAY_INDEX);
        assert (index <= numberOfExpenditures);
        logger.log(Level.INFO, LOG_ASSERT_PASSED);
        return expenditureArrayList.get(index - 1).getCategory();
    }

    /**
     * Edits the expenditure object's attribute based on the user's input values.
     *
     * @param index       The expenditure object to be updated
     * @param description The new description, if any
     * @param amount      The new amount, if any
     * @param category    The new category, if any
     * @param isPermanent The new recurring status, if any
     */
    public void editExpenditure(int index, String description, double amount, int category, Boolean isPermanent) {
        logger.log(Level.INFO, LOG_EDIT_EXP);
        assert (index > ARRAY_INDEX);
        assert (index <= numberOfExpenditures);
        logger.log(Level.INFO, LOG_ASSERT_PASSED);
        editExpDesc(index, description);
        editExpAmount(index, amount);
        editExpCat(index, category);
        editExpPerm(index, isPermanent);
    }

    /**
     * Edits the expenditure's recurring status.
     *
     * @param index       The expenditure's index in the list
     * @param isPermanent The expenditure's recurring status
     */
    private void editExpPerm(int index, Boolean isPermanent) {
        if (isPermanent != null) {
            expenditureArrayList.get(index - 1).setPermanent(isPermanent);
        }
    }

    /**
     * Edits the expenditure's description.
     *
     * @param index    The expenditure's index in the list
     * @param category The expenditure's category
     */
    private void editExpCat(int index, Integer category) {
        if (category != null) {
            expenditureArrayList.get(index - 1).setCategory(category);
        }
    }

    /**
     * Edits the expenditure's amount.
     *
     * @param index  The expenditure's index in the list
     * @param amount The expenditure's amount
     */
    private void editExpAmount(int index, Double amount) {
        if (amount != null) {
            expenditureArrayList.get(index - 1).setAmount(amount);
        }
    }

    /**
     * Edits the expenditure's description.
     *
     * @param index       The expenditure's index in the list
     * @param description The expenditure's description
     */
    private void editExpDesc(int index, String description) {
        if (description != null) {
            expenditureArrayList.get(index - 1).setDescription(description);
        }
    }

    /**
     * Search through expenditure list for matching description or amount.
     *
     * @param description The user's search string.
     * @param category    The user's specified category
     */
    public void find(String description, int category) {
        logger.log(Level.INFO, LOG_FIND);
        if (category == 0) {
            matchString(description);
        } else {
            matchString(description, category);
        }
    }

    /**
     * Check the expenditure list for expenditure where its description or amount
     * contains input string.
     *
     * @param description The user's search string.
     */
    private void matchString(String description) {
        for (Expenditure item : expenditureArrayList) {
            boolean hasDescription = item.getDescription().contains(description);
            boolean hasAmount = Double.toString(item.getAmount()).contains(description);
            if (hasDescription || hasAmount) {
                System.out.println(item);
            }
        }
    }

    /**
     * Check the expenditure list for expenditure where its description or amount
     * contains input string and is the specified category.
     *
     * @param description The user's search string.
     * @param category    The user's specified category
     */
    private void matchString(String description, int category) {
        for (Expenditure item : expenditureArrayList) {
            boolean inCategory = item.getCategory().equals(Category.getLabelForIndex(category));
            boolean hasDescription = item.getDescription().contains(description);
            boolean hasAmount = Double.toString(item.getAmount()).contains(description);
            if (inCategory && (hasDescription || hasAmount)) {
                System.out.println(item);
            }
        }
    }

    /**
     * Iterates through expenditure list and removes all expired expenditure.
     */
    public void updateList() {
        for (Expenditure item : expenditureArrayList) {
            checkExpenditureDate(item);
        }
    }

    /**
     * Check and remove expenditure if expenditure is expired. In this case, it is
     * defined as any expenditure not created this month.
     *
     * @param item The expenditure object
     */
    private void checkExpenditureDate(Expenditure item) {
        LocalDate itemDate = item.getInitDate();
        if (itemDate.getYear() <= LocalDate.now().getYear()
                && itemDate.getMonthValue() < LocalDate.now().getMonthValue()) {
            expenditureArrayList.remove(item);
        }
    }

    /**
     * Set the init date of a given expenditure object in the list.
     *
     * @param index    The index of the specified expenditure
     * @param initDate Init date of the expenditure
     */
    public void setExpenditureInitDate(int index, LocalDate initDate) {
        expenditureArrayList.get(index - 1).setInitDate(initDate);
    }
}
