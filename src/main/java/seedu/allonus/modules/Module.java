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
    public String toString() {
        return "[Module] " + moduleCode + " " + category + ": " + day + ", " + timeSlot;
    }

    public boolean isEqualTo(Module anotherModule) {
        boolean isEqual = (this.moduleCode.equals(anotherModule.getModuleCode()))
                && (this.category.equals(anotherModule.getCategory()))
                && (this.day.equals(anotherModule.getDay()))
                && (this.timeSlot.equals(anotherModule.getTimeSlot()));
        return isEqual;
    }
}
