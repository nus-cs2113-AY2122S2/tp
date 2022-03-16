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
        return incomeArrayList.get(index - 1).getAmount();
    }

    public void remove(int index) {
        incomeArrayList.remove(index - 1);
        numberOfIncomes--;
    }

    public String getDescription(int index) {
        return incomeArrayList.get(index - 1).getDescription();
    }

    public int getNumberOfIncomes() {
        return numberOfIncomes;
    }

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