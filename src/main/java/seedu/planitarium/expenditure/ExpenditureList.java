package seedu.planitarium.expenditure;

import java.util.ArrayList;

public class ExpenditureList {

    private ArrayList<Expenditure> expenditureArrayList;
    private int numberOfExpenditures;

    /**
     * Creates a new ExpenditureList object
     */
    public ExpenditureList() {
       this.expenditureArrayList = new ArrayList<>();
    }

    /**
     * Adds an expenditure record to the expenditure list.
     *
     * @param description The description of what the user had spent on
     * @param amount The cost for this expenditure
     */
    public void addExpenditure(String description, double amount) {
        assert (description != null);
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
        assert (index >= 1);
        assert (index <= numberOfExpenditures);
        return expenditureArrayList.get(index - 1).getAmount();
    }

    /**
     * Removes an expenditure entry based on its index on the list.
     *
     * @param index The index of the expenditure on the person's expenditure list
     */
    public void remove(int index) {
        assert (index >= 1);
        assert (index <= numberOfExpenditures);
        expenditureArrayList.remove(index - 1);
        numberOfExpenditures--;
    }

    /**
     * Returns the total cost of all expenditure in the person's list.
     *
     * @return The total cost of all expenditure in the list
     */
    public double getTotalExpenditure() {
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
        int listIndex = 1;
        for (Expenditure Item : expenditureArrayList) {
            System.out.println(listIndex++ + ". " + Item);
        }
    }

    /**
     * Returns the number of entries in the person's expenditure list.
     * @return The number of expenditure entries
     */
    public int getNumberOfExpenditures() {
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
        assert (index >= 1);
        assert (index <= numberOfExpenditures);
        return expenditureArrayList.get(index - 1).getDescription();
    }
}
