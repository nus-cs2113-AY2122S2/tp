package seedu.duke.tasks;

import seedu.duke.util.StringConstants;

public class Module {
    private static final String LS = System.lineSeparator();
    private static final String MODULE_STRING_WITH_DESC = "%s (%s)";
    private static final String INDENT = StringConstants.INDENT;

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

    public String getModuleDescription() {
        return moduleDescription;
    }

    /**
     * Returns the task list associated with the module.
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Adds one task in task list associated with the module.
     */
    public void addTask(Task task) {
        taskList.addTask(task);
    }

    /**
     * Formats the module and all tasks associated with it as a string.
     */
    public String printModuleTaskList() {
        return this + LS + taskList.getAllTasks(INDENT);
    }

    /**
     * Formats the module as a string.
     */
    @Override
    public String toString() {
        if (moduleDescription != null) {
            return String.format(MODULE_STRING_WITH_DESC, moduleCode, moduleDescription);
        }
        return moduleCode;
    }
}
