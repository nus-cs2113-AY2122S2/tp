package seedu.duke.tasks;

public class Module {
    private static final String LS = System.lineSeparator();
    private static final String MODULE_STRING_WITH_DESC = "%s (%s)";
    private static final String INDENT = "    ";

    private String moduleCode;
    private String moduleDescription;
    private final TaskList taskList;

    public Module(String moduleCode) {
        this(moduleCode, null);
    }
    public Module(String moduleCode, String moduleDescription) {
        this.moduleCode = moduleCode;
        this.moduleDescription = moduleDescription;
        this.taskList = new TaskList();
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public String printModuleTaskList() {
        return this + LS + taskList.getAllTasks(INDENT);
    }

    @Override
    public String toString() {
        if (moduleDescription != null) {
            return String.format(MODULE_STRING_WITH_DESC, moduleCode, moduleDescription);
        }
        return moduleCode;
    }
}
