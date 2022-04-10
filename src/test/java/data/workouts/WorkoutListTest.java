package data.workouts;

import data.exercises.ExerciseList;
import data.exercises.InvalidExerciseException;
import data.plans.InvalidPlanException;
import data.plans.PlanList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.LogHandler;
import werkit.UI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


class WorkoutListTest {
    ExerciseList exerciseList;
    WorkoutList wl;

    @BeforeEach
    void setUp() {
        LogHandler.startLogHandler();
        exerciseList = new ExerciseList();
        wl = new WorkoutList(exerciseList);

        exerciseList.addExerciseToList("push up");
        exerciseList.addExerciseToList("pull up");
        exerciseList.addExerciseToList("sit up");
        exerciseList.addExerciseToList("hip thrust");
        exerciseList.addExerciseToList("burpee");
        exerciseList.addExerciseToList("lunge");
        exerciseList.addExerciseToList("crunch");
        exerciseList.addExerciseToList("russian twist");
        exerciseList.addExerciseToList("jumping jack");
    }

    @Test
    void createAndAddWorkout_normalCreation_expectSuccess() throws InvalidExerciseException,
            InvalidWorkoutException {
        String newWorkout1 = "russian twist /reps 1000";
        Workout outputWorkout1 = wl.createNewWorkout(newWorkout1);
        wl.addNewWorkoutToLists(outputWorkout1);
        assertEquals(outputWorkout1.getExerciseName(), "russian twist");
        assertEquals(outputWorkout1.getRepetitions(), 1000);
        assertTrue(wl.checkForExistingWorkout("russian twist", 1000));

        String newWorkout2 = "crunch /reps 2359";
        Workout outputWorkout2 = wl.createNewWorkout(newWorkout2);
        wl.addNewWorkoutToLists(outputWorkout2);
        assertEquals(outputWorkout2.getExerciseName(), "crunch");
        assertEquals(outputWorkout2.getRepetitions(), 2359);
        assertTrue(wl.checkForExistingWorkout("crunch", 2359));

        String newWorkout3 = "lunge /reps 2147483647";
        Workout outputWorkout3 = wl.createNewWorkout(newWorkout3);
        wl.addNewWorkoutToLists(outputWorkout3);
        assertEquals(outputWorkout3.getExerciseName(), "lunge");
        assertEquals(outputWorkout3.getRepetitions(), 2147483647);
    }

    @Test
    void createAndAddWorkout_invalidExerciseName_expectInvalidExcerciseException() {
        String invalidWorkout = "weeeeeee /reps 500";
        assertThrows(InvalidExerciseException.class, () -> wl.createNewWorkout(invalidWorkout));
    }

    @Test
    void createAndAddWorkout_invalidRepCount_expectInvalidWorkoutException() {
        String invalidWorkout1 = "push up /reps -12345";
        assertThrows(InvalidWorkoutException.class, () -> wl.createNewWorkout(invalidWorkout1));

        String invalidWorkout2 = "sit up /reps 0";
        assertThrows(InvalidWorkoutException.class, () -> wl.createNewWorkout(invalidWorkout2));

        String invalidWorkout3 = "russian twist /reps 2147483648";
        assertThrows(NumberFormatException.class, () -> wl.createNewWorkout(invalidWorkout3));
    }

    @Test
    void createAndAddWorkout_addExistingWorkout_expectInvalidWorkoutException() throws InvalidExerciseException,
            InvalidWorkoutException {
        String workout = "burpee /reps 100";
        Workout newWorkout = wl.createNewWorkout(workout);
        wl.addNewWorkoutToLists(newWorkout);

        assertThrows(InvalidWorkoutException.class, () -> wl.createNewWorkout(workout));
    }

    @Test
    void listWorkout_expectNoPrints() {
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        wl.listAllWorkout();
        System.out.flush();
        String expectedOutput = "The workout list is empty";
        assertEquals(expectedOutput, consoleOutput.toString().trim());
    }

