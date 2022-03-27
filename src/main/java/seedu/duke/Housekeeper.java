package seedu.duke;

import java.util.ArrayList;

/**
 * Records the age, name and availability of each Housekeeper.
 */
public class Housekeeper {
    private String name;
    private int age;
    private String availability;
    private ArrayList<Integer> availableList = new ArrayList<>();
    private static final String[] daysList = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
        "Sunday"};

    public Housekeeper(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Verifies if housekeeper is available of the day given.
     *
     * @param day represent the day user is interested in.
     * @return True if housekeeper is available, false if housekeeper is not available.
     */
    public boolean isAvailableOn(int day) {
        day -= 1;
        for (Integer i : availableList) {
            if (i == day) {
                return true;
            }
        }
        return false;
    }

    public String getAvailability() {
        if (availability == null) {
            return "<Enter Availability>";
        }
        String availableStr = "( ";
        for (Integer i : availableList) {
            availableStr += daysList[i] + " ";
        }
        availableStr += ")";
        return availableStr;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
        availableList.clear();
        String[] days = availability.split(",");
        for (String day : days) {
            availableList.add(Integer.parseInt(day) - 1);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "[ " + getName() + " ]: " + getAge() + ", " + getAvailability();
    }

}
