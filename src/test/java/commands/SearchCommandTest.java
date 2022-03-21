package commands;

import data.exercises.ExerciseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.LogHandler;
import werkit.UI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SearchCommandTest {
    @BeforeEach
    public void setUp() {
        LogHandler.startLogHandler();
    }

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
}
