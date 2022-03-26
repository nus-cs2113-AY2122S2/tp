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
            + "DATE must be given in the format: d/M/yyyy, where\n"
            + "year is in 4 digits, month and day in one or two digits.\n"
            + "TIME must be given in the format: HH:mm.\n"
            + "FREQUENCY can either be daily, weekly, or a monthly repeat of the task.\n"
            + "DEADLINE is in the format d/M/yyyy [TIME_TO_COMPLETE_BY]";

    private String taskDescription;
    private LocalDateTime doOnStartDateTime;
    private LocalDateTime doOnEndDateTime;
    private LocalDateTime byDate;
    private Frequency frequency;

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

    public void setTaskByDate(LocalDateTime byDate) {
        this.byDate = byDate;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

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
