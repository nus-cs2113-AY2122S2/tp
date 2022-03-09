package seedu.planitarium;

import java.util.ArrayList;

public class ExpenditureList {

    protected ArrayList<Expenditure> expenditureArrayList;

    public ExpenditureList() {
       this.expenditureArrayList = new ArrayList<>();
    }

    public void addExpenditure(String description, double amount) {
        this.expenditureArrayList.add(new Expenditure(description, amount));
    }

    public double getExpenditureValue(int index) {
        double value = 0;
        try {
            value =  expenditureArrayList.get(index - 1).amount;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index");
        }

        return value;
    }

    public void remove(int index) {
        try {
            expenditureArrayList.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index");
        }
    }

    public double getTotalExpenditure() {
        double totalAmount = 0;
        for (Expenditure item : expenditureArrayList) {
            totalAmount += item.amount;
        }
        return totalAmount;
    }


    public void printExpenditureList() {
        int list_index = 1;
        for (Expenditure Item : expenditureArrayList) {
            System.out.println(list_index++ + ". " + Item);
        }
    }
}
