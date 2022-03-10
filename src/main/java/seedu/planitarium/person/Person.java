package seedu.planitarium.person;

import seedu.planitarium.ExpenditureList;
import seedu.planitarium.IncomeList;

public class Person {
    protected static String name;
    protected static IncomeList incomeList;
    protected static ExpenditureList expenditureList;

    public Person(String name) {
        this.name = name;
        incomeList = new IncomeList();
        expenditureList = new ExpenditureList();
    }

    public String getName() {
        return name;
    }

    public static void addIncome(String description, double amount) {
        incomeList.addIncome(description, amount);
        System.out.println("An income of " + amount + " from " + description + " has been added");
    }

    public static void deleteIncome(int index) {
        String description = incomeList.getIncomeDescription(index);
        double value = incomeList.getIncomeValue(index);
        incomeList.remove(index);
        System.out.println("An income of " + value + " for " + description + " has been removed");
    }

    public static void addExpend(String description, int value) {
        expenditureList.addExpenditure(description, value);
        System.out.println("An expenditure of " + value + " for " + description + " has been added");
    }

    public static void deleteExpend(int index) {
        String description = expenditureList.getExpenditureDescription(index);
        double value = expenditureList.getExpenditureValue(index);
        expenditureList.remove(index);
        System.out.println("An expenditure of " + value + " for " + description + " has been removed");
    }

    public static void listExpenditure() {
        System.out.println("Here is the expenditure list for " + name);
        expenditureList.printExpenditureList();
    }

    public static void listIncome() {
        incomeList.printIncomeList();
    }

    private static float getTotalExpenditure() {
        float totalSum = 0;
        for (Expenditure item: expenditureList) {
            totalSum += item.getValue();
        }
        return totalSum;
    }

    private static float getTotalIncome() {
        float totalSum = 0;
        for (Expenditure item: incomeList) {
            totalSum += item.getValue();
        }
        return totalSum;
    }

    public static float getDisposable() {
        return getTotalIncome() - getTotalExpenditure();
    }
}
