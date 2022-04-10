package seedu.duke.housekeeperlists;

import seedu.duke.exceptions.InvalidUserException;
import seedu.duke.exceptions.UserDoesNotExistException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.stream.Collectors.toList;

/**
 * Represents a data structure containing all housekeeper profiles.
 */
public class HousekeeperList {
    private ArrayList<Housekeeper> housekeeperList;
    private static final int ONE_HOUSEKEEPER = 1;
    private ArrayList<Housekeeper> housekeeperExceedValidAgeList = new ArrayList<>();
    private static final int ONE_YEAR = 1;
    private static final int MAX_AGE_ACCEPTED = 60;
    private static Logger logger = Logger.getLogger("housekeeperListLogger");

    public HousekeeperList() {
        ArrayList<Housekeeper> housekeeperList = new ArrayList<>();
        setHousekeeperList(housekeeperList);
    }

    public Housekeeper getHousekeeper(int index) {
        return housekeeperList.get(index);
    }

    public void addHousekeeper(Housekeeper newHousekeeper) {
        getHousekeeperList().add(newHousekeeper);
    }

    /**
     * Method checks if Housekeeper name is current in records.
     *
     * @param name The name of the housekeeper.
     * @return false If name is not in records and true if name is in records.
     */
    public boolean hasNameAdded(String name) {
        String convertNameToLowerCase = name.toLowerCase();
        ArrayList<Housekeeper> housekeeperFound = (ArrayList<Housekeeper>) housekeeperList.stream()
                .filter((t) -> t.getName().toLowerCase().equals(convertNameToLowerCase))
                .collect(toList());
        if (housekeeperFound.size() >= ONE_HOUSEKEEPER) {
            return true;
        }
        return false;
    }

    public int getTotalHousekeeper() {
        return housekeeperList.size();
    }

    public void setHousekeeperList(ArrayList<Housekeeper> housekeeperList) {
        this.housekeeperList = housekeeperList;
    }

    public ArrayList<Housekeeper> getHousekeeperList() {
        return housekeeperList;
    }

    /**
     * Store a list of Housekeeper valid on day user is querying.
     *
     * @param day Input day user is interested to view.
     * @return List of found housekeeper.
     */
    public ArrayList<Housekeeper> getAvailableHousekeeperByDay(int day) {
        ArrayList<Housekeeper> foundList = new ArrayList<>();
        for (Housekeeper housekeeper : housekeeperList) {
            if (housekeeper.isAvailableOn(day)) {
                foundList.add(housekeeper);
            }
        }
        return foundList;
    }

    /**
     * Method to find the housekeeper name in the records and add their availability into the records.
     *
     * @param name         Housekeeper Name.
     * @param availability Housekeeper's availability to be added in records.
     */
    public void addAvailabilityInList(String name, String availability) throws UserDoesNotExistException {
        boolean isExist = false;
        String nameConvertLowerCase = name.toLowerCase();
        for (int i = 0; i < housekeeperList.size(); i++) {
            Housekeeper housekeeperToCompare = housekeeperList.get(i);
            String housekeeperToCompareName = housekeeperToCompare.getName().toLowerCase();
            if (housekeeperToCompareName.equals(nameConvertLowerCase)) {
                isExist = true;
                housekeeperToCompare.setAvailability(availability);
            }
        }
        if (!isExist) {
            throw new UserDoesNotExistException();
        }
    }

    /**
     * This method calls housekeeper's method to set each housekeeper availability as false.
     */
    public void resetAvailability() {
        for (Housekeeper housekeeper : housekeeperList) {
            housekeeper.setNullAvailability();
        }
    }

    /**
     * Finds the housekeeper's index in the list.
     *
     * @param name Housekeeper to be remove.
     * @return Index of the housekeeper to be remove in the list.
     */
    private int getHousekeeperRemove(String name) {
        int index = 0;
        int i = 0;
        String nameLowerCase = name.trim().toLowerCase();
        for (Housekeeper housekeeper : housekeeperList) {
            String nameForCompare = housekeeper.getName().toLowerCase();
            if (nameForCompare.equals(nameLowerCase)) {
                index = i;
            }
            i += 1;
        }
        return index;
    }

    private void removeHousekeeper(int housekeeperToRemoveIndex) {
        getHousekeeperList().remove(housekeeperToRemoveIndex);
    }

    /**
     * Increases each housekeeper's age by one and records housekeeper who are over the age limit into another list.
     */
    public void increaseAllAgeByOne() {
        housekeeperExceedValidAgeList.clear();
        for (Housekeeper housekeeper : housekeeperList) {
            int increasedAge = housekeeper.getAge() + ONE_YEAR;
            if (increasedAge > MAX_AGE_ACCEPTED) {
                housekeeperExceedValidAgeList.add(housekeeper);
            } else {
                housekeeper.setAge(increasedAge);
            }
        }
    }

    public ArrayList<Housekeeper> getHousekeeperExceedValidAgeList() {
        return housekeeperExceedValidAgeList;
    }

    public void deleteOverAgeHousekeeper() {
        for (Housekeeper housekeeper : housekeeperExceedValidAgeList) {
            int indexToRemove = getHousekeeperRemove(housekeeper.getName());
            removeHousekeeper(indexToRemove);
        }
    }

    public void addHousekeeperInList(Housekeeper housekeeper) throws InvalidUserException {
        boolean isRecorded = hasNameAdded(housekeeper.getName());
        if (!isRecorded) {
            addHousekeeper(housekeeper);
        } else {
            throw new InvalidUserException();
        }
    }


    public void removeHousekeeperInList(String name) throws UserDoesNotExistException {
        boolean isRecorded = hasNameAdded(name);
        if (isRecorded) {
            int housekeeperToRemoveIndex = getHousekeeperRemove(name);
            removeHousekeeper(housekeeperToRemoveIndex);
        } else {
            logger.log(Level.WARNING, "Housekeeper to be deleted was not in the list.");
            throw new UserDoesNotExistException();
        }
    }
}
