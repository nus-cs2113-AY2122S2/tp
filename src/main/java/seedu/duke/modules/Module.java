package seedu.duke.modules;

public class Module {
    private String moduleCode;
    private String category;
    private String day;
    private String timeSlot;

    public Module(String moduleCode, String category) {
        this.moduleCode = moduleCode;
        this.category = category;
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
    public String toString() {
        return "[Module] " + "Code='" + moduleCode + '\'' + ", Category='" + category + '\'';
    }
}
