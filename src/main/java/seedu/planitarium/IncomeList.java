package seedu.planitarium;

import exceptions.UnknownException;

import java.util.ArrayList;

public class IncomeList {

    private ArrayList<Income> incomeArrayList;

    public IncomeList() {
        this.incomeArrayList = new ArrayList<>();
    }

    public void addIncome(String description, double amount) {
        this.incomeArrayList.add(new Income(description, amount));
    }

    public double getIncomeValue(int index) {
        return incomeArrayList.get(index - 1).getAmount();
    }

    public void remove(int index) {
        incomeArrayList.remove(index - 1);
    }

    public double getTotalIncome() {
        double totalAmount = 0;
        for (Income item : incomeArrayList) {
            totalAmount += item.amount;
        }
        return totalAmount;
    }

    public void printIncomeList() {
        int list_index = 1;
        for (Income Item : incomeArrayList) {
            System.out.println(list_index++ + ". " + Item);
        }
    }
}