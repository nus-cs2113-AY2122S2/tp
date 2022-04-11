//@@author teanweijun

package seedu.planitarium.family;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.global.Constants;
import seedu.planitarium.global.UI;

import java.util.logging.Level;

public class Family {
    private final PersonList parents;
    private final PersonList myGen;
    private final PersonList children;

    private static final String LOG_CLASS_NAME = Family.class.getSimpleName();
    private static final String LOG_FILE_PATH = LOG_CLASS_NAME + ".log";
    private static final ProjectLogger LOGGER = new ProjectLogger(LOG_CLASS_NAME, LOG_FILE_PATH);

    /**
     * Constructs a new Family object.
     */
    public Family() {
        parents = new PersonList();
        myGen = new PersonList();
        children = new PersonList();
        LOGGER.log(Level.INFO, Constants.FAMILY_INIT_MESSAGE);
    }

    /**
     * Returns the array list specified.
     *
     * @param group The index of the group to return
     * @return The array list specified by the group index
     */
    public PersonList getList(int group) {
        String infoString = "Entering getList()";
        LOGGER.log(Level.INFO, infoString);
        assert (group >= Constants.SINGULAR);
        assert (group <= Constants.NUM_GROUPS);
        infoString = "Index assertions passed in getList()";
        LOGGER.log(Level.INFO, infoString);
        PersonList toReturn;
        switch (group) {
        case Constants.PARENTS:
            toReturn = parents;
            break;
        case Constants.MY_GEN:
            toReturn = myGen;
            break;
        default:
            toReturn = children;
        }
        return toReturn;
    }

    /**
     * Returns how to address the array list specified.
     *
     * @param group The index of the group to address
     * @return The way to address the array list specified
     */
    private String getGenerationName(int group) {
        String infoString = "Entering getGenerationName()";
        LOGGER.log(Level.INFO, infoString);
        assert (group >= Constants.SINGULAR);
        assert (group <= Constants.NUM_GROUPS);
        infoString = "Index assertions passed in getGenerationName()";
        LOGGER.log(Level.INFO, infoString);
        String toReturn;
        switch (group) {
        case Constants.PARENTS:
            toReturn = "Parents";
            break;
        case Constants.MY_GEN:
            toReturn = "My generation";
            break;
        default:
            toReturn = "Children";
        }
        return toReturn;
    }

    /**
     * Adds a person to the array list specified by the group index.
     *
     * @param group The index of the group to add to
     * @param name The name of the person to be added
     * @param isSilent Whether to print confirmation
     */
    public void addPerson(int group, String name, Boolean isSilent) {
        LOGGER.log(Level.INFO, Constants.ADD_PERSON_CALL_MESSAGE);
        getList(group).addPerson(name);
        if (isSilent) {
            return;
        }
        String generation = getGenerationName(group);
        printPersonAdded(name, generation);
    }

    /**
     * Prints the name of the person added, and to which array list.
     *
     * @param name The name of the person added
     * @param generation The group the person is added to
     */
    private void printPersonAdded(String name, String generation) {
        System.out.println(name + " has been successfully added to " + generation);
    }

    /**
     * Removes a person from the array list specified by the group index.
     *
     * @param group The index of the group to remove from
     * @param personIndex The index of the person to be removed
     */
    public void deletePerson(int group, int personIndex) {
        LOGGER.log(Level.INFO, Constants.DELETE_PERSON_CALL_MESSAGE);
        getList(group).deletePerson(personIndex);
    }

    /**
     * Adds an income to the list of incomes of a person in the array list specified by the group index.
     *
     * @param group The index of the group to find the person
     * @param personIndex The index of the person in the group
     * @param description The source of the income
     * @param amount The value of the income
     * @param isPermanent Whether the income is recurring
     * @param isSilent Whether to print confirmation
     */
    public void addIncome(int group, int personIndex, String description, Double amount, Boolean isPermanent,
                          Boolean isSilent) {
        LOGGER.log(Level.INFO, Constants.ADD_INCOME_CALL_MESSAGE);
        getList(group).addIncome(personIndex, description, amount, isPermanent, isSilent);
    }

    /**
     * Removes an income from the list of incomes of a person in the array list specified by the group index.
     *
     * @param group The index of the group to find the person
     * @param personIndex The index of the person in the group
     * @param incomeIndex The index of the income to be removed
     */
    public void deleteIncome(int group, int personIndex, int incomeIndex) {
        LOGGER.log(Level.INFO, Constants.DELETE_INCOME_CALL_MESSAGE);
        getList(group).deleteIncome(personIndex, incomeIndex);
    }

    /**
     * Adds an expenditure to the list of expenditures of a person in the array list specified by the group index.
     *
     * @param group The index of the group to find the person
     * @param personIndex The index of the person in the group
     * @param description The reason for the expenditure
     * @param amount The value of the expenditure
     * @param category The category of the expenditure
     * @param isPermanent Whether the expenditure is recurring
     * @param isSilent Whether to print confirmation
     */
    public void addExpend(int group, int personIndex, String description, Double amount, Integer category,
                          Boolean isPermanent, Boolean isSilent) {
        LOGGER.log(Level.INFO, Constants.ADD_EXPEND_CALL_MESSAGE);
        getList(group).addExpend(personIndex, description, amount, category, isPermanent, isSilent);
    }

