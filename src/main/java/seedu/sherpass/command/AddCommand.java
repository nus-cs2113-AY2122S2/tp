package seedu.sherpass.command;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.exception.TimeClashException;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import java.time.LocalDateTime;

import static seedu.sherpass.constant.Message.ADD_TASK_RESULT_MESSAGE;
import static seedu.sherpass.constant.Message.TASK_COUNT_MESSAGE_1;
import static seedu.sherpass.constant.Message.TASK_COUNT_MESSAGE_2;
import static seedu.sherpass.constant.Message.TAB_INDENT;

public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = "Add: Adds a task into the task list.\n"
            + "Usage: add TASK_DESCRIPTION /do DATE /start START_TIME /end END_TIME /repeat FREQUENCY\n"
            + "       add TASK_DESCRIPTION /do DATE /start START_TIME /end END_TIME [/bydate DEADLINE "
            + "/bytime END_TIME]\n\n"
            + "DATE & DEADLINE format: d/M/yyyy\n"
            + "START_TIME & END_TIME format: HH:mm\n"
            + "FREQUENCY: daily, weekly, or monthly\n\n"
            + "Warning:\n"
            + "If using the optional deadline parameter, both the bydate and the bytime needs to be used together";

    private String taskDescription;
    private LocalDateTime doOnStartDateTime;
    private LocalDateTime doOnEndDateTime;
    private LocalDateTime byDate;
    private Frequency frequency;

    public AddCommand(String taskDescription, LocalDateTime doOnStartDateTime, LocalDateTime doOnEndDateTime) {
        this.taskDescription = taskDescription;
        this.doOnStartDateTime = doOnStartDateTime;
        this.doOnEndDateTime = doOnEndDateTime;
    }

    /**
     * Accept parsed user input for by date, in proper format.
     *
     * @param byDate parsed by date.
     */
    public void setTaskByDate(LocalDateTime byDate) {
        this.byDate = byDate;
    }

    /**
     * Accept parsed user input for frequency.
     * Does not check if frequency is valid or not.
     *
     * @param frequency parsed frequency.
     */
    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    /**
     * Executes the adding of a task or multiple tasks.
     *
     * @param taskList Array representation of tasks.
     * @param ui       User Interface.
     * @param storage  Overwrites the save file data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task newTask;
        int identifier = taskList.generateIdentifier();
        newTask = new Task(identifier, taskDescription, byDate, doOnStartDateTime, doOnEndDateTime);
        try {
            taskList.addTask(newTask, frequency, false);
            storage.writeSaveData(taskList);
            ui.showToUser(ADD_TASK_RESULT_MESSAGE);
            ui.showToUser(TAB_INDENT + newTask);
            ui.showToUser(TASK_COUNT_MESSAGE_1 + taskList.getSize() + TASK_COUNT_MESSAGE_2);
        } catch (TimeClashException | InvalidInputException exception) {
            ui.showError(exception.getMessage());
        }
    }
}
