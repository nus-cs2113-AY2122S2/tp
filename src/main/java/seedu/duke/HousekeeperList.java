package seedu.duke;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

/**
 * Represents a data structure containing all housekeeper profiles.
 */
public class HousekeeperList {
    private ArrayList<Housekeeper> housekeeperList;
    private static final int ONE_HOUSEKEEPER = 1;

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
     * @param name         Housekeeper Name
     * @param availability Housekeeper's availability to be added in records
     */
    public void searchAvailability(String name, String availability) throws UserExistException {
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
            throw new UserExistException();
        }
    }
}
