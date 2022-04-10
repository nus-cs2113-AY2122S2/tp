package commands;

import data.exercises.ExerciseList;
import data.exercises.InvalidExerciseException;
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

public class ExerciseCommandTest {

    ExerciseList exerciseList;
    WorkoutList workoutList;
    PlanList planList;
    UI ui;
    Parser parser;
    FileManager fileManager;
    DayList dayList;

    @BeforeEach
    void setUp() throws InvalidWorkoutException, InvalidExerciseException, IOException {
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
    }

    @Test
    void execute_validListExercise_expectSuccess() throws InvalidCommandException {
        ExerciseCommand listExerciseCommand = parser.createExerciseCommand("exercise /list");
        String expectedOutput =
                "There are 3 exercises available.\n" + "Here is the list of exercises: \n"
                        + "----------------------------------------------------------------------\n"
                        + ui.getColorText(TextColor.COLOR_YELLOW, "1. push up\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "2. sit up\n")
                        + ui.getColorText(TextColor.COLOR_YELLOW, "3. pull up\n")
                        + "----------------------------------------------------------------------\n"
                        + "End of exercise list.";
        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        listExerciseCommand.execute();
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "")
                .replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }
}
