//@@author teanweijun

package seedu.planitarium.person;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.global.Constants;

import java.util.logging.Level;

public class Family {
    private PersonList parents;
    private PersonList myGen;
    private PersonList children;
    private static ProjectLogger logger = new ProjectLogger(Family.class.getName(), "Family.log");

    /**
     * Constructs a new Family object.
     */
    public Family() {
        parents = new PersonList();
        myGen = new PersonList();
        children = new PersonList();
        String infoString = "Logger for PersonList initialised";
        logger.getLogger().log(Level.INFO, infoString);
    }

    /**
     * Returns the array list specified.
     *
     * @param group The index of the group to return
     * @return The array list specified by the group index
     */
    private PersonList getList(int group) {
        String infoString = "Entering getList()";
        logger.getLogger().log(Level.INFO, infoString);
        assert(group >= 1);
        assert(group <= 3);
        infoString = "Index assertions passed in getList()";
        logger.getLogger().log(Level.INFO, infoString);
        PersonList toReturn = null;
        switch(group) {
        case Constants.PARENTS:
            toReturn = parents;
            break;
        case Constants.MY_GEN:
            toReturn = myGen;
            break;
        case Constants.CHILDREN:
            toReturn = children;
            break;
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
        logger.getLogger().log(Level.INFO, infoString);
        getList(group).addPerson(name);
    }

    /**
     * Removes a person from the array list specified by the group index.
     *
     * @param personIndex The index of the person to be removed
     * @param group The index of the group to remove from
     */
    public void deletePerson(int personIndex, int group) {
        String infoString = "Method removePerson() called";
        logger.getLogger().log(Level.INFO, infoString);
        getList(group).deletePerson(personIndex);
    }

    /**
     * Adds an income to the list of incomes of a person in the array list specified by the group index.
     *
     * @param personIndex The index of the person in the group
     * @param group The index of the group to find the person
     * @param description The source of the income
     * @param amount The value of the income
     */
    public void addIncome(int personIndex, int group, String description, double amount) {
        String infoString = "Method addIncome() called";
        logger.getLogger().log(Level.INFO, infoString);
        getList(group).addIncome(personIndex, description, amount);
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
        logger.getLogger().log(Level.INFO, infoString);
        getList(group).deleteIncome(personIndex, incomeIndex);
    }

    /**
     * Adds an expenditure to the list of expenditures of a person in the array list specified by the group index.
     *
     * @param personIndex The index of the person in the group
     * @param group The index of the group to find the person
     * @param description The reason for the expenditure
     * @param amount The value of the expenditure
     */
    public void addExpend(int personIndex, int group, String description, double amount) {
        String infoString = "Method addExpend() called";
        logger.getLogger().log(Level.INFO, infoString);
        getList(group).addExpend(personIndex, description, amount);
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
        logger.getLogger().log(Level.INFO, infoString);
        getList(group).deleteExpend(personIndex, expendIndex);
    }

    /**
     * Lists the disposable incomes of each generation.
     */
    public void list() {
        String infoString = "Method list() called";
        logger.getLogger().log(Level.INFO, infoString);
        System.out.println("Here are your disposable incomes by group:");
        for (int i = 0; i < Constants.NUM_GROUPS; i++) {
            PersonList personList = getList(i);
            double disposable = personList.getRemain();
            String generation = null;
            switch(i) {
            case Constants.PARENTS:
                generation = "Parents";
                break;
            case Constants.MY_GEN:
                generation = "My generation";
                break;
            case Constants.CHILDREN:
                generation = "Children";
                break;
            }
            System.out.println((i + 1) + ". " + generation + ": " + disposable);
        }
    }

    /**
     * Lists the names of everyone in the array list specified by the group index and
     * their list of income and expenditure.
     *
     * @param group The index of the group to print
     */
    public void detailList(int group) {
        String infoString = "Method detailList() called";
        logger.getLogger().log(Level.INFO, infoString);
        PersonList personList = getList(group);
        String generation = null;
        switch (group) {
        case Constants.PARENTS:
            generation = "Parents";
            break;
        case Constants.MY_GEN:
            generation = "My generation";
            break;
        case Constants.CHILDREN:
            generation = "Children";
            break;
        }
        System.out.println("For " + generation + ":");
        personList.list();
    }
}
