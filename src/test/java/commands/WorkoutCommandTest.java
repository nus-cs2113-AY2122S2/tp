package commands;

import data.exercises.ExerciseList;
import data.exercises.InvalidExerciseException;
import data.plans.PlanList;
import data.schedule.DayList;
import data.workouts.InvalidWorkoutException;
import data.workouts.Workout;
import data.workouts.WorkoutList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.FileManager;
import storage.LogHandler;
import werkit.Parser;
import werkit.UI;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorkoutCommandTest {
    @BeforeEach
    public void setUp() {
        LogHandler.startLogHandler();
    }

    @Test
    public void workoutCommand_normalWorkoutNewConstruction_constructSuccess() throws IOException,
            InvalidCommandException {
        String userInput = "workout /new russian twist /reps 1000";
        String userAction = "/new";
        String userArguments = "russian twist /reps 1000";
        ExerciseList el = new ExerciseList();
        WorkoutList wl = new WorkoutList(el);
        PlanList pl = new PlanList(wl);
        FileManager fm = new FileManager(pl);

        WorkoutCommand commandTest = new WorkoutCommand(userInput, fm, wl, pl, userAction, userArguments);

        assertEquals("workout /new russian twist /reps 1000", commandTest.getUserInput());
        assertEquals("/new", commandTest.getUserAction());
        assertEquals("russian twist /reps 1000", commandTest.getUserArguments());
    }

    @Test
    public void workoutCommand_invalidAction_exceptionThrown() throws IOException, InvalidCommandException {
        String userInput1 = "workout /pancakes";
        String userAction1 = "/pancakes";
        String userArguments1 = null;

        String userInput2 = "workout /create running /reps 20";
        String userAction2 = "/create";
        String userArguments2 = "running /reps 20";

        ExerciseList el = new ExerciseList();
        WorkoutList wl = new WorkoutList(el);
        PlanList pl = new PlanList(wl);
        FileManager fm = new FileManager(pl);

        assertThrows(InvalidCommandException.class,
            () -> new WorkoutCommand(userInput1, fm, wl, pl, userAction1, userArguments1));
        assertThrows(InvalidCommandException.class,
            () -> new WorkoutCommand(userInput2, fm, wl, pl, userAction2, userArguments2));
    }

    @Test
    public void execute_validCreateWorkout_expectSuccess() throws IOException,
            InvalidCommandException {
        ExerciseList el = new ExerciseList();
        el.addExerciseToList("squat");
        WorkoutList wl = new WorkoutList(el);
        PlanList pl = new PlanList(wl);
        FileManager fm = new FileManager(pl);
        DayList dl = new DayList(pl);
        UI ui = new UI();
        Parser parser = new Parser(ui, el, wl, fm, pl, dl);

        //Check for existence of workouts.txt file. If none, create it.
        fm.checkAndCreateDirectoriesAndFiles();

        //Create random workout for test case
        Random rand = new Random();
        int upperbound = Integer.MAX_VALUE;
        int randomInteger = rand.nextInt(upperbound);
        String createTestWorkout = "squat | " + randomInteger;

        //Read from current workouts.txt for existing workouts.
        //If workout exist already, recreate a new random workout.
        Scanner workoutFileReader = new Scanner(fm.getWorkoutFilePath());
        while (workoutFileReader.hasNext()) {
            try {
                String planFileDataLine = workoutFileReader.nextLine();
                if (planFileDataLine.contains(createTestWorkout)) {
                    createTestWorkout = "squat | " + rand.nextInt(upperbound);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("File data error: insufficient parameters in plan data.");
            } catch (NumberFormatException e) {
                System.out.println("File data error: " + e.getMessage());
            }
        }

        //Create and add workout with the random workout
        String fetchRepsFromNewRandom = createTestWorkout.split("\\|")[1];
        String commandInput = "workout /new squat /reps" + fetchRepsFromNewRandom;
        WorkoutCommand createValidWorkoutCommand = parser.createWorkoutCommand(commandInput);
        createValidWorkoutCommand.execute();

        //Assert that new workout has been created and inserted into workouts.txt.
        //Afterwhich, remove that new workout from workouts.txt.
        boolean hasNewTestWorkout = false;
        ArrayList<String> linesInFile = new ArrayList<String>();
        Scanner workoutCheckFileReader = new Scanner(fm.getWorkoutFilePath());
        while (workoutCheckFileReader.hasNext()) {
            try {
                String workoutCheckFileDataLine = workoutCheckFileReader.nextLine();
                if (workoutCheckFileDataLine.contains(createTestWorkout)) {
                    hasNewTestWorkout = true;
                    continue;
                }
                linesInFile.add(workoutCheckFileDataLine);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("File data error: insufficient parameters in plan data.");
            } catch (NumberFormatException e) {
                System.out.println("File data error: " + e.getMessage());
            }
        }

        FileWriter fileWriter = new FileWriter(fm.getWorkoutFilePath().toString());
        for (int i = 0; i < linesInFile.size(); i += 1) {
            fileWriter.append(linesInFile.get(i));
            fileWriter.append(System.lineSeparator());
        }
        fileWriter.close();
        assertTrue(hasNewTestWorkout);
    }

    @Test
    void execute_invalidCreateWorkout_expectCatchNumberFormatException() throws InvalidCommandException {
        ExerciseList el = new ExerciseList();
        el.addExerciseToList("push up");
        WorkoutList wl = new WorkoutList(el);
        PlanList pl = new PlanList(wl);
        FileManager fm = new FileManager(pl);
        DayList dl = new DayList(pl);
        UI ui = new UI();
        Parser parser = new Parser(ui, el, wl, fm, pl, dl);

        WorkoutCommand createInvalidWorkoutCommand = parser.createWorkoutCommand("workout /new push up /reps abc");
        String expectedOutput =
                "Uh oh, the user argument supplied is invalid."
                        + "Please try again. Alternatively, type 'help' if you need\n"
                        + "more information on the commands.";
        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        createInvalidWorkoutCommand.execute();
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "").replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }

    @Test
    void execute_invalidCreateWorkout_expectCatchArrayIndexOutOfBoundsException() throws InvalidCommandException {
        ExerciseList el = new ExerciseList();
        el.addExerciseToList("push up");
        WorkoutList wl = new WorkoutList(el);
        PlanList pl = new PlanList(wl);
        FileManager fm = new FileManager(pl);
        DayList dl = new DayList(pl);
        UI ui = new UI();
        Parser parser = new Parser(ui, el, wl, fm, pl, dl);

        WorkoutCommand createInvalidWorkoutCommand = parser.createWorkoutCommand("workout /new push up /reps");
        String expectedOutput =
                "Uh oh, it seems like too few arguments were entered.\n"
                        + "Please try again. Alternatively, type 'help' if you need\n"
                        + "more information on the commands.";
        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        createInvalidWorkoutCommand.execute();
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "").replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }

    @Test
    public void execute_validDeleteWorkout_expectSuccess() throws IOException,
            InvalidCommandException, InvalidWorkoutException, InvalidExerciseException {
        ExerciseList el = new ExerciseList();
        el.addExerciseToList("push up");
        el.addExerciseToList("bicep curl");
        el.addExerciseToList("pull up");
        el.addExerciseToList("squat");
        el.addExerciseToList("lunge");
        el.addExerciseToList("hip thrust");
        el.addExerciseToList("sit up");
        el.addExerciseToList("crunch");
        el.addExerciseToList("russian twist");
        el.addExerciseToList("running");
        el.addExerciseToList("swimming");
        el.addExerciseToList("jumping jack");
        el.addExerciseToList("burpee");
        WorkoutList wl = new WorkoutList(el);
        PlanList pl = new PlanList(wl);
        FileManager fm = new FileManager(pl);
        DayList dl = new DayList(pl);
        UI ui = new UI();

        //Check for existence of workouts.txt file. If none, create it.
        fm.checkAndCreateDirectoriesAndFiles();

        //Create random workout for test case
        Random rand = new Random();
        int upperbound = Integer.MAX_VALUE;
        int randomInteger = rand.nextInt(upperbound);
        String createTestWorkout = "squat | " + randomInteger;

        //Read from current workouts.txt for existing workouts.
        //If workout exist already, recreate a new random workout.
        Scanner workoutFileReader = new Scanner(fm.getWorkoutFilePath());
        int trackNumberOfWorkouts = 1;
        while (workoutFileReader.hasNext()) {
            try {
                trackNumberOfWorkouts += 1;
                String workoutFileDataLine = workoutFileReader.nextLine();
                String getExerciseNameLine = workoutFileDataLine.split("\\|")[0].trim();
                String getRepsLine = workoutFileDataLine.split("\\|")[1].trim();
                Workout newWorkout = wl.createNewWorkout(getExerciseNameLine + " /reps " + getRepsLine);
                wl.addNewWorkoutToLists(newWorkout);
                if (workoutFileDataLine.contains(createTestWorkout)) {
                    createTestWorkout = "squat | " + rand.nextInt(upperbound);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("File data error: insufficient parameters in plan data.");
            } catch (NumberFormatException e) {
                System.out.println("File data error: " + e.getMessage());
            }
        }

        Parser parser = new Parser(ui, el, wl, fm, pl, dl);
        //Add plans to file so it doesn't get lost as delete method also depends on plans
        Scanner planFileReader = new Scanner(fm.getPlanFilePath());
        while (planFileReader.hasNext()) {
            try {
                String planFileDataLine = planFileReader.nextLine();
                String[] parsedPlanFileDataLine = fm.parsePlansFileData(planFileDataLine);
                fm.addFilePlanToList(pl, parsedPlanFileDataLine);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("File data error: insufficient parameters in plan data.");
            } catch (NumberFormatException e) {
                System.out.println("File data error: " + e.getMessage());
            }
        }

        //Create and add workout with the random workout
        String fetchRepsFromNewRandom = createTestWorkout.split("\\|")[1];
        String commandInput = "workout /new squat /reps" + fetchRepsFromNewRandom;
        WorkoutCommand createValidWorkoutCommand = parser.createWorkoutCommand(commandInput);
        createValidWorkoutCommand.execute();
        assertEquals(wl.getWorkoutsDisplayList().size(), trackNumberOfWorkouts);

        //Now delete that newly created workout using the delete method.
        //Assert that workoutlist is one element lesser than before delete.
        String commandDeleteInput = "workout /delete " + wl.getWorkoutsDisplayList().size();
        WorkoutCommand deleteValidWorkoutCommand = parser.createWorkoutCommand(commandDeleteInput);
        deleteValidWorkoutCommand.execute();
        assertEquals(wl.getWorkoutsDisplayList().size(), trackNumberOfWorkouts - 1);
    }
}
