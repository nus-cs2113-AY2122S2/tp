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
     * Returns the parents array list.
     *
     * @return The parents array list
     */
    public PersonList getParents() {
        return parents;
    }

    /**
     * Returns the myGen array list.
     *
     * @return The myGen array list
     */
    public PersonList getMyGen() {
        return myGen;
    }

    /**
     * Returns the children array list.
     *
     * @return The children array list
     */
    public PersonList getChildren() {
        return children;
    }

    /**
     * Returns the array list specified.
     *
     * @param group The index of the group to return
     * @return The array list specified by group
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
        case Constants.MY_GEN:
            toReturn = myGen;
        case Constants.CHILDREN:
            toReturn = children;
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
     * @param index The index of the person to be removed
     * @param group The index of the group to remove from
     */
    public void removePerson(int index, int group) {
        String infoString = "Method removePerson() called";
        logger.getLogger().log(Level.INFO, infoString);
        getList(group).removePerson(index);
    }
}
