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
     * Checks if an activity is not created when an activity has duplicate names in the involved list.
     *
     * @throws InvalidDataException If there are no sessions stored or
     *                              if the session unique identifier specified was not found.
     */
    @Test
    public void run_hasNameDuplicatesInInvolvedList_activityListSizeRemainsOne() throws InvalidDataException {
        String userInput = "activity /create /sid 1 /n Dinner /p Alice /i Alice Alice Charlie /co 30";
        Command command = Parser.getCommand(userInput);
        assertEquals(ActivityCreateCommand.class, command.getClass());
        command.run(manager);
        assertEquals(1, manager.getProfile().getSession(1).getActivityList().size());
    }

    /**
     * Checks if activity unique identifier is not incremented if an activity fails
     * to be created due to duplicate names in involved list.
     */
    @Test
    public void run_hasNameDuplicatesInInvolvedList_activityIdNotIncremented() {
        int currentActivityId = manager.getProfile().getActivityIdTracker();
        String userInput = "activity /create /sid 1 /n Dinner /p Alice /i Alice Alice Charlie /co 30";
        Command command = Parser.getCommand(userInput);
        assertEquals(ActivityCreateCommand.class, command.getClass());
        command.run(manager);
        int testActivityId = manager.getProfile().getActivityIdTracker();
        assertEquals(currentActivityId, testActivityId);
    }

    /**
     * Checks if activity is created successfully and added into list of activities.
     *
     * @throws InvalidDataException If there are no sessions stored or
     *                              if the session unique identifier specified was not found.
     */
    @Test
    public void run_validCommand_activityListSizeBecomesTwo() throws InvalidDataException {
        String userInput = "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob Charlie /co 30";
        Command command = Parser.getCommand(userInput);
        assertEquals(ActivityCreateCommand.class, command.getClass());
        command.run(manager);
        assertEquals(2, manager.getProfile().getSession(1).getActivityList().size());
    }

    /**
     * Checks if activity is created successfully and activity unique identifier is incremented.
     */
    @Test
    public void run_validCommand_activityIdIncremented() {
        int currentActivityId = manager.getProfile().getActivityIdTracker();
        String userInput = "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob Charlie /co 30";
        Command command = Parser.getCommand(userInput);
        assertEquals(ActivityCreateCommand.class, command.getClass());
        command.run(manager);
        int testActivityId = manager.getProfile().getActivityIdTracker();
        assertEquals(currentActivityId + 1, testActivityId);
    }
}
