package commands;

import data.exercises.ExerciseList;
import data.exercises.InvalidExerciseException;
import data.plans.InvalidPlanException;
import data.plans.PlanList;
import data.schedule.DayList;
import data.workouts.InvalidWorkoutException;
import data.workouts.WorkoutList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.FileManager;
import storage.LogHandler;
import werkit.Parser;
import werkit.UI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlanCommandTest {
    ExerciseList exerciseList;
    WorkoutList workoutList;
    PlanList planList;
    UI ui;
    Parser parser;
    FileManager fileManager;
    DayList dayList;

    @BeforeEach
    void setUp() throws InvalidWorkoutException, InvalidExerciseException {
        LogHandler.startLogHandler();
        exerciseList = new ExerciseList();
        workoutList = new WorkoutList(exerciseList);
        planList = new PlanList(workoutList);
        ui = new UI();
        fileManager = new FileManager(planList);
        dayList = new DayList(planList);
        parser = new Parser(ui, exerciseList, workoutList, fileManager, planList, dayList);

        exerciseList.addExerciseToList("push up");
        exerciseList.addExerciseToList("sit up");
        exerciseList.addExerciseToList("pull up");

        workoutList.createAndAddWorkout("push up /reps 10");
        workoutList.createAndAddWorkout("sit up /reps 15");
        workoutList.createAndAddWorkout("pull up /reps 20");
    }

    @Test
    void setUserAction_createInvalidAction_expectInvalidCommandException() {
        assertThrows(InvalidCommandException.class,
            () -> new PlanCommand("plan /test", fileManager,
                    planList, "/test", ""));
    }

    @Test
    void execute_invalidCreatePlan_expectCatchNumberFormatException() throws InvalidCommandException {
        PlanCommand createInvalidPlanCommand = parser.createPlanCommand("plan /new Plan A /workouts abc");
        String expectedOutput =
                "Uh oh, a number was expected in your input, but a non-formattable\n"
                        + "number was received."
                        + "Please try again. Alternatively, type 'help' if you need\n"
                        + "more information on the commands.";
        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        createInvalidPlanCommand.execute();
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "").replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }

    @Test
    void execute_invalidCreatePlan_expectCatchInvalidPlanException() throws InvalidCommandException {
        PlanCommand createInvalidPlanCommand = parser.createPlanCommand("plan /new rest day /workouts 1,2");
        String expectedOutput =
                "Uh oh, this plan name is reserved for use.\n"
                        + "Please try again";
        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        createInvalidPlanCommand.execute();
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "").replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }

    @Test
    void execute_invalidCreatePlan_expectArrayIndexOutOfBoundsException() throws InvalidCommandException {
        PlanCommand createInvalidPlanCommand = parser.createPlanCommand("plan /new hh /workouts");
        String expectedOutput =
                "Uh oh, it seems like too few arguments were entered.\n"
                        + "Please try again. Alternatively, type 'help' if you need\n"
                        + "more information on the commands.";
        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        createInvalidPlanCommand.execute();
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "").replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }

    @Test
    void execute_validListPlan_expectSuccess() throws InvalidCommandException, InvalidPlanException {
        planList.createAndAddPlan("Plan A /workouts 1,2");
        PlanCommand listPlanCommand = parser.createPlanCommand("plan /list");
        String expectedOutput =
                "Here are all your plan(s).\n"
                        + "To view each plan in detail, enter\n'plan /details <plan number in list>'.\n"
                        + "1. Plan A\n";
        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        listPlanCommand.execute();
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "").replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }
}