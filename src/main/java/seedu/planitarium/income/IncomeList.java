package seedu.planitarium.income;

import java.util.ArrayList;

public class IncomeList {

    private ArrayList<Income> incomeArrayList;
    private int numberOfIncomes;

    /**
     * Creates a new Income Object.
     */
    public IncomeList() {
        this.incomeArrayList = new ArrayList<>();
    }

    /**
     * Creates and add a new income object to the income list.
     * @param description The description of the user's income
     * @param amount The income amount
     */
    public void addIncome(String description, double amount) {
        assert (description != null);
        assert (amount >= 0);
        this.incomeArrayList.add(new Income(description, amount));
        numberOfIncomes++;
    }

    /**
     * Returns the amount of an income object in the list.
     * @param index The index of the income on the person's income list
     * @return The income amount
     */
    public double getIncomeValue(int index) {
        assert (index > 0);
        assert (index <= numberOfIncomes);
        return incomeArrayList.get(index - 1).getAmount();
    }

    /**
     * Removes an income object from list of income.
     * @param index The index of the income on the person's income list
     */
    public void remove(int index) {
        assert (index > 0);
        assert (index <= numberOfIncomes);
        incomeArrayList.remove(index - 1);
        numberOfIncomes--;
    }

    /**
     * Returns the description of an income object from a
     * person's expenditure list.
     * @param index The index of the income on the list
     * @return The description of the income
     */
    public String getDescription(int index) {
        assert (index > 0);
        assert (index <= numberOfIncomes);
        return incomeArrayList.get(index - 1).getDescription();
    }

    /**
     * Returns the number of income in the person's income list.
     * @return The number of income
     */
    public int getNumberOfIncomes() {
        return numberOfIncomes;
    }

    /**
     * Returns the total income amount in the person's list.
     * @return The total amount of all income in the list
     */
    public double getTotalIncome() {
        double totalAmount = 0;
        for (Income item : incomeArrayList) {
            totalAmount += item.amount;
        }
        return totalAmount;
    }

    public void printIncomeList() {
        int listIndex = 1;
        for (Income item : incomeArrayList) {
            System.out.println(listIndex++ + ". " + item);
        }
    }
}