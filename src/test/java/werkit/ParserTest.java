package werkit;

import commands.ExerciseCommand;
import commands.ExitCommand;
import commands.HelpCommand;
import commands.WorkoutCommand;
import commands.InvalidCommandException;
import data.exercises.ExerciseList;
import data.plans.PlanList;
import data.schedule.DayList;
import data.workouts.WorkoutList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.FileManager;
import storage.LogHandler;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    private UI ui;
    private ExerciseList exerciseList;
    private WorkoutList workoutList;
    private FileManager fileManager;
    private Parser parser;
    private PlanList planList;
    private DayList dayList;

    @BeforeEach
    void setUp() {
        LogHandler.startLogHandler();
        this.parser = new Parser(ui, exerciseList, workoutList, fileManager, planList, dayList);
    }

    @Test
    void parseUserInput_validHelpCommand_expectSuccess() throws InvalidCommandException, IOException {
        assertTrue(parser.parseUserInput("help") instanceof HelpCommand);
    }

    @Test
    void parseUserInput_validExitCommand_expectSuccess() throws InvalidCommandException, IOException {
        assertTrue(parser.parseUserInput("exit") instanceof ExitCommand);
    }

    @Test
    void parseUserInput_invalidGeneralCommand_exceptionThrown() {
        assertThrows(InvalidCommandException.class, () -> parser.parseUserInput("exitt"));
        assertThrows(InvalidCommandException.class, () -> parser.parseUserInput("helpp"));
        assertThrows(InvalidCommandException.class, () -> parser.parseUserInput(""));
        assertThrows(InvalidCommandException.class, () -> parser.parseUserInput("|"));
        assertThrows(InvalidCommandException.class, () -> parser.parseUserInput("workout /new | push up /reps 20"));
    }

    @Test
    void createWorkoutCommand_validWorkoutCommand_expectSuccess() throws InvalidCommandException, IOException {
        assertTrue(parser.createWorkoutCommand("workout /new push up /reps 20") instanceof WorkoutCommand);
        assertTrue(parser.createWorkoutCommand("workout /update 1 15") instanceof WorkoutCommand);
        assertTrue(parser.createWorkoutCommand("workout /delete 1") instanceof WorkoutCommand);
        assertTrue(parser.createWorkoutCommand("workout /list") instanceof WorkoutCommand);
    }

    @Test
    void createWorkoutCommand_invalidWorkoutCommand_exceptionThrown() {
        assertThrows(InvalidCommandException.class, () -> parser.createWorkoutCommand("workout /new"));
        assertThrows(InvalidCommandException.class, () -> parser.createWorkoutCommand("workout /delete"));
        assertThrows(InvalidCommandException.class, () -> parser.createWorkoutCommand("workout /update"));
        assertThrows(InvalidCommandException.class, () -> parser.createWorkoutCommand("workout /test"));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> parser.createWorkoutCommand("workout"));
    }

    @Test
    void createExerciseCommand_validExerciseCommand_expectSuccess() throws InvalidCommandException {
        assertTrue(parser.createExerciseCommand("exercise /list") instanceof ExerciseCommand);
    }

    @Test
    void createExerciseCommand_invalidExerciseCommand_exceptionThrown() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> parser.createExerciseCommand("exercise"));
        assertThrows(InvalidCommandException.class, () -> parser.createExerciseCommand("exercise /test"));
    }
}