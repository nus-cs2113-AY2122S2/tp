package commands;

import data.exercises.ExerciseList;
import data.workouts.WorkoutList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.FileManager;
import storage.LogHandler;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        FileManager fm = new FileManager();
        ExerciseList el = new ExerciseList();
        WorkoutList wl = new WorkoutList(el);

        WorkoutCommand commandTest = new WorkoutCommand(userInput, fm, wl, userAction, userArguments);

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

        FileManager fm = new FileManager();
        ExerciseList el = new ExerciseList();
        WorkoutList wl = new WorkoutList(el);

        assertThrows(InvalidCommandException.class,
            () -> new WorkoutCommand(userInput1, fm, wl, userAction1, userArguments1));
        assertThrows(InvalidCommandException.class,
            () -> new WorkoutCommand(userInput2, fm, wl, userAction2, userArguments2));
    }
}
