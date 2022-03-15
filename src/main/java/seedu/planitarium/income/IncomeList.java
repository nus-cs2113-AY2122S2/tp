package seedu.planitarium.income;

import java.util.ArrayList;

public class IncomeList {

    private ArrayList<Income> incomeArrayList;
    private int numberOfIncomes;

    public IncomeList() {
        this.incomeArrayList = new ArrayList<>();
    }

    public void addIncome(String description, double amount) {
        this.incomeArrayList.add(new Income(description, amount));
        numberOfIncomes++;
    }

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

    public double getNumberOfIncome () {
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
        for (Income Item : incomeArrayList) {
            System.out.println(listIndex++ + ". " + Item);
        }
    }
}