    /**
     * Removes an expenditure from the list of expenditures of a person in the array list specified by the group index.
     *
     * @param group The index of the group to find the person
     * @param personIndex The index of the person in the group
     * @param expendIndex The index of the expenditure to be removed
     */
    public void deleteExpend(int group, int personIndex, int expendIndex) {
        LOGGER.log(Level.INFO, Constants.DELETE_EXPEND_CALL_MESSAGE);
        getList(group).deleteExpend(personIndex, expendIndex);
    }

    /**
     * Lists the disposable incomes of each generation.
     */
    public void overview() {
        LOGGER.log(Level.INFO, Constants.OVERVIEW_CALL_MESSAGE);
        System.out.println("Here is the overview for your family:");
        Double sum = 0.0;
        for (int i = Constants.SINGULAR; i <= Constants.NUM_GROUPS; i++) {
            PersonList personList = getList(i);
            Double income = personList.getTotalIncome();
            String incomeString = UI.formatValue(income);
            Double expenditure = personList.getTotalExpenditure();
            String expenditureString = UI.formatValue(expenditure);
            Double remain = personList.getRemain();
            sum += remain;
            String disposable = UI.formatValue(remain);
            String generation = getGenerationName(i);
            printFormattedOverview(i, incomeString, expenditureString, disposable, generation);
        }
        String familyDisposable = UI.formatValue(sum);
        System.out.println("Total disposable income in family: " + familyDisposable);
    }

    /**
     * Prints the total income, expenditure, and disposable income of the generation.
     *
     * @param index The generation number
     * @param income The income for the generation
     * @param expenditure The expenditure for the generation
     * @param disposable The disposable income of the generation
     * @param generation The generation name
     */
    private void printFormattedOverview(int index, String income, String expenditure, String disposable,
                                        String generation) {
        System.out.println(index + ". " + generation + ":" + System.lineSeparator()
                + Constants.INDENTATION + "Income: " + income + System.lineSeparator()
                + Constants.INDENTATION + "Expenditure: " + expenditure + System.lineSeparator()
                + Constants.INDENTATION + "Disposable: " + disposable + System.lineSeparator());
    }

    /**
     * Lists the names of everyone in the array list specified by the group index and
     * their list of income and expenditure.
     *
     * @param group The index of the group to print
     */
    public void list(int group) {
        LOGGER.log(Level.INFO, Constants.LIST_CALL_MESSAGE);
        String generation = getGenerationName(group);
        System.out.println("For " + generation + ":");
        PersonList personList = getList(group);
        personList.list();
    }

    /**
     * Returns the number of members in the array list specified by the group index.
     *
     * @param group The index of the group
     * @return The number of members in the array list specified
     */
    public int getNumberOfMembers(int group) {
        LOGGER.log(Level.INFO, Constants.NUM_MEMBERS_CALL_MESSAGE);
        return getList(group).getNumberOfMembers();
    }

    /**
     * Returns the number of incomes of a person in the array list specified by the group index.
     *
     * @param group The index of the group
     * @param personIndex The index of the person
     * @return The number of incomes of the person in the array list specified
     */
    public int getNumberOfIncomes(int group, int personIndex) {
        LOGGER.log(Level.INFO, Constants.NUM_INCOMES_CALL_MESSAGE);
        return getList(group).getNumberOfIncomes(personIndex);
    }

    /**
     * Returns the number of expenditures of a person in the array list specified by the group index.
     *
     * @param group The index of the group
     * @param personIndex The index of the person
     * @return The number of expenditures of the person in the array list specified
     */
    public int getNumberOfExpenditures(int group, int personIndex) {
        LOGGER.log(Level.INFO, Constants.NUM_EXPENDS_CALL_MESSAGE);
        return getList(group).getNumberOfExpenditures(personIndex);
    }

    /**
     *  Edits an income of a person in the array list specified by the group index.
     *
     * @param group The index of the group
     * @param personIndex The index of the person
     * @param incomeIndex The index of the income
     * @param description The source of the income
     * @param amount The value of the income
     * @param isPermanent Whether the income is recurring
     */
    public void editIncome(int group, int personIndex, int incomeIndex, String description, Double amount,
                           Boolean isPermanent) {
        LOGGER.log(Level.INFO, Constants.EDIT_INCOME_CALL_MESSAGE);
        getList(group).editIncome(personIndex, incomeIndex, description, amount, isPermanent);
    }

    /**
     *  Edits an expenditure of a person in the array list specified by the group index.
     *
     * @param group The index of the group
     * @param personIndex The index of the person
     * @param expendIndex The index of the expenditure
     * @param description The reason for the expenditure
     * @param amount The value of the expenditure
     * @param category The category of the expenditure
     * @param isPermanent Whether the expenditure is recurring
     */
    public void editExpend(int group, int personIndex, int expendIndex, String description, Double amount,
                           Integer category, Boolean isPermanent) {
        LOGGER.log(Level.INFO, Constants.EDIT_EXPEND_CALL_MESSAGE);
        getList(group).editExpend(personIndex, expendIndex, description, amount, category, isPermanent);
    }

    /**
     * Prints entries found in the category provided containing the stated description.
     *
     * @param description The string to look for
     * @param category The category of the entry
     */
    public void find(String description, Integer category) {
        LOGGER.log(Level.INFO, Constants.FIND_CALL_MESSAGE);
        for (int i = Constants.SINGULAR; i <= Constants.NUM_GROUPS; i++) {
            getList(i).find(description, category);
        }
    }
}
