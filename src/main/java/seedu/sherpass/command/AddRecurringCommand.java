package seedu.sherpass.command;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.SECONDS;
import static seedu.sherpass.constant.Message.ERROR_START_AFTER_END_TIME_MESSAGE;

public class AddRecurringCommand extends Command {
    private String taskDescription;
    private LocalDateTime doOnStartDateTime;
    private LocalDateTime doOnEndDateTime;
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

    public void setDoOnStartDateTime(LocalDateTime doOnStartDateTime) {
        this.doOnStartDateTime = doOnStartDateTime;
    }

    public void setDoOnEndDateTime(LocalDateTime doOnEndDateTime) {
        this.doOnEndDateTime = doOnEndDateTime;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (doOnStartDateTime.isAfter(doOnEndDateTime)) {
            ui.showToUser(ERROR_START_AFTER_END_TIME_MESSAGE);
            return;
        }
        int identifier = taskList.generateIdentifier();
        Task newTask = new Task(identifier, taskDescription, null,
                doOnStartDateTime, doOnEndDateTime, frequency);
        long duration = doOnStartDateTime.until(doOnEndDateTime, SECONDS);
        LocalDateTime endDate;

        if (frequency == Frequency.DAILY) {
            endDate = doOnStartDateTime.plusMonths(1);
        } else if (frequency == Frequency.WEEKLY) {
            endDate = doOnStartDateTime.plusMonths(2);
        } else {
            endDate = doOnStartDateTime.plusYears(1);
        }

        int count = 0;
        StringBuilder addedTaskString = new StringBuilder();
        do {
            ++count;
            taskList.addTask(newTask);
            addedTaskString.append(newTask);
            addedTaskString.append("\n ");
            if (frequency == Frequency.DAILY) {
                doOnStartDateTime = doOnStartDateTime.plusDays(1);
            } else if (frequency == Frequency.WEEKLY) {
                doOnStartDateTime = doOnStartDateTime.plusWeeks(1);
            } else {
                doOnStartDateTime = doOnStartDateTime.plusMonths(1);
            }
            doOnEndDateTime = doOnStartDateTime.plusSeconds(duration);
            newTask = new Task(identifier, taskDescription, null, doOnStartDateTime, doOnEndDateTime, frequency);
        }
        while (newTask.getDoOnStartDateTime().isBefore(endDate));

        ui.showToUser("Got it. I've added " + count + " tasks:\n " + addedTaskString
                + "\nNow you have " + taskList.getTasks().size() + " task(s) in the list.");
        storage.writeSaveData(taskList);
    }
}
