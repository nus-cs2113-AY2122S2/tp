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
