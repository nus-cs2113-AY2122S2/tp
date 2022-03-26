//@@author teanweijun

package seedu.planitarium.person;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.category.Category;
import seedu.planitarium.global.Constants;

import java.util.logging.Level;

public class Family {
    private PersonList parents;
    private PersonList myGen;
    private PersonList children;
    private static ProjectLogger logger = new ProjectLogger(Family.class.getName(), "Family.log");
    private static final String INDEX_ERROR_MESSAGE = "Invalid index passed in";

    /**
     * Constructs a new Family object.
     */
    public Family() {
        parents = new PersonList();
        myGen = new PersonList();
        children = new PersonList();
        String infoString = "Logger for PersonList initialised";
        logger.log(Level.INFO, infoString);
    }

    /**
     * Returns the array list specified.
     *
     * @param group The index of the group to return
     * @return The array list specified by the group index
     */
    private PersonList getList(int group) {
        String infoString = "Entering getList()";
        logger.log(Level.INFO, infoString);
        assert (group >= 1);
        assert (group <= 3);
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
            logger.log(Level.SEVERE, INDEX_ERROR_MESSAGE);
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
        assert (group >= 1);
        assert (group <= 3);
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
            logger.log(Level.SEVERE, INDEX_ERROR_MESSAGE);
        }
        return toReturn;
    }

    /**
     * Adds a person to the array list specified by the group index.
     *
     * @param name The name of the person to be added
     * @param group The index of the group to add to
     */
    public void addPerson(String name, int group) {
        String infoString = "Method addPerson() called";
        logger.log(Level.INFO, infoString);
        getList(group).addPerson(name, Constants.FOR_USER);
    }

    /**
     * Removes a person from the array list specified by the group index.
     *
     * @param personIndex The index of the person to be removed
     * @param group The index of the group to remove from
     */
    public void deletePerson(int personIndex, int group) {
        String infoString = "Method removePerson() called";
        logger.log(Level.INFO, infoString);
        getList(group).deletePerson(personIndex);
    }

    /**
     * Adds an income to the list of incomes of a person in the array list specified by the group index.
     *
     * @param personIndex The index of the person in the group
     * @param group The index of the group to find the person
     * @param description The source of the income
     * @param amount The value of the income
     * @param isPermanent Whether the income is recurring
     */
    public void addIncome(int personIndex, int group, String description, double amount, boolean isPermanent) {
        String infoString = "Method addIncome() called";
        logger.log(Level.INFO, infoString);
        getList(group).addIncome(personIndex, description, amount, isPermanent);
    }

    /**
     * Removes an income from the list of incomes of a person in the array list specified by the group index.
     *
     * @param personIndex The index of the person in the group
     * @param group The index of the group to find the person
     * @param incomeIndex The index of the income to be removed
     */
    public void deleteIncome(int personIndex, int group, int incomeIndex) {
        String infoString = "Method deleteIncome() called";
        logger.log(Level.INFO, infoString);
        getList(group).deleteIncome(personIndex, incomeIndex);
    }

    /**
     * Adds an expenditure to the list of expenditures of a person in the array list specified by the group index.
     *
     * @param personIndex The index of the person in the group
     * @param group The index of the group to find the person
     * @param description The reason for the expenditure
     * @param amount The value of the expenditure
     * @param isPermanent Whether the expenditure is recurring
     * @param category The category of the expenditure
     */
    public void addExpend(int personIndex, int group, String description, double amount, boolean isPermanent,
                          Category category) {
        String infoString = "Method addExpend() called";
        logger.log(Level.INFO, infoString);
        getList(group).addExpend(personIndex, description, amount, isPermanent, category);
    }

    /**
     * Removes an expenditure from the list of expenditures of a person in the array list specified by the group index.
     *
     * @param personIndex The index of the person in the group
     * @param group The index of the group to find the person
     * @param expendIndex The index of the expenditure to be removed
     */
    public void deleteExpend(int personIndex, int group, int expendIndex) {
        String infoString = "Method deleteExpend() called";
        logger.log(Level.INFO, infoString);
        getList(group).deleteExpend(personIndex, expendIndex);
    }

    /**
     * Lists the disposable incomes of each generation.
     */
    public void overview() {
        String infoString = "Method list() called";
        logger.log(Level.INFO, infoString);
        System.out.println("Here are your disposable incomes by group:");
        for (int i = 0; i < Constants.NUM_GROUPS; i++) {
            PersonList personList = getList(i);
            double income = personList.getTotalIncome();
            double expenditure = personList.getTotalExpenditure();
            double disposable = personList.getRemain();
            String generation = getGenerationName(i);
            System.out.println((i + 1) + ". " + generation + ":" + System.lineSeparator()
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
        String infoString = "Method detailList() called";
        logger.log(Level.INFO, infoString);
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
        String infoString = "Method getNumberOfMembers() called";
        logger.log(Level.INFO, infoString);
        return getList(group).getNumberOfMembers();
    }

    /**
     * Returns the number of incomes of a person in the array list specified by the group index.
     *
     * @param personIndex The index of the person
     * @param group The index of the group
     * @return The number of incomes of the person in the array list specified
     */
    public int getNumberOfIncomes(int personIndex, int group) {
        String infoString = "Method getNumberOfIncomes() called";
        logger.log(Level.INFO, infoString);
        return getList(group).getNumberOfIncomes(personIndex);
    }

    /**
     * Returns the number of expenditures of a person in the array list specified by the group index.
     *
     * @param personIndex The index of the person
     * @param group The index of the group
     * @return The number of expenditures of the person in the array list specified
     */
    public int getNumberOfExpenditures(int personIndex, int group) {
        String infoString = "Method getNumberOfExpenditures() called";
        logger.log(Level.INFO, infoString);
        return getList(group).getNumberOfExpenditures(personIndex);
    }
}
