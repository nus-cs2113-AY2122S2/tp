package seedu.sherpass.command;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.exception.TimeClashException;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import java.time.LocalDateTime;

import static seedu.sherpass.constant.Message.ERROR_SCHEDULE_CLASH_MESSAGE;

public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = "Add: Adds a task into the task list.\n"
            + "Usage: add TASK_DESCRIPTION /do DATE /start START_TIME /end END_TIME /repeat FREQUENCY\n"
            + "       add TASK_DESCRIPTION /do DATE /start START_TIME /end END_TIME [/by DEADLINE]\n\n"
            + "DATE & DEADLINE format: d/M/yyyy\n"
            + "START_TIME & END_TIME format: HH:mm\n"
            + "FREQUENCY: daily, weekly, or monthly";

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
     * Accept parsed user input (in proper format) for preparation of adding task.
     *
     * @param taskDescription parsed task description.
     * @param doOnStartDateTime parsed doOnStartDateTime.
     * @param doOnEndDateTime parsed doOnEndDateTime.
     * @throws InvalidInputException of start time is after end time.
     */
    /*
    public void setTaskContent(String taskDescription, LocalDateTime doOnStartDateTime,
                               LocalDateTime doOnEndDateTime) throws
            InvalidInputException {
        if (doOnStartDateTime.isAfter(doOnEndDateTime)) {
            throw new InvalidInputException(ERROR_START_AFTER_END_TIME_MESSAGE);
        }
        this.taskDescription = taskDescription;
        this.doOnStartDateTime = doOnStartDateTime;
        this.doOnEndDateTime = doOnEndDateTime;
    }
    */

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
        newTask = new Task(identifier, taskDescription, byDate, doOnStartDateTime, doOnEndDateTime, frequency);
        try {
            taskList.addTask(newTask);
            ui.showToUser("Got it! I've added this task:\n   "
                    + newTask + "\n"
                    + "Now you have " + taskList.getSize() + " task(s) in your schedule!");
            storage.writeSaveData(taskList);
        } catch (TimeClashException exception) {
            ui.showToUser(ERROR_SCHEDULE_CLASH_MESSAGE);
            ui.showLine();
            ui.showToUser("Clashing task: " + exception.getMessage());
        } catch (InvalidInputException exception) {
            ui.showToUser(exception.getMessage());
        }
    }
}