//@@author teanweijun

package seedu.planitarium.person;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.global.Constants;

import java.util.logging.Level;

public class Family {
    private PersonList parents;
    private PersonList myGen;
    private PersonList children;

    private static final String LOG_CLASS_NAME = Family.class.getSimpleName();
    private static final String LOG_FILE_PATH = LOG_CLASS_NAME + ".log";
    private static ProjectLogger logger = new ProjectLogger(LOG_CLASS_NAME, LOG_FILE_PATH);

    /**
     * Constructs a new Family object.
     */
    public Family() {
        parents = new PersonList();
        myGen = new PersonList();
        children = new PersonList();
        logger.log(Level.INFO, Constants.FAMILY_INIT_MESSAGE);
    }

    /**
     * Returns the array list specified.
     *
     * @param group The index of the group to return
     * @return The array list specified by the group index
     */
    public PersonList getList(int group) {
        String infoString = "Entering getList()";
        logger.log(Level.INFO, infoString);
        assert (group >= Constants.SINGULAR);
        assert (group <= Constants.NUM_GROUPS);
        infoString = "Index assertions passed in getList()";
        logger.log(Level.INFO, infoString);
        PersonList toReturn = null;
        switch (group) {
        case Constants.PARENTS:
            toReturn = parents;
            break;
        case Constants.MY_GEN:
            toReturn = myGen;
            break;
        case Constants.CHILDREN:
            toReturn = children;
            break;
        default:
            logger.log(Level.SEVERE, Constants.INDEX_ERROR_MESSAGE);
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
        logger.log(Level.INFO, infoString);
        assert (group >= Constants.SINGULAR);
        assert (group <= Constants.NUM_GROUPS);
        infoString = "Index assertions passed in getGenerationName()";
        logger.log(Level.INFO, infoString);
        String toReturn = null;
        switch (group) {
        case Constants.PARENTS:
            toReturn = "Parents";
            break;
        case Constants.MY_GEN:
            toReturn = "My generation";
            break;
        case Constants.CHILDREN:
            toReturn = "Children";
            break;
        default:
            logger.log(Level.SEVERE, Constants.INDEX_ERROR_MESSAGE);
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
    public void addPerson(int group, String name, boolean isSilent) {
        logger.log(Level.INFO, Constants.ADD_PERSON_CALL_MESSAGE);
        getList(group).addPerson(name);
        if (isSilent) {
            return;
        }
        String generation = getGenerationName(group);
        System.out.println(name + " has been added successfully to " + generation);
    }

    /**
     * Removes a person from the array list specified by the group index.
     *
     * @param group The index of the group to remove from
     * @param personIndex The index of the person to be removed
     */
    public void deletePerson(int group, int personIndex) {
        logger.log(Level.INFO, Constants.REMOVE_PERSON_CALL_MESSAGE);
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
    public void addIncome(int group, int personIndex, String description, double amount, boolean isPermanent,
                          boolean isSilent) {
        logger.log(Level.INFO, Constants.ADD_INCOME_CALL_MESSAGE);
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
        logger.log(Level.INFO, Constants.DELETE_INCOME_CALL_MESSAGE);
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
    public void addExpend(int group, int personIndex, String description, double amount, int category,
                          boolean isPermanent, boolean isSilent) {
        logger.log(Level.INFO, Constants.ADD_EXPEND_CALL_MESSAGE);
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
        logger.log(Level.INFO, Constants.DELETE_EXPEND_CALL_MESSAGE);
        getList(group).deleteExpend(personIndex, expendIndex);
    }

    /**
     * Lists the disposable incomes of each generation.
     */
    public void overview() {
        logger.log(Level.INFO, Constants.OVERVIEW_CALL_MESSAGE);
        System.out.println("Here are your disposable incomes by group:");
        for (int i = Constants.SINGULAR; i <= Constants.NUM_GROUPS; i++) {
            PersonList personList = getList(i);
            double income = personList.getTotalIncome();
            double expenditure = personList.getTotalExpenditure();
            double disposable = personList.getRemain();
            String generation = getGenerationName(i);
            System.out.println(i + ". " + generation + ":" + System.lineSeparator()
                    + "Income: $" + income + System.lineSeparator()
                    + "Expenditure: $" + expenditure + System.lineSeparator()
                    + "Disposable: $" + disposable);
        }
    }

    /**
     * Lists the names of everyone in the array list specified by the group index and
     * their list of income and expenditure.
     *
     * @param group The index of the group to print
     */
    public void list(int group) {
        logger.log(Level.INFO, Constants.LIST_CALL_MESSAGE);
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
        logger.log(Level.INFO, Constants.NUM_MEMBERS_CALL_MESSAGE);
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
        logger.log(Level.INFO, Constants.NUM_INCOMES_CALL_MESSAGE);
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
        logger.log(Level.INFO, Constants.NUM_EXPENDS_CALL_MESSAGE);
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
    public void editIncome(int group, int personIndex, int incomeIndex, String description, double amount,
                           boolean isPermanent) {
        logger.log(Level.INFO, Constants.EDIT_INCOME_CALL_MESSAGE);
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
    public void editExpend(int group, int personIndex, int expendIndex, String description, double amount,
                           int category, boolean isPermanent) {
        logger.log(Level.INFO, Constants.EDIT_EXPEND_CALL_MESSAGE);
        getList(group).editExpend(personIndex, expendIndex, description, amount, category, isPermanent);
    }

    /**
     * Prints entries found in the category provided containing the stated description.
     *
     * @param description The string to look for
     * @param category The category of the entry
     */
    public void find(String description, int category) {
        logger.log(Level.INFO, Constants.FIND_CALL_MESSAGE);
        for (int i = Constants.SINGULAR; i <= Constants.NUM_GROUPS; i++) {
            getList(i).find(description, category);
        }
    }
}
