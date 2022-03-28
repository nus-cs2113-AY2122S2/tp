//@@author teanweijun

package seedu.planitarium.person;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.category.Category;
import seedu.planitarium.global.Constants;
import seedu.planitarium.money.IncomeList;
import seedu.planitarium.money.ExpenditureList;

import java.util.logging.Level;

public class Person {
    private String name;
    private IncomeList incomeList;
    private ExpenditureList expenditureList;
    private static ProjectLogger logger = new ProjectLogger(Person.class.getName(), "Person.log");

    /**
     * Constructs a new Person object.
     *
     * @param name The name of the person to be created
     */
    public Person(String name) {
        assert (name != null);
        this.name = name;
        incomeList = new IncomeList();
        expenditureList = new ExpenditureList();
        String infoString = "New Person initialised";
        logger.log(Level.INFO, infoString);
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
     * Returns the list of incomes.
     *
     * @return The person's list of incomes
     */
    public IncomeList getIncomeList() {
        return incomeList;
    }

    /**
     * Returns the list of expenditures.
     *
     * @return The person's list of expenditures
     */
    public ExpenditureList getExpenditureList() {
        return expenditureList;
    }

    /**
     * Adds an income to the list of incomes.
     *
     * @param description The source of the income
     * @param amount The value of the income
     * @param isPermanent Whether the income is recurring
     * @param isSilent Whether to print confirmation
     */
    public void addIncome(String description, double amount, boolean isPermanent, boolean isSilent) {
        assert (description != null);
        this.incomeList.addIncome(description, amount, isPermanent);
        if (isSilent) {
            return;
        }
        if (isPermanent) {
            System.out.println("A recurring income of " + amount + " from " + description + " has been added to "
                    + name);
        } else {
            System.out.println("An income of " + amount + " from " + description + " has been added to " + name);
        }
    }

    /**
     * Removes an income from the list of incomes.
     *
     * @param incomeIndex The index of the income to be removed
     */
    public void deleteIncome(int incomeIndex) {
        assert (incomeIndex >= Constants.SINGULAR);
        assert (incomeIndex <= getNumberOfIncomes());
        String description = incomeList.getDescription(incomeIndex);
        double value = incomeList.getIncomeValue(incomeIndex);
        incomeList.remove(incomeIndex);
        System.out.println("An income of " + value + " for " + description + " has been removed from " + name);
    }

    /**
     * Adds an expenditure to the list of expenditures.
     *
     * @param description The reason for the expenditure
     * @param amount The value of the expenditure
     * @param category The category of the expenditure
     * @param isPermanent Whether the expenditure is recurring
     * @param isSilent Whether to print confirmation
     */
    public void addExpend(String description, double amount, int category, boolean isPermanent, boolean isSilent) {
        assert (description != null);
        expenditureList.addExpenditure(description, amount, category, isPermanent);
        if (isSilent) {
            return;
        }
        if (isPermanent) {
            System.out.println("A recurring expenditure of " + amount + " for " + description + " has been added to "
                    + name);
        } else {
            System.out.println("An expenditure of " + amount + " for " + description + " has been added to " + name);
        }
    }

    /**
     * Removes an expenditure from the list of expenditures.
     *
     * @param index The index of the expenditure to be removed.
     */
    public void deleteExpend(int index) {
        assert (index >= Constants.SINGULAR);
        assert (index <= getNumberOfExpenditures());
        String description = expenditureList.getDescription(index);
        double value = expenditureList.getExpenditureValue(index);
        expenditureList.remove(index);
        System.out.println("An expenditure of " + value + " for " + description
                + " has been removed from " + name);
    }

    /**
     * Lists the expenditures of the person.
     */
    public void listExpenditure() {
        System.out.println("Here is the expenditure list for " + name + ":");
        expenditureList.printExpenditureList();
    }

    /**
     * Lists the income of the person.
     */
    public void listIncome() {
        System.out.println("Here is the income list for " + name + ":");
        incomeList.printIncomeList();
    }

    /**
     * Returns the total value of the person's expenditures.
     *
     * @return Total value of expenditures
     */
    public double getTotalExpenditure() {
        return expenditureList.getTotalExpenditure();
    }

    /**
     * Returns the total value of the person's incomes.
     *
     * @return Total value of incomes
     */
    public double getTotalIncome() {
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

    /**
     * Returns the name in a format suitable for saving.
     *
     * @return The name with delimiter
     */
    public String saveName() {
        return "u " + name;
    }

    /**
     *  Edits an income in the list of incomes.
     *
     * @param incomeIndex The index of the income
     * @param description The source of the income
     * @param amount The value of the income
     * @param isPermanent Whether the income is recurring
     */
    public void editIncome(int incomeIndex, String description, double amount, boolean isPermanent) {
        incomeList.editIncome(incomeIndex, description, amount, isPermanent);
    }

    /**
     *  Edits an expenditure in the list of expenditures of the specified person.
     *
     * @param expendIndex The index of the expenditure
     * @param description The reason for the expenditure
     * @param amount The value of the expenditure
     * @param category The category of the expenditure
     * @param isPermanent Whether the expenditure is recurring
     */
    public void editExpend(int expendIndex, String description, double amount, int category, boolean isPermanent) {
        expenditureList.editExpend(expendIndex, description, amount, category, isPermanent);
    }
}
