package werkit;


import commands.InvalidCommandException;
import commands.WorkoutCommand;
import commands.SearchCommand;
import commands.ExitCommand;
import commands.HelpCommand;
import commands.ExerciseCommand;
import commands.PlanCommand;
import data.exercises.ExerciseList;
import data.plans.PlanList;
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

    @BeforeEach
    void setUp() {
        LogHandler.startLogHandler();
        this.parser = new Parser(ui, exerciseList, workoutList, fileManager, planList);
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

    //Workout tests
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
        assertThrows(InvalidCommandException.class, () -> parser.createWorkoutCommand("workout /list extra"));
        assertThrows(InvalidCommandException.class, () -> parser.createWorkoutCommand("workout /delete"));
        assertThrows(InvalidCommandException.class, () -> parser.createWorkoutCommand("workout /update"));
        assertThrows(InvalidCommandException.class, () -> parser.createWorkoutCommand("workout /test"));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> parser.createWorkoutCommand("workout"));
    }

    //Search tests
    @Test
    void createSearchCommand_validSearchCommand_expectSuccess() throws InvalidCommandException, IOException {
        assertTrue(parser.createSearchCommand("search /exercise up") instanceof SearchCommand);
        assertTrue(parser.createSearchCommand("search /exercise asdaskd") instanceof SearchCommand);
        assertTrue(parser.createSearchCommand("search /plan cool") instanceof SearchCommand);
        assertTrue(parser.createSearchCommand("search /plan asldasld") instanceof SearchCommand);
    }

    @Test
    void createSearchCommand_invalidSearchCommand_exceptionThrown() {
        assertThrows(InvalidCommandException.class, () -> parser.createSearchCommand("search /new"));
        assertThrows(InvalidCommandException.class, () -> parser.createSearchCommand("search /list extra"));
        assertThrows(InvalidCommandException.class, () -> parser.createSearchCommand("search /delete"));
        assertThrows(InvalidCommandException.class, () -> parser.createSearchCommand("search /update"));
        assertThrows(InvalidCommandException.class, () -> parser.createSearchCommand("search /test"));
        assertThrows(InvalidCommandException.class, () -> parser.createSearchCommand("search /exercise"));
        assertThrows(InvalidCommandException.class, () -> parser.createSearchCommand("search /plan"));
        assertThrows(InvalidCommandException.class, () -> parser.createSearchCommand("search fasdjaks"));
        assertThrows(InvalidCommandException.class, () -> parser.createSearchCommand("search -1"));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> parser.createSearchCommand("search"));
    }

    //Exercise tests
    @Test
    void createExerciseCommand_validExerciseCommand_expectSuccess() throws InvalidCommandException {
        assertTrue(parser.createExerciseCommand("exercise /list") instanceof ExerciseCommand);
    }

    @Test
    void createExerciseCommand_invalidExerciseCommand_exceptionThrown() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> parser.createExerciseCommand("exercise"));
        assertThrows(InvalidCommandException.class, () -> parser.createExerciseCommand("exercise /test"));
        assertThrows(InvalidCommandException.class, () -> parser.createExerciseCommand("exercise /list extra"));
    }

    //Plan tests
    @Test
    void createPlanCommand_validPlanCommand_expectSuccess() throws InvalidCommandException {
        assertTrue(parser.createPlanCommand("plan /new Random /workouts 1,2,3") instanceof PlanCommand);
        assertTrue(parser.createPlanCommand("plan /new Random /workouts 1,1,1,1,1,1,1,1,1,1") instanceof PlanCommand);
        assertTrue(parser.createPlanCommand("plan /new Random /workouts 2") instanceof PlanCommand);
        assertTrue(parser.createPlanCommand("plan /list") instanceof PlanCommand);
    }

    @Test
    void createPlanCommand_invalidPlanCommand_exceptionThrown() {
        assertThrows(InvalidCommandException.class, () -> parser.createPlanCommand("plan /new"));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> parser.createPlanCommand("plan"));
        assertThrows(InvalidCommandException.class, () -> parser.createWorkoutCommand("plan /test"));
        assertThrows(InvalidCommandException.class, () -> parser.createWorkoutCommand("plan /list extra"));
    }
}