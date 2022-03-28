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

import static seedu.sherpass.constant.DateAndTimeFormat.outputDateOnlyFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.dayOnlyFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.inputTimeIndependentFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.inputWithTimeFormat;

public class TimetableTest {

    @Test
    void prepareTimetable_TodayDate_expectTodayTimetable() {
        ArrayList<Task> dummyList = new ArrayList<>();
        Ui ui = new Ui();
        Task testTask = new Task(1,"submit DG", LocalDateTime.now(),
                null, null, null, 1);
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
                null, null, null, 1);
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
        ArrayList<Task> dummyList = new ArrayList<>();
        Ui ui = new Ui();

        Task testTask = new Task(-1,"submit DG",
                LocalDateTime.parse("29/3/2022", inputTimeIndependentFormat),
                LocalDateTime.parse(LocalDate.now().format(outputDateOnlyFormat) + " 09:00", inputWithTimeFormat),
                LocalDateTime.parse(LocalDate.now().format(outputDateOnlyFormat) + " 11:00", inputWithTimeFormat),
                null, 1);

        String expectedOutput = "------------------------------------------------"
                + "---------------------------------------" + System.lineSeparator()
                + "|  Day       |  Time         |  Mark Status |  Task Description    |  To complete by  |"
                + System.lineSeparator()
                + "|  " + LocalDate.now().format(dayOnlyFormat)
                + "       | " + testTask.getTimePeriod() + " |              |  1. submit DG        "
                + "|  Tue, 29/03/2022 |"
                + System.lineSeparator()
                + "| " + LocalDate.now().format(outputDateOnlyFormat)
                + " |               |              |                      |                  |"
                + System.lineSeparator()
                + "------------------------------------------------------"
                + "---------------------------------"
                + System.lineSeparator();

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
            sb.append("---------------------------------------------------------------------------------------")
                    .append(System.lineSeparator())
                    .append("|  Day       |  Time         |  Mark status |  Task Description    |  To complete by  |")
                    .append(System.lineSeparator())
                    .append("|  ")
                    .append(currentDate.plus(i, ChronoUnit.DAYS).format(dayOnlyFormat))
                    .append("       |             Your schedule is empty for the day!                        |")
                    .append(System.lineSeparator()).append("| ")
                    .append(currentDate.plus(i, ChronoUnit.DAYS).format(outputDateOnlyFormat))
                    .append(" |                                                                        |")
                    .append(System.lineSeparator())
                    .append("---------------------------------------------------------------------------------------")
                    .append(System.lineSeparator());
        }
        Timetable.showScheduleOfTheWeek(taskList, ui);
        assertEquals(sb.toString(), outContent.toString());
    }

    @Test
    void showTodaySchedule_fourNonRepeatingInputsNoTimeClashNoByDate_expectSuccess() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setOut(new PrintStream(outContent));
        ArrayList<Task> dummyList =  new  ArrayList<>();
        String currentDate = LocalDate.now().format(outputDateOnlyFormat);
        dummyList.add(new Task(1,"A significantly long task description",
                null, LocalDateTime.parse(currentDate + " 09:00", inputWithTimeFormat),
                LocalDateTime.parse(currentDate + " 10:00", inputWithTimeFormat), null, 1));
        dummyList.add(new Task(2, "A somewhat long description",
                null, LocalDateTime.parse(currentDate + " 10:00", inputWithTimeFormat),
                LocalDateTime.parse(currentDate + " 11:00", inputWithTimeFormat),
                null, 2));
        dummyList.add(new Task(3, "break time!",
                null, LocalDateTime.parse(currentDate + " 11:00", inputWithTimeFormat),
                LocalDateTime.parse(currentDate + " 13:00", inputWithTimeFormat),
                null, 3));
        dummyList.add(new Task(4,"One more task to add",
                null, LocalDateTime.parse(currentDate + " 13:00", inputWithTimeFormat),
                LocalDateTime.parse(currentDate + " 15:00", inputWithTimeFormat),
                null, 4));
        String currentDay = LocalDate.now().format(dayOnlyFormat);
        String expectedOutput = "------------------------------------------------------------"
                + "-------------------------------------------------" + System.lineSeparator()
                + "|  Day       |  Time         |  Mark Status |  Task Description              "
                + "            |  To complete by  |" + System.lineSeparator()
                + "|  " + currentDay
                + "       | " + dummyList.get(0).getTimePeriod() + " |              |"
                + "  1. A significantly long task description  |"
                + "                  |" + System.lineSeparator()
                + "| " + currentDate
                + " | " + dummyList.get(1).getTimePeriod()
                + " |              |  2. A somewhat long description            |"
                + "                  |" + System.lineSeparator()
                + "|            | " + dummyList.get(2).getTimePeriod() + " |              |  3. break time!"
                + "                            |"
                + "                  |" + System.lineSeparator()
                + "|            | " + dummyList.get(3).getTimePeriod() + " |              | "
                + " 4. One more task to add                   |"
                + "                  |" + System.lineSeparator()
                + "---------------------------------------"
                + "----------------------------------------------------------------------"
                + System.lineSeparator();
        Ui ui = new Ui();
        Timetable.showTodaySchedule(new TaskList(dummyList), ui);

        assertEquals(expectedOutput, outContent.toString());
    }
}
