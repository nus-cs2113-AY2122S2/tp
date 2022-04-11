//@@author teanweijun

package seedu.planitarium.family;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.global.Constants;
import seedu.planitarium.global.UI;
import seedu.planitarium.money.Expenditure;
import seedu.planitarium.money.Income;
import seedu.planitarium.money.IncomeList;
import seedu.planitarium.money.ExpenditureList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;

public class Person {
    private final String name;
    private final IncomeList incomeList;
    private final ExpenditureList expenditureList;

    private static final String LOG_CLASS_NAME = Person.class.getSimpleName();
    private static final String LOG_FILE_PATH = LOG_CLASS_NAME + ".log";
    private static final ProjectLogger LOGGER = new ProjectLogger(LOG_CLASS_NAME, LOG_FILE_PATH);

    /**
     * Constructs a new Person object.
     *
     * @param name The name of the person to be created
     */
    public Person(String name) {
        String infoString = "Entering Person()";
        LOGGER.log(Level.INFO, infoString);
        assert (name != null);
        this.name = name;
        infoString = "Non-null assertion passed in Person()";
        LOGGER.log(Level.INFO, infoString);
        incomeList = new IncomeList();
        expenditureList = new ExpenditureList();
        LOGGER.log(Level.INFO, Constants.PERSON_INIT_MESSAGE);
    }

    /**
     * Returns the name of the person.
     *
     * @return The name of the person
     */
    public String getName() {
        LOGGER.log(Level.INFO, Constants.GET_NAME_CALL_MESSAGE);
        return name;
    }

    /**
     * Returns the list of incomes.
     *
     * @return The person's list of incomes
     */
    public ArrayList<Income> getIncomeList() {
        LOGGER.log(Level.INFO, Constants.GET_INCOME_LIST_CALL_MESSAGE);
        return incomeList.getIncomeArrayList();
    }

    /**
     * Returns the list of expenditures.
     *
     * @return The person's list of expenditures
     */
    public ArrayList<Expenditure> getExpenditureList() {
        LOGGER.log(Level.INFO, Constants.GET_EXPEND_LIST_CALL_MESSAGE);
        return expenditureList.getExpenditureArrayList();
    }

    /**
     * Adds an income to the list of incomes.
     *
     * @param description The source of the income
     * @param amount The value of the income
     * @param isPermanent Whether the income is recurring
     * @param isSilent Whether to print confirmation
     */
    public void addIncome(String description, Double amount, Boolean isPermanent, Boolean isSilent) {
        LOGGER.log(Level.INFO, Constants.ADD_INCOME_CALL_MESSAGE);
        incomeList.addIncome(description, amount, isPermanent);
        if (isSilent) {
            return;
        }
        String income = UI.formatValue(amount);
        if (isPermanent) {
            System.out.println("A recurring income of " + income + " from " + description + " has been added to "
                    + name);
        } else {
            System.out.println("An income of " + income + " from " + description + " has been added to " + name);
        }
    }

