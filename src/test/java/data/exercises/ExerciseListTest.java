package data.exercises;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.LogHandler;

import textcolors.TextColor;
import werkit.UI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExerciseListTest {
    ExerciseList exerciseList;

    @BeforeEach
    void setUp() {
        LogHandler.startLogHandler();
        exerciseList = new ExerciseList();
    }

    @Test
    public void execute_validGetQuantity_expectSuccess() {
        String firstSampleExercise = "push up";
        String secondSampleExercise = "pull up";
        exerciseList.addExerciseToList(firstSampleExercise);
        exerciseList.addExerciseToList(secondSampleExercise);
        assertEquals(2, exerciseList.getNumberOfExercises());
    }

    @Test
    public void execute_validGetExerciseAt_expectSuccess() {
        String firstSampleExercise = "push up";
        String secondSampleExercise = "pull up";
        exerciseList.addExerciseToList(firstSampleExercise);
        exerciseList.addExerciseToList(secondSampleExercise);
        assertEquals("pull up", exerciseList.getExerciseAt(2));
    }

    @Test
    public void execute_validCheckExist_expectSuccess() {
        String firstSampleExercise = "push up";
        String secondSampleExercise = "pull up";
        exerciseList.addExerciseToList(firstSampleExercise);
        exerciseList.addExerciseToList(secondSampleExercise);
        assertTrue(exerciseList.checkIfExerciseExists("pull up"));
    }

    @Test
    void execute_validPrintExercise_expectSuccess() {

        UI ui;
        ui = new UI();

        String firstSampleExercise = "push up";
        String secondSampleExercise = "pull up";
        exerciseList.addExerciseToList(firstSampleExercise);
        exerciseList.addExerciseToList(secondSampleExercise);

        String expectedOutput =
                "There are 2 exercises available.\n" + "Here is the list of exercises: \n"
                        + "----------------------------------------------------------------------\n"
                        + ui.getColorText(TextColor.COLOR_YELLOW, "1. push up\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "2. pull up\n")
                        + "----------------------------------------------------------------------\n"
                        + "End of exercise list.";
        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        exerciseList.printExerciseList();
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "")
                .replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }

    @Test
    void execute_validCheckPopulateExercise_expectSuccess() {

        UI ui;
        ui = new UI();

        exerciseList.populateExercisesToList();

        String expectedOutput =
                "There are 13 exercises available.\n" + "Here is the list of exercises: \n"
                        + "----------------------------------------------------------------------\n"
                        + ui.getColorText(TextColor.COLOR_YELLOW, "1. push up\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "2. bicep curl\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "3. pull up\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "4. squat\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "5. lunge\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "6. hip thrust\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "7. sit up\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "8. crunch\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "9. russian twist\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "10. running\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "11. swimming\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "12. jumping jack\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "13. burpee\n")
                        + "----------------------------------------------------------------------\n"
                        + "End of exercise list.";
        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        exerciseList.printExerciseList();
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "")
                .replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }
}

