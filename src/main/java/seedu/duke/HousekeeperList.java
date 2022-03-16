package seedu.duke;

import java.util.ArrayList;
import java.util.Locale;

import static java.util.stream.Collectors.toList;

public class HousekeeperList {
    private ArrayList<Housekeeper> housekeeperList;

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

    public boolean hasNameAdded(String name) {
        String convertNameToLowerCase = name.toLowerCase();
        ArrayList<Housekeeper> housekeeperFound = (ArrayList<Housekeeper>) housekeeperList.stream()
                .filter((t) -> t.getName().toLowerCase().contains(convertNameToLowerCase))
                .collect(toList());
        if (housekeeperFound.size() >= 1) {
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
}
