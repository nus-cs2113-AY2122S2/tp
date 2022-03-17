package seedu.sherpass.util;

import org.junit.jupiter.api.Test;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.sherpass.constant.DateAndTimeFormat.parseFormat;

class ReminderTest {
    @Test
    void showDailyTask_ExpectDailyTasks() {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        Task expectedTask;

        tasks.addTask("return book", LocalDate.parse("20/3/2022", parseFormat), LocalDate.now());
        tasks.addTask("buy laptop", LocalDate.parse("29/12/2099", parseFormat), null);
        Reminder reminder = new Reminder(tasks, ui);

        expectedTask = tasks.getTasks().get(0);
        assertEquals(expectedTask, reminder.showDailyTask().get(0));
    }
}
