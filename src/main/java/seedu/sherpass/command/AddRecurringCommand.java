package seedu.sherpass.command;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.CommonLogic;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import java.time.LocalDateTime;

import static seedu.sherpass.constant.Message.ERROR_EMPTY_DESCRIPTION_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_FREQUENCY_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_START_AFTER_END_TIME_MESSAGE;

public class AddRecurringCommand extends Command {
    private String taskDescription;
    private LocalDateTime doOnStartDateTime;
    private LocalDateTime doOnEndDateTime;
    private Frequency frequency;

    public static final String COMMAND_WORD = "addrecurring";
    public static final String MESSAGE_USAGE = "addrecurring: Adds a recurring task into the task list.\n"
            + "A recurring task contains a task description, date with the start and end time."
            + "\n\nTo execute the command,\nenter 'addrecurring TASK_DESCRIPTION /do TASK_DATE /start START_TIME"
            + " /end END_TIME.\n"
            + "E.g. addrecurring weekly revision /do 1/6/2022 /start 13:00 /end 14:00\n\n"
            + "All task dates must be given in the format:\n"
            + "\t\td/M/yyyy, where year is in 4 digits, month and day in one or two digits.\n"
            + "Time must be given in HH:mm format.";

    public AddRecurringCommand() {

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

    private String isValidArgument() {
        if (taskDescription.isBlank()) {
            return ERROR_EMPTY_DESCRIPTION_MESSAGE;
        } else if (doOnStartDateTime.isAfter(doOnEndDateTime)) {
            return ERROR_START_AFTER_END_TIME_MESSAGE;
        } else if (frequency == null) {
            return ERROR_INVALID_FREQUENCY_MESSAGE;
        }
        return "";
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String error = isValidArgument();
        if (!error.isBlank()) {
            ui.showToUser(error);
            return;
        }
        int identifier = taskList.generateIdentifier();
        Task newTask = new Task(identifier, taskDescription, null,
                doOnStartDateTime, doOnEndDateTime, frequency);
        LocalDateTime endDate = CommonLogic.getEndDateForRecurrence(doOnStartDateTime, frequency);

        int count = 0;
        StringBuilder addedTaskString = new StringBuilder();
        do {
            ++count;
            taskList.addTask(newTask);
            addedTaskString.append(newTask);
            addedTaskString.append("\n ");
            doOnStartDateTime = CommonLogic.incrementDate(doOnStartDateTime, frequency);
            doOnEndDateTime = CommonLogic.incrementDate(doOnEndDateTime, frequency);
            newTask = new Task(identifier, taskDescription, null, doOnStartDateTime, doOnEndDateTime, frequency);
        } while (newTask.getDoOnStartDateTime().isBefore(endDate));

        ui.showToUser("Got it. I've added " + count + " tasks:\n " + addedTaskString
                + "\nNow you have " + taskList.getTasks().size() + " task(s) in the list.");
        storage.writeSaveData(taskList);
    }
}
