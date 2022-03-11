package seedu.planitarium.person;

import seedu.planitarium.ExpenditureList;
import seedu.planitarium.IncomeList;

public class Person {
    protected String name;
    protected IncomeList incomeList;
    protected ExpenditureList expenditureList;

    /**
     * Constructs a new Person object.
     *
     * @param name The name of the person to be created
     */
    public Person(String name) {
        this.name = name;
        incomeList = new IncomeList();
        expenditureList = new ExpenditureList();
    }

    /**
     * Returns the name of the person.
     *
     * @return The name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Adds an income to the list of incomes.
     *
     * @param description The source of the income
     * @param amount The value of the income
     */
    public static void addIncome(String description, double amount) {
        incomeList.addIncome(description, amount);
        System.out.println("An income of " + amount + " from " + description + " has been added");
    }

    /**
     * Removes an income from the list of incomes.
     *
     * @param index The index of the income to be removed
     */
    public static void deleteIncome(int index) {
        String description = incomeList.getDescription(index);
        double value = incomeList.getIncomeValue(index);
        incomeList.remove(index);
        System.out.println("An income of " + value + " for " + description + " has been removed");
    }

    /**
     * Adds an expenditure to the list of expenditures.
     *
     * @param description The reason for the expenditure
     * @param value The value of the expenditure
     */
    public static void addExpend(String description, int value) {
        expenditureList.addExpenditure(description, value);
        System.out.println("An expenditure of " + value + " for " + description + " has been added");
    }

    /**
     * Removes an expenditure from the list of expenditures.
     *
     * @param index The index of the expenditure to be removed.
     */
    public static void deleteExpend(int index) {
        String description = expenditureList.getDescription(index);
        double value = expenditureList.getExpenditureValue(index);
        expenditureList.remove(index);
        System.out.println("An expenditure of " + value + " for " + description + " has been removed");
    }

    /**
     * Lists the expenditures of the person.
     */
    public static void listExpenditure() {
        System.out.println("Here is the expenditure list for " + name);
        expenditureList.printExpenditureList();
    }

    /**
     * Lists the income of the person.
     */
    public static void listIncome() {
        System.out.println("Here is the income list for " + name);
        incomeList.printIncomeList();
    }

    /**
     * Returns the total value of the person's expenditures.
     *
     * @return Total value of expenditures
     */
    private static double getTotalExpenditure() {
        return expenditureList.getTotalExpenditure();
    }

    /**
     * Returns the total value of the person's incomes.
     *
     * @return Total value of incomes
     */
    private static double getTotalIncome() {
        return incomeList.getTotalIncome();
    }

    /**
     * Returns the amount of money leftover by the person. Can be negative.
     *
     * @return The total value contributed by the person
     */
    public static double getDisposable() {
        return getTotalIncome() - getTotalExpenditure();
    }

    /**
     * Returns the number of incomes the person has.
     *
     * @return The number of incomes
     */
    public static int getNumberOfIncome() {
        return incomeList.getNumberOfIncomes();
    }

    /**
     * Returns the number of expenditures the person has.
     *
     * @return The number of expenditures
     */
    public static int getNumberOfExpenditures() {
        return expenditureList.getNumberOfExpenditures();
    }
}
