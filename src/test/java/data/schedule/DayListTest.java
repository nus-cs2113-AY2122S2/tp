package data.schedule;

import data.exercises.ExerciseList;
import data.exercises.InvalidExerciseException;
import data.plans.InvalidPlanException;
import data.plans.Plan;
import data.plans.PlanList;
import data.workouts.InvalidWorkoutException;
import data.workouts.Workout;
import data.workouts.WorkoutList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.FileManager;
import storage.LogHandler;
import werkit.UI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DayListTest {
    ExerciseList exerciseList;
    WorkoutList workoutList;
    PlanList planList;
    DayList dayList;
    FileManager fileManager;
    UI ui;

    //@@author emilysim00
    @BeforeEach
    void setUp() throws InvalidWorkoutException, InvalidExerciseException, InvalidPlanException {
        LogHandler.startLogHandler();
        exerciseList = new ExerciseList();
        workoutList = new WorkoutList(exerciseList);
        planList = new PlanList(workoutList);
        dayList = new DayList(planList);
        ui = new UI();
        fileManager = new FileManager(planList);
        dayList = new DayList(planList);

        exerciseList.addExerciseToList("push up");
        exerciseList.addExerciseToList("sit up");
        exerciseList.addExerciseToList("pull up");

        Workout newWorkout1 = workoutList.createNewWorkout("push up /reps 10");
        workoutList.addNewWorkoutToLists(newWorkout1);
        Workout newWorkout2 = workoutList.createNewWorkout("sit up /reps 15");
        workoutList.addNewWorkoutToLists(newWorkout2);
        Workout newWorkout3 = workoutList.createNewWorkout("pull up /reps 20");
        workoutList.addNewWorkoutToLists(newWorkout3);

        Plan newPlan1 = planList.createNewPlan("more muscles /workouts 1,2,1,2");
        planList.addNewPlanToLists(newPlan1);
        Plan newPlan2 = planList.createNewPlan("more arm muscles /workouts 1,3,1,3");
        planList.addNewPlanToLists(newPlan2);
        Plan newPlan3 = planList.createNewPlan("only core /workouts 2,2,2,2");
        planList.addNewPlanToLists(newPlan3);
    }
    //@@author

    @Test
    void updateSchedule_expectSuccess() throws InvalidScheduleException {
        String newPlanToAddInSchedule = "1 1";
        Day newDaySchedule = dayList.updateDay(newPlanToAddInSchedule);
        assertEquals(newDaySchedule.getDayNumber(), 1);
        assertEquals(newDaySchedule.getPlanForThisDay().getPlanName(), "more muscles");

        String newPlanToAddInSchedule1 = "2 1";
        Day newDaySchedule1 = dayList.updateDay(newPlanToAddInSchedule1);
        assertEquals(newDaySchedule1.getDayNumber(), 2);
        assertEquals(newDaySchedule1.getPlanForThisDay().getPlanName(), "more muscles");
    }

    @Test
    void updateSchedule_expectSuccessPrints() throws InvalidScheduleException {
        String newPlanToAddInSchedule = "1 1";
        Day newDaySchedule = dayList.updateDay(newPlanToAddInSchedule);
        assertEquals(newDaySchedule.getDayNumber(), 1);
        assertEquals(newDaySchedule.getPlanForThisDay().getPlanName(), "more muscles");
        String expectedOutput = "Alright, the following plan schedule has been created:\n"
                + "\n" + "\t" + "Monday -- more muscles\n" + "\n";
        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        ui.printNewScheduleCreatedMessage(newDaySchedule);
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "").replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }

    @Test
    void updateSchedule_expectInvalidUpdate() throws InvalidScheduleException {
        String newPlanToAddInSchedule = "9 1";
        assertThrows(InvalidScheduleException.class, () -> dayList.updateDay(newPlanToAddInSchedule));

        String newPlanToAddInSchedule1 = "2 5";
        assertThrows(InvalidScheduleException.class, () -> dayList.updateDay(newPlanToAddInSchedule1));
    }

    @Test
    void clearSchedule_expectSuccess() throws InvalidScheduleException {
        String dayToClear = "1";
        Day newDaySchedule = dayList.updateDay("1 1");
        assertEquals(newDaySchedule.getDayNumber(), 1);
        assertEquals(newDaySchedule.getPlanForThisDay().getPlanName(), "more muscles");
        dayList.clearDayPlan(dayToClear);
        assertEquals(dayList.getDayList()[0], null);
    }

    @Test
    void clearSchedule_expectInvalidClear() throws InvalidScheduleException {
        String dayToClear = "9";
        assertThrows(InvalidScheduleException.class, () -> dayList.clearDayPlan(dayToClear));

        String dayToClear1 = "0";
        assertThrows(InvalidScheduleException.class, () -> dayList.clearDayPlan(dayToClear1));
    }

    @Test
    void clearAllSchedule_expectSuccess() throws InvalidScheduleException {
        Day newDaySchedule = dayList.updateDay("1 1");
        Day newDaySchedule2 = dayList.updateDay("1 2");
        dayList.clearAllSchedule();
        assertEquals(dayList.getDayList()[0], null);
        assertEquals(dayList.getDayList()[1], null);
        assertEquals(dayList.getDayList()[2], null);
        assertEquals(dayList.getDayList()[3], null);
        assertEquals(dayList.getDayList()[4], null);
        assertEquals(dayList.getDayList()[5], null);
        assertEquals(dayList.getDayList()[6], null);
    }

    @Test
    void printSchedule_expectSuccessNoPlansAdded() {
        String header = String.format("%8s %7s %20s", "Day", "|", "Plan Name");
        String info = "";
        for (int i = 1; i <= 7; i++) {
            info += String.format("%12s %3s %35s", dayList.covertDayNumberToDay(i), "|",
                    dayList.padWithSpaces("rest day") + "\n");
        }

        String expectedOutput = "\n                         WORKOUT SCHEDULE\n"
                + "----------------------------------------------------------------------\n"
                + header + "\n" +  "----------------------------------------------------------------------\n"
                + info;

        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        dayList.printSchedule();
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "").replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }

}