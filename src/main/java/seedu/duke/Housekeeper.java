package seedu.duke;

/**
 * Records the age, name and availability of each Housekeeper.
 */
public class Housekeeper {
    private String name;
    private int age;
    private String availability;

    public Housekeeper(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getAvailability() {
        if (availability == null) {
            return "<Enter Availability>";
        }
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
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
