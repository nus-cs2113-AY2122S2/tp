package data.plans;

import data.exercises.ExerciseList;
import data.exercises.InvalidExerciseException;
import data.workouts.InvalidWorkoutException;
import data.workouts.WorkoutList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.LogHandler;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlanListTest {
    ExerciseList exerciseList;
    WorkoutList workoutList;
    PlanList planList;

    @BeforeEach
    void setUp() throws InvalidWorkoutException, InvalidExerciseException {
        LogHandler.startLogHandler();
        exerciseList = new ExerciseList();
        workoutList = new WorkoutList(exerciseList);
        planList = new PlanList(workoutList);

        exerciseList.addExerciseToList("push up");
        exerciseList.addExerciseToList("pull up");
        exerciseList.addExerciseToList("sit up");
        exerciseList.addExerciseToList("burpee");
        exerciseList.addExerciseToList("russian twist");

        workoutList.createAndAddWorkout("push up /reps 10");
        workoutList.createAndAddWorkout("sit up /reps 15");
        workoutList.createAndAddWorkout("burpee /reps 20");
        workoutList.createAndAddWorkout("burpee /reps 50");
        workoutList.createAndAddWorkout("pull up /reps 10");
    }

    @Test
    void listAllPlan_expectThreePrints() throws InvalidPlanException {
        planList.createAndAddPlan("Plan 1 /workouts 1,2,3");
        planList.createAndAddPlan("Plan 2 /workouts 1");
        planList.createAndAddPlan("Plan 3 /workouts 1,5,4,3");
        String expectedOutput =
                "Here are all your plan(s).\n"
                        + "To view each plan in detail, enter\n'plan /details <plan number in list>'.\n"
                        + "1. Plan 1\n"
                        + "2. Plan 2\n"
                        + "3. Plan 3\n";
        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        planList.listAllPlan();
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "").replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }

    @Test
    void createAndAddPlan_validPlansAdded_expectValidPlans() throws InvalidPlanException {
        planList.createAndAddPlan("Plan 1 /workouts 1,5,4,3");
        planList.createAndAddPlan("Plan 2 /workouts 1,5,4,3,1,1,1");

        for (int i = 0; i < planList.getPlansDisplayList().size(); i += 1) {
            assertEquals("Plan " + (i + 1), planList.getPlansDisplayList().get(i).toString());
        }
    }

    @Test
    void createAndAddPlan_samePlanName_expectInvalidPlanException() throws InvalidPlanException {
        planList.createAndAddPlan("Plan 1 /workouts 1,2,3");
        assertThrows(InvalidPlanException.class,
            () -> planList.createAndAddPlan("Plan 1 /workouts 2,2,2"));
    }

    @Test
    void createAndAddPlan_minMaxWorkoutsToAdd_expectException() {
        assertThrows(InvalidPlanException.class,
            () -> planList.createAndAddPlan("Exceed 10 Plan /workouts 1,1,1,1,1,2,2,2,2,2,3"));
        assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> planList.createAndAddPlan("Min 1 Plan /workouts"));
    }

    @Test
    void createAndAddPlan_workoutIndexOutOfRange_expectInvalidPlanException() {
        assertThrows(InvalidPlanException.class,
            () -> planList.createAndAddPlan("Plan 1 /workouts 1,2,3,9"));
        assertThrows(InvalidPlanException.class,
            () -> planList.createAndAddPlan("Plan 2 /workouts 9"));
    }

}