package seedu.sherpass.command;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;
import seedu.sherpass.util.parser.TaskParser;

import java.time.LocalDateTime;


import static seedu.sherpass.constant.Message.ERROR_START_AFTER_END_TIME_MESSAGE;

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


    /**
     * Accept parsed user input (in proper format) for preparation of adding task.
     *
     * @param taskDescription parsed task description.
     * @param doOnStartDateTime parsed doOnStartDateTime.
     * @param doOnEndDateTime parsed doOnEndDateTime.
     * @throws InvalidInputException of start time is after end time.
     */
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
     * @param ui User Interface.
     * @param storage Overwrites the save file data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task newTask;
        if (TaskParser.isValidFreq(frequency)) {
            int identifier = taskList.generateIdentifier();
            newTask = new Task(identifier, taskDescription, null,
                    doOnStartDateTime, doOnEndDateTime, frequency, 0);
            taskList.addTask(newTask, true, ui);
        } else {
            newTask = new Task(-1, taskDescription, byDate,
                    doOnStartDateTime, doOnEndDateTime, null, 0);
            taskList.addTask(newTask, false, ui);
        }
        storage.writeSaveData(taskList);
    }
}
