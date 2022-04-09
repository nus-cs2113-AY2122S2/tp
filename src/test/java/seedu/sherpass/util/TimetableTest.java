package seedu.sherpass.util;

import org.junit.jupiter.api.Test;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.timetable.Timetable;
import seedu.sherpass.timetable.TimetableLogic;

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
    void prepareTimetable_TodayDate_expectEmptyTimetable() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ArrayList<Task> testArrayList = new ArrayList<>();
        Task testTask = new Task(1,"submit DG", null,
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusMinutes(1));
        testArrayList.add(testTask);
        TaskList actualTaskList = new TaskList(testArrayList);
        Ui ui = new Ui();

        String expectedOutput = "---------------------------------------------------"
                + "------------------------------------" + System.lineSeparator()
                + "|  Day       |  Time         |  Mark status |  Task Description    |  To complete by  |"
                + System.lineSeparator()
                + "|  " + LocalDate.now().format(dayOnlyFormat)
                + "       |             Your schedule is empty for the day!                        |"
                + System.lineSeparator()
                + "| " + LocalDate.now().format(outputDateOnlyFormat)
                + " |                                                                        |"
                + System.lineSeparator()
                + "---------------------------------------------------------------------------------------"
                + System.lineSeparator();

        Timetable.showScheduleByDay(LocalDate.now(), actualTaskList, ui);
        assertEquals(expectedOutput, outContent.toString());
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
                LocalDateTime.parse(LocalDate.now().format(outputDateOnlyFormat) + " 11:00", inputWithTimeFormat));

        String expectedOutput = "---------------------------------------------"
                + "------------------------------------------------" + System.lineSeparator()
                + "|  Day       |  Time         |  Mark Status |  Task Description    |  To complete by        |"
                + System.lineSeparator()
                + "|  " + LocalDate.now().format(dayOnlyFormat)
                + "       | " + testTask.getDoOnDateTimePeriod() + " |              |  1. submit DG        "
                + "|  Tue, 29/03/2022 00:00 |"
                + System.lineSeparator()
                + "| " + LocalDate.now().format(outputDateOnlyFormat)
                + " |               |              |                      |                        |"
                + System.lineSeparator()
                + "---------------------------------------------------"
                + "------------------------------------------"
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
        LocalDate currentDate = TimetableLogic.resetDateToMonday(LocalDate.now(), ui);
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
        Timetable.showScheduleOfTheWeek(LocalDate.now(), taskList, ui);
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
                LocalDateTime.parse(currentDate + " 10:00", inputWithTimeFormat)));
        dummyList.add(new Task(2, "A somewhat long description",
                null, LocalDateTime.parse(currentDate + " 10:00", inputWithTimeFormat),
                LocalDateTime.parse(currentDate + " 11:00", inputWithTimeFormat)));
        dummyList.add(new Task(3, "break time!",
                null, LocalDateTime.parse(currentDate + " 11:00", inputWithTimeFormat),
                LocalDateTime.parse(currentDate + " 13:00", inputWithTimeFormat)));
        dummyList.add(new Task(4,"One more task to add",
                null, LocalDateTime.parse(currentDate + " 13:00", inputWithTimeFormat),
                LocalDateTime.parse(currentDate + " 15:00", inputWithTimeFormat)));
        String currentDay = LocalDate.now().format(dayOnlyFormat);
        String expectedOutput = "------------------------------------------------------------"
                + "-------------------------------------------------" + System.lineSeparator()
                + "|  Day       |  Time         |  Mark Status |  Task Description              "
                + "            |  To complete by  |" + System.lineSeparator()
                + "|  " + currentDay
                + "       | " + dummyList.get(0).getDoOnDateTimePeriod() + " |              |"
                + "  1. A significantly long task description  |"
                + "                  |" + System.lineSeparator()
                + "| " + currentDate
                + " | " + dummyList.get(1).getDoOnDateTimePeriod()
                + " |              |  2. A somewhat long description            |"
                + "                  |" + System.lineSeparator()
                + "|            | " + dummyList.get(2).getDoOnDateTimePeriod() + " |              |  3. break time!"
                + "                            |"
                + "                  |" + System.lineSeparator()
                + "|            | " + dummyList.get(3).getDoOnDateTimePeriod() + " |              | "
                + " 4. One more task to add                   |"
                + "                  |" + System.lineSeparator()
                + "---------------------------------------"
                + "----------------------------------------------------------------------"
                + System.lineSeparator();
        Ui ui = new Ui();
        Timetable.showScheduleByDay(LocalDate.now(), new TaskList(dummyList), ui);

        assertEquals(expectedOutput, outContent.toString());
    }
}
