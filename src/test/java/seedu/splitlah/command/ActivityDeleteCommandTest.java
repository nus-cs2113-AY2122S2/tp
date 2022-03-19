package seedu.splitlah.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.splitlah.data.Manager;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivityDeleteCommandTest {

    Manager manager = new Manager();

    /**
     * Creates a session that is stored and managed by the Manager object.
     * Creates 2 activities in the new session that was created.
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
     * Checks if an activity is deleted with missing delimiters.
     */
    @Test
    public void prepare_hasMissingDelimiter_InvalidCommand() {
        // Case 1: Missing both /sid and /aid delimiters
        String argsMissingBothDelimiters = "activity /delete";
        Command activityWithMissingBothDelimiters = Parser.getCommand(argsMissingBothDelimiters);
        assertEquals(InvalidCommand.class, activityWithMissingBothDelimiters.getClass());

        // Case 2: Missing /sid delimiter only
        String argsMissingSidDelimiter = "activity /delete /aid 1";
        Command activityWithMissingSidDelimiter = Parser.getCommand(argsMissingSidDelimiter);
        assertEquals(InvalidCommand.class, activityWithMissingSidDelimiter.getClass());

        // Case 3: Missing /aid delimiter only
        String argsMissingAidDelimiter = "activity /delete /sid 1";
        Command activityWithMissingAidDelimiter = Parser.getCommand(argsMissingAidDelimiter);
        assertEquals(InvalidCommand.class, activityWithMissingAidDelimiter.getClass());
    }

    @Test
    public void prepare_hasMissingArgument_InvalidCommand() {
        // Case 1: Missing both Session Id and Activity Id arguments
        String argsMissingBothArguments = "activity /delete /sid /aid";
        Command activityWithMissingBothArguments = Parser.getCommand(argsMissingBothArguments);
        assertEquals(InvalidCommand.class, activityWithMissingBothArguments.getClass());

        // Case 2: Missing Session Id argument only
        String argsMissingSessionIdArgument = "activity /delete /sid /aid 1";
        Command activityWithMissingSessionIdArgument = Parser.getCommand(argsMissingSessionIdArgument);
        assertEquals(InvalidCommand.class, activityWithMissingSessionIdArgument.getClass());

        // Case 3: Missing Activity Id argument only
        String argsMissingActivityIdArgument = "activity /delete /sid 1 /aid";
        Command activityWithMissingActivityIdArgument = Parser.getCommand(argsMissingActivityIdArgument);
        assertEquals(InvalidCommand.class, activityWithMissingActivityIdArgument.getClass());
    }

    /**
     * Checks if an activity is deleted with an invalid session unique identifier.
     *
     * @throws InvalidDataException If there are no sessions stored or
     *                              if the session unique identifier specified was not found.
     */
    @Test
    public void run_sessionDoesNotExists_activityListSizeRemainsTwo() throws InvalidDataException {
        String userInput = "activity /delete /sid 3 /aid 1";
        Command command = Parser.getCommand(userInput);
        command.run(manager);
        assertEquals(2, manager.getProfile().getSession(1).getActivityList().size());
    }

    /**
     * Checks if an activity is deleted with an invalid activity unique identifier.
     *
     * @throws InvalidDataException If there are no sessions stored or
     *                              if the session unique identifier specified was not found.
     */
    @Test
    public void run_activityDoesNotExists_activityListSizeRemainsTwo() throws InvalidDataException {
        String userInput = "activity /delete /sid 1 /aid 3";
        Command command = Parser.getCommand(userInput);
        command.run(manager);
        assertEquals(2, manager.getProfile().getSession(1).getActivityList().size());
    }

    @Test
    public void run_validCommand_activityListsSizeBecomesOne() throws InvalidDataException {
        String userInput = "activity /delete /sid 1 /aid 1";
        Command command = Parser.getCommand(userInput);
        command.run(manager);
        assertEquals(1, manager.getProfile().getSession(1).getActivityList().size());
    }

}
