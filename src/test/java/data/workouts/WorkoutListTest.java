package data.workouts;

import data.exercises.ExerciseList;
import data.exercises.InvalidExerciseException;
import data.workouts.WorkoutOutOfRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class WorkoutListTest {
    ExerciseList exerciseList = new ExerciseList();
    WorkoutList wl = new WorkoutList(exerciseList);

    @BeforeEach
    void setUp() {
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
    void listWorkout_expectNoPrints() {
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        wl.listWorkout();
        System.out.flush();
        String expectedOutput = "The workout list is empty";
        assertEquals(expectedOutput, consoleOutput.toString().trim());
    }

    @Test
    void listWorkout_expectThreePrints() throws InvalidWorkoutException, InvalidExerciseException {
        wl.createAndAddWorkout("push up /reps 11");
        wl.createAndAddWorkout("sit up /reps 15");
        wl.createAndAddWorkout("lunge /reps 10");
        String expectedOutput =
                "Showing workouts 1-3 of 3:\n"
                        + "1. push up (11 reps)\n"
                        + "2. sit up (15 reps)\n"
                        + "3. lunge (10 reps)\n"
                        + "Showed all items in list\n";
        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        wl.listWorkout();
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "").replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }

    @Test
    void deleteWorkout_validIndexToDelete_expectDeleteWorkout() throws
            InvalidWorkoutException, InvalidExerciseException,
            WorkoutOutOfRangeException {

        wl.createAndAddWorkout("push up /reps 11");
        wl.createAndAddWorkout("sit up /reps 15");
        wl.createAndAddWorkout("lunge /reps 10");

        int workoutNumberToDeleteInList = 2;

        assertEquals("sit up (15 reps)", wl.getWorkoutsList().get(workoutNumberToDeleteInList - 1).toString());
        wl.deleteWorkout(Integer.toString(workoutNumberToDeleteInList));
        assertEquals("lunge (10 reps)", wl.getWorkoutsList().get(workoutNumberToDeleteInList - 1).toString());
    }

    @Test
    void deleteWorkout_indexOutOfRange_expectWorkoutOutOfRangeException() throws InvalidWorkoutException,
            InvalidExerciseException {

        wl.createAndAddWorkout("push up /reps 11");
        wl.createAndAddWorkout("sit up /reps 15");
        wl.createAndAddWorkout("lunge /reps 10");

        int workoutNumberToDeleteInList = 5;

        assertThrows(WorkoutOutOfRangeException.class,
            () -> wl.deleteWorkout(Integer.toString(workoutNumberToDeleteInList)));

    }

    @Test
    void deleteWorkout_invalidParseArgumentInput_expectNumberFormatException() throws InvalidWorkoutException,
            InvalidExerciseException,
            ArrayIndexOutOfBoundsException {

        wl.createAndAddWorkout("push up /reps 11");
        wl.createAndAddWorkout("sit up /reps 15");
        wl.createAndAddWorkout("lunge /reps 10");

        String invalidArgumentSupplied = "t5";

        assertThrows(NumberFormatException.class,
            () -> wl.deleteWorkout(invalidArgumentSupplied));

    }
}