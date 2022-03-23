package seedu.allonus.modules;

/**
 * Represents a module at NUS with attributes module code, module category, day of the week, and timeslot of class.
 */
public class Module {
    private String moduleCode;
    private String category;
    private String day;
    private String timeSlot;

    /**
     * Constructs a module object.
     * Takes the method attributes as function parameters and sets them.
     * @param moduleCode String that represents module code.
     * @param category String that represents module category.
     * @param day String that represents day of the week on which
     *           classes occur for a module.
     * @param timeSlot String that represents the timeslot of a module.
     */
    public Module(String moduleCode, String category, String day, String timeSlot) {
        this.moduleCode = moduleCode;
        this.category = category;
        this.day = day;
        this.timeSlot = timeSlot;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getCategory() {
        return category;
    }

    public String getDay() {
        return day;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    /**
     * Converts a module class to a string representation.
     * @return Returns string representation of a module and its attributes.
     */
    @Override
    public String toString() {
        return "[Module] " + moduleCode + " " + category + ": " + day + ", " + timeSlot;
    }

    /**
     * Checks if two modules are equal based on their attributes.
     * @param anotherModule The Module to compare with for equality.
     * @return true if the two Modules are equal.
     */
    public boolean isEqualTo(Module anotherModule) {
        boolean isEqual = (this.moduleCode.equals(anotherModule.getModuleCode()))
                && (this.category.equals(anotherModule.getCategory()))
                && (this.day.equals(anotherModule.getDay()))
                && (this.timeSlot.equals(anotherModule.getTimeSlot()));
        return isEqual;
    }
}
