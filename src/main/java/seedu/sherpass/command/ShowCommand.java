package seedu.sherpass.command;

import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Timetable;
import seedu.sherpass.util.Ui;

import java.time.LocalDate;

import static seedu.sherpass.constant.Message.ERROR_INVALID_INPUT_MESSAGE;
import static seedu.sherpass.constant.Message.WELCOME_MESSAGE_TWO;

public class ShowCommand extends Command {
    public static final String COMMAND_WORD = "show";
    public static final String MESSAGE_USAGE = "Show: shows the array of tasks in a list format\n"
            + "or in a timetable format.\n\nTo generate the timetable, use \n1) `show today`,\n"
            + "2) `show week`\n3) `show <date>`, where date is in the format d/M/YYYY.\n"
            + "E.g. show 23/5/2022 to show timetable for 23th May 2022.\n\n"
            + "To generate a list of all tasks, use 4) `show all`.\n"
            + "To generate a list of pending tasks, use 5) `show todo`.";

    LocalDate dayInput;
    String selection;

    public ShowCommand(LocalDate dayInput, String selection) {
        this.dayInput = dayInput;
        this.selection = selection;
    }

    /**
     * Executes the show command. Shows the user the schedule
     * for the day that is specified, the list of incomplete
     * tasks, and the current weekly schedule.
     *
     * @param taskList Task array.
     * @param ui       Ui for printing task content.
     * @param storage  Storage for overwriting/appending save data. Not used in this method.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (dayInput != null) {
            ui.showToUser("Here is the schedule you wanted:");
            Timetable.showScheduleByDay(dayInput, taskList, ui);
            return;
        }

        switch (selection) {
        case "todo":
            ui.showToUser("Here are your pending tasks:");
            taskList.printPendingTasks(ui);
            break;
        case "today":
            ui.showToUser(WELCOME_MESSAGE_TWO);
            Timetable.showTodaySchedule(taskList, ui);
            ui.showToUser("What would you like to do next?");
            break;
        case "week":
            ui.showToUser("Here is your schedule for the week:");
            Timetable.showScheduleOfTheWeek(taskList, ui);
            ui.showToUser("What would you like to do next?");
            break;
        case "all":
            taskList.printAllTasks(ui);
            break;
        default:
            ui.showToUser(ERROR_INVALID_INPUT_MESSAGE);
        }
    }
}
