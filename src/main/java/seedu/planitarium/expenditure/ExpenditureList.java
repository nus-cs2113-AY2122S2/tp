package seedu.planitarium.expenditure;

import seedu.planitarium.ProjectLogger;

import java.util.ArrayList;
import java.util.logging.Level;

public class ExpenditureList {
    private static int BASE_INDEX = 1;
    private ArrayList<Expenditure> expenditureArrayList;
    private int numberOfExpenditures;
    private ProjectLogger logger;

    /**
     * Creates a new ExpenditureList object.
     */
    public ExpenditureList() {
        this.expenditureArrayList = new ArrayList<>();
        logger = new ProjectLogger(ExpenditureList.class.getName(), "ExpenditureList.log");
        logger.getLogger().log(Level.INFO, "Logger for ExpenditureList initialised.");
    }

    /**
     * Adds an expenditure record to the expenditure list.
     *
     * @param description The description of what the user had spent on
     * @param amount The cost for this expenditure
     */
    public void addExpenditure(String description, double amount) {
        logger.getLogger().log(Level.INFO, "addExpenditure() called");
        assert (description != null);
        logger.getLogger().log(Level.INFO, "Passed addExpenditure() assertions");
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
        logger.getLogger().log(Level.INFO, "getExpenditureValue() called");
        assert (index >= BASE_INDEX);
        assert (index <= numberOfExpenditures);
        logger.getLogger().log(Level.INFO, "Passed getExpenditureValue() assertions");
        return expenditureArrayList.get(index - 1).getAmount();
    }

    /**
     * Removes an expenditure entry based on its index on the list.
     *
     * @param index The index of the expenditure on the person's expenditure list
     */
    public void remove(int index) {
        logger.getLogger().log(Level.INFO, "remove() called");
        assert (index >= BASE_INDEX);
        assert (index <= numberOfExpenditures);
        logger.getLogger().log(Level.INFO, "Passed remove() assertions");
        expenditureArrayList.remove(index - 1);
        numberOfExpenditures--;
    }

    /**
     * Returns the total cost of all expenditure in the person's list.
     *
     * @return The total cost of all expenditure in the list
     */
    public double getTotalExpenditure() {
        logger.getLogger().log(Level.INFO, "getTotalExpenditure() called");
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
        logger.getLogger().log(Level.INFO, "printExpenditureList() called");
        int listIndex = BASE_INDEX;
        for (Expenditure item : expenditureArrayList) {
            System.out.println(listIndex++ + ". " + item);
        }
    }

    /**
     * Returns the number of entries in the person's expenditure list.
     * @return The number of expenditure entries
     */
    public int getNumberOfExpenditures() {
        logger.getLogger().log(Level.INFO, "getNumberOfExpenditure() called");
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
        logger.getLogger().log(Level.INFO, "getDescription() called");
        assert (index >= BASE_INDEX);
        assert (index <= numberOfExpenditures);
        logger.getLogger().log(Level.INFO, "Passed getDescription() Assertions");
        return expenditureArrayList.get(index - 1).getDescription();
    }
}
