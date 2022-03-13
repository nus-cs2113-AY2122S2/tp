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
        assert(name != NULL);
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
    public void addIncome(String description, double amount) {
        assert(description != NULL);
        this.incomeList.addIncome(description, amount);
        System.out.println("An income of " + amount + " from " + description + " has been added");
    }

    /**
     * Removes an income from the list of incomes.
     *
     * @param index The index of the income to be removed
     */
    public void deleteIncome(int index) {
        assert(index >= 1);
        assert(index <= getNumberOfIncomes());
        String description = incomeList.getDescription(index - 1);
        double value = incomeList.getIncomeValue(index - 1);
        incomeList.remove(index - 1);
        System.out.println("An income of " + value + " for " + description + " has been removed");
    }

    /**
     * Adds an expenditure to the list of expenditures.
     *
     * @param description The reason for the expenditure
     * @param value The value of the expenditure
     */
    public void addExpend(String description, int value) {
        assert(description != NULL);
        expenditureList.addExpenditure(description, value);
        System.out.println("An expenditure of " + value + " for " + description + " has been added");
    }

    /**
     * Removes an expenditure from the list of expenditures.
     *
     * @param index The index of the expenditure to be removed.
     */
    public void deleteExpend(int index) {
        assert(index >= 1);
        assert(index <= getNumberOfExpenditures());
        String description = expenditureList.getDescription(index - 1);
        double value = expenditureList.getExpenditureValue(index - 1);
        expenditureList.remove(index - 1);
        System.out.println("An expenditure of " + value + " for " + description + " has been removed");
    }

    /**
     * Lists the expenditures of the person.
     */
    public void listExpenditure() {
        System.out.println("Here is the expenditure list for " + name);
        expenditureList.printExpenditureList();
    }

    /**
     * Lists the income of the person.
     */
    public void listIncome() {
        System.out.println("Here is the income list for " + name);
        incomeList.printIncomeList();
    }

    /**
     * Returns the total value of the person's expenditures.
     *
     * @return Total value of expenditures
     */
    private double getTotalExpenditure() {
        return expenditureList.getTotalExpenditure();
    }

    /**
     * Returns the total value of the person's incomes.
     *
     * @return Total value of incomes
     */
    private double getTotalIncome() {
        return incomeList.getTotalIncome();
    }

    /**
     * Returns the amount of money leftover by the person. Can be negative.
     *
     * @return The total value contributed by the person
     */
    public double getDisposable() {
        return getTotalIncome() - getTotalExpenditure();
    }

    /**
     * Returns the number of incomes the person has.
     *
     * @return The number of incomes
     */
    public int getNumberOfIncomes() {
        return incomeList.getNumberOfIncomes();
    }

    /**
     * Returns the number of expenditures the person has.
     *
     * @return The number of expenditures
     */
    public int getNumberOfExpenditures() {
        return expenditureList.getNumberOfExpenditures();
    }
}
