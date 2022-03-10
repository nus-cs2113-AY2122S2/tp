package seedu.planitarium;

import java.util.ArrayList;

public class ExpenditureList {

    private ArrayList<Expenditure> expenditureArrayList;
    private int numberOfExpenditures;

    public ExpenditureList() {
       this.expenditureArrayList = new ArrayList<>();
    }

    public void addExpenditure(String description, double amount) {
        this.expenditureArrayList.add(new Expenditure(description, amount));
        numberOfExpenditures++;
    }

    public double getExpenditureValue(int index) {
        return expenditureArrayList.get(index - 1).amount;
    }

    public void remove(int index) {
        expenditureArrayList.remove(index - 1);
        numberOfExpenditures--;
    }

    public double getTotalExpenditure() {
        double totalAmount = 0;
        for (Expenditure item : expenditureArrayList) {
            totalAmount += item.amount;
        }
        return totalAmount;
    }


    public void printExpenditureList() {
        int listIndex = 1;
        for (Expenditure Item : expenditureArrayList) {
            System.out.println(listIndex++ + ". " + Item);
        }
    }
    
    public int getNumberOfExpenditures() {
        return numberOfExpenditures;
    }

    public String getDescription(int index) {
        return expenditureArrayList.get(index - 1).description;
    }
}
