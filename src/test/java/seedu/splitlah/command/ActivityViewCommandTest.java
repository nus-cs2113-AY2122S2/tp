package seedu.splitlah.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.splitlah.data.Manager;
import seedu.splitlah.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActivityViewCommandTest {

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
     * Checks if activities are listed with missing delimiters.
     */
    @Test
    public void prepare_hasMissingDelimiter_InvalidCommand() {
        // Missing sid and aid
        String argsMissingSidAidDelimiter = "activity /view";
        Command activityWithMissingSidAidDelimiter = Parser.getCommand(argsMissingSidAidDelimiter);
        assertEquals(InvalidCommand.class, activityWithMissingSidAidDelimiter.getClass());

        // Missing aid
        String argsMissingAidDelimiter = "activity /view /sid 1";
        Command activityWithMissingAidDelimiter = Parser.getCommand(argsMissingAidDelimiter);
        assertEquals(InvalidCommand.class, activityWithMissingAidDelimiter.getClass());

        // Missing sid
        String argsMissingSidDelimiter = "activity /view /aid 1";
        Command activityWithMissingSidDelimiter = Parser.getCommand(argsMissingSidDelimiter);
        assertEquals(InvalidCommand.class, activityWithMissingSidDelimiter.getClass());
    }


}
