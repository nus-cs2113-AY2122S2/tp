package seedu.duke.housekeeperlists;

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

    /**
     * This method converts the given available day in int to the actual name of the day.
     *
     * @return String of days the housekeeper is available to work on.
     */
    public String getAvailability() {
        if (availability == null) {
            return "N/A";
        }
        String availableStr = "";
        int incrementCounter = 1;
        for (Integer i : availableList) {
            if (availableList.size() <= incrementCounter) {
                availableStr += daysList[i];
            } else {
                availableStr += daysList[i] + " ";
            }
            incrementCounter += 1;
        }
        return availableStr;
    }

    /**
     * Ensure the availability written into the file is in the correct format such that the next time it loads the
     * correct format of availability is given.
     *
     * @param availability Availability of housekeeper given in integer.
     * @return Valid format of availability to be written into housekeeper_file.txt.
     */
    public String getAvailabilityFile(String availability) {
        if (availability == null) {
            return "N/A";
        }
        String[] availabilitySplit = availability.split(",");
        String availabilityForFile = "";
        int incrementCounter = 1;
        for (String day: availabilitySplit) {
            String trimDay = day.trim();
            if (!(incrementCounter == availabilitySplit.length)) {
                availabilityForFile += trimDay;
                availabilityForFile += ",";
            } else {
                availabilityForFile += trimDay;
            }
            incrementCounter += 1;
        }
        return availabilityForFile;
    }

    /**
     * Allows updating or adding availability of housekeeper and does not allow duplicated days to be recorded more
     * than once.
     *
     * @param availability Given by the user in integer format.
     */
    public void setAvailability(String availability) {
        this.availability = availability;
        availableList.clear();
        String[] days = availability.split(",");
        for (String day : days) {
            String trimDay = day.trim();
            int convertToCorrectDay = Integer.parseInt(trimDay) - 1;
            if (!(availableList.contains(convertToCorrectDay))) {
                availableList.add(convertToCorrectDay);
            }
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
        return "[" + getName().toUpperCase() + "]: " + "Age: " + getAge() + ", Availability: " + getAvailability();
    }

    public String toFileString() {
        return getName() + " | " + getAge() + " | " + getAvailabilityFile(availability);
    }

    public void setNullAvailability() {
        this.availability = null;
    }
}
