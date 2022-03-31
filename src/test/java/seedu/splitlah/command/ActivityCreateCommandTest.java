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
    public void run_hasNameDuplicatesInInvolvedList_activityNotCreated() throws InvalidDataException {
        int currentActivityId = manager.getProfile().getActivityIdTracker();
        String userInput = "activity /create /sid 1 /n Dinner /p Alice /i Alice Alice Charlie /co 30";
        Command command = Parser.getCommand(userInput);
        assertEquals(ActivityCreateCommand.class, command.getClass());
        command.run(manager);
        assertEquals(1, manager.getProfile().getSession(1).getActivityList().size());
        int testActivityId = manager.getProfile().getActivityIdTracker();
        assertEquals(currentActivityId, testActivityId);
    }

    /**
     * Checks if an activity is not created when an activity has at least one name in involved list that is
     * not found in session.
     *
     * @throws InvalidDataException If there are no sessions stored or
     *                              if the session unique identifier specified was not found.
     */
    @Test
    public void run_hasInvalidNameInInvolvedList_activityListSizeRemainsOne() throws InvalidDataException {
        int currentActivityId = manager.getProfile().getActivityIdTracker();
        String userInput = "activity /create /sid 1 /n Dinner /p Alice /i Eve Mallory /co 30";
        Command command = Parser.getCommand(userInput);
        assertEquals(ActivityCreateCommand.class, command.getClass());
        command.run(manager);
        assertEquals(1, manager.getProfile().getSession(1).getActivityList().size());
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
        int currentActivityId = manager.getProfile().getActivityIdTracker();
        String userInput = "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob Charlie /co 30";
        Command command = Parser.getCommand(userInput);
        assertEquals(ActivityCreateCommand.class, command.getClass());
        command.run(manager);
        assertEquals(2, manager.getProfile().getSession(1).getActivityList().size());
        int testActivityId = manager.getProfile().getActivityIdTracker();
        assertEquals(currentActivityId + 1, testActivityId);
    }
}
