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

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlanCommandTest {
    ExerciseList exerciseList;
    WorkoutList workoutList;
    PlanList planList;
    UI ui;
    Parser parser;
    FileManager fileManager;
    DayList dayList;

    @BeforeEach
    void setUp() throws InvalidWorkoutException, InvalidExerciseException, IOException {
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
    void execute_validCreatePlan_expectSuccess() throws InvalidCommandException, IOException {
        //Check for existence of plans.txt file. If none, create it.
        fileManager.checkAndCreateDirectoriesAndFiles();

        //Create random plan name for test case
        Random rand = new Random();
        int upperbound = Integer.MAX_VALUE;
        int randomInteger = rand.nextInt(upperbound);
        String createTestPlanName = "t" + randomInteger;

        //Read from current plans.txt for existing plan names.
        //If plan name exist already, recreate a new random plan name.
        Scanner planFileReader = new Scanner(fileManager.getPlanFilePath());
        while (planFileReader.hasNext()) {
            try {
                String planFileDataLine = planFileReader.nextLine();
                if (planFileDataLine.contains(createTestPlanName)) {
                    createTestPlanName = "t" + rand.nextInt(upperbound);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("File data error: insufficient parameters in plan data.");
            } catch (NumberFormatException e) {
                System.out.println("File data error: " + e.getMessage());
            }
        }

        //Create and add new plan with the random plan name
        String commandInput = "plan /new " + createTestPlanName + " /workouts 1,2,1";
        PlanCommand createValidPlanCommand = parser.createPlanCommand(commandInput);
        createValidPlanCommand.execute();

        //Assert that new plan with random plan name has been created
        //and inserted into plans.txt. Afterwhich, remove that new plan from plans.txt.
        boolean hasNewTestPlan = false;
        ArrayList<String> linesInFile = new ArrayList<String>();
        Scanner planCheckFileReader = new Scanner(fileManager.getPlanFilePath());
        while (planCheckFileReader.hasNext()) {
            try {
                String planCheckFileDataLine = planCheckFileReader.nextLine();
                if (planCheckFileDataLine.contains(createTestPlanName)) {
                    hasNewTestPlan = true;
                    continue;
                }
                linesInFile.add(planCheckFileDataLine);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("File data error: insufficient parameters in plan data.");
            } catch (NumberFormatException e) {
                System.out.println("File data error: " + e.getMessage());
            }
        }

        FileWriter fileWriter = new FileWriter(fileManager.getPlanFilePath().toString());
        for (int i = 0; i < linesInFile.size(); i += 1) {
            fileWriter.append(linesInFile.get(i));
            fileWriter.append(System.lineSeparator());
        }
        fileWriter.close();
        assertTrue(hasNewTestPlan);
    }

    @Test
    void execute_invalidCreatePlan_expectCatchNumberFormatException() throws InvalidCommandException {
        PlanCommand createInvalidPlanCommand = parser.createPlanCommand("plan /new Plan A /workouts abc");
        String expectedOutput =
                "Uh oh, the user argument supplied is invalid.\n"
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
    void execute_invalidCreatePlan_expectCatchArrayIndexOutOfBoundsException() throws InvalidCommandException {
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
                        + "1. plan a\n";
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