    /**
     * Removes an income from the list of incomes.
     *
     * @param incomeIndex The index of the income to be removed
     */
    public void deleteIncome(int incomeIndex) {
        String infoString = "Entering deleteIncome()";
        LOGGER.log(Level.INFO, infoString);
        assert (incomeIndex >= Constants.SINGULAR);
        assert (incomeIndex <= getNumberOfIncomes());
        infoString = "Index assertions passed in deleteIncome()";
        LOGGER.log(Level.INFO, infoString);
        String description = incomeList.getDescription(incomeIndex);
        Double value = incomeList.getIncomeValue(incomeIndex);
        String valueString = UI.formatValue(value);
        incomeList.remove(incomeIndex);
        System.out.println("An income of " + valueString + " for " + description + " has been removed from " + name);
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
    public void addExpend(String description, Double amount, Integer category, Boolean isPermanent, Boolean isSilent) {
        LOGGER.log(Level.INFO, Constants.ADD_EXPEND_CALL_MESSAGE);
        expenditureList.addExpenditure(description, amount, category, isPermanent);
        if (isSilent) {
            return;
        }
        String expend = UI.formatValue(amount);
        if (isPermanent) {
            System.out.println("A recurring expenditure of " + expend + " for " + description + " has been added to "
                    + name);
        } else {
            System.out.println("An expenditure of " + expend + " for " + description + " has been added to " + name);
        }
    }

    /**
     * Removes an expenditure from the list of expenditures.
     *
     * @param expendIndex The index of the expenditure to be removed.
     */
    public void deleteExpend(int expendIndex) {
        String infoString = "Entering deleteExpend()";
        LOGGER.log(Level.INFO, infoString);
        assert (expendIndex >= Constants.SINGULAR);
        assert (expendIndex <= getNumberOfExpenditures());
        infoString = "Index assertions passed in deleteExpend()";
        LOGGER.log(Level.INFO, infoString);
        String description = expenditureList.getDescription(expendIndex);
        Double value = expenditureList.getExpenditureValue(expendIndex);
        String valueString = UI.formatValue(value);
        expenditureList.remove(expendIndex);
        System.out.println("An expenditure of " + valueString + " for " + description
                + " has been removed from " + name);
    }

    /**
     * Lists the expenditures of the person.
     */
    public void listExpenditure() {
        LOGGER.log(Level.INFO, Constants.LIST_EXPEND_CALL_MESSAGE);
        System.out.println(Constants.INDENTATION + "List of expenditures:");
        expenditureList.printExpenditureList();
    }

    /**
     * Lists the income of the person.
     */
    public void listIncome() {
        LOGGER.log(Level.INFO, Constants.LIST_INCOME_CALL_MESSAGE);
        System.out.println(Constants.INDENTATION + "List of incomes:");
        incomeList.printIncomeList();
    }

    /**
     * Returns the total value of the person's expenditures.
     *
     * @return Total value of expenditures
     */
    public Double getTotalExpenditure() {
        LOGGER.log(Level.INFO, Constants.GET_TOTAL_EXPEND_CALL_MESSAGE);
        return expenditureList.getTotalExpenditure();
    }

    /**
     * Returns the total value of the person's incomes.
     *
     * @return Total value of incomes
     */
    public Double getTotalIncome() {
        LOGGER.log(Level.INFO, Constants.GET_TOTAL_INCOME_CALL_MESSAGE);
        return incomeList.getTotalIncome();
    }

    /**
     * Returns the amount of money leftover by the person. Can be negative.
     *
     * @return The total value contributed by the person
     */
    public Double getDisposable() {
        LOGGER.log(Level.INFO, Constants.GET_DISPOSABLE_CALL_MESSAGE);
        return getTotalIncome() - getTotalExpenditure();
    }

    /**
     * Returns the number of incomes the person has.
     *
     * @return The number of incomes
     */
    public int getNumberOfIncomes() {
        LOGGER.log(Level.INFO, Constants.NUM_INCOMES_CALL_MESSAGE);
        return incomeList.getNumberOfIncomes();
    }

    /**
     * Returns the number of expenditures the person has.
     *
     * @return The number of expenditures
     */
    public int getNumberOfExpenditures() {
        LOGGER.log(Level.INFO, Constants.NUM_EXPENDS_CALL_MESSAGE);
        return expenditureList.getNumberOfExpenditures();
    }

    /**
     * Returns the name in a format suitable for saving.
     *
     * @return The name with delimiter
     */
    public String getSaveName() {
        LOGGER.log(Level.INFO, Constants.SAVE_NAME_CALL_MESSAGE);
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
    public void editIncome(int incomeIndex, String description, Double amount, Boolean isPermanent) {
        LOGGER.log(Level.INFO, Constants.EDIT_INCOME_CALL_MESSAGE);
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
    public void editExpend(int expendIndex, String description, Double amount, Integer category, Boolean isPermanent) {
        LOGGER.log(Level.INFO, Constants.EDIT_EXPEND_CALL_MESSAGE);
        expenditureList.editExpenditure(expendIndex, description, amount, category, isPermanent);
    }

    /**
     * Prints entries found in the category provided containing the stated description.
     *
     * @param description The string to look for
     * @param category The category of the entry
     */
    public void find(String description, Integer category) {
        LOGGER.log(Level.INFO, Constants.FIND_CALL_MESSAGE);
        System.out.println("Entries found for " + name + ":");
        incomeList.find(description);
        expenditureList.find(description, category);
    }

    /**
     * Sets the init date of the specified income.
     *
     * @param incomeIndex The index of the income
     * @param initDate The date to set to
     */
    public void setIncomeDate(int incomeIndex, LocalDate initDate) {
        incomeList.setIncomeInitDate(incomeIndex, initDate);
    }

    /**
     * Sets the init date of the specified expenditure.
     *
     * @param expendIndex The index of the expenditure
     * @param initDate The date to set to
     */
    public void setExpenditureDate(int expendIndex, LocalDate initDate) {
        expenditureList.setExpenditureInitDate(expendIndex, initDate);
    }
}
