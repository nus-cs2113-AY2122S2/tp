package seedu.duke.data;

import java.util.ArrayList;

import seedu.duke.util.Grades;
import seedu.duke.util.StringConstants;

public class Module {
    private static final String LS = System.lineSeparator();
    private static final String MODULE_STRING_WITH_DESC = "%s (%s) (%sMC, Grade: %s)";
    private static final String MODULE_STRING_NO_DESC = "%s (%sMC, Grade: %s)";
    private static final String GENERAL_TASK_STRING = "%s";
    private static final String INDENT = StringConstants.INDENT;
    private static final String NOT_ENTERED = StringConstants.NOT_ENTERED_STR;
    private static final int NOT_APPLICABLE = 0;

    private final String moduleCode;
    private String moduleDescription;
    private Grades moduleGrade;
    private boolean isGeneralTask;
    private final int modularCredit;
    private final TaskList taskList;

    public Module(String moduleCode) {
        this(moduleCode, null, NOT_APPLICABLE);
        isGeneralTask = true;
    }

    public Module(String moduleCode, String moduleDescription, int modularCredit) {
        this.moduleCode = moduleCode;
        this.moduleDescription = moduleDescription;
        this.taskList = new TaskList();
        this.modularCredit = modularCredit;
        this.moduleGrade = Grades.NOT_ENTERED;
        this.isGeneralTask = false;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleDescription(String description) {
        this.moduleDescription = description;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public int getModularCredit() {
        return modularCredit;
    }

    public Grades getModuleGrade() {
        return moduleGrade;
    }

    public void setModuleGrade(String moduleGrade) {
        this.moduleGrade = Grades.getGradeEnum(moduleGrade);
    }

    /**
     * Returns the task list associated with the module.
     */
    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskArrayList(ArrayList<Task> list) {
        taskList.setList(list);
    }

    /**
     * Adds one task in task list associated with the module.
     */
    public void addTask(Task task) {
        taskList.addTask(task);
    }

    /**
     * Formats the module and its associated tasks according to given parameters.
     */
    public String printModuleTaskList(boolean showCompletedTasks) {
        return this + LS + taskList.getAllTasks(INDENT, showCompletedTasks);
    }

    public String printModuleTaskListWithTag(String tag, boolean showCompletedTasks) {
        return this + LS + taskList.getTasksWithTag(INDENT, tag, showCompletedTasks);
    }

    /**
     * Formats the module as a string.
     */
    @Override
    public String toString() {
        if (isGeneralTask) {
            return String.format(GENERAL_TASK_STRING, moduleCode);
        } else if (moduleDescription != null) {
            return String.format(MODULE_STRING_WITH_DESC, moduleCode, moduleDescription, modularCredit, moduleGrade);
        }
        return String.format(MODULE_STRING_NO_DESC, moduleCode, modularCredit, moduleGrade);
    }
}
