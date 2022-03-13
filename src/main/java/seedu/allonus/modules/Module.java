package seedu.allonus.modules;

public class Module {
    private String moduleCode;
    private String category;
    private String day;
    private String timeSlot;

    public Module(String moduleCode, String category, String day, String timeSlot) {
        this.moduleCode = moduleCode;
        this.category = category;
        this.day = day;
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

    @Override
    //needs to be fixed
    public String toString() {
        return "[Module] " + moduleCode + " " + category + ": " + day + ", " + timeSlot;
    }
}
