package seedu.sherpass.util;

import org.junit.jupiter.api.Test;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static seedu.sherpass.constant.DateAndTimeFormat.dateOnlyFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.dayOnlyFormat;

public class TimetableTest {

    @Test
    void prepareTimetable_TodayDate_expectTodayTimetable() {
        ArrayList<Task> dummyList = new ArrayList<>();
        Ui ui = new Ui();
        Task testTask = new Task(1,"submit DG", LocalDateTime.now(),
                null, null, null);
        dummyList.add(testTask);
        TaskList testList = new TaskList(dummyList);
        ArrayList<Task> filteredList = testList.getFilteredTasksByDate(LocalDate.now());

        Timetable actualTimetable = Timetable.prepareTimetable(LocalDate.now(), filteredList, ui);
        Timetable expectTimetable = Timetable.prepareTimetable(LocalDate.now(), dummyList, ui);

        assertEquals(expectTimetable, actualTimetable);
    }

    @Test
    void prepareTimetable_TodayDate_expectEmptyTimetable() {
        ArrayList<Task> testArrayList = new ArrayList<>();
        Task testTask = new Task(1,"submit DG", LocalDateTime.now().plusDays(1),
                null, null, null);
        testArrayList.add(testTask);
        TaskList actualTaskList = new TaskList(testArrayList);
        ArrayList<Task> filteredList = actualTaskList.getFilteredTasksByDate(LocalDate.now());
        Ui ui = new Ui();

        Timetable actualTimetable = Timetable.prepareTimetable(LocalDate.now(), filteredList, ui);
        Timetable expectTimetable = Timetable.prepareTimetable(LocalDate.now(), new ArrayList<>(), ui);

        assertEquals(expectTimetable, actualTimetable);
    }

    @Test
    void showScheduleByDay_CurrentDate_expectTodayTimetable() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedOutput = "-------------------------------------------------"
                + "------------------------------------" + System.lineSeparator()
                + "|  Day       |  Time       |  Mark Status |  Task Description    |  To complete by  |"
                + System.lineSeparator()
                + "|  " + LocalDate.now().format(dayOnlyFormat)
                + "       | 0900 - 1100 |              |  1. submit DG        |  29/03/2022      |"
                + System.lineSeparator()
                + "| " + LocalDate.now().format(dateOnlyFormat)
                + " |             |              |                      |                  |"
                + System.lineSeparator()
                + "-------------------------------------------------------------------------------------"
                + System.lineSeparator();
        ArrayList<Task> dummyList = new ArrayList<>();
        Ui ui = new Ui();

        Task testTask = new Task(1,"submit DG", LocalDate.of(2022,3,29).atStartOfDay(),
                LocalDateTime.now(), LocalDateTime.now(), null);

        dummyList.add(testTask);
        TaskList testList = new TaskList(dummyList);
        Timetable.showScheduleByDay(LocalDate.now(), testList, ui);
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void showScheduleOfTheWeek_EmptySchedule_expectEmptyTimetableForTheWeek() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Ui ui = new Ui();
        TaskList taskList = new TaskList(new ArrayList<>());
        LocalDate currentDate = Timetable.resetDateToMonday(LocalDate.now().format(dayOnlyFormat), ui);
        assert (currentDate != null);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            sb.append("-------------------------------------------------------------------------------------")
                    .append(System.lineSeparator())
                    .append("|  Day       |  Time       |  Mark status |  Task Description    |  To complete by  |")
                    .append(System.lineSeparator())
                    .append("|  ")
                    .append(currentDate.plus(i, ChronoUnit.DAYS).format(dayOnlyFormat))
                    .append("       |             Your schedule is empty for the day!                      |")
                    .append(System.lineSeparator()).append("| ")
                    .append(currentDate.plus(i, ChronoUnit.DAYS).format(dateOnlyFormat))
                    .append(" |                                                                      |")
                    .append(System.lineSeparator())
                    .append("-------------------------------------------------------------------------------------")
                    .append(System.lineSeparator());
        }
        Timetable.showScheduleOfTheWeek(taskList, ui);
        assertEquals(sb.toString(), outContent.toString());
    }

    @Test
    void showTodaySchedule_fourShortAndLongTaskDescriptions_expectSuccess() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setOut(new PrintStream(outContent));
        ArrayList<Task> dummyList =  new  ArrayList<>();
        dummyList.add(new Task(1,"A significantly long task description",
                null, LocalDateTime.now(),LocalDateTime.now(), null));
        dummyList.add(new Task(1, "A somewhat long description",
                null, LocalDateTime.now(),LocalDateTime.now(), null));
        dummyList.add(new Task(1, "Some more stuff",
                null, LocalDateTime.now(),LocalDateTime.now(), null));
        dummyList.add(new Task(1,"One more task to add",
                null, LocalDateTime.now(),LocalDateTime.now(), null));
        Ui ui = new Ui();
        Timetable.showTodaySchedule(new TaskList(dummyList), ui);
        String expectedOutput = "----------------------------------------------------------"
                + "-------------------------------------------------" + System.lineSeparator()
                + "|  Day       |  Time       |  Mark Status |  Task Description              "
                + "            |  To complete by  |" + System.lineSeparator()
                + "|  " + LocalDate.now().format(dayOnlyFormat)
                + "       | 0900 - 1100 |              |  1. A significantly long task description  |"
                + "                  |" + System.lineSeparator()
                + "| " + LocalDate.now().format(dateOnlyFormat)
                + " | 0900 - 1100 |              |  1. A somewhat long description            |"
                + "                  |" + System.lineSeparator()
                + "|            | 0900 - 1100 |              |  1. Some more stuff                        |"
                + "                  |" + System.lineSeparator()
                + "|            | 0900 - 1100 |              |  1. One more task to add                   |"
                + "                  |" + System.lineSeparator()
                + "---------------------------------------"
                + "--------------------------------------------------------------------"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
}
