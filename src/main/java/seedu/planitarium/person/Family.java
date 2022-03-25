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

}
