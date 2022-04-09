package commands;

import data.exercises.ExerciseList;
import data.exercises.InvalidExerciseException;
import data.plans.InvalidPlanException;
import data.plans.Plan;
import data.plans.PlanList;
import data.schedule.DayList;
import data.workouts.InvalidWorkoutException;
import data.workouts.WorkoutList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.FileManager;
import storage.LogHandler;
import textcolors.TextColor;
import werkit.Parser;
import werkit.UI;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SearchCommandTest {
    ExerciseList exerciseList;
    WorkoutList workoutList;
    PlanList planList;
    UI ui;
    Parser parser;
    FileManager fileManager;
    DayList dayList;

    //@@author a1021492980
    @BeforeEach
    void setUp() throws InvalidWorkoutException, InvalidExerciseException, IOException, InvalidPlanException {
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

        var testWorkout1 = workoutList.createNewWorkout("push up /reps 10");
        var testWorkout2 = workoutList.createNewWorkout("sit up /reps 15");
        var testWorkout3 = workoutList.createNewWorkout("pull up /reps 20");
        workoutList.addNewWorkoutToLists(testWorkout1);
        workoutList.addNewWorkoutToLists(testWorkout2);
        workoutList.addNewWorkoutToLists(testWorkout3);

        Plan newPlan1 = planList.createNewPlan("more muscles /workouts 1,2,3");
        planList.addNewPlanToLists(newPlan1);
        Plan newPlan2 = planList.createNewPlan("legs /workouts 2,3");
        planList.addNewPlanToLists(newPlan2);
        Plan newPlan3 = planList.createNewPlan("arms /workouts 3");
        planList.addNewPlanToLists(newPlan3);
    }
    //@@author

    @Test
    public void searchCommand_normalSearchExerciseConstruction_constructSuccess() throws
            InvalidCommandException {
        String userInput = "search /exercise up";
        String userAction = "/exercise";
        String userArguments = "up";
        UI ui = new UI();
        ExerciseList el = new ExerciseList();
        SearchCommand commandTest = new SearchCommand(userInput, ui, el, userAction, userArguments);

        assertEquals("search /exercise up", commandTest.getUserInput());
        assertEquals("/exercise", commandTest.getUserAction());
        assertEquals("up", commandTest.getUserArguments());
    }

    @Test
    public void searchCommand_normalSearchPlanConstruction_constructSuccess() throws
            InvalidCommandException {
        String userInput = "search /plan gg";
        String userAction = "/plan";
        String userArguments = "gg";
        UI ui = new UI();
        ExerciseList el = new ExerciseList();
        SearchCommand commandTest = new SearchCommand(userInput, ui, el, userAction, userArguments);

        assertEquals("search /plan gg", commandTest.getUserInput());
        assertEquals("/plan", commandTest.getUserAction());
        assertEquals("gg", commandTest.getUserArguments());
    }

    @Test
    public void searchCommand_invalidAction_exceptionThrown() {
        String userInput1 = "search /exercise";
        String userAction1 = "/exercise";
        String userArguments1 = null;

        String userInput2 = "search /plan";
        String userAction2 = "/plan";
        String userArguments2 = null;

        String userInput3 = "search";
        String userAction3 = null;
        String userArguments3 = null;

        UI ui = new UI();
        ExerciseList el = new ExerciseList();

        assertThrows(NullPointerException.class,
            () -> new SearchCommand(userInput1, ui, el, userAction1, userArguments1));
        assertThrows(NullPointerException.class,
            () -> new SearchCommand(userInput2, ui, el, userAction2, userArguments2));
        assertThrows(NullPointerException.class,
            () -> new SearchCommand(userInput3, ui, el, userAction3, userArguments3));
    }

    @Test
    void execute_validSearchForAll_expectSuccess() throws InvalidCommandException {
        SearchCommand searchAllCommand = parser.createSearchCommand("search /all a");
        String expectedOutput =
                "Sorry, no matching exercise found for the keyword"
                        + ui.getColorText(TextColor.COLOR_YELLOW, " [a]") + ".\n"
                        + "----------------------------------------------------------------------\n"
                        + "Sorry, no matching workout found for the keyword"
                        + ui.getColorText(TextColor.COLOR_YELLOW, " [a]") + ".\n"
                        + "----------------------------------------------------------------------\n"
                        + "The plan(s) containing keyword(s)" + ui.getColorText(TextColor.COLOR_YELLOW, " [a] ")
                        + "is(are) listed below together\n"
                        + "with its(their) original index number(s).\n"
                        + "----------------------------------------------------------------------\n"
                        + ui.getColorText(TextColor.COLOR_YELLOW, "3. arms");
        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        searchAllCommand.execute();
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "")
                .replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }

    @Test
    void execute_validSearchForAll2_expectSuccess() throws InvalidCommandException {
        SearchCommand searchAllCommand = parser.createSearchCommand("search /all u");
        String expectedOutput =
                "The exercise(s) containing keyword(s)" + ui.getColorText(TextColor.COLOR_YELLOW, " [u] ")
                        + "is(are) listed below together\n" + "with its(their) original index number(s).\n"
                        + "----------------------------------------------------------------------\n"
                        + ui.getColorText(TextColor.COLOR_YELLOW, "1. push up\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "2. sit up\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "3. pull up\n")
                        + "----------------------------------------------------------------------\n"
                        + "The workout(s) containing keyword(s)" + ui.getColorText(TextColor.COLOR_YELLOW, " [u] ")
                        + "is(are) listed below together\n"
                        + "with its(their) original index number(s).\n"
                        + "----------------------------------------------------------------------\n"
                        + ui.getColorText(TextColor.COLOR_YELLOW, "1. push up (10 reps)\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "2. sit up (15 reps)\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "3. pull up (20 reps)\n")
                        + "----------------------------------------------------------------------\n"
                        + "The plan(s) containing keyword(s)" + ui.getColorText(TextColor.COLOR_YELLOW, " [u] ")
                        + "is(are) listed below together\n" + "with its(their) original index number(s).\n"
                        + "----------------------------------------------------------------------\n"
                        + ui.getColorText(TextColor.COLOR_YELLOW, "1. more muscles\n");

        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        searchAllCommand.execute();
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "")
                .replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }

    @Test
    void execute_validSearchForAll3_expectSuccess() throws InvalidCommandException {
        SearchCommand searchAllCommand = parser.createSearchCommand("search /all 10");
        String expectedOutput =
                "Sorry, no matching exercise found for the keyword"
                        + ui.getColorText(TextColor.COLOR_YELLOW, " [10]") + ".\n"
                        + "----------------------------------------------------------------------\n"
                        + "The workout(s) with " + ui.getColorText(TextColor.COLOR_YELLOW, "reps = 10")
                        + " is(are) listed below together\n"
                        + "with its(their) original index number(s).\n"
                        + "----------------------------------------------------------------------\n"
                        + ui.getColorText(TextColor.COLOR_YELLOW, "1. push up (10 reps)\n")
                        + "----------------------------------------------------------------------\n"
                        + "Sorry, no matching plan found for the keyword"
                        + ui.getColorText(TextColor.COLOR_YELLOW, " [10]") + '.';

        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        searchAllCommand.execute();
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "")
                .replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }

    @Test
    void execute_validSearchForAllNoMatch_expectSuccess() throws InvalidCommandException {
        SearchCommand searchAllCommand = parser.createSearchCommand("search /all asldkaskd");
        String expectedOutput =
                "Sorry, no matching exercise found for the keyword"
                        + ui.getColorText(TextColor.COLOR_YELLOW, " [asldkaskd]") + ".\n"
                        + "----------------------------------------------------------------------\n"
                        + "Sorry, no matching workout found for the keyword"
                        + ui.getColorText(TextColor.COLOR_YELLOW, " [asldkaskd]") + ".\n"
                        + "----------------------------------------------------------------------\n"
                        + "Sorry, no matching plan found for the keyword"
                        + ui.getColorText(TextColor.COLOR_YELLOW, " [asldkaskd]") + '.';

        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        searchAllCommand.execute();
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "")
                .replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }

    @Test
    void execute_validSearchForExercise_expectSuccess() throws InvalidCommandException {
        SearchCommand searchExerciseCommand = parser.createSearchCommand("search /exercise u");
        String expectedOutput =
                "The exercise(s) containing keyword(s)" + ui.getColorText(TextColor.COLOR_YELLOW, " [u] ")
                        + "is(are) listed below together\n" + "with its(their) original index number(s).\n"
                        + "----------------------------------------------------------------------\n"
                        + ui.getColorText(TextColor.COLOR_YELLOW, "1. push up\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "2. sit up\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "3. pull up\n");
        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        searchExerciseCommand.execute();
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "")
                .replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }

    @Test
    void execute_validSearchForWorkout_expectSuccess() throws InvalidCommandException {
        SearchCommand searchWorkoutCommand = parser.createSearchCommand("search /workout 15");
        String expectedOutput =
                "The workout(s) with " + ui.getColorText(TextColor.COLOR_YELLOW, "reps = 15")
                        + " is(are) listed below together\n"
                        + "with its(their) original index number(s).\n"
                        + "----------------------------------------------------------------------\n"
                        + ui.getColorText(TextColor.COLOR_YELLOW, "2. sit up (15 reps)\n");
        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        searchWorkoutCommand.execute();
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "")
                .replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }

    @Test
    void execute_validSearchForPlan_expectSuccess() throws InvalidCommandException {
        SearchCommand searchPlanCommand = parser.createSearchCommand("search /plan s");
        String expectedOutput =
                "The plan(s) containing keyword(s)" + ui.getColorText(TextColor.COLOR_YELLOW, " [s] ")
                        + "is(are) listed below together\n" + "with its(their) original index number(s).\n"
                        + "----------------------------------------------------------------------\n"
                        + ui.getColorText(TextColor.COLOR_YELLOW, "1. more muscles\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "2. legs\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "3. arms\n");
        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        searchPlanCommand.execute();
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "")
                .replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }
}