    @Test
    void listWorkout_expectThreePrints() throws InvalidWorkoutException, InvalidExerciseException {
        Workout newWorkout1 = wl.createNewWorkout("push up /reps 11");
        wl.addNewWorkoutToLists(newWorkout1);
        Workout newWorkout2 = wl.createNewWorkout("sit up /reps 15");
        wl.addNewWorkoutToLists(newWorkout2);
        Workout newWorkout3 = wl.createNewWorkout("lunge /reps 10");
        wl.addNewWorkoutToLists(newWorkout3);

        String expectedOutput =
                "Showing workouts 1-3 of 3:\n"
                        + "1. push up (11 reps)\n"
                        + "2. sit up (15 reps)\n"
                        + "3. lunge (10 reps)\n"
                        + "Showed all workouts in list\n";
        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        wl.listAllWorkout();
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "").replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }

    @Test
    void deleteWorkout_validIndexToDelete_expectDeleteWorkout() throws
            InvalidWorkoutException, InvalidExerciseException, InvalidPlanException {

        Workout newWorkout1 = wl.createNewWorkout("push up /reps 11");
        wl.addNewWorkoutToLists(newWorkout1);
        Workout newWorkout2 = wl.createNewWorkout("sit up /reps 15");
        wl.addNewWorkoutToLists(newWorkout2);
        Workout newWorkout3 = wl.createNewWorkout("lunge /reps 10");
        wl.addNewWorkoutToLists(newWorkout3);
        PlanList planList = new PlanList(wl);
        UI ui = new UI();

        int workoutNumberToDeleteInList = 2;

        assertEquals("sit up (15 reps)", wl.getWorkoutsDisplayList().get(workoutNumberToDeleteInList - 1).toString());

        wl.deleteWorkout(Integer.toString(workoutNumberToDeleteInList));
        assertEquals("lunge (10 reps)", wl.getWorkoutsDisplayList().get(workoutNumberToDeleteInList - 1).toString());
    }

    @Test
    void deleteWorkout_indexOutOfRange_expectInvalidWorkoutException() throws InvalidWorkoutException,
            InvalidExerciseException {

        Workout newWorkout1 = wl.createNewWorkout("push up /reps 11");
        wl.addNewWorkoutToLists(newWorkout1);
        Workout newWorkout2 = wl.createNewWorkout("sit up /reps 15");
        wl.addNewWorkoutToLists(newWorkout2);
        Workout newWorkout3 = wl.createNewWorkout("lunge /reps 10");
        wl.addNewWorkoutToLists(newWorkout3);

        PlanList planList = new PlanList(wl);
        UI ui = new UI();

        int workoutNumberToDeleteInList = 5;
        assertThrows(InvalidWorkoutException.class,
            () -> wl.deleteWorkout(Integer.toString(workoutNumberToDeleteInList)));

    }

    @Test
    void deleteWorkout_invalidParseArgumentInput_expectNumberFormatException() throws InvalidWorkoutException,
            InvalidExerciseException, ArrayIndexOutOfBoundsException {

        Workout newWorkout1 = wl.createNewWorkout("push up /reps 11");
        wl.addNewWorkoutToLists(newWorkout1);
        Workout newWorkout2 = wl.createNewWorkout("sit up /reps 15");
        wl.addNewWorkoutToLists(newWorkout2);
        Workout newWorkout3 = wl.createNewWorkout("lunge /reps 10");
        wl.addNewWorkoutToLists(newWorkout3);

        PlanList planList = new PlanList(wl);
        UI ui = new UI();

        String invalidArgumentSupplied = "t5";
        assertThrows(NumberFormatException.class,
            () -> wl.deleteWorkout(invalidArgumentSupplied));

    }

    @Test
    void updateWorkout_validInputArgument_expectSuccessUpdate() throws InvalidWorkoutException,
            InvalidExerciseException {
        Workout newWorkout1 = wl.createNewWorkout("push up /reps 11");
        wl.addNewWorkoutToLists(newWorkout1);
        Workout newWorkout2 = wl.createNewWorkout("sit up /reps 15");
        wl.addNewWorkoutToLists(newWorkout2);
        Workout newWorkout3 = wl.createNewWorkout("lunge /reps 10");
        wl.addNewWorkoutToLists(newWorkout3);

        int indexToUpdate = 3;
        int newReps = 15;
        String updateArgument = Integer.toString(indexToUpdate) + " " + Integer.toString(newReps);

        String workoutDetails = wl.getWorkoutsDisplayList().get(indexToUpdate - 1).toString();

        assertEquals("lunge (10 reps)", workoutDetails);
        wl.updateWorkout(updateArgument);
        workoutDetails = wl.getWorkoutsDisplayList().get(indexToUpdate - 1).toString();
        assertEquals("lunge (15 reps)", workoutDetails);
    }

    @Test
    void updateWorkout_InvalidNumberFormat_expectNumberFormatException() throws InvalidWorkoutException,
            InvalidExerciseException, ArrayIndexOutOfBoundsException {
        Workout newWorkout1 = wl.createNewWorkout("push up /reps 11");
        wl.addNewWorkoutToLists(newWorkout1);
        Workout newWorkout2 = wl.createNewWorkout("sit up /reps 15");
        wl.addNewWorkoutToLists(newWorkout2);
        Workout newWorkout3 = wl.createNewWorkout("lunge /reps 10");
        wl.addNewWorkoutToLists(newWorkout3);

        String indexToUpdate = "a";
        int newReps = 15;
        String updateArgument = indexToUpdate + " " + Integer.toString(newReps);

        assertThrows(NumberFormatException.class,
            () -> wl.updateWorkout(updateArgument));
    }

    @Test
    void updateWorkout_workoutIndexOutOfRange_expectInvalidWorkoutException() throws InvalidWorkoutException,
            InvalidExerciseException {
        Workout newWorkout1 = wl.createNewWorkout("push up /reps 11");
        wl.addNewWorkoutToLists(newWorkout1);
        Workout newWorkout2 = wl.createNewWorkout("sit up /reps 15");
        wl.addNewWorkoutToLists(newWorkout2);
        Workout newWorkout3 = wl.createNewWorkout("lunge /reps 10");
        wl.addNewWorkoutToLists(newWorkout3);

        String indexToUpdate = "4";
        int newReps = 15;
        String updateArgument = indexToUpdate + " " + Integer.toString(newReps);

        assertThrows(InvalidWorkoutException.class,
            () -> wl.updateWorkout(updateArgument));
    }

    @Test
    void updateWorkout_workoutToUpdateHasExisted_expectInvalidWorkoutException() throws InvalidWorkoutException,
            InvalidExerciseException {
        Workout newWorkout1 = wl.createNewWorkout("pull up /reps 15");
        wl.addNewWorkoutToLists(newWorkout1);
        Workout newWorkout2 = wl.createNewWorkout("sit up /reps 15");
        wl.addNewWorkoutToLists(newWorkout2);
        Workout newWorkout3 = wl.createNewWorkout("pull up /reps 10");
        wl.addNewWorkoutToLists(newWorkout3);

        String indexToUpdate = "3";
        int newReps = 15;
        String updateArgument = indexToUpdate + " " + Integer.toString(newReps);

        assertThrows(InvalidWorkoutException.class,
            () -> wl.updateWorkout(updateArgument));

    }

    @Test
    void checkForExistingWorkout_uniqueNewWorkout_expectFalse() throws InvalidWorkoutException,
            InvalidExerciseException {
        Workout newWorkout = wl.createNewWorkout("russian twist /reps 1000");
        wl.addNewWorkoutToLists(newWorkout);

        String newWorkoutExerciseName1 = "running";
        int newWorkoutRepsCount1 = 1000;
        String newWorkoutExerciseName2 = "russian twist";
        int newWorkoutRepsCount2 = 999;

        assertEquals(false, wl.checkForExistingWorkout(newWorkoutExerciseName1, newWorkoutRepsCount1));
        assertEquals(false, wl.checkForExistingWorkout(newWorkoutExerciseName2, newWorkoutRepsCount2));
    }

    @Test
    void checkForExistingWorkout_nonUniqueNewWorkout_expectTrue()
            throws InvalidWorkoutException, InvalidExerciseException {
        Workout newWorkout = wl.createNewWorkout("russian twist /reps 1000");
        wl.addNewWorkoutToLists(newWorkout);

        String newWorkoutExerciseName = "russian twist";
        int newWorkoutRepsCount = 1000;

        assertEquals(true, wl.checkForExistingWorkout(newWorkoutExerciseName, newWorkoutRepsCount));
    }
}