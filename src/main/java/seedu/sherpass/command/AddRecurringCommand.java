package seedu.sherpass.command;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import java.time.LocalDateTime;

public class AddRecurringCommand extends Command {
    private String taskDescription;
    private LocalDateTime doOnDate;
    private boolean hasDoOnTime;
    private Frequency frequency;

    public static final String COMMAND_WORD = "addrecurring";
    public static final String MESSAGE_USAGE = "addrecurring: Adds a recurring task into the task list.\n"
            + "A recurring task contains a task description and an optional date"
            + "\n\nTo execute the command,\nenter 'addrecurring TASK_DESCRIPTION [/do TASK_DATE].\n"
            + "E.g. addrecurring weekly revision /do 1/6/2022\n\n"
            + "All task dates must be given in the format:\n"
            + "\t\td/M/yyyy [HH:mm] , where\n"
            + "year is in 4 digits, month and day in one or two digits.";

    public AddRecurringCommand() {

    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setDoOnDate(LocalDateTime doOnDate) {
        this.doOnDate = doOnDate;
    }

    public boolean getHasDoOnTime() {
        return hasDoOnTime;
    }

    public void setHasDoOnTime(boolean hasDoOnTime) {
        this.hasDoOnTime = hasDoOnTime;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task newTask = new Task(taskDescription, doOnDate, hasDoOnTime);
        int identifier = newTask.getIdentifier();
        LocalDateTime startDate = doOnDate;
        LocalDateTime endDate;
        int count = 0;
        if (frequency == Frequency.DAILY) {
            endDate = startDate.plusMonths(1);
            do {
                ++count;
                taskList.addTask(newTask);
                startDate = startDate.plusDays(1);
                newTask = new Task(taskDescription, startDate, hasDoOnTime);
                newTask.setIdentifier(identifier);
            } while (newTask.getDoOnDate().isBefore(endDate));
        } else if (frequency == Frequency.WEEKLY) {
            endDate = startDate.plusMonths(2);
            do {
                ++count;
                taskList.addTask(newTask);
                startDate = startDate.plusWeeks(1);
                newTask = new Task(taskDescription, startDate, hasDoOnTime);
            } while (newTask.getDoOnDate().isBefore(endDate));
        } else {
            endDate = startDate.plusYears(1);
            do {
                ++count;
                taskList.addTask(newTask);
                startDate = startDate.plusMonths(1);
                newTask = new Task(taskDescription, startDate, hasDoOnTime);
            } while (newTask.getDoOnDate().isBefore(endDate));
        }

        ui.showToUser("Got it. I've added " + count + " tasks:\n " + newTask
                + "\nNow you have " + taskList.getTasks().size() + " task(s) in the list.");
        storage.writeSaveData(taskList);
    }
}
