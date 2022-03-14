package werkit;

import commands.Command;
import commands.InvalidCommandException;
import commands.WorkoutCommand;
import data.exercises.ExerciseList;
import data.workouts.WorkoutList;
import org.junit.jupiter.api.Test;
import storage.FileManager;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {
    private UI ui = new UI();
    private ExerciseList exerciseList = new ExerciseList();
    private WorkoutList workoutList = new WorkoutList(exerciseList);
    private FileManager fileManager = new FileManager();

    @Test
    public void parseUserInput_normalWorkoutCreateCommandInput_expectWorkoutCommand()
            throws InvalidCommandException {
        Parser parser = new Parser(ui, exerciseList, workoutList, fileManager);

        String testInput = "workout /new russian twist /reps 1000";
        Command result = parser.parseUserInput(testInput);
        assertTrue(result instanceof WorkoutCommand);
    }

    @Test
    public void parseUserInput_invalidBaseKeyword_expectInvalidCommandException() {
        Parser parser = new Parser(ui, exerciseList, workoutList, fileManager);

        String testInput = "hmmm /new russian twist /reps 1000";
        assertThrows(InvalidCommandException.class,
            () -> parser.parseUserInput(testInput));
    }
}