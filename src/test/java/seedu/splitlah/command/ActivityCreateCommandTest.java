package seedu.splitlah.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.splitlah.data.Manager;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivityCreateCommandTest {

    Manager manager = new Manager();

    /**
     * Creates a session that is stored and managed by the Manager object.
     * Creates an activity in the new session that was created.
     */
    @BeforeEach
    void setUp() {
        String sessionOneArgs = "session /create /n Class outing /d 15-02-2022 /pl Alice Bob Charlie";
        Command createSessionOne = Parser.getCommand(sessionOneArgs);
        createSessionOne.run(manager);
        String activityOneArgs = "activity /create /sid 1 /n Lunch /p Alice /i Alice Bob Charlie /co 15";
        Command createActivityOne = Parser.getCommand(activityOneArgs);
        createActivityOne.run(manager);
    }

    /**
     * Checks if an activity is created when an activity is missing both cost and cost list.
     * @throws InvalidDataException If there are no sessions stored or
     *                              if the session unique identifier specified was not found.
     */
    @Test
    public void prepare_hasMissingCostAndCostList_invalidCommand() throws InvalidDataException {
        String userInput = "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob Charlie";
        Command command = Parser.getCommand(userInput);
        assertEquals(InvalidCommand.class, command.getClass());
    }

    /**
     * Checks if an activity is created when an activity has both cost and cost list.
     * @throws InvalidDataException If there are no sessions stored or
     *                              if the session unique identifier specified was not found.
     */
    @Test
    public void prepare_hasBothCostAndCostList_invalidCommand() throws InvalidDataException {
        String userInput = "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob Charlie /co 30 /cl 10 10 10";
        Command command = Parser.getCommand(userInput);
        assertEquals(InvalidCommand.class, command.getClass());
    }

    /**
     * Checks if an activity is created when an activity has different length for involved list and cost list.
     * @throws InvalidDataException If there are no sessions stored or
     *                              if the session unique identifier specified was not found.
     */
    @Test
    public void prepare_costListAndInvolvedListDifferentLength_invalidCommand() throws InvalidDataException {
        String firstUserInput = "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob Charlie /cl 10 10";
        Command firstCommand = Parser.getCommand(firstUserInput);
        assertEquals(InvalidCommand.class, firstCommand.getClass());
        String secondUserInput = "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob /cl 10 10 10";
        Command secondCommand = Parser.getCommand(secondUserInput);
        assertEquals(InvalidCommand.class, secondCommand.getClass());
    }

    /**
     * Checks if an activity is created when an activity has duplicate names in the involved list.
     * @throws InvalidDataException If there are no sessions stored or
     *                              if the session unique identifier specified was not found.
     */
    @Test
    public void run_hasNameDuplicatesInInvolvedList_activityListSizeRemainsOne() throws InvalidDataException {
        String userInput = "activity /create /sid 1 /n Dinner /p Alice /i Alice Alice Charlie /co 30";
        Command command = Parser.getCommand(userInput);
        command.run(manager);
        assertEquals(1, manager.getProfile().getSession(1).getActivityList().size());
    }

    /**
     * Checks if activity is created with missing delimiters.
     */
    @Test
    public void prepare_hasMissingDelimiter_InvalidCommand() {
        // Case 1: Missing /sid delimiter
        String argsMissingSessionIdDelimiter = "activity /create /n Dinner /p Alice /i Alice Bob Charlie /co 15";
        Command sessionWithMissingSessionIdDelimiter = Parser.getCommand(argsMissingSessionIdDelimiter);
        assertEquals(InvalidCommand.class, sessionWithMissingSessionIdDelimiter.getClass());

        // Case 2: Missing /n delimiter
        String argsMissingNameDelimiter = "activity /create /sid 1 /p Alice /i Alice Bob Charlie /co 15";
        Command sessionWithMissingNameDelimiter = Parser.getCommand(argsMissingNameDelimiter);
        assertEquals(InvalidCommand.class, sessionWithMissingNameDelimiter.getClass());

        // Case 3: Missing /p delimiter
        String argsMissingPayerDelimiter = "activity /create /sid 1 /n Dinner /i Alice Bob Charlie /co 15";
        Command sessionWithMissingPayerDelimiter = Parser.getCommand(argsMissingPayerDelimiter);
        assertEquals(InvalidCommand.class, sessionWithMissingPayerDelimiter.getClass());

        // Case 4: Missing /i delimiter
        String argsMissingInvolvedListDelimiter = "activity /create /sid 1 /n Dinner /p Alice /co 15";
        Command activityWithMissingInvolvedListDelimiter = Parser.getCommand(argsMissingInvolvedListDelimiter);
        assertEquals(InvalidCommand.class, activityWithMissingInvolvedListDelimiter.getClass());
    }

    /**
     * Checks if activity is created with missing arguments.
     */
    @Test
    public void prepare_hasMissingArguments_InvalidCommand() {
        // Case 1: Missing session ID
        String argsMissingSessionIdArgument = "activity /create /sid /n Dinner /p Alice /i Alice Bob Charlie /co 15";
        Command sessionWithMissingSessionIdArgument = Parser.getCommand(argsMissingSessionIdArgument);
        assertEquals(InvalidCommand.class, sessionWithMissingSessionIdArgument.getClass());

        // Case 2: Missing Activity Name
        String argsMissingNameArgument = "activity /create /sid 1 /n /p Alice /i Alice Bob Charlie /co 15";
        Command sessionWithMissingNameArgument = Parser.getCommand(argsMissingNameArgument);
        assertEquals(InvalidCommand.class, sessionWithMissingNameArgument.getClass());

        // Case 3: Missing Payer
        String argsMissingPayerArgument = "activity /create /sid 1 /n Dinner /p /i Alice Bob Charlie /co 15";
        Command sessionWithMissingPayerArgument = Parser.getCommand(argsMissingPayerArgument);
        assertEquals(InvalidCommand.class, sessionWithMissingPayerArgument.getClass());

        // Case 4: Missing involved list
        String argsMissingInvolvedListArgument = "activity /create /sid 1 /n Dinner /p Alice /i /co 15";
        Command activityWithMissingInvolvedListArgument = Parser.getCommand(argsMissingInvolvedListArgument);
        assertEquals(InvalidCommand.class, activityWithMissingInvolvedListArgument.getClass());
    }

}
