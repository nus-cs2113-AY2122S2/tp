package seedu.splitlah.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.splitlah.data.Manager;
import seedu.splitlah.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActivityListCommandTest {

    Manager manager = new Manager();

    /**
     * Creates a session managed by the Manager object.
     * Creates two activities in the new session.
     */
    @BeforeEach
    void setUp() {
        String sessionArgs = "session /create /n Class outing /d 15-02-2022 /pl Alice Bob Charlie";
        Command createSession = Parser.getCommand(sessionArgs);
        createSession.run(manager);
        String activityOneArgs = "activity /create /sid 1 /n Lunch /p Alice /i Alice Bob Charlie /co 15";
        Command createActivityOne = Parser.getCommand(activityOneArgs);
        createActivityOne.run(manager);
        String activityTwoArgs = "activity /create /sid 1 /n Lunch /p Alice /i Bob Charlie /cl 5 5";
        Command createActivityTwo = Parser.getCommand(activityTwoArgs);
        createActivityTwo.run(manager);
    }

    /**
     * Checks if activities are listed with missing session unique identifier.
     */
    @Test
    public void prepare_hasMissingDelimiter_InvalidCommand() {
        // Missing delimiter
        String inputMissingSidDelimiter = "activity /list /1";
        Command activityWithMissingSidDelimiter = Parser.getCommand(inputMissingSidDelimiter);
        assertEquals(InvalidCommand.class, activityWithMissingSidDelimiter.getClass());
    }

    /**
     * Checks if activities are listed with missing argument after the delimiter.
     */
    @Test
    public void prepare_hasMissingArgument_InvalidCommand() {
        // Missing argument after delimiter.
        String inputMissingArgument = "activity /list /sid";
        Command activityWithMissingArgument = Parser.getCommand(inputMissingArgument);
        assertEquals(InvalidCommand.class, activityWithMissingArgument.getClass());
    }
    
}
