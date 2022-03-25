package seedu.sherpass.util;


import org.junit.jupiter.api.Test;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.sherpass.constant.DateAndTimeFormat.dateOnlyFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.dayOnlyFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.inputFormat;

public class TimetableTest {

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
        Task testTask = new Task("submit DG", LocalDate.parse("29/3/2022", inputFormat), LocalDate.now());
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
        dummyList.add(new Task("A significantly long task description",
                null, LocalDate.now()));
        dummyList.add(new Task("A somewhat long description",
                null, LocalDate.now()));
        dummyList.add(new Task("Some more stuff",
                null, LocalDate.now()));
        dummyList.add(new Task("One more task to add",
                null, LocalDate.now()));
